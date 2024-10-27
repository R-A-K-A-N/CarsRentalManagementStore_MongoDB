/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Cars_DB {
    private static String serverName = "localhost";  
    private static String dbName = "java_car_rental"; 
    private static int portNumber = 27017;  

    public static MongoDatabase getConnection() {
        MongoClient mongoClient = null;
        MongoDatabase database = null;
        
        try {
            mongoClient = MongoClients.create("mongodb://" + serverName + ":" + portNumber);
            
            database = mongoClient.getDatabase(dbName);
        } catch (Exception ex) {
            Logger.getLogger(Cars_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return database;  
    }
}
