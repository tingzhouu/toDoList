package todolist;

import java.util.*;
import java.io.*;

public class ItemInList implements Serializable{
    private Calendar dateCreated;
    private Calendar dateDue;
    private String description;
    
    public ItemInList(Calendar dateCreated, Calendar dateDue, String description) {
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.description = description;
    }
    
    public Calendar getDateCreated() {
        return dateCreated;
    }
    
    public Calendar getDateDue() {
        return dateDue;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String toString() {
        return "Date Created: " + dateCreated.getTime() + "\n" + "Date Due: " 
                    + dateDue.getTime() + "\n" + "Description: " + description;
    }
    
}