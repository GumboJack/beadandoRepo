package DAO;

import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.*;

import java.util.ArrayList;
import java.util.Collection;

public interface ItemDAO {
    void addItem(Item item) throws ObjectAlreadyExistException;
    void removeItem(Item item) throws ObjectDoesNotExistException;
    void updateItem(Item item) throws ObjectDoesNotExistException;
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
