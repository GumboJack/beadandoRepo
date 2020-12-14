package service;

import model.Item;
import model.ItemType;

import java.util.ArrayList;
import java.util.Collection;

public interface IItemService {
    void addItem(Item item);
    void removeItem(Item item);
    void updateItem(Item item);
    Item getItem(String barcode);
    Collection<Item> getItems();
    Collection<Item> getItemsFilteredByType(Collection<ItemType> types);
    Collection<Item> getItemsFilteredByManufacturer(Collection<String> manufacturers);
    Collection<Item> getItemsFiltered(Collection<ItemType> type, Collection<String> manufacturer);
    Collection<Item> getItemsBetween(double min, double max);
    Collection<Item> getLowStock();
    Collection<Item> getItemsByName(String name);
    Collection<String> getAllManufacturers();

}
