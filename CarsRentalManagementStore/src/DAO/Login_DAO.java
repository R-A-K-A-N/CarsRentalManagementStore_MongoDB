package DAO;

import DTO.Users_DTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;

public class Login_DAO {

    public Users_DTO checkLogin(String username, String password) {
        try {
            MongoDatabase database = Cars_DB.getConnection(); 
            MongoCollection<Document> collection = database.getCollection("users"); 

            Document query = new Document("username", username).append("password", password);
            Document result = collection.find(query).first(); 

            if (result != null) {
                Users_DTO user = new Users_DTO();
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                return user;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null; 
    }
}
