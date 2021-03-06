package service;

import DAO.ItemDAO;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Item;
import model.ItemType;

import java.util.ArrayList;
import java.util.Collection;

public class ItemServiceImpl implements IItemService {

    private ItemDAO dao;

    public ItemServiceImpl(ItemDAO dao) {
        this.dao = dao;
    }

    public void addItem(Item item) {
        try {
            dao.addItem(item);
        } catch (ObjectAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(Item item) {
        try {
            dao.removeItem(item);
        } catch (ObjectDoesNotExistException e) {
            e.printStackTrace();
        }

    }

    public void updateItem(Item item) {
        try {
            dao.updateItem(item);
        } catch (ObjectDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public Item getItem(String barcode) {
        return dao.getItem(barcode);
    }

    public Collection<Item> getItems() {
        return dao.getItems();
    }

    public Collection<Item> getItemsFilteredByType(Collection<ItemType> types) {
        return dao.getItemsFilteredByType(types);
    }

    public Collection<Item> getItemsFilteredByManufacturer(Collection<String> manufacturers) {
        return dao.getItemsFilteredByManufacturer(manufacturers);
    }

    public Collection<Item> getItemsFiltered(Collection<ItemType> types, Collection<String> manufacturers) {
        return dao.getItemsFiltered(types, manufacturers);
    }


    public Collection<Item> getItemsBetween(double min, double max) {
        return dao.getItemsBetween(min, max);
    }


    public Collection<Item> getItemsByName(String name) {
        return getItemsByName(name);
    }

    public Collection<Item> getLowStock() {
        return dao.getLowStock();
    }

    public Collection<String> getAllManufacturers() {
        return dao.getAllManufacturers();
    }
}
