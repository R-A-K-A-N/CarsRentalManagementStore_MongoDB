/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Brands_DTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import java.io.ByteArrayInputStream;
import org.bson.Document;
import java.util.Base64;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Brands_DAO {
    private List<Document> deletedBrands = new ArrayList<>();
    private String getImageMimeType(byte[] imageBytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            String mimeType = ImageIO.getImageReaders(ImageIO.createImageInputStream(bais)).next().getFormatName();
            return "image/" + mimeType.toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    public void addBrand(Brands_DTO brands) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("brands");

            Document maxBrand = collection.find().sort(new Document("brand_id", -1)).first();
            int newBrandId = (maxBrand != null) ? maxBrand.getInteger("brand_id") + 1 : 1;

            String encodedLogo = null;
            if (brands.getLogo() != null) {
                String mimeType = getImageMimeType(brands.getLogo());
                if (mimeType != null) {
                    encodedLogo = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(brands.getLogo());
                }
            }

            Document brandDoc = new Document("brand_id", newBrandId)
                    .append("name", brands.getName())
                    .append("logo", encodedLogo);

            collection.insertOne(brandDoc);
            JOptionPane.showMessageDialog(null, "The New Brand Has Been Added", "Add Brand", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Brand Not Added: " + ex.getMessage(), "Add Brand", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    public void updateBrand(Brands_DTO brands) {
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            String encodedLogo = null;
            if (brands.getLogo() != null) {
                String mimeType = getImageMimeType(brands.getLogo());
                if (mimeType != null) {
                    encodedLogo = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(brands.getLogo());
                }
            }

            Document updatedData = new Document("name", brands.getName())
                                    .append("logo", encodedLogo);

            UpdateResult result = collection.updateOne(Filters.eq("brand_id", brands.getBrand_id()), new Document("$set", updatedData));

            if (result.getModifiedCount() > 0) {
                JOptionPane.showMessageDialog(null, "The Brand Has Been Updated", "Update Brand", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No Brand Found to Update", "Update Brand", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Brand Not Updated: " + ex.getMessage(), "Update Brand", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteBrand(int brand_id) {
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            Document brandToDelete = collection.find(Filters.eq("brand_id", brand_id)).first();
            if (brandToDelete != null) {
                deletedBrands.add(brandToDelete);

                long deletedCount = collection.deleteOne(Filters.eq("brand_id", brand_id)).getDeletedCount();
                if (deletedCount > 0) {
                    JOptionPane.showMessageDialog(null, "The Brand Has Been Deleted", "Delete Brand", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No Brand Found to Delete", "Delete Brand", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Brand Found with the Given ID", "Delete Brand", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Brand Not Deleted: " + ex.getMessage(), "Delete Brand", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void undoDeleteBrand() {
        if (!deletedBrands.isEmpty()) {
            Document lastDeletedBrand = deletedBrands.remove(deletedBrands.size() - 1);
            try {
                MongoDatabase database = Cars_DB.getConnection();
                MongoCollection<Document> collection = database.getCollection("brands");

                collection.insertOne(lastDeletedBrand);
                JOptionPane.showMessageDialog(null, "The Brand Has Been Restored", "Undo Delete", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error undoing delete: " + ex.getMessage(), "Undo Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No deleted brand to undo.", "Undo Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public Brands_DTO getBrandById(int brand_id) {
        Brands_DTO brand = null;
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            Document doc = collection.find(Filters.eq("brand_id", brand_id)).first();
            if (doc != null) {
                String logoBase64 = doc.getString("logo");
                byte[] decodedLogo = null;
                if (logoBase64 != null && logoBase64.contains(",")) {
                    String base64Data = logoBase64.split(",")[1];
                    decodedLogo = Base64.getDecoder().decode(base64Data);
                }
                brand = new Brands_DTO(doc.getInteger("brand_id"), doc.getString("name"), decodedLogo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error retrieving brand: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return brand;
    }
    
    public ArrayList<Brands_DTO> brandsList() {
        ArrayList<Brands_DTO> list = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            for (Document doc : collection.find().sort(new Document("brand_id", 1))) {
                String logoBase64 = doc.getString("logo");
                byte[] decodedLogo = null;
                if (logoBase64 != null && logoBase64.contains(",")) {
                    String base64Data = logoBase64.split(",")[1];
                    decodedLogo = Base64.getDecoder().decode(base64Data);
                }
                Brands_DTO brand = new Brands_DTO(doc.getInteger("brand_id"), doc.getString("name"), decodedLogo);
                list.add(brand);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Brands_DTO> getData() {
        List<Brands_DTO> brands = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            for (Document doc : collection.find().sort(new Document("brand_id", 1))) {
                int brand_id = doc.getInteger("brand_id");
                String name = doc.getString("name");
                String logoBase64 = doc.getString("logo");
                byte[] logo = null;
                if (logoBase64 != null && logoBase64.contains(",")) {
                    String base64Data = logoBase64.split(",")[1];
                    logo = Base64.getDecoder().decode(base64Data);
                }

                brands.add(new Brands_DTO(brand_id, name, logo));
            }
        } catch (Exception ex) {
            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brands;
    }
    
    public HashMap<Integer, String> brandsHashMap() {
        HashMap<Integer, String> brands_map = new HashMap<>();
        try {
            MongoDatabase database = Cars_DB.getConnection();
            MongoCollection<Document> collection = database.getCollection("brands");

            for (Document doc : collection.find()) {
                brands_map.put(doc.getInteger("brand_id"), doc.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return brands_map;
    }
}
