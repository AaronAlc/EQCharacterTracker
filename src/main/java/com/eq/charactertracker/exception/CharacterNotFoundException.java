package com.eq.charactertracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException{
    public CharacterNotFoundException(Long characterId){
        super("Character not found with Id: " + characterId);
    }
}
