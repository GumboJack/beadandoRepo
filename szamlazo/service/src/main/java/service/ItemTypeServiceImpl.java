package service;

import model.ItemType;
import java.util.ArrayList;
import java.util.Collection;

public class ItemTypeServiceImpl implements ItemTypeService {

    public Collection<ItemType> getAllItemType() {
        Collection<ItemType> typeArray = new ArrayList<ItemType>();
        for (ItemType itemtype:
             ItemType.values()) {
            typeArray.add(itemtype);
        }
        return typeArray;
    }
}
