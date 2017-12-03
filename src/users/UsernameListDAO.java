package users;

import java.util.*;
import java.io.*;

public class UsernameListDAO {
    
    public static UsernameList loadExistingUsernameList() {
        String fileName = "data/usernamelist.txt";
        UsernameList output = null;
        
        try { 
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oin = new ObjectInputStream(fin);
                        
            output = (UsernameList) oin.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return output;
    }
    
    public static void saveExistingUsernameList(UsernameList list) {
        String fileName = "data/usernamelist.txt";

        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            
            out.writeObject(list);
            out.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}