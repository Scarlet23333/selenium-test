package com.seleniumtest;
import java.util.Scanner;

import util.AESUtil;

public class GenerateEncryptPassword 
{
    public static void main (String[] args) throws Exception
    {
        String secretKey = AESUtil.generateKey();
        Scanner passwordScanner = new Scanner(System.in);
        System.out.println("input plain password: ");
        String plainPassword = passwordScanner.nextLine();
        String encryptPassword = AESUtil.encrypt(plainPassword, secretKey);
        System.out.println("secretKey, encryptPassword: " + secretKey + ", " + encryptPassword);
        passwordScanner.close();
    }
}
