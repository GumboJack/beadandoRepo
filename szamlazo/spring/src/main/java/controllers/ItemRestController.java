package controllers;

import model.Item;
import model.ItemType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ItemServiceImpl;
import service.ItemTypeServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("rest")
public class ItemRestController {

    @Autowired
    private ItemServiceImpl itemJsonDB;

    @Autowired
    private ItemTypeServiceImpl itemTypeService;

    @GetMapping(value = "/items", produces = "application/json")
    @ResponseBody
    public String getItems(){
        //tesztAdatBetoltes();
        try{
            Collection<Item> items = itemJsonDB.getItems();
            if (items != null && !items.isEmpty()){
                JSONArray responseArray = new JSONArray();
                items.forEach(item -> responseArray.put(new JSONObject(item)));
                return new JSONObject().put("error", "0").put("items", responseArray).toString();
            } else {
                return new JSONObject().put("error", "1").put("msg", "There are no items available!").toString();
            }
        }catch (Exception e){
            return new JSONObject().put("error", "3").put("msg", "Unexpected error!").toString();
        }
    }

    @GetMapping(value = "/itemtypes", produces = "application/json")
    @ResponseBody
    public String getItemTypes(){
        try {
            Collection<ItemType> itemTypes = itemTypeService.getAllItemType();
            JSONArray responseArray = new JSONArray();
            itemTypes.forEach(itemType -> responseArray.put(itemType));
            return new JSONObject().put("error", "0").put("itemTypes", responseArray).toString();
        }catch (Exception e){
            return new JSONObject().put("error", "1").put("msg", "Unexpected error!").toString();
        }
    }

    @GetMapping(value = "/manufacturers", produces = "application/json")
    @ResponseBody
    public String getManufacturers(){
        try {
            JSONArray responseArray = new JSONArray();
            itemJsonDB.getAllManufacturers().forEach(manufacturer -> responseArray.put(manufacturer));
            return new JSONObject().put("error", "0").put("manufacturers", responseArray).toString();
        }catch (Exception e){
            return new JSONObject().put("error", "1").put("msg", "Unexpected error!").toString();
        }
    }
/*
    private void tesztAdatBetoltes(){
        try {
            Item item1 = new Item();
            item1.setBarcode("11");
            item1.setName("Macbook Air");
            item1.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item1.setManufacturer("Apple");
            item1.setType(ItemType.NOTEBOOK);
            item1.setStock(10);
            item1.setPrice(1199d);

            Item item2 = new Item();
            item2.setBarcode("12");
            item2.setName("Macbook Pro 13");
            item2.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item2.setManufacturer("Apple");
            item2.setType(ItemType.NOTEBOOK);
            item2.setStock(10);
            item2.setPrice(1899d);

            Item item3 = new Item();
            item3.setBarcode("13");
            item3.setName("Macbook Pro 15");
            item3.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item3.setManufacturer("Apple");
            item3.setType(ItemType.NOTEBOOK);
            item3.setStock(10);
            item3.setPrice(2199d);

            Item item4 = new Item();
            item4.setBarcode("14");
            item4.setName("Watch Series 4");
            item4.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item4.setManufacturer("Apple");
            item4.setType(ItemType.ACESSORIES);
            item4.setStock(10);
            item4.setPrice(699d);

            Item item5 = new Item();
            item5.setBarcode("15");
            item5.setName("Mac Mini");
            item5.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item5.setManufacturer("Apple");
            item5.setType(ItemType.DESKTOP);
            item5.setStock(10);
            item5.setPrice(1799d);

            Item item6 = new Item();
            item6.setBarcode("16");
            item6.setName("iMac");
            item6.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item6.setManufacturer("Apple");
            item6.setType(ItemType.DESKTOP);
            item6.setStock(10);
            item6.setPrice(2699d);

            Item item7 = new Item();
            item7.setBarcode("17");
            item7.setName("Airpods");
            item7.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!");
            item7.setManufacturer("Apple");
            item7.setType(ItemType.ACESSORIES);
            item7.setStock(10);
            item7.setPrice(299d);

            itemJsonDB.addItem(item1);
            itemJsonDB.addItem(item2);
            itemJsonDB.addItem(item3);
            itemJsonDB.addItem(item4);
            itemJsonDB.addItem(item5);
            itemJsonDB.addItem(item6);
            itemJsonDB.addItem(item7);

        } catch (EmptyStringException e) {
            e.printStackTrace();
        } catch (InvalidValueException e) {
            e.printStackTrace();
        }
    }*/

}
