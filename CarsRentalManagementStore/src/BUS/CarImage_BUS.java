/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CarImage_DAO;
import DTO.CarImage_DTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CarImage_BUS {
    CarImage_DAO carImageDAO = new CarImage_DAO();
    
    public void addCarImage(int car_id, byte[] car_image) {
        CarImage_DTO carImageDTO = new CarImage_DTO(); 
        carImageDTO.setCar_id(car_id); 
        carImageDTO.setCar_img(car_image); 

        carImageDAO.addCarImage(carImageDTO); 
    }
    
    public ArrayList<CarImage_DTO> carImagesList(int car_id) {
        return carImageDAO.carImagesList(car_id);
    }
    
    public void removeCarImage(int img_id){
        carImageDAO.removeCarImage(img_id); 
    }
    
    public void undoRemoveCarImage() {
        carImageDAO.undoRemoveCarImage(); 
    }
}
