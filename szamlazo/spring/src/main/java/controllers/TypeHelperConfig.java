package controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ItemTypeServiceImpl;

@Configuration
public class TypeHelperConfig {

    @Bean("typeService")
    public ItemTypeServiceImpl itemTypeService(){
        return new ItemTypeServiceImpl();
    }
}
