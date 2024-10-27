/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.Cars_DAO;
import DTO.CarImage_DTO;
import DTO.Cars_DTO;
import java.util.*;
import java.util.logging.*;
import DTO.Locations_DTO;
import org.bson.Document;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Cars_BUS {
    Cars_DAO carDAO = new Cars_DAO();
    
    public ArrayList<Cars_DTO> getAllCars() {
        return carDAO.carsList();
    }
    
    public void addCar(int car_id, int brand, String model, String fuel, String color, String _class_, int passengers, String gearbox, int price, String air_cond, String airbag, String sunroof, String heated_seats, String nav_sys, String bluetooth, String elec_window, String gps){
        Cars_DTO car = new Cars_DTO();
        car.setCar_id(car_id);
        car.setBrand(brand);
        car.setModel(model);
        car.setFuel(fuel);
        car.setColor(color);
        car.setClass_(_class_);
        car.setPassengers(passengers);
        car.setGearbox(gearbox);
        car.setPrice(price);
        car.setAir_cond(air_cond);
        car.setAirbag(airbag);
        car.setSunroof(sunroof);
        car.setHeated_seats(heated_seats);
        car.setNav_sys(nav_sys);
        car.setBluetooth(bluetooth);
        car.setElec_window(elec_window);
        car.setGps(gps);
        
        carDAO.addCar(car);
    }

    public void editCar(int car_id, int brand, String model, String fuel, String color, String _class_, int passengers, String gearbox, int price, String air_cond, String airbag, String sunroof, String heated_seats, String nav_sys, String bluetooth, String elec_window, String gps){
        Cars_DTO car = new Cars_DTO();
        car.setCar_id(car_id);
        car.setBrand(brand);
        car.setModel(model);
        car.setFuel(fuel);
        car.setColor(color);
        car.setClass_(_class_);
        car.setPassengers(passengers);
        car.setGearbox(gearbox);
        car.setPrice(price);
        car.setAir_cond(air_cond);
        car.setAirbag(airbag);
        car.setSunroof(sunroof);
        car.setHeated_seats(heated_seats);
        car.setNav_sys(nav_sys);
        car.setBluetooth(bluetooth);
        car.setElec_window(elec_window);
        car.setGps(gps);
        carDAO.editCar(car);
    }

    public void deleteCar(int car_id){
        carDAO.deleteCar(car_id);
    }
    
    public Cars_DTO undoDeleteCar() {
        return carDAO.undoDeleteCar();
    }

    public void restoreCar(Cars_DTO car) {
        carDAO.restoreCar(car);
    }
     
    public ArrayList<Cars_DTO> getData(String query) {
        return carDAO.getData(query);
    }
        
    public Cars_DTO getCarById(int car_id) {
        return carDAO.getCarById(car_id);
    }
    
    public ArrayList<Cars_DTO> carsByBrandList(int car_brand_id) {
        return carDAO.carsByBrandList(car_brand_id);
    }
}
