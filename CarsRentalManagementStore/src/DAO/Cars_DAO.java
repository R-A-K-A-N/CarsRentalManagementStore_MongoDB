/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Cars_DTO;
import GUI.MenuForm;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Cars_DAO {
    private Stack<Cars_DTO> deletedCars = new Stack<>();
    public ArrayList<Cars_DTO> carsList() {
        ArrayList<Cars_DTO> list = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            for (Document Cardoc : collection.find().sort(new Document("car_id", 1))) { 
                Cars_DTO car = new Cars_DTO(
                        Cardoc.getInteger("car_id"), 
                        Cardoc.getInteger("brand_id"),
                        Cardoc.getString("model"),
                        Cardoc.getString("fuel"),
                        Cardoc.getString("color"),
                        Cardoc.getString("class"),
                        Cardoc.getInteger("passengers"),
                        Cardoc.getString("gearbox"),
                        Cardoc.getInteger("price"),
                        Cardoc.getString("air_conditioning"),
                        Cardoc.getString("air_bag"),
                        Cardoc.getString("sunroof"),
                        Cardoc.getString("heated_seats"),
                        Cardoc.getString("nav_system"),
                        Cardoc.getString("bluetooth"),
                        Cardoc.getString("electric_windows"),
                        Cardoc.getString("gps")
                );
                list.add(car);
            }
        } catch (Exception e) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
        
    public void addCar(Cars_DTO car){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            Document maxCar = collection.find().sort(new Document("car_id", -1)).first();
            int newCarId = (maxCar != null) ? maxCar.getInteger("car_id") + 1 : 1;

            Document carDoc = new Document("car_id", newCarId)
                    .append("brand_id", car.getBrand())
                    .append("model", car.getModel())
                    .append("fuel", car.getFuel())
                    .append("color", car.getColor())
                    .append("class", car.getClass_())
                    .append("passengers", car.getPassengers())
                    .append("gearbox", car.getGearbox())
                    .append("price", car.getPrice())
                    .append("air_conditioning", car.getAir_cond())
                    .append("air_bag", car.getAirbag())
                    .append("sunroof", car.getSunroof())
                    .append("heated_seats", car.getHeated_seats())
                    .append("nav_system", car.getNav_sys())
                    .append("bluetooth", car.getBluetooth())
                    .append("electric_windows", car.getElec_window())
                    .append("gps", car.getGps());

            collection.insertOne(carDoc);
            JOptionPane.showMessageDialog(null, "The New Car Has Been Added", "Add Car", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Car Not Added", "Add Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editCar(Cars_DTO car){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            Document updatedData = new Document("brand_id", car.getBrand())
                    .append("model", car.getModel())
                    .append("fuel", car.getFuel())
                    .append("color", car.getColor())
                    .append("class", car.getClass_())
                    .append("passengers", car.getPassengers())
                    .append("gearbox", car.getGearbox())
                    .append("price", car.getPrice())
                    .append("air_conditioning", car.getAir_cond())
                    .append("air_bag", car.getAirbag())
                    .append("sunroof", car.getSunroof())
                    .append("heated_seats", car.getHeated_seats())
                    .append("nav_system", car.getNav_sys())
                    .append("bluetooth", car.getBluetooth())
                    .append("electric_windows", car.getElec_window())
                    .append("gps", car.getGps());

            collection.updateOne(Filters.eq("car_id", car.getCar_id()), new Document("$set", updatedData));
            JOptionPane.showMessageDialog(null, "The Car Has Been Updated", "Update Car", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Car Not Updated", "Update Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteCar(int car_id){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            Document doc = collection.find(Filters.eq("car_id", car_id)).first();
            if (doc != null) {
                Cars_DTO deletedCar = new Cars_DTO(
                    doc.getInteger("car_id"),
                    doc.getInteger("brand_id"),
                    doc.getString("model"),
                    doc.getString("fuel"),
                    doc.getString("color"),
                    doc.getString("class"),
                    doc.getInteger("passengers"),
                    doc.getString("gearbox"),
                    doc.getInteger("price"),
                    doc.getString("air_conditioning"),
                    doc.getString("air_bag"),
                    doc.getString("sunroof"),
                    doc.getString("heated_seats"),
                    doc.getString("nav_system"),
                    doc.getString("bluetooth"),
                    doc.getString("electric_windows"),
                    doc.getString("gps")
                );
                deletedCars.push(deletedCar);  
            }

            collection.deleteOne(Filters.eq("car_id", car_id));
            JOptionPane.showMessageDialog(null, "The Car Has Been Deleted", "Delete Car", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Car Not Deleted", "Delete Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Cars_DTO undoDeleteCar() {
        if (!deletedCars.isEmpty()) {
            return deletedCars.pop();  
        }
        return null;
    }

    public void restoreCar(Cars_DTO car) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");
            
            Document carDoc = new Document("car_id", car.getCar_id())
                    .append("brand_id", car.getBrand())
                    .append("model", car.getModel())
                    .append("fuel", car.getFuel())
                    .append("color", car.getColor())
                    .append("class", car.getClass_())
                    .append("passengers", car.getPassengers())
                    .append("gearbox", car.getGearbox())
                    .append("price", car.getPrice())
                    .append("air_conditioning", car.getAir_cond())
                    .append("air_bag", car.getAirbag())
                    .append("sunroof", car.getSunroof())
                    .append("heated_seats", car.getHeated_seats())
                    .append("nav_system", car.getNav_sys())
                    .append("bluetooth", car.getBluetooth())
                    .append("electric_windows", car.getElec_window())
                    .append("gps", car.getGps());
            
            collection.insertOne(carDoc);
            JOptionPane.showMessageDialog(null, "The Car Has Been Restored", "Restore Car", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Car Not Restored", "Restore Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Cars_DTO> getData(String query) { 
        ArrayList<Cars_DTO> carList = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            Document filter = Document.parse(query);

            FindIterable<Document> documents = collection.find(filter).sort(new Document("car_id", 1));

            for (Document doc : documents) {
                Cars_DTO car = new Cars_DTO(
                    doc.getInteger("car_id"), 
                    doc.getInteger("brand_id"),
                    doc.getString("model"),
                    doc.getString("fuel"),
                    doc.getString("color"),
                    doc.getString("class"),
                    doc.getInteger("passengers"),
                    doc.getString("gearbox"),
                    doc.getInteger("price"),
                    doc.getString("air_conditioning"),
                    doc.getString("air_bag"),
                    doc.getString("sunroof"),
                    doc.getString("heated_seats"),
                    doc.getString("nav_system"),
                    doc.getString("bluetooth"),
                    doc.getString("electric_windows"),
                    doc.getString("gps")
                );
                carList.add(car);
            }
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }
 
    public Cars_DTO getCarById(int car_id){
        Cars_DTO car = null;

        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");
            
            Document doc = collection.find(Filters.eq("car_id", car_id)).first();

            if (doc != null) {
                car = new Cars_DTO(
                        doc.getInteger("car_id"),
                        doc.getInteger("brand_id"),
                        doc.getString("model"),
                        doc.getString("fuel"),
                        doc.getString("color"),
                        doc.getString("class"),
                        doc.getInteger("passengers"),
                        doc.getString("gearbox"),
                        doc.getInteger("price"),
                        doc.getString("air_conditioning"),
                        doc.getString("air_bag"),
                        doc.getString("sunroof"),
                        doc.getString("heated_seats"),
                        doc.getString("nav_system"),
                        doc.getString("bluetooth"),
                        doc.getString("electric_windows"),
                        doc.getString("gps")
                );
            }else {
                JOptionPane.showMessageDialog(null, "No Car With This ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return car;
    }
    
    public ArrayList<Cars_DTO> carsByBrandList(int car_brand_id) {
        ArrayList<Cars_DTO> carList = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("cars");

            for (Document Cardoc : collection.find(Filters.eq("brand_id", car_brand_id)).sort(new Document("car_id", 1))) { 
                Cars_DTO car = new Cars_DTO();
                car.setCar_id(Cardoc.getInteger("car_id"));
                car.setBrand(Cardoc.getInteger("brand_id")); 
                car.setModel(Cardoc.getString("model"));
                car.setFuel(Cardoc.getString("fuel"));
                car.setColor(Cardoc.getString("color"));
                car.setPrice(Cardoc.getInteger("price")); 

                carList.add(car);
            }
        } catch (Exception e) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return carList;
    }
}
