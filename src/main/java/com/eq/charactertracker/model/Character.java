package com.eq.charactertracker.model;

import com.eq.charactertracker.constants.CharacterClass;
import com.eq.charactertracker.constants.ServerEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Serializable {

    private Long id;
    private String name;
    private ServerEnum server;
    private CharacterClass characterClass;
    private Short level;
    private Short aaSpent;
    private Short aaAvailable;
    private User user;

}
