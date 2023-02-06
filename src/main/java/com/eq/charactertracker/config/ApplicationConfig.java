package com.eq.charactertracker.config;

import com.eq.charactertracker.config.modelmapper.EntityToModelMap;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new EntityToModelMap());
        return modelMapper;
    }

}
