package com.eq.charactertracker.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(Long externalId){
        super("Item not found with externalId: " + externalId);
    }
}
