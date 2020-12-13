package by.epam.bikesharing.service;

import by.epam.bikesharing.constant.ServiceConstant;
import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.validation.AuthenticatorValidation;

import static by.epam.bikesharing.constant.ServiceConstant.UPDATE_SUCCESS;

public class ProfileLogic {

    private static final String INVALID_EMAIL = "message.invalid_email";
    private static final String INVALID_IMAGE = "message.invalid_image";
    private static final String INVALID_LOGIN = "message.invalid_login";
    private static final String INVALID_PASSWORD = "message.invalid_password";
    private static final String LOGIN_IN_USE = "message.login_in_use";
    private static final String EMAIL_IN_USE = "message.email_in_use";
    private static final String SAME_PASSWORDS = "message.same_password";
    private static final String CONFIRM_PASSWORDS = "message.confirm_password";
    private static final String OLD_PASSWORD_WRONG = "message.wrong_password";
    private User oldUser;
    private User newUser;
    private String login;
    private String email;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
    private String image;

    public ProfileLogic(User oldUser) {
        this.oldUser = oldUser;
    }

    public String updateProfile() {
        String result = UPDATE_SUCCESS;
        newUser = new User(oldUser);
        if (!oldUser.getLogin().equals(login) && !login.isBlank()) {
            if (!UPDATE_SUCCESS.equals(result = updateLogin()))
                return result;
        }
        if (!oldUser.getEmail().equals(email) && !email.isBlank()) {
            if (!UPDATE_SUCCESS.equals(result = updateEmail()))
                return result;
        }
        if (image != null) {
            if (!updateImage())
                return INVALID_IMAGE;
        }
        if (!newPassword.isBlank()) {
            if (!UPDATE_SUCCESS.equals(result = updatePassword()))
                return result;
        }
        UserDao dao = new UserDao();
        dao.update(newUser);
        dao.updateUserImage(oldUser.getId(), newUser.getImage());
        dao.closeConnection();
        return result;
    }

    private String updateLogin() {
        if (!AuthenticatorValidation.isValidLogin(login)) {
            return INVALID_LOGIN;
        }
        if (AuthenticatorValidation.loginIsInUse(login)) {
            return LOGIN_IN_USE;
        }
        newUser.setLogin(login);
        return UPDATE_SUCCESS;
    }

    private String updateEmail() {
        if (!AuthenticatorValidation.isValidEmail(email)) {
            return INVALID_EMAIL;
        }
        if (AuthenticatorValidation.emailIsInUse(email)) {
            return EMAIL_IN_USE;
        }
        newUser.setEmail(email);
        return UPDATE_SUCCESS;
    }

    private boolean updateImage() {
        if (image.length() <= ServiceConstant.MAX_IMAGE_SIZE) {
            newUser.setImage(image);
            return true;
        }
        return false;
    }

    private String updatePassword() {
        if (!oldUser.getPasswordHash().isCorrectPassword(oldPassword)) {
            return OLD_PASSWORD_WRONG;
        }
        if (!AuthenticatorValidation.isValidPassword(newPassword)) {
            return INVALID_PASSWORD;
        }
        if (newPassword.equals(oldPassword)) {
            return SAME_PASSWORDS;
        }
        if (!newPassword.equals(repeatPassword)) {
            return CONFIRM_PASSWORDS;
        }
        newUser.setPasswordHash(new PasswordHash(newPassword));
        return UPDATE_SUCCESS;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
