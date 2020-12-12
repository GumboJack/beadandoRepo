package DAO;

import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.*;

import java.util.Collection;

public interface ItemDAO {
    void addItem(Item item) throws ObjectAlreadyExistException;
    void removeItem(Item item) throws ObjectDoesNotExistException;
    void updateItem(Item item) throws ObjectDoesNotExistException;
    Item getItem(String barcode);
    Collection<Item> getItems();
    Collection<Item> getItemsByType(ItemType type);
    Collection<Item> getItemsBetween(double min, double max);
    Collection<Item> getLowStock();
    Collection<Item> getItemsByManufacturer(String manufacturer);
    Collection<Item> getItemsByName(String name);
    Collection<String> getAllManufacturers();
}
