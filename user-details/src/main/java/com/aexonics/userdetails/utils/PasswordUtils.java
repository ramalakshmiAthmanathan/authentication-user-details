package com.aexonics.userdetails.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {
    public static String encodePasswordWithHashAndSalt(String password){
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);
        return generatedSecuredPasswordHash;
    }
    public static boolean validatePassword(String originalPassword,String encodedPassword){
        return BCrypt.checkpw(originalPassword,encodedPassword);
    }
}
