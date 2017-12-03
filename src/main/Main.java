package main;

import java.util.*;
import java.io.*;

import todolist.*;
import users.*;

public class Main {
    
    public static void main (String[] args) {
        start();
    }
    
    public static void displayOptionsForUser() {
        System.out.println("=========================");
        System.out.println("Hello user! What would you like to do today?");
        System.out.println("Option 1: View your list");
        System.out.println("Option 2: Add a new item to the list");
        System.out.println("Option 3: Delete the existing list and create a new one");
        System.out.println("Option 4: Exit");
        System.out.println("=========================");
        System.out.println();
    }
    
    public static void displayOptionsForAdmin() {
        System.out.println("=========================");
        System.out.println("Hello admin! What would you like to do today?");
        System.out.println("Option 1: Delete the existing username list and create a new one");
        System.out.println("Option 2: Add a new user to the existing username list");
        System.out.println("Option 3: View all existing users");
        System.out.println("Option 4: Exit");
        System.out.println("=========================");
        System.out.println();
    }
    
    public static void start() {
        System.out.println("Are you a user or the admin?");
        System.out.println("Option 1: User");
        System.out.println("Option 2: Admin");
        System.out.print("Please enter your option: ");
        Scanner sc = new Scanner(System.in);
        int userOrAdmin = sc.nextInt();
        
        switch (userOrAdmin) {
            case 1:
                runUserMenu();
                break;
            
            case 2:
                runAdminMenu();
                break;
        }
    }
    
    public static void runAdminMenu() {
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            displayOptionsForAdmin();
            System.out.print("Please enter your option: ");
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    createNewUsernameList();
                    break;
                
                case 2:
                    addNewUserToExistingList();
                    break;
                    
                case 3:
                    seeAllUsersInExistingList();
                    break;
            }
        } while (option != 4);

        System.out.println("Have a nice day!");

    }
    
    public static void createNewUsernameList() {
        UsernameListDAO.saveExistingUsernameList(new UsernameList());
    }
    
    public static void addNewUserToExistingList() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the username: ");
        String username = sc.next();
        System.out.print("Please enter the password: ");
        String password = sc.next();
        
        UsernameList usernamelist = UsernameListDAO.loadExistingUsernameList();
        usernamelist.addUser(username, password);
        UsernameListDAO.saveExistingUsernameList(usernamelist);
        
        ToDoListDAO.saveUserToDoList(username, new ToDoList());
    }
    
    public static void seeAllUsersInExistingList() {
        UsernameList usernamelist = UsernameListDAO.loadExistingUsernameList();
        
        usernamelist.printAllUserAndPassword();
    }
    
    public static void runUserMenu() {
        boolean correctUsernameAndPassword = false;
        String username;
        String password;
        
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter your username! >");
            username = sc.next();
            System.out.print("Please enter your password! >");
            password = sc.next();
            System.out.println();
            
            UsernameList usernamelist = UsernameListDAO.loadExistingUsernameList();
                        
            correctUsernameAndPassword = usernamelist.checkUsernamePassword(username, password);
            
            if (!correctUsernameAndPassword) {
                System.out.println("Invalid username or password, please try again!");
            }
            
        } while (!correctUsernameAndPassword);
        
        
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            displayOptionsForUser();
        
            System.out.print("Please enter your option: ");
            option = sc.nextInt();
            System.out.println();
        
        
            switch (option) {
                
                case 1:
                    runOption1(username);
                    break;
                
                case 2:
                    runOption2(username);
                    break;
                
                case 3:
                    runOption3(username);
                    break;
            }
        } while (option != 4);
        
        System.out.println("Have a nice day!");
    }
    
    public static void runOption1(String username) {
        ToDoList list = ToDoListDAO.loadUserToDoList(username);
        System.out.println(list.toString());        
    }
    
    public static void runOption2(String username) {
        ToDoList list = ToDoListDAO.loadUserToDoList(username);
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter due date of to-do item (YYYYMMDD)> ");
        int dateEntered = sc.nextInt();
        
        System.out.print("Please enter description of to-do item> ");
        String descriptionEntered = sc.next();
        
        ItemInList newItem = new ItemInList(dateEntered, descriptionEntered);
        list.addItem(newItem);
        
        ToDoListDAO.saveUserToDoList(username, list);
    }
    
    public static void runOption3(String username) {
        ToDoList newList = new ToDoList();
        ToDoListDAO.saveUserToDoList(username, newList);
    }
}