package com.example.TennisReservation.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    public String sendPass() {
        
        String password = "";
        try {
            File f = new File("/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/sen.txt");
            Scanner read;
            read = new Scanner(f);
            password = this.decrypt(read.nextLine());
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return password;
        
            
    }
    public String decrypt(String word) {
        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance("DESede");
            String EncryptKey = "ThisIsSpartaThisIsSparta";
            byte[] arrayBytes = EncryptKey.getBytes("UTF8");
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKey key = skf.generateSecret(ks);
            Cipher cipher = Cipher.getInstance("DESede");

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(word);
            byte[] plainText = cipher.doFinal(encryptedText);
            String decrypt = new String(plainText);
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}