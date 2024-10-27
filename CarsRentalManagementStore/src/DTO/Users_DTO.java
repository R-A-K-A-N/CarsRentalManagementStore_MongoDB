package DTO;

public class Users_DTO {

    public Users_DTO() {
    }

    public Users_DTO(int user_id, String fullname, String username, String password, String user_type, byte[] picture, String phone, String email) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.user_type = user_type;
        this.picture = picture;
        this.phone = phone;
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private int user_id; 
    private String fullname;
    private String username;
    private String password;
    private String user_type;
    private byte[] picture;
    private String phone;
    private String email;

    

    
    
}
