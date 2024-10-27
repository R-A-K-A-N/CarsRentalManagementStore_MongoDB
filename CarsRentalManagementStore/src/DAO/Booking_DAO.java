/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.Booking_BUS;
import DTO.Booking_DTO;
import GUI.MenuForm;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Booking_DAO {
    private Booking_DTO lastDeletedBooking = null;
    public void addNewBooking(Booking_DTO booking) {
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("reservation");

            Document maxBooking = collection.find().sort(new Document("book_id", -1)).first();
            int newBookingId = (maxBooking != null) ? maxBooking.getInteger("book_id") + 1 : 1;
            booking.setBook_id(newBookingId);

            Document existingBooking = collection.find(new Document("car_id", booking.getCar_id())
                    .append("pickup_date", booking.getPickup_date())
                    .append("pickup_time", booking.getPickup_time())).first();

            if (existingBooking != null) {
                JOptionPane.showMessageDialog(null, "This car is already booked for the selected date and time", "Booking Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Document doc = new Document("book_id", newBookingId)
                        .append("car_id", booking.getCar_id())
                        .append("customer_id", booking.getCustomer_id())
                        .append("pickup_city", booking.getPickup_city())
                        .append("pickup_address", booking.getPickup_address())
                        .append("pickup_date", booking.getPickup_date())
                        .append("pickup_time", booking.getPickup_time())
                        .append("dropoff_city", booking.getDropoff_city())
                        .append("dropoff_address", booking.getDropoff_address())
                        .append("dropoff_date", booking.getDropoff_date())
                        .append("dropoff_time", booking.getDropoff_time())
                        .append("total_price", booking.getTotal_price());

                collection.insertOne(doc);
                JOptionPane.showMessageDialog(null, "This Car Has Been Booked", "Booking", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeBooking(int book_id) {
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("reservation");

            Document booking = collection.find(new Document("book_id", book_id)).first();

            if (booking == null) {
                JOptionPane.showMessageDialog(null, "No such reservation found", "Delete Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String dropoffDate = booking.getString("dropoff_date");
                if (isDateInPast(dropoffDate)) {
                    JOptionPane.showMessageDialog(null, "Cannot delete past reservations", "Delete Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    lastDeletedBooking = new Booking_DTO(
                        booking.getInteger("book_id"),
                        booking.getInteger("car_id"),
                        booking.getInteger("customer_id"),
                        booking.getString("pickup_city"),
                        booking.getString("pickup_address"),
                        booking.getString("pickup_date"),
                        booking.getString("pickup_time"),
                        booking.getString("dropoff_city"),
                        booking.getString("dropoff_address"),
                        booking.getString("dropoff_date"),
                        booking.getString("dropoff_time"),
                        booking.getInteger("total_price")
                    );

                    collection.deleteOne(new Document("book_id", book_id));
                    JOptionPane.showMessageDialog(null, "The Reservation Has Been Deleted", "Delete Reservation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void undoRemoveBooking(int book_id) {
        try {
            if (lastDeletedBooking == null) {
                JOptionPane.showMessageDialog(null, "No deleted reservation to undo", "Undo Error", JOptionPane.ERROR_MESSAGE);
            } else {
                MongoDatabase database = Cars_DB.getConnection();
                MongoCollection<Document> collection = database.getCollection("reservation");

                Document doc = new Document("book_id", lastDeletedBooking.getBook_id())
                        .append("car_id", lastDeletedBooking.getCar_id())
                        .append("customer_id", lastDeletedBooking.getCustomer_id())
                        .append("pickup_city", lastDeletedBooking.getPickup_city())
                        .append("pickup_address", lastDeletedBooking.getPickup_address())
                        .append("pickup_date", lastDeletedBooking.getPickup_date())
                        .append("pickup_time", lastDeletedBooking.getPickup_time())
                        .append("dropoff_city", lastDeletedBooking.getDropoff_city())
                        .append("dropoff_address", lastDeletedBooking.getDropoff_address())
                        .append("dropoff_date", lastDeletedBooking.getDropoff_date())
                        .append("dropoff_time", lastDeletedBooking.getDropoff_time())
                        .append("total_price", lastDeletedBooking.getTotal_price());

                collection.insertOne(doc);
                JOptionPane.showMessageDialog(null, "The Reservation Has Been Restored", "Undo", JOptionPane.INFORMATION_MESSAGE);

                lastDeletedBooking = null;
            }
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isDateInPast(String date) {
        LocalDate today = LocalDate.now();
        LocalDate dropoffDate = LocalDate.parse(date);
        return dropoffDate.isBefore(today);
    }

    public void editBooking(Booking_DTO booking) {
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("reservation");

            Document existingBooking = collection.find(new Document("book_id", booking.getBook_id())).first();

            if (existingBooking == null) {
                JOptionPane.showMessageDialog(null, "No such reservation found", "Update Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String dropoffDate = existingBooking.getString("dropoff_date");
                if (isDateInPast(dropoffDate)) {
                    JOptionPane.showMessageDialog(null, "Cannot edit past reservations", "Update Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Document updateDoc = new Document("$set", new Document("car_id", booking.getCar_id())
                            .append("customer_id", booking.getCustomer_id())
                            .append("pickup_city", booking.getPickup_city())
                            .append("pickup_address", booking.getPickup_address())
                            .append("pickup_date", booking.getPickup_date())
                            .append("pickup_time", booking.getPickup_time())
                            .append("dropoff_city", booking.getDropoff_city())
                            .append("dropoff_address", booking.getDropoff_address())
                            .append("dropoff_date", booking.getDropoff_date())
                            .append("dropoff_time", booking.getDropoff_time())
                            .append("total_price", booking.getTotal_price()));

                    collection.updateOne(new Document("id", booking.getBook_id()), updateDoc);
                    JOptionPane.showMessageDialog(null, "The Reservation Has Been Updated", "Update Reservation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Booking_DTO> bookingList() {
        ArrayList<Booking_DTO> booklist = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("reservation");

            for (Document doc : collection.find().sort(new Document("book_id", 1))) {
                Integer bookId = doc.getInteger("book_id");
                if (bookId == null) {
                    System.out.println("Warning: book_id is null for document: " + doc.toJson());
                    continue; 
                }

                Integer carId = doc.getInteger("car_id");
                if (carId == null) {
                    System.out.println("Warning: car_id is null for document: " + doc.toJson());
                    continue; 
                }

                Integer customerId = doc.getInteger("customer_id");
                if (customerId == null) {
                    System.out.println("Warning: customer_id is null for document: " + doc.toJson());
                    continue; 
                }

                String pickupCity = doc.getString("pickup_city");
                String pickupAddress = doc.getString("pickup_address");
                String pickupDate = doc.getString("pickup_date");
                String pickupTime = doc.getString("pickup_time");
                String dropoffCity = doc.getString("dropoff_city");
                String dropoffAddress = doc.getString("dropoff_address");
                String dropoffDate = doc.getString("dropoff_date");
                String dropoffTime = doc.getString("dropoff_time");
                Integer totalPrice = doc.getInteger("total_price");

                if (totalPrice == null) {
                    System.out.println("Warning: total_price is null for document: " + doc.toJson());
                    continue; 
                }

                Booking_DTO book = new Booking_DTO(
                    bookId,
                    carId,
                    customerId,
                    pickupCity,
                    pickupAddress,
                    pickupDate,
                    pickupTime,
                    dropoffCity,
                    dropoffAddress,
                    dropoffDate,
                    dropoffTime,
                    totalPrice
                );
                booklist.add(book);
            }
            System.out.println("Number of bookings found: " + booklist.size());
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booklist;
    }
    
    public List<Booking_DTO> getData() {
        List<Booking_DTO> booklist = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("reservation");

            for (Document doc : collection.find().sort(new Document("book_id", 1))) { 
                Booking_DTO book = new Booking_DTO(
                    doc.getInteger("book_id"),
                    doc.getInteger("car_id"),
                    doc.getInteger("customer_id"),
                    doc.getString("pickup_city"),
                    doc.getString("pickup_address"),
                    doc.getString("pickup_date"),
                    doc.getString("pickup_time"),
                    doc.getString("dropoff_city"),
                    doc.getString("dropoff_address"),
                    doc.getString("dropoff_date"),
                    doc.getString("dropoff_time"),
                    doc.getInteger("total_price")
                );
                booklist.add(book);
            }
        } catch (Exception ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booklist;
    }
}
