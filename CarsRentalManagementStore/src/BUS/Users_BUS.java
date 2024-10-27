package BUS;

import DAO.Users_DAO;
import DTO.Users_DTO;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users_BUS {
    Users_DAO userDAO = new Users_DAO();

    public ArrayList<Users_DTO> getAllUsers() {
        return userDAO.usersList();
    }

    public void addUser(int user_id, String fullname, String username, String password, String user_type, byte[] picture, String phone, String email) {
        Users_DTO user = new Users_DTO();
        user.setUser_id(user_id);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        user.setUser_type(user_type);
        user.setPicture(picture);
        user.setPhone(phone);
        user.setEmail(email);
        userDAO.addUser(user);
    }

    public void updateUser(int user_id, String fullname, String username, String password, String user_type, byte[] picture, String phone, String email) {
        Users_DTO user = new Users_DTO();
        user.setUser_id(user_id);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        user.setUser_type(user_type);
        user.setPicture(picture);
        user.setPhone(phone);
        user.setEmail(email);
        userDAO.updateUser(user);
    }

    public void deleteUser(int user_id) {
        userDAO.deleteUser(user_id);
    }
    
    public Users_DTO undoUser() {
        return userDAO.undoUser(); 
    }
    
    public Users_DTO getUserById(int user_id) {
        return userDAO.getUserById(user_id);
    }
    
    public ArrayList<Users_DTO> getData() { 
        return userDAO.getData("");
    }
}
