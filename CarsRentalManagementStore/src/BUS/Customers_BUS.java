package BUS;

import DAO.Customers_DAO;
import DTO.Customers_DTO;
import java.util.ArrayList;

public class Customers_BUS {
    Customers_DAO customersDAO = new Customers_DAO();
    
    public ArrayList<Customers_DTO> getAllCustomers() {
        return customersDAO.customersList();
    }
    
    public void addCustomer(int customer_id, String fullname, String birthday, String phone, String email, String address) {
        Customers_DTO customers = new Customers_DTO();
        customers.setCustomer_id(customer_id);
        customers.setFullname(fullname);
        customers.setBirthday(birthday);
        customers.setPhone(phone);
        customers.setEmail(email);
        customers.setAddress(address);
        customersDAO.addCustomer(customers);
    }
    
    public void editCustomer(int customer_id, String fullname, String birthday, String phone, String email, String address) {
        Customers_DTO customers = new Customers_DTO();
        customers.setCustomer_id(customer_id);
        customers.setFullname(fullname);
        customers.setBirthday(birthday);
        customers.setPhone(phone);
        customers.setEmail(email);
        customers.setAddress(address);
        customersDAO.editCustomer(customers);
    }
    
    public void removeCustomer(int customer_id) {
        customersDAO.removeCustomer(customer_id);
    }
    
    public void undoCustomer() {
        customersDAO.undoCustomer();
    }
    
    public Customers_DTO getCustomerById(int customer_id) {
        return customersDAO.getCustomerById(customer_id);
    }
    
    public ArrayList<Customers_DTO> getData(String query) {
        return customersDAO.getData(query);
    }
}
