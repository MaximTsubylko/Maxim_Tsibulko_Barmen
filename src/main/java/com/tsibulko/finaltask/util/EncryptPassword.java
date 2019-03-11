package com.tsibulko.finaltask.util;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.service.ServiceException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EncryptPassword {

    public static void encrypt(Customer user) throws ServiceException {
        try {
            String passwordWithSalt = user.getPassword() + user.getLogin();
            byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            String decimalHash = IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                    .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
            user.setPassword(decimalHash);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e, "Encrypt password error");
        }
    }
}
