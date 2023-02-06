package com.eq.charactertracker.repo;

import com.eq.charactertracker.constants.ServerEnum;
import com.eq.charactertracker.entity.ArmorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmorRepo extends JpaRepository<ArmorEntity, Long> {

    Optional<ArmorEntity> findArmorEntityByExtIdAndServer(Long extId, ServerEnum server);
}
