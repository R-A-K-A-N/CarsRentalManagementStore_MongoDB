/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Locations_DTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
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
public class Locations_DAO {
    private Stack<Locations_DTO> actionHistory = new Stack<>();
    public void addLocation(Locations_DTO location){
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("locations");

            Document maxLocation = collection.find().sort(new Document("location_id", -1)).first();
            int newLocationId = 1; 
            if (maxLocation != null) {
                newLocationId = maxLocation.getInteger("location_id") + 1;
            }

            Document existingLocation = collection.find(new Document("location_id", newLocationId)).first();
            if (existingLocation != null) {
                JOptionPane.showMessageDialog(null, "Location ID already exists", "Add Location Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Document locationDoc = new Document("location_id", newLocationId)
                    .append("city", location.getCity())
                    .append("address", location.getAddress());

            collection.insertOne(locationDoc);
            JOptionPane.showMessageDialog(null, "The New Location Has Been Added", "Add Location", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Location Not Added", "Add Location", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateLocation(Locations_DTO location){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("locations");

            Document updatedData = new Document("city", location.getCity())
                    .append("address", location.getAddress());

            collection.updateOne(Filters.eq("location_id", location.getLocation_id()), new Document("$set", updatedData));
            JOptionPane.showMessageDialog(null, "The Location Has Been Updated", "Update Location", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Location Not Updated", "Update Location", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteLocation(int location_id){
        try {
            Locations_DTO locationToDelete = getLocationById(location_id);
            if (locationToDelete != null) {
                MongoDatabase database = Cars_DB.getConnection(); 
                MongoCollection<Document> collection = database.getCollection("locations");

                collection.deleteOne(Filters.eq("location_id", location_id));
                actionHistory.push(locationToDelete); 
                JOptionPane.showMessageDialog(null, "The Location Has Been Deleted", "Delete Location", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Location Not Found", "Delete Location", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Location Not Deleted", "Delete Location", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Locations_DTO undoLocation() {
        if (!actionHistory.isEmpty()) {
            Locations_DTO lastAction = actionHistory.pop(); 
            if (lastAction != null) {
                addLocation(lastAction);
                return lastAction;
            }
        }
        return null;
    }
    
    // create a function to return a resultset
    public ArrayList<Locations_DTO> getData(String query) {
        ArrayList<Locations_DTO> locationList = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("locations");

            if (query != null && !query.isEmpty()) {
                int locationId = Integer.parseInt(query);
                Document doc = collection.find(Filters.eq("location_id", locationId)).first();
                if (doc != null) {
                    Locations_DTO location = new Locations_DTO(
                            doc.getInteger("location_id"),
                            doc.getString("city"),
                            doc.getString("address")
                    );
                    locationList.add(location);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locationList;
    }
    
    public ArrayList<Locations_DTO> locationsList() {
        ArrayList<Locations_DTO> list = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("locations");

            for (Document doc : collection.find().sort(new Document("location_id", 1))) {
                Locations_DTO location = new Locations_DTO(
                        doc.getInteger("location_id"),
                        doc.getString("city"),
                        doc.getString("address")
                );
                list.add(location);
            }
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Locations_DTO getLocationById(int location_id){
        Locations_DTO location = null;
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("locations");

            Document doc = collection.find(Filters.eq("location_id", location_id)).first();

            if (doc != null) {
                location = new Locations_DTO(
                        doc.getInteger("location_id"),
                        doc.getString("city"),
                        doc.getString("address")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }
    
    public ArrayList<Locations_DTO> locationsListByCity(String city) {
        ArrayList<Locations_DTO> loclist = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("locations");
            
            for (Document doc : collection.find(new Document("city", city))) {
                Locations_DTO location = new Locations_DTO(
                        doc.getInteger("location_id"),
                        doc.getString("city"),
                        doc.getString("address")
                );
                loclist.add(location);
            }
        } catch (Exception ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loclist;
    }
}
