package JSON;

import DAO.ItemDAO;
import exception.EmptyStringException;
import exception.InvalidValueException;
import exception.ObjectAlreadyExistException;
import exception.ObjectDoesNotExistException;
import model.Item;
import model.ItemType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static helper.FileHelper.deleteFile;
import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.*;

public class ItemDAOJSONTest {

    String filePath = "items.json";


    @Test(expected = ObjectAlreadyExistException.class)
    public void addItem() throws IOException, EmptyStringException, ObjectAlreadyExistException, InvalidValueException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.addItem(item2);
        jsonDAO.addItem(item3);
        jsonDAO.addItem(item2);
    }

    @org.junit.Test(expected = ObjectDoesNotExistException.class)
    public void removeItem() throws EmptyStringException, InvalidValueException, IOException, ObjectAlreadyExistException, ObjectDoesNotExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.removeItem(item1);
        jsonDAO.removeItem(item2);
    }

    @org.junit.Test(expected = ObjectDoesNotExistException.class)
    public void updateItem() throws EmptyStringException, InvalidValueException, IOException, ObjectAlreadyExistException, ObjectDoesNotExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("1");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.updateItem(item2);
        jsonDAO.updateItem(item3);

    }

    @org.junit.Test
    public void getItem() throws IOException, EmptyStringException, InvalidValueException, ObjectAlreadyExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        assertEquals(jsonDAO.getItem("1"), null);

        jsonDAO.addItem(item1);
        Item result = jsonDAO.getItem("1");
        assertNotNull(result);
        assertTrue(result.equals(item1));
    }

    @org.junit.Test
    public void getItems() throws IOException, EmptyStringException, InvalidValueException, ObjectAlreadyExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.addItem(item2);
        jsonDAO.addItem(item3);

        ArrayList<Item> list = new ArrayList<>();
        ((ArrayList<Item>) list).add(item1);
        ((ArrayList<Item>) list).add(item2);
        ((ArrayList<Item>) list).add(item3);

        ArrayList<Item> items = jsonDAO.getItems().stream().collect(toCollection(ArrayList::new));
        assertArrayEquals(list.toArray(), items.toArray());
    }

    @org.junit.Test
    public void getItemsByType() throws ObjectAlreadyExistException, InvalidValueException, EmptyStringException, IOException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.addItem(item2);
        jsonDAO.addItem(item3);

        assertTrue(jsonDAO.getItemsFilteredByType(new ArrayList<ItemType>(Arrays.asList(ItemType.ACESSORIES))).stream().allMatch(item -> item.getType().equals(ItemType.ACESSORIES)));
    }

    @org.junit.Test
    public void getItemsBetween() throws IOException, EmptyStringException, InvalidValueException, ObjectAlreadyExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.addItem(item2);
        jsonDAO.addItem(item3);

        assertTrue(jsonDAO.getItemsBetween(100, 200).stream().allMatch(item -> item.getPrice() <= 100 || item.getPrice() >= 200));
    }

    @org.junit.Test
    public void getLowStock() throws IOException, EmptyStringException, InvalidValueException, ObjectAlreadyExistException {
        deleteFile(filePath);

        ItemDAO jsonDAO = new ItemDAOJSON(filePath);

        Item item1 = new Item();
        item1.setBarcode("1");
        item1.setName("Test 1");
        item1.setDescription("none");
        item1.setManufacturer("none");
        item1.setPrice(100.);
        item1.setStock(2);
        item1.setType(ItemType.ACESSORIES);

        Item item2 = new Item();
        item2.setBarcode("2");
        item2.setName("Test 2");
        item2.setDescription("none");
        item2.setManufacturer("none");
        item2.setPrice(200.);
        item2.setStock(3);
        item2.setType(ItemType.NOTEBOOK);

        Item item3 = new Item();
        item3.setBarcode("3");
        item3.setName("Test 3");
        item3.setDescription("none");
        item3.setManufacturer("none");
        item3.setPrice(300.);
        item3.setStock(2);
        item3.setType(ItemType.ACESSORIES);

        jsonDAO.addItem(item1);
        jsonDAO.addItem(item2);
        jsonDAO.addItem(item3);

        assertTrue(jsonDAO.getLowStock().stream().allMatch(item -> item.getStock() == Item.stockLow));
    }
}