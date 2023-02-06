package com.eq.charactertracker.entity;


import com.eq.charactertracker.base.BaseAttributes;
import com.eq.charactertracker.constants.ServerEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "augmentations")
@Data
public class AugmentationEntity extends BaseAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short augType;

    private Long extId;

    private ServerEnum server;
}
