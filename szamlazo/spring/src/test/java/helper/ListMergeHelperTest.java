package helper;

import exception.EmptyStringException;
import exception.InvalidValueException;
import model.Item;
import model.ItemType;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ListMergeHelperTest {

    @Test
    public void mergeItemLists() throws EmptyStringException, InvalidValueException {
        ArrayList<Item> items1 = new ArrayList<>();
        ArrayList<Item> items2 = new ArrayList<>();
        ArrayList<Item> items3 = new ArrayList<>();


        Item item1 = new Item();
        item1.setPrice(1000.0);
        item1.setName("test1");
        item1.setStock(10);
        item1.setManufacturer("test1");
        item1.setType(ItemType.ACESSORIES);
        item1.setBarcode("1");
        item1.setDescription("-");

        Item item2 = new Item();
        item2.setPrice(2000.0);
        item2.setName("test2");
        item2.setStock(10);
        item2.setManufacturer("test2");
        item2.setType(ItemType.ACESSORIES);
        item2.setBarcode("2");
        item2.setDescription("-");

        Item item3 = new Item();
        item3.setPrice(3000.0);
        item3.setName("test3");
        item3.setStock(10);
        item3.setManufacturer("test3");
        item3.setType(ItemType.NOTEBOOK);
        item3.setBarcode("3");
        item3.setDescription("-");

        Item item4 = new Item();
        item4.setPrice(4000.0);
        item4.setName("test4");
        item4.setStock(10);
        item4.setManufacturer("test4");
        item4.setType(ItemType.DESKTOP);
        item4.setBarcode("4");
        item4.setDescription("-");

        items1.add(item1);
        items1.add(item2);

        items2.add(item4);
        items2.add(item2);

        items3.add(item3);
        items3.add(item1);

        ArrayList<Item> expectedList = new ArrayList<>();
        expectedList.add(item1);
        expectedList.add(item2);
        expectedList.add(item3);
        expectedList.add(item4);

        ArrayList<Item> result = (ArrayList<Item>) ListMergeHelper.mergeItemLists(items1, items2, items3);

        for (Item item:
             result) {
            System.out.println(item.toString());
            assertTrue(expectedList.contains(item));
        }
    }

}