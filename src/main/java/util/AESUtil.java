package util;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    // AES Encryption
    public static String encrypt(String data, String secretKey) throws Exception {
        // Create a SecretKeySpec object with the key and AES algorithm
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        
        // Initialize Cipher with AES encryption algorithm
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Encrypt the data
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // Return encrypted data as a Base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // AES Decryption
    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        // Create a SecretKeySpec object with the key and AES algorithm
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // Initialize Cipher with AES decryption algorithm
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        // Return decrypted data as a string
        return new String(decryptedData);
    }

    // Method to generate a random AES key (for 128-bit)
    public static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit AES key
        SecretKey secretKey = keyGen.generateKey();

        // Return the key as a Base64 encoded string
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
