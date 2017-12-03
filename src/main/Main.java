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
        System.out.println("Hello! This is your To-Do List!");
        System.out.println("Option 1: View your list");
        System.out.println("Option 2: Add a new item to the list");
        System.out.println("Option 3: Delete the existing list and create a new one");
        System.out.println("Option 4: Exit");
        System.out.println();
    }
    
    public static void displayOptionsForAdmin() {
        System.out.println("Option 1: Create a new username list.");
        System.out.println("Option 2: Add a new user to the existing username list.");
        System.out.println("Option 3: View all existing users");
        System.out.println("Option 4: Exit");
    }
    
    public static boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! Please enter your username!");
        String username = sc.next();
        
        System.out.println("Your password?");
        String password = sc.next();
        
        return UsernameListDAO.loadExistingUsernameList().checkUsernamePassword(username, password);
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
            
            case 2:
                runAdminMenu();
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
    }
    
    public static void seeAllUsersInExistingList() {
        UsernameList usernamelist = UsernameListDAO.loadExistingUsernameList();
        Map<String, String> list = usernamelist.getUserList();
        for (String user: list.keySet()) {
            System.out.println(user + "   " + list.get(user));
        }
    }
    
    public static void runUserMenu() {
        
        int option;
        do {
            Scanner sc = new Scanner(System.in);
            displayOptionsForUser();
        
            System.out.print("Please enter your option: ");
            option = sc.nextInt();
            System.out.println();
        
        
            switch (option) {
                
                case 1:
                    runOption1();
                    break;
                
                case 2:
                    runOption2();
                    break;
                
                case 3:
                    runOption3();
                    break;
            }
        } while (option != 4);
        
        System.out.println("Have a nice day!");
    }
    
    public static void runOption1() {
        ToDoList list = ToDoListDAO.loadUserToDoList("zhou");
        System.out.println(list.toString());        
    }
    
    public static void runOption2() {
        ToDoList list = ToDoListDAO.loadUserToDoList("zhou");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter due date of to-do item (YYYYMMDD)> ");
        int dateEntered = sc.nextInt();
        
        System.out.print("Please enter description of to-do item> ");
        String descriptionEntered = sc.next();
        
        ItemInList newItem = new ItemInList(dateEntered, descriptionEntered);
        list.addItem(newItem);
        
        ToDoListDAO.saveUserToDoList("zhou" ,list);
    }
    
    public static void runOption3() {
        ToDoList newList = new ToDoList();
        ToDoListDAO.saveUserToDoList("zhou", newList);
    }
}