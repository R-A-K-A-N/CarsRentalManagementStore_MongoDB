/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class Booking_DTO {

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    int book_id;
    int car_id;
    int customer_id;
    String pickup_city;
    String pickup_address;
    String pickup_date;
    String pickup_time;
    String dropoff_city;
    String dropoff_address;
    String dropoff_date;
    String dropoff_time;
    int total_price;

    public Booking_DTO() {
    }

    public Booking_DTO(int book_id, int car_id, int customer_id, String pickup_city, String pickup_address, String pickup_date, String pickup_time, String dropoff_city, String dropoff_address, String dropoff_date, String dropoff_time, int total_price) {
        this.book_id = book_id;
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.pickup_city = pickup_city;
        this.pickup_address = pickup_address;
        this.pickup_date = pickup_date;
        this.pickup_time = pickup_time;
        this.dropoff_city = dropoff_city;
        this.dropoff_address = dropoff_address;
        this.dropoff_date = dropoff_date;
        this.dropoff_time = dropoff_time;
        this.total_price = total_price;
    }


    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getPickup_city() {
        return pickup_city;
    }

    public void setPickup_city(String pickup_city) {
        this.pickup_city = pickup_city;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getDropoff_city() {
        return dropoff_city;
    }

    public void setDropoff_city(String dropoff_city) {
        this.dropoff_city = dropoff_city;
    }

    public String getDropoff_address() {
        return dropoff_address;
    }

    public void setDropoff_address(String dropoff_address) {
        this.dropoff_address = dropoff_address;
    }

    public String getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(String dropoff_date) {
        this.dropoff_date = dropoff_date;
    }

    public String getDropoff_time() {
        return dropoff_time;
    }

    public void setDropoff_time(String dropoff_time) {
        this.dropoff_time = dropoff_time;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
    
    
}
