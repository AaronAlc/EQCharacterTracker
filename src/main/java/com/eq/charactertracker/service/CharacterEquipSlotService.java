package com.eq.charactertracker.service;

import com.eq.charactertracker.constants.CharacterClass;
import com.eq.charactertracker.constants.EquipSlotEnum;
import com.eq.charactertracker.repo.EquipSlotRepo;
import com.eq.charactertracker.entity.CharacterEquipSlotEntity;
import com.eq.charactertracker.model.CharacterEquipSlot;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterEquipSlotService {

    private final EquipSlotRepo equipSlotRepo;
    private final ModelMapper modelMapper;

    public List<CharacterEquipSlot> searchCharactersByClassesAndSlots(List<CharacterClass> classes, List<EquipSlotEnum> slots) {
        if(classes == null || classes.size() == 0){
            classes = Arrays.stream(CharacterClass.values()).collect(Collectors.toList());
        }
        if(slots == null || slots.size() == 0){
            slots = Arrays.stream(EquipSlotEnum.values()).collect(Collectors.toList());
        }
        List<CharacterEquipSlotEntity> entities = equipSlotRepo.findCharacterEquipSlotEntitiesByCharacter_CharacterClassAndEquipSlot(classes, slots);
        return convertListofCharacterEquipSlotEntityToModel(entities);
    }

    public List<CharacterEquipSlot> getAllCharacterArmorSlots() {
        List<CharacterEquipSlotEntity> entities = equipSlotRepo.findAll();
        return convertListofCharacterEquipSlotEntityToModel(entities);
    }

    private List<CharacterEquipSlot> convertListofCharacterEquipSlotEntityToModel(List<CharacterEquipSlotEntity> l1){
        List<CharacterEquipSlot> characterEquipSlotList = new ArrayList<>();
        for(CharacterEquipSlotEntity entity: l1){
            characterEquipSlotList.add(modelMapper.map(entity, CharacterEquipSlot.class));
        }
        return characterEquipSlotList;
    }
}
