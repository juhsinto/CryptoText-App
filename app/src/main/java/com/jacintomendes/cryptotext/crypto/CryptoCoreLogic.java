package com.jacintomendes.cryptotext.crypto;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Jm on 25-Oct-14.
 */


public class CryptoCoreLogic {
//    public static void main(String[] args) throws Exception {
//        String key = "qwertyuiopasdfgh sad qwe 12312 zxcweq rwqer sdfwer 3";
//        String plainText = "this is a really cool secret^%$&%^$&%^ sahdfoiuash foiasoausfyoas iowier 3iury237982346 0sad7 23eg  q3uieasiduhoi2u3ye o278tADwr2qi3e gIUWDqowduywe qoeiquydoid wqiduqo i";
//
//
//        String encryptedString = encryptString(plainText, key);
//        System.out.println("The encryptedString is "+ encryptedString);
//        System.out.println("The decrypted String is "+ decryptString(encryptedString, badkey));
//    }

    private static SecretKeySpec getKey(String key) {
        SecretKeySpec skeySpec = null;
        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyBytes = sha.digest(keyBytes);
            // use only first 128 bit
            keyBytes = Arrays.copyOf(keyBytes, 16);
            skeySpec = new SecretKeySpec(keyBytes, "AES");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            return skeySpec;
        }
    }


    public static String encryptString(String inputText, String key) {
        byte[] encrypted = null;
        try {
            SecretKeySpec skeySpec = getKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encrypted = cipher.doFinal(inputText.getBytes("UTF-8"));
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            return new String(Hex.encodeHex(encrypted));
        }
    }

    public static String decryptString(String encryptedString, String key) {
        byte[] original = null;
        String originalString = "";
        try {
            byte[] encryptedBytes = Hex.decodeHex(encryptedString.toCharArray());
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            SecretKeySpec skeySpec = getKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            original = cipher.doFinal(encryptedBytes);
            originalString = new String(original, "UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
            originalString = "Bad Key!";
        } finally {
            return originalString;
        }
    }

}
