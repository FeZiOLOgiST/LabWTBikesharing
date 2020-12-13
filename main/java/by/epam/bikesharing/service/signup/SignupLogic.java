package by.epam.bikesharing.service.signup;

import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.service.PasswordHash;

public class SignupLogic {

    private static final String INITIAL_USER_ROLE = "user";

    public void addUser(String userLogin, String userEmail, PasswordHash userHash) {
        User user = new User();
        user.setLogin(userLogin);
        user.setEmail(userEmail);
        user.setRole(INITIAL_USER_ROLE);
        user.setPasswordHash(userHash);
        UserDao userDAO = new UserDao();
        userDAO.create(user);
    }
}