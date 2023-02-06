package com.eq.charactertracker.config.modelmapper;

import com.eq.charactertracker.entity.ArmorEntity;
import com.eq.charactertracker.model.Armor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;


public class ArmorEntityToArmorConverter implements Converter<ArmorEntity, Armor> {

    @Override
    public Armor convert(MappingContext<ArmorEntity, Armor> mappingContext) {
        ArmorEntity armorEntity = mappingContext.getSource();
        Armor armor = new Armor(armorEntity);
        return armor;
    }
}
