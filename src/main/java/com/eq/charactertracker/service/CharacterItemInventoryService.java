package com.eq.charactertracker.service;

import com.eq.charactertracker.constants.ServerEnum;
import com.eq.charactertracker.entity.CharacterInventoryEntity;
import com.eq.charactertracker.entity.ItemEntity;
import com.eq.charactertracker.exception.ItemNotFoundException;
import com.eq.charactertracker.model.Character;
import com.eq.charactertracker.model.Item;
import com.eq.charactertracker.model.QuestItem;
import com.eq.charactertracker.repo.CharacterInventoryRepo;
import com.eq.charactertracker.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterItemInventoryService {

    private final ItemRepo itemRepo;
    private final ModelMapper modelMapper;
    private final CharacterInventoryRepo characterInventoryRepo;

    public Item getItemByExternalId(Long externalId){
        Optional<ItemEntity> itemEntity = itemRepo.findItemEntityByExternalId(externalId);
        if(itemEntity.isPresent()){
            return modelMapper.map(itemEntity, Item.class);
        }
        throw new ItemNotFoundException(externalId);
    }

    public Item createOrGetItem(Item item){
        Optional<ItemEntity> optionalItemEntity = itemRepo.findItemEntityByExternalId(item.getExternalId());
        if(optionalItemEntity.isPresent()){
            return modelMapper.map(optionalItemEntity, Item.class);
        }

        ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);
        itemEntity = itemRepo.save(itemEntity);

        return modelMapper.map(itemEntity, Item.class);
    }

    public boolean findIfCharacterHasItemInInventory(Character character, QuestItem item){
        return characterInventoryRepo.findCharacterInventoryEntityByCharacter_IdAndItem_ExternalIdAndItem_Server(character.getId(),
                item.getItem().getExternalId(),
                item.getItem().getServer()).isPresent();
    }

    public Optional<CharacterInventoryEntity> findItemEntityInCharacterInventoryByCharacterAndExternalId(Long characterId, Long exteranlId){
        return characterInventoryRepo.findCharacterInventoryEntityByCharacter_IdAndItem_ExternalIdAndItem_Server(characterId, exteranlId, ServerEnum.THF);
    }

}
