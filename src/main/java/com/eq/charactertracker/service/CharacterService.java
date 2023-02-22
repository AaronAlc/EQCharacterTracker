package com.eq.charactertracker.service;

import com.eq.charactertracker.repo.CharacterRepo;
import com.eq.charactertracker.repo.UserRepo;
import com.eq.charactertracker.entity.CharacterEntity;
import com.eq.charactertracker.entity.UserEntity;
import com.eq.charactertracker.exception.CharacterAlreadyExistsException;
import com.eq.charactertracker.exception.CharacterNotFoundException;
import com.eq.charactertracker.model.Character;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final ModelMapper modelMapper;
    private final CharacterRepo characterRepo;
    private final UserRepo userRepo;
    private final THFCharacterService thfCharacterService;
    public Character createCharacter(Character character, Long userId) {
        CharacterEntity characterEntity = characterRepo.findCharacterEntityByUserIdAndName(userId, character.getName());
        if(characterEntity != null){
            throw new CharacterAlreadyExistsException(character);
        }
        characterEntity = modelMapper.map(character, CharacterEntity.class);
        UserEntity userEntity = userRepo.getReferenceById(userId);
        characterEntity.setUser(userEntity);
        characterEntity = characterRepo.save(characterEntity);

        Character returnedCharacter = modelMapper.map(characterEntity, Character.class);


        return returnedCharacter;
    }

    public Character getCharacterById(Long characterId) {

        Optional<CharacterEntity> characterEntity = characterRepo.findById(characterId);
        if(characterEntity.isPresent()) {
            Character character = modelMapper.map(characterEntity.get(), Character.class);;
            return character;
        }
        throw new CharacterNotFoundException(characterId);
    }

    public List<Character> getAllCharacters() {
        List<CharacterEntity> characterEntityList = characterRepo.findAll();
        List<Character> characterList = new ArrayList<>();
        characterEntityList.stream().forEach(ce -> characterList.add(modelMapper.map(ce, Character.class)));
        return characterList;
    }

    public List<Character> createCharacters(List<Character> characters, Long userId) {
        List<Character> characterList = new ArrayList<>();
        for(Character character: characters){
            characterList.add(createCharacter(character, userId));
        }
        return characterList;
    }

    public List<Character> searchCharacters(Pageable pageable){
        Page<CharacterEntity> characterEntityList = characterRepo.findAll(pageable);
        List<Character> characterList = new ArrayList<>();
        for(CharacterEntity characterEntity: characterEntityList){
            characterList.add(modelMapper.map(characterEntity, Character.class));
        }
        return characterList;
    }

    public List<Character> refreshCharacters(Long userId) {
        List<CharacterEntity> characterEntityList = characterRepo.findCharacterEntitiesByUserId(userId);
        characterEntityList.stream().forEach(e -> thfCharacterService.updateExternalArmorDetails(e));
        return convertCharacterEntityListToCharacterModelList(characterEntityList);
    }

    private List<Character> convertCharacterEntityListToCharacterModelList(List<CharacterEntity> entities){
        List<Character> characterList = new ArrayList<>();
        entities.stream().forEach(e -> characterList.add(modelMapper.map(e, Character.class)));
        return characterList;
    }
}
