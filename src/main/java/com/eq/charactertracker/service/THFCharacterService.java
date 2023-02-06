package com.eq.charactertracker.service;

import com.eq.charactertracker.constants.EquipSlotEnum;
import com.eq.charactertracker.constants.ServerEnum;
import com.eq.charactertracker.constants.ServerInformation;
import com.eq.charactertracker.constants.THFWebAttributesEnum;
import com.eq.charactertracker.entity.ArmorEntity;
import com.eq.charactertracker.entity.CharacterEntity;
import com.eq.charactertracker.entity.CharacterEquipSlotEntity;
import com.eq.charactertracker.exception.CharacterNotFoundException;
import com.eq.charactertracker.model.CharacterEquipSlot;
import com.eq.charactertracker.repo.ArmorRepo;
import com.eq.charactertracker.repo.CharacterRepo;
import com.eq.charactertracker.repo.EquipSlotRepo;
import com.eq.charactertracker.util.CharacterArmorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class THFCharacterService {

    private final ArmorRepo armorRepo;
    private final CharacterRepo characterRepo;
    private final EquipSlotRepo equipSlotRepo;
    private final ModelMapper modelMapper;

    public void updateExternalArmorDetails(CharacterEntity character){
        ServerEnum server = character.getServer();
        String characterUrl = ServerInformation.THF_MAGELO_URL + "character=" + character.getName();
        try {
            Document doc = Jsoup.connect(characterUrl).get();
            for(EquipSlotEnum slotEnum: EquipSlotEnum.values()) {
                log.info(String.format("Updating slot:  %s", slotEnum));
                ArmorEntity armorEntity = saveOrGetArmor(character, doc, slotEnum);
                if(armorEntity == null){
                    log.info(String.format("Unable to find information on armor for character %s with slot %s", character, slotEnum));
                    continue;
                }
                Optional<CharacterEquipSlotEntity> optionalCharacterEquipSlotEntity = equipSlotRepo.findCharacterEquipSlotEntityByCharacterAndEquipSlot(character, slotEnum);
                CharacterEquipSlotEntity characterEquipSlotEntity = optionalCharacterEquipSlotEntity.orElseGet(CharacterEquipSlotEntity::new);

                characterEquipSlotEntity.setEquipSlot(slotEnum);
                characterEquipSlotEntity.setCharacter(character);
                characterEquipSlotEntity.setArmor(armorEntity);
                equipSlotRepo.save(characterEquipSlotEntity);
            }
        } catch (IOException e) {
            log.error("Unable to connect to url: " + characterUrl + " IOException: " + e.getMessage());
        }

    }

    private ArmorEntity saveOrGetArmor(CharacterEntity character, Document doc, EquipSlotEnum slotEnum) {
        Elements elements = doc.selectXpath("//*[@id='" + slotEnum.getSlotNumber() + "']/*[@class='title-item']/a");
        ArmorEntity armorEntity = null;
        if(!elements.isEmpty()) {
            Element element = elements.get(0);
            String itemName = element.text();
            log.info(String.format("Getting information on Armor: %s", itemName));
            String itemUrl = element.attr("href");
            String itemExtId = itemUrl.substring(itemUrl.indexOf("?id=") + 4);
            Long extItemId = Long.valueOf(itemExtId);
            ServerEnum server = character.getServer();
            Optional<ArmorEntity> armorEntityOptional = armorRepo.findArmorEntityByExtIdAndServer(extItemId, server);
            if(armorEntityOptional.isPresent()) {
                return armorEntityOptional.get();
            }else{
                armorEntity = ArmorEntity.builder()
                        .name(itemName)
                        .server(character.getServer())
                        .extId(extItemId)
                        .build();
                getItemStatInformation(armorEntity, itemUrl);
                CharacterArmorUtil.setNullFieldsToZero(armorEntity);
                armorEntity = armorRepo.save(armorEntity);
            }
        }
        return armorEntity;
    }

    private ArmorEntity getItemStatInformation(ArmorEntity armorEntity, String itemUrl) {
        Document itemDoc = null;
        itemUrl = ServerInformation.THF_WEBTOOLS_URL + itemUrl;
        try {
            itemDoc = Jsoup.connect(itemUrl).get();
        } catch (IOException e) {
            log.error("Unable to connect to Url: " + itemUrl);
        }
        Elements trElements = itemDoc.body().selectXpath("//*[@class='table']//*[@class='display-item']/tbody/tr");
            for(int i = 0; i < trElements.size(); i++){
                Element tr = trElements.get(i);
                Elements tdElements = tr.children();
                //they are always 3 elements going to make an assumption to cut down on time
                String traitName = tdElements.get(0).text().trim();
                THFWebAttributesEnum thfWebAttribute =THFWebAttributesEnum.getTHFWebAttributeByHtmlName(traitName);
                if(thfWebAttribute != null){
                    //BaseAttributes class
                    Field field = null;
                    try {
                        field = armorEntity.getClass().getSuperclass().getDeclaredField(thfWebAttribute.getFieldName());
                    } catch (NoSuchFieldException e) {
                        log.error("Unable to access field: " + thfWebAttribute.getFieldName() + " Field does not exist exception " + e.getMessage());
                        break;
                    }
                    String text = tdElements.get(1).text();
                    String value = !(text.contains("%")) ? text : text.substring(0, text.length() - 1);
                    Object obj;
                    if(text.contains("%")) {
                        obj = Double.valueOf(value);
                        if (obj == null) {
                            obj = Double.valueOf(0);
                        }
                    }else {
                        obj = Integer.valueOf(value);
                        if (obj == null) {
                            obj = Integer.valueOf(0);
                        }
                    }
                    try {
                        field.setAccessible(true);
                        field.set(armorEntity, obj);
                    } catch (IllegalAccessException e) {
                        log.error("Unable to access field: " + field +" with illegal access: " + e.getMessage());
                    }finally {
                        field.setAccessible(false);
                    }
                }
            }
        return armorEntity;
    }

    public List<CharacterEquipSlot> getArmorByCharacterIdAndServer(Long characterId, ServerEnum serverName) {
       List<CharacterEquipSlotEntity> entities = equipSlotRepo.findCharacterEquipSlotEntitiesByCharacter_IdAndCharacter_Server(characterId, serverName).orElseThrow(() -> new CharacterNotFoundException(characterId));
       List<CharacterEquipSlot> characterEquipSlotList = new ArrayList<>();
       entities.stream().forEach(e -> characterEquipSlotList.add(modelMapper.map(e, CharacterEquipSlot.class)));

       return characterEquipSlotList;
    }
}
