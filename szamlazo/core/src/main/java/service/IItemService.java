package service;

import model.Item;
import model.ItemType;

import java.util.Collection;

public interface IItemService {
    void addItem(Item item);
    void removeItem(Item item);
    void updateItem(Item item);
    Item getItem(String barcode);
    Collection<Item> getItems();
    Collection<Item> getItemsByType(ItemType type);
    Collection<Item> getItemsBetween(double min, double max);
    Collection<Item> getItemsByManufacturer(String manufacturer);
    Collection<Item> getItemsByName(String name);
    Collection<Item> getLowStock();
    Collection<String> getAllManufacturers();

}
