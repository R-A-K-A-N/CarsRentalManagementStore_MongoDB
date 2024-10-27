/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.Booking_DAO;
import DTO.Booking_DTO;
import GUI.MenuForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Booking_BUS {
    Booking_DAO bookingDAO = new Booking_DAO();
    
    public void addNewBooking(int car_id, int customer_id, String pickup_city, String pickup_address, String pickup_date, String pickup_time, String dropoff_city, String dropoff_address, String dropoff_date, String dropoff_time, int total_price) {
        try {
            Booking_DTO booking = new Booking_DTO();
            booking.setCar_id(car_id);
            booking.setCustomer_id(customer_id);
            booking.setPickup_city(pickup_city);
            booking.setPickup_address(pickup_address);
            booking.setPickup_date(pickup_date);
            booking.setPickup_time(pickup_time);
            booking.setDropoff_city(dropoff_city);
            booking.setDropoff_address(dropoff_address);
            booking.setDropoff_date(dropoff_date);
            booking.setDropoff_time(dropoff_time);
            booking.setTotal_price(total_price);
            bookingDAO.addNewBooking(booking);
        } catch (Exception ex) {
            Logger.getLogger(Booking_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error adding booking: " + ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editBooking(int book_id, int car_id, int customer_id, String pickup_city, String pickup_address, String pickup_date, String pickup_time, String dropoff_city, String dropoff_address, String dropoff_date, String dropoff_time, int total_price) {
        try {
            Booking_DTO booking = new Booking_DTO();
            booking.setBook_id(book_id);
            booking.setCar_id(car_id);
            booking.setCustomer_id(customer_id);
            booking.setPickup_city(pickup_city);
            booking.setPickup_address(pickup_address);
            booking.setPickup_date(pickup_date);
            booking.setPickup_time(pickup_time);
            booking.setDropoff_city(dropoff_city);
            booking.setDropoff_address(dropoff_address);
            booking.setDropoff_date(dropoff_date);
            booking.setDropoff_time(dropoff_time);
            booking.setTotal_price(total_price);
            bookingDAO.editBooking(booking);
        } catch (Exception ex) {
            Logger.getLogger(Booking_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error editing booking: " + ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeBooking(int book_id) {
        try {
            bookingDAO.removeBooking(book_id);
        } catch (Exception ex) {
            Logger.getLogger(Booking_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error removing booking: " + ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void undoRemoveBooking(int book_id) {
        try {
            bookingDAO.undoRemoveBooking(book_id);
        } catch (Exception ex) {
            Logger.getLogger(Booking_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error undoing booking removal: " + ex.getMessage(), "Undo Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Booking_DTO> bookingList() {
        return bookingDAO.bookingList();
    }
    public List<Booking_DTO> getData() {
        return bookingDAO.getData();
    }
}
