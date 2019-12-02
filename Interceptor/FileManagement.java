package Interceptor;

import Maze.TrappedPlus;

import java.io.*;
import java.util.ArrayList;


public class FileManagement {
    public static Users loadUserFile() {
        Users users = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(FileManagement.class.getResource("/text/login.ser").getFile());
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (Users) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            return users;

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return users;

    }

    public static void saveAllToFile(Users users) {

        String filename = "login.ser";


        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(FileManagement.class.getResource("/text/" + filename).getFile());
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(users);

            out.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveAToFile(User user) {

        Users users=loadUserFile();
        if(users==null)
            users=new Users();
        users.getUsers().add(user);
        saveAllToFile(users);
    }
    public static User getUser(User key){
       User u=null;
       Users users=loadUserFile();
        for (User user:users.getUsers()) {
            if(user.getUsername().equalsIgnoreCase(key.getUsername()) && user.getPassword().equalsIgnoreCase(key.getPassword()))
                u=user;

        }
       return  u;

    }
}
