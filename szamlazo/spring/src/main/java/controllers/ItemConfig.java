package controllers;

import JSON.ItemDAOJSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ItemServiceImpl;
import service.ItemTypeServiceImpl;

import java.io.IOException;

@Configuration
public class ItemConfig {

    @Bean("itemJsonDB")
    public ItemServiceImpl serviceJSON() throws IOException {
        return new ItemServiceImpl(new ItemDAOJSON("item.json"));
    }

    @Bean("itemMysqlDB")
    public ItemServiceImpl serviceMySQL(){
        return new ItemServiceImpl(null);
    }


    @Bean("itemTypeService")
    public ItemTypeServiceImpl serviceItemType() {return new ItemTypeServiceImpl();}
}
