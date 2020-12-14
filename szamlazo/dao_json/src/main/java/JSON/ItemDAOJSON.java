package JSON;

import DAO.ItemDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.istack.internal.NotNull;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import helper.RegexHelper;
import model.Item;
import model.ItemType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ItemDAOJSON implements ItemDAO {

    private File jsonFile;
    private ObjectMapper mapper;

    public ItemDAOJSON(String jsonFilePath) throws IOException {
        File jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()){
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.flush();
            writer.close();
        }
        this.jsonFile = jsonFile;
        mapper = new ObjectMapper();
    }

    @Override
    public void addItem(@NotNull Item item) throws ObjectAlreadyExistException {
        Collection<Item> items = getItems();
        String barcode = item.getBarcode();
        if (items != null && getItem(barcode) == null){
            items.add(item);
            try {
                mapper.writeValue(jsonFile, items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new ObjectAlreadyExistException(String.format("Item with barcode [%s] already exist", barcode));
        }
    }

    @Override
    public void removeItem(@NotNull Item item) throws ObjectDoesNotExistException {
        Collection<Item> items = getItems();
        String barcode = item.getBarcode();
        if (items != null && !items.isEmpty() &&
                items.removeIf(listItem -> listItem.getBarcode().equals(barcode))){
            try {
                mapper.writeValue(jsonFile, items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            throw new ObjectDoesNotExistException(String.format("Item with barcode [%s] does not exist.", barcode));
        }
    }

    @Override
    public void updateItem(@NotNull Item item) throws ObjectDoesNotExistException {
        Collection<Item> items = getItems();
        String barcode = item.getBarcode();
        if (items != null && !items.isEmpty()){
            if (items.removeIf(listItem -> listItem.getBarcode().equals(barcode))){
                items.add(item);
            } else {
                throw new ObjectDoesNotExistException(String.format("Item with barcode [%s] does not exist.", barcode));
            }
            try {
                mapper.writeValue(jsonFile, items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            throw new ObjectDoesNotExistException(String.format("Item with barcode [%s] does not exist.", barcode));
        }
    }

    public Item getItem(String barcode) {
        return getItems().stream().filter(item -> item.getBarcode().equals(barcode)).findAny().orElse(null);
    }

    @Override
    public Collection<Item> getItems() {
        Collection<Item> items = null;
        TypeReference type = new TypeReference<ArrayList<Item>>(){};
        try{
            items = (Collection<Item>) mapper.readValue(jsonFile, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Collection<Item> getItemsFilteredByType(Collection<ItemType> types) {
        return getItems().stream().filter(item -> types.contains(item.getType())).collect(Collectors.toList());
    }

    @Override
    public Collection<Item> getItemsFiltered(Collection<ItemType> types, Collection<String> manufacturers) {
        return getItems().stream().filter(item -> types.contains(item.getType()) &&
                manufacturers.stream().anyMatch(manufacturer -> RegexHelper.containsWord(item.getManufacturer(), manufacturer)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Item> getItemsFilteredByManufacturer(Collection<String> manufacturers) {
        return getItems().stream()
                .filter(item -> manufacturers.stream()
                        .anyMatch(manufacturer -> RegexHelper.containsWord(item.getManufacturer(), manufacturer)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Item> getItemsBetween(double min, double max) {
        return getItems().stream().filter(item -> item.getPrice() >= min && item.getPrice() <= max).collect(Collectors.toList());
    }

    @Override
    public Collection<Item> getLowStock() {
        return getItems().stream().filter(item -> item.getStock() <= Item.stockLow).collect(Collectors.toList());
    }


    @Override
    public Collection<Item> getItemsByName(String name) {
        return getItems().stream().filter(item -> RegexHelper.containsWord(item.getName(), name)).collect(Collectors.toList());
    }

    @Override
    public Collection<String> getAllManufacturers() {
        return getItems().stream().map(Item::getManufacturer).distinct().collect(Collectors.toList());
    }
}
