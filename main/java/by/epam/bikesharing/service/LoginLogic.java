package by.epam.bikesharing.service;

import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.IncorrectPasswordException;
import by.epam.bikesharing.exception.NoSuchUserException;

public class LoginLogic {

    public User loginUser(String login, String password) throws IncorrectPasswordException, NoSuchUserException {

        User user = new UserDao().findUserByLogin(login);
        if (user != null) {
            if (user.getPasswordHash().isCorrectPassword(password)) {
                return user;
            } else {
                throw new IncorrectPasswordException();
            }
        } else {
            throw new NoSuchUserException();
        }
    }
}