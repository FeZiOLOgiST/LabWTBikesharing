package by.epam.bikesharing.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHash {

    private String password;
    private byte[] hash;
    private byte[] salt;
    private SecureRandom random = new SecureRandom();

    public PasswordHash(String password) {
        this.password = password;
    }

    public PasswordHash(byte[] hash, byte[] salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public boolean isCorrectPassword(String password) {
        return Arrays.equals(generateHash(password), hash);
    }

    public byte[] getHash() {
        return hash != null ? hash : (hash = generateHash(password));
    }

    public byte[] getSalt() {
        return salt != null ? salt : (salt = generateSalt(16));
    }

    private byte[] generateSalt(int byteLength) {
        byte[] salt = new byte[byteLength];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] generateHash(String password) {
        byte[] hash = null;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt(), 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public void setPassword(String password) {
        this.password = password;
        hash = null;
        salt = null;
    }
}