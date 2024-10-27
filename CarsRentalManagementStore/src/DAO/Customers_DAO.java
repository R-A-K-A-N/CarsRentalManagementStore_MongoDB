package DAO;

import DTO.Customers_DTO;
import GUI.MenuForm;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.Stack;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Customers_DAO {
    private Customers_DTO lastDeletedCustomer = null;
    public void addCustomer(Customers_DTO customers) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("customers");

            Document maxCustomer = collection.find().sort(new Document("customer_id", -1)).first();
            int newCustomerId = (maxCustomer != null) ? maxCustomer.getInteger("customer_id") + 1 : 1;

            Document customerDoc = new Document("customer_id", newCustomerId)
                    .append("fullname", customers.getFullname())
                    .append("birthday", customers.getBirthday())
                    .append("phone", customers.getPhone())
                    .append("email", customers.getEmail())
                    .append("address", customers.getAddress());

            collection.insertOne(customerDoc);
            JOptionPane.showMessageDialog(null, "The New Customer Has Been Added", "Add Customer", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Customer Not Added", "Add Customer", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editCustomer(Customers_DTO customers) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("customers");

            Document updatedData = new Document("fullname", customers.getFullname())
                    .append("birthday", customers.getBirthday())
                    .append("phone", customers.getPhone())
                    .append("email", customers.getEmail())
                    .append("address", customers.getAddress());

            collection.updateOne(Filters.eq("customer_id", customers.getCustomer_id()), new Document("$set", updatedData));
            JOptionPane.showMessageDialog(null, "The Customer Info Has Been Updated", "Update Customer", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Customer Info Not Updated", "Update Customer", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeCustomer(int customer_id) {
        Customers_DTO customer = getCustomerById(customer_id); 
        if (customer != null) {
            lastDeletedCustomer = customer; 
            try {
                MongoDatabase database = Cars_DB.getConnection(); 
                MongoCollection<Document> collection = database.getCollection("customers");

                collection.deleteOne(Filters.eq("customer_id", customer_id));
                JOptionPane.showMessageDialog(null, "The Customer Has Been Deleted", "Delete Customer", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "Customer Not Deleted", "Delete Customer", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void undoCustomer() {
        if (lastDeletedCustomer != null) {
            addCustomer(lastDeletedCustomer); 
            lastDeletedCustomer = null; 
            JOptionPane.showMessageDialog(null, "Undo Successful: Customer Restored", "Undo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No Action to Undo", "Undo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<Customers_DTO> getData(String query) {
        ArrayList<Customers_DTO> customerList = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("customers");

            if (query != null && !query.isEmpty()) {
                int customerId = Integer.parseInt(query);
                Document doc = collection.find(Filters.eq("customer_id", customerId)).first();
                if (doc != null) {
                    Customers_DTO customer = new Customers_DTO(
                            doc.getInteger("customer_id"), 
                            doc.getString("fullname"),
                            doc.getString("birthday"),
                            doc.getString("phone"),
                            doc.getString("email"),
                            doc.getString("address")
                    );
                    customerList.add(customer);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return customerList;
    }
    
    public ArrayList<Customers_DTO> customersList() {
        ArrayList<Customers_DTO> list = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("customers");

            for (Document doc : collection.find().sort(new Document("customer_id", 1))) {
                Customers_DTO customer = new Customers_DTO(
                        doc.getInteger("customer_id"), 
                        doc.getString("fullname"),
                        doc.getString("birthday"),
                        doc.getString("phone"),
                        doc.getString("email"),
                        doc.getString("address")
                );
                list.add(customer);
            }
        } catch (Exception e) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public Customers_DTO getCustomerById(int customer_id) {
        Customers_DTO customer = null;
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("customers");

            Document doc = collection.find(Filters.eq("customer_id", customer_id)).first();

            if (doc != null) {
                customer = new Customers_DTO(
                        doc.getInteger("customer_id"), 
                        doc.getString("fullname"),
                        doc.getString("birthday"),
                        doc.getString("phone"),
                        doc.getString("email"),
                        doc.getString("address")
                );
            }
        } catch (Exception e) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return customer;
    }
}
