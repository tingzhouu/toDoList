package todolist;

import java.util.*;
import java.io.*;


public class ToDoList implements Serializable {
    private List<ItemInList> list;
    
    public ToDoList() {
        list = new ArrayList<>();
    }
    
    public ToDoList(List<ItemInList> list) {
        this.list = list;
    }
    
    public void addItem(ItemInList itemToAdd) {
        list.add(itemToAdd);
    }
    
    public List<ItemInList> getList() {
        return list;
    }
    
    public String toString() {
        if (list.size() == 0) {
            return "There are no to-do entries in this list!" + "\n";
        }
        
        String output = "";
        for (ItemInList item: list) {
            output += item.toString() + "\n\n";
        }
        return output;
    }
}