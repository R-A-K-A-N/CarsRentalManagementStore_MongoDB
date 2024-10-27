/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CarImage_DTO;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CarImage_DAO {
    private CarImage_DTO lastRemovedImage = null;
    public ArrayList<CarImage_DTO> carImagesList(int car_id) {
        ArrayList<CarImage_DTO> images = new ArrayList<>();

        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("car_images");

            for (Document doc : collection.find(Filters.eq("car_id", car_id)).sort(new Document("img_id", 1))) { 
                CarImage_DTO car_image = new CarImage_DTO();
                car_image.setImg_id(doc.getInteger("img_id"));
                car_image.setCar_id(doc.getInteger("car_id"));

                String encodedImage = doc.getString("car_img");
                byte[] decodedImage = Base64.getDecoder().decode(encodedImage);
                car_image.setCar_img(decodedImage);

                images.add(car_image);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return images;
    }
    
    public void addCarImage(CarImage_DTO carImage){
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("car_images");

            int maxImgId = 0;
            for (Document doc : collection.find(Filters.eq("car_id", carImage.getCar_id()))) {
                int currentImgId = doc.getInteger("img_id", 0); 
                if (currentImgId > maxImgId) {
                    maxImgId = currentImgId; 
                }
            }

            int newImgId = maxImgId + 1;

            String encodedImage = Base64.getEncoder().encodeToString(carImage.getCar_img());

            Document carImageDoc = new Document("img_id", newImgId) // Sử dụng img_id mới
                    .append("car_id", carImage.getCar_id())
                    .append("car_img", encodedImage);

            collection.insertOne(carImageDoc);
            System.out.println("Image Added Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeCarImage(int img_id)
    {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("car_images");

            // Lưu ảnh đã xóa
            Document removedImageDoc = collection.find(Filters.eq("img_id", img_id)).first();
            if (removedImageDoc != null) {
                lastRemovedImage = new CarImage_DTO();
                lastRemovedImage.setImg_id(removedImageDoc.getInteger("img_id"));
                lastRemovedImage.setCar_id(removedImageDoc.getInteger("car_id"));
                lastRemovedImage.setCar_img(Base64.getDecoder().decode(removedImageDoc.getString("car_img")));
            }

            collection.deleteOne(Filters.eq("img_id", img_id));
            System.out.println("Image Deleted Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void undoRemoveCarImage() {
        if (lastRemovedImage != null) {
            addCarImage(lastRemovedImage); 
            lastRemovedImage = null; 
            System.out.println("Image Undone Successfully");
        } else {
            System.out.println("No image to undo.");
        }
    }
}
