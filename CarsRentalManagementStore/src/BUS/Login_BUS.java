package BUS;

import DAO.Login_DAO;
import DAO.Users_DAO;
import DTO.Users_DTO;

public class Login_BUS {

    private Users_DAO usersDao;

    public Login_BUS() {
        usersDao = new Users_DAO();
    }

    public Users_DTO login(String username, String password) {
        return usersDao.getUser(username, password);
    }
}
