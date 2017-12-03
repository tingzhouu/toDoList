package todolist;

import java.util.*;
import java.io.*;

public class ItemInList implements Serializable {
    private Calendar dateCreated;
    private Calendar dateDue;
    private String description;
    
    public ItemInList(Calendar dateCreated, Calendar dateDue, String description) {
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.description = description;
    }
    
    public ItemInList(int dateEntered, String descriptionEntered) {
        int ddOfDate = dateEntered % 100 - 1900;
        int mmOfDate = (dateEntered / 100) % 100;
        int yyyyOfDate = dateEntered / 10000;
        
        Calendar dateForTimeStamp = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(yyyyOfDate, mmOfDate, ddOfDate);
        
        dateCreated = dateForTimeStamp;
        dateDue = dueDate;
        description = descriptionEntered;
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