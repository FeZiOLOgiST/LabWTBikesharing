package by.epam.bikesharing.service.email;

import java.security.SecureRandom;

public class VerificationCode {

    private SecureRandom random = new SecureRandom();

    public String getCode(int length) {
        StringBuilder codeStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            codeStringBuilder.append(random.nextInt(10));
        }
        return codeStringBuilder.toString();
    }
}