package com.eq.charactertracker.repo;

import com.eq.charactertracker.constants.ServerEnum;
import com.eq.charactertracker.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends JpaRepository<CharacterEntity, Long> {

    CharacterEntity findCharacterEntityByUserIdAndName(Long userId, String name);
    CharacterEntity findCharacterEntityByNameAndServer(String name, ServerEnum server);

    List<CharacterEntity> findCharacterEntitiesByUserId(Long userId);

}

