package todolist;

import java.util.*;
import java.io.*;


public class ToDoListDAO {
    
    public static ToDoList loadUserToDoList(String username) {
        String fileName = "data/" + username + ".txt";
        ToDoList output = null;
        
        try { 
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oin = new ObjectInputStream(fin);
                        
            output = (ToDoList) oin.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return output;
    }
    
    public static void saveUserToDoList(String username, ToDoList list) {
        String fileName = "data/" + username + ".txt";

        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            
            out.writeObject(list);
            out.flush();
            System.out.println("List saved!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}