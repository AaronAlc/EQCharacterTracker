package com.eq.charactertracker.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseQuest {
    protected Boolean completed;
}
