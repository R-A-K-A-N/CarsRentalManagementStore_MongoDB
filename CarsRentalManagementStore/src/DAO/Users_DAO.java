package DAO;

import DTO.Users_DTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Users_DAO {
    private Stack<Users_DTO> actionHistory = new Stack<>();
    public void addUser(Users_DTO user){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            Document maxUser = collection.find().sort(new Document("user_id", -1)).first();
            int newUserId = (maxUser != null) ? maxUser.getInteger("user_id") + 1 : 1;

            String base64Image = Base64.getEncoder().encodeToString(user.getPicture());

            Document userDoc = new Document("user_id", newUserId)
                    .append("fullname", user.getFullname())
                    .append("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("user_type", user.getUser_type())
                    .append("picture", base64Image)
                    .append("phone", user.getPhone())
                    .append("email", user.getEmail());

            collection.insertOne(userDoc);
            JOptionPane.showMessageDialog(null, "The New User Has Been Added", "Add User", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "User Not Added", "Add User", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateUser(Users_DTO user){
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            String base64Image = Base64.getEncoder().encodeToString(user.getPicture());

            Document updatedData = new Document("fullname", user.getFullname())
                    .append("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("user_type", user.getUser_type())
                    .append("picture", base64Image)  
                    .append("phone", user.getPhone())
                    .append("email", user.getEmail());

            collection.updateOne(Filters.eq("user_id", user.getUser_id()), new Document("$set", updatedData));
            JOptionPane.showMessageDialog(null, "The User Has Been Updated", "Update User", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "User Not Updated", "Update User", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteUser(int user_id) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            Users_DTO userToDelete = getUserById(user_id);
            if (userToDelete != null) {
                collection.deleteOne(Filters.eq("user_id", user_id));
                actionHistory.push(userToDelete); 
                JOptionPane.showMessageDialog(null, "The User Has Been Deleted", "Delete User", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User Not Found", "Delete User", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "User Not Deleted", "Delete User", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public Users_DTO undoUser() {
        if (!actionHistory.isEmpty()) {
            Users_DTO lastAction = actionHistory.pop(); 
            if (lastAction != null) {
                addUser(lastAction);
                return lastAction;
            }
        }
        return null;
    }
    
    public ArrayList<Users_DTO> getData(String query) {
        ArrayList<Users_DTO> resultList = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            if (query != null && !query.isEmpty()) {
                int userId = Integer.parseInt(query);
                Document doc = collection.find(Filters.eq("user_id", userId)).first();
                if (doc != null) {
                    String base64Image = doc.getString("picture");
                    byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                    Users_DTO user = new Users_DTO(
                            doc.getInteger("user_id"),
                            doc.getString("fullname"),
                            doc.getString("username"),
                            doc.getString("password"),
                            doc.getString("user_type"),
                            imageBytes, 
                            doc.getString("phone"),
                            doc.getString("email")
                    );
                    resultList.add(user);
                }
            } else {
                FindIterable<Document> docs = collection.find();
                for (Document doc : docs) {
                    String base64Image = doc.getString("picture");
                    byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                    Users_DTO user = new Users_DTO(
                            doc.getInteger("user_id"),
                            doc.getString("fullname"),
                            doc.getString("username"),
                            doc.getString("password"),
                            doc.getString("user_type"),
                            imageBytes, 
                            doc.getString("phone"),
                            doc.getString("email")
                    );
                    resultList.add(user);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }
    
    public ArrayList<Users_DTO> usersList() {
        ArrayList<Users_DTO> list = new ArrayList<>();
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            for (Document doc : collection.find().sort(new Document("user_id", 1))) { 
                String base64Image = doc.getString("picture");
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                Users_DTO user = new Users_DTO(
                        doc.getInteger("user_id"),
                        doc.getString("fullname"),
                        doc.getString("username"),
                        doc.getString("password"),
                        doc.getString("user_type"),
                        imageBytes,
                        doc.getString("phone"),
                        doc.getString("email")
                );
                list.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Users_DTO getUserById(int user_id){
        Users_DTO user = null;
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            Document doc = collection.find(Filters.eq("user_id", user_id)).first();
            if (doc != null) {
                String base64Image = doc.getString("picture");
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                user = new Users_DTO(
                        doc.getInteger("user_id"),
                        doc.getString("fullname"),
                        doc.getString("username"),
                        doc.getString("password"),
                        doc.getString("user_type"),
                        imageBytes,
                        doc.getString("phone"),
                        doc.getString("email")
                );
            } else {
                JOptionPane.showMessageDialog(null, "No User With This ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }
    
    public Users_DTO getUser(String username, String password) {
        Users_DTO user = null;
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users");

            Document doc = collection.find(Filters.and(Filters.eq("username", username), Filters.eq("password", password))).first();
            if (doc != null) {
                String base64Image = doc.getString("picture");
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                user = new Users_DTO(
                        doc.getInteger("user_id"),
                        doc.getString("fullname"),
                        doc.getString("username"),
                        doc.getString("password"),
                        doc.getString("user_type"),
                        imageBytes,
                        doc.getString("phone"),
                        doc.getString("email")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
