package BUS;

import DAO.Brands_DAO;
import DTO.Brands_DTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;


public class Brands_BUS {
    Brands_DAO brandDAO = new Brands_DAO();

    public ArrayList<Brands_DTO> getAllBrands() {
        try {
            return brandDAO.brandsList();
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error fetching brands: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void addBrand(int brand_id, String name, byte[] logo) {
        try {
            Brands_DTO brand = new Brands_DTO();
            brand.setBrand_id(brand_id);
            brand.setName(name);
            brand.setLogo(logo);
            brandDAO.addBrand(brand);
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error adding brand: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateBrand(int brand_id, String name, byte[] logo) {
        try {
            Brands_DTO brand = new Brands_DTO();
            brand.setBrand_id(brand_id);
            brand.setName(name);
            brand.setLogo(logo);
            brandDAO.updateBrand(brand);
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error updating brand: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteBrand(int brand_id) {
        try {
            brandDAO.deleteBrand(brand_id);
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error deleting brand: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void undoDeleteBrand() {
        try {
            brandDAO.undoDeleteBrand();
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error undoing delete: " + ex.getMessage(), "Undo Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Brands_DTO getBrandById(int brand_id) {
        try {
            return brandDAO.getBrandById(brand_id);
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error fetching brand by ID: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public List<Brands_DTO> getData() {
        try {
            return brandDAO.getData();
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error fetching brands data: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    public HashMap<Integer, String> getBrandsHashMap() {
        try {
            return brandDAO.brandsHashMap();
        } catch (Exception ex) {
            Logger.getLogger(Brands_BUS.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error fetching brands hash map: " + ex.getMessage(), "Brand Error", JOptionPane.ERROR_MESSAGE);
            return new HashMap<>();
        }
    }
}
