/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rockdrigo
 */
public class SecurityUtils {

    public static String getMD5(String input) throws NoSuchAlgorithmException {
        String output = null;
        if (input != null) {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(input.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            output = bigInt.toString(16);
            while (output.length() < 32) {
                output = "0" + output;
            }
        }
        return output;
    }
}
