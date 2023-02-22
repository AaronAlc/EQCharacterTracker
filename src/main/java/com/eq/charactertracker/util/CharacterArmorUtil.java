package com.eq.charactertracker.util;

import com.eq.charactertracker.entity.ArmorEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class CharacterArmorUtil {
    public static void setNullFieldsToZero(ArmorEntity armorEntity) {
        Field[] superClassFields = armorEntity.getClass().getSuperclass().getDeclaredFields();

        for(Field field: superClassFields){
            try {
                field.setAccessible(true);
                Object obj = field.get(armorEntity);
                if(obj == null){
                    if(field.getType().equals(Integer.class)) {
                        field.set(armorEntity, 0);
                    } else if (field.getType().equals(Double.class)) {
                        field.set(armorEntity, Double.valueOf("0"));
                    }
                }
            } catch (IllegalAccessException e) {
                log.error(String.format("Can't set field %s Illegal access Exception %s", field.getName(), e.getMessage()));
            }finally {
                field.setAccessible(false);
            }
        }
    }
}
