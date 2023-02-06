package com.eq.charactertracker.constants;

import lombok.Getter;

@Getter
public enum ServerEnum {
    PEQ("PEQ The Grand Creation"),
    THF("The Hidden Forest");

    private final String serverName;
    ServerEnum(String serverName) {
        this.serverName = serverName;
    }

}
