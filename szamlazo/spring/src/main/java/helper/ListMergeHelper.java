package helper;

import model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ListMergeHelper {

    public static Collection<Item> mergeItemLists(Collection<Item> mainList, Collection<Item>... arrayLists){
        Collection<Item> result = new ArrayList<>();
        if (arrayLists.length > 1){
            for (int i = 0; i < arrayLists.length; i++){
                final Collection<Item> actualList = arrayLists[i];
                result = actualList.stream().filter(item -> mainList.contains(item)).collect(Collectors.toList());
            }
        }
        return result;
    }
}
