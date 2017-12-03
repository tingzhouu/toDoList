package users;

import java.util.*;
import java.io.*;

public class UsernameList implements Serializable{
    private Map<String, String> userList;
    
    public UsernameList() {
        userList = new HashMap<>();
    }
    
    public void addUser(String username, String password) {
        userList.put(username, password);
    }
    
    public Map<String, String> getUserList() {
        return userList;
    }
    
    public boolean checkUsernamePassword(String usernameEntered, String passwordEntered) {
        boolean usernameExists = userList.containsKey(usernameEntered);
        
        if (!usernameExists) {
            return false;
        }
        
        String password = userList.get(usernameEntered);
        if (!password.equals(passwordEntered)) {
            return false;
        }
        
        return true;
    }
}