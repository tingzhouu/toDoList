package main;

import java.util.*;
import java.io.*;

import todolist.*;

public class Main {
    
    public static void main (String[] args) {
        start();
    }
    
    public static ToDoList loadFile() {
        ToDoList output = null;
        
        try { 
            FileInputStream fin = new FileInputStream("data/zhou.txt");
            ObjectInputStream oin = new ObjectInputStream(fin);
                output = (ToDoList) oin.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return output;
    }
    
    public static void saveFile(ToDoList list) {
        try {
            FileOutputStream fout = new FileOutputStream("data/zhou.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            
            out.writeObject(list);
            out.flush();
            System.out.println("List saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void displayOptions() {
        System.out.println("Hello! This is your To-Do List!");
        System.out.println("Option 1: View your list");
        System.out.println("Option 2: Add a new item to the list");
        System.out.println("Option 3: Delete the existing list and create a new one");
        System.out.println("Option 4: Exit");
        System.out.println();
    }
    
    public static void start() {
        int option;
        
        do {
            Scanner sc = new Scanner(System.in);
            displayOptions();
        
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
        ToDoList list = loadFile();
        System.out.println(list.toString());        
    }
    
    public static void runOption2() {
        ToDoList list = loadFile();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter due date of to-do item (YYYYMMDD)> ");
        int dateEntered = sc.nextInt();
        
        System.out.print("Please enter description of to-do item> ");
        String descriptionEntered = sc.next();
        
        int ddOfDate = dateEntered % 100 - 1900;
        int mmOfDate = (dateEntered / 100) % 100;
        int yyyyOfDate = dateEntered / 10000;
        
        Calendar dateForTimeStamp = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(yyyyOfDate, mmOfDate, ddOfDate);
        
        ItemInList newItem = new ItemInList(dateForTimeStamp, dueDate, descriptionEntered);
        list.addItem(newItem);
        
        saveFile(list);
    }
    
    public static void runOption3() {
        ToDoList newList = new ToDoList();
        saveFile(newList);
    }
}