/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.Locations_DAO;
import DTO.Locations_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Locations_BUS {
    Locations_DAO locationDAO = new Locations_DAO();

    public ArrayList<Locations_DTO> getAllLocations() {
        return locationDAO.locationsList();
    }

    public void addLocation(int location_id, String city, String address) {
        Locations_DTO location = new Locations_DTO();
        location.setLocation_id(location_id);
        location.setCity(city);
        location.setAddress(address);
        locationDAO.addLocation(location);
    }

    public void updateLocation(int location_id, String city, String address) {
        Locations_DTO location = new Locations_DTO();
        location.setLocation_id(location_id);
        location.setCity(city);
        location.setAddress(address);
        locationDAO.updateLocation(location);
    }

    public void deleteLocation(int location_id) {
        locationDAO.deleteLocation(location_id);
    }
    
    public void undoLocation() {
        locationDAO.undoLocation();
    }
    
    public Locations_DTO getLocationById(int location_id) {
        return locationDAO.getLocationById(location_id);
    }
    
    public ArrayList<Locations_DTO> getData(String query) {
        return locationDAO.getData(query);
    }
    
    public ArrayList<Locations_DTO> locationsListByCity(String city) {
        return locationDAO.locationsListByCity(city);
    }
}
