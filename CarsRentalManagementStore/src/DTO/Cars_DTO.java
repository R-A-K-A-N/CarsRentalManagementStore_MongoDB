/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import DTO.Brands_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Cars_DTO {
    private int car_id; 
    private int brand;
    private String model;
    private String fuel;
    private String color;
    private String _class_;
    private int passengers;
    private String gearbox;
    private int price;
    private String air_cond;
    private String airbag;
    private String sunroof;
    private String heated_seats;
    private String nav_sys;
    private String bluetooth;
    private String elec_window;
    private String gps;

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClass_() {
        return _class_;
    }

    public void setClass_(String _class_) {
        this._class_ = _class_;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAir_cond() {
        return air_cond;
    }

    public void setAir_cond(String air_cond) {
        this.air_cond = air_cond;
    }

    public String getAirbag() {
        return airbag;
    }

    public void setAirbag(String airbag) {
        this.airbag = airbag;
    }

    public String getSunroof() {
        return sunroof;
    }

    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    public String getHeated_seats() {
        return heated_seats;
    }

    public void setHeated_seats(String heated_seats) {
        this.heated_seats = heated_seats;
    }

    public String getNav_sys() {
        return nav_sys;
    }

    public void setNav_sys(String nav_sys) {
        this.nav_sys = nav_sys;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getElec_window() {
        return elec_window;
    }

    public void setElec_window(String elec_window) {
        this.elec_window = elec_window;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Cars_DTO() {}
    
    public Cars_DTO(int car_id, int brand, String model, String fuel, String color, String _class_, int passengers, String gearbox, int price, String air_cond, String airbag, String sunroof, String heated_seats, String nav_sys, String bluetooth, String elec_window, String gps) {
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.color = color;
        this._class_ = _class_;
        this.passengers = passengers;
        this.gearbox = gearbox;
        this.price = price;
        this.air_cond = air_cond;
        this.airbag = airbag;
        this.sunroof = sunroof;
        this.heated_seats = heated_seats;
        this.nav_sys = nav_sys;
        this.bluetooth = bluetooth;
        this.elec_window = elec_window;
        this.gps = gps;
    }
    
}
