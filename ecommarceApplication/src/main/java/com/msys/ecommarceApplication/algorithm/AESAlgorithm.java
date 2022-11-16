package com.msys.ecommarceApplication.algorithm;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Service
public class AESAlgorithm {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes(StandardCharsets.UTF_8);

    public static Key generateKey() {
        final Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    public static String encrypt(String valueToEncrypt, Key key) throws Exception {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] encryptValue = cipher.doFinal(valueToEncrypt.getBytes());
        final byte[] encryptedByteValue = new Base64().encode(encryptValue);
        return new String(encryptedByteValue);
    }

    public static String decrypt(String encryptedValue, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
        final byte[] encryptValue = cipher.doFinal(decodedBytes);
        return new String(encryptValue);
    }
}
