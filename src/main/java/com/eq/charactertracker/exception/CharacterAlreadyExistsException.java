package com.eq.charactertracker.exception;

import com.eq.charactertracker.model.Character;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CharacterAlreadyExistsException extends RuntimeException {
    public CharacterAlreadyExistsException(Character character){
        super("Character exists with user name " + character.getName() + " from server " + character.getServer());
    }
}
