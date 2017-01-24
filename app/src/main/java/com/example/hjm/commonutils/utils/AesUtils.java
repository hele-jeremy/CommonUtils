package com.example.hjm.commonutils.utils;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public class AesUtils {

    private static final String Algorithm="AES";
    private static final String transformation="AES/CBC/PKCS5Padding";
    public static String initKey(int keysize){
        try {
            KeyGenerator keyGenerator= KeyGenerator.getInstance(Algorithm);
            keyGenerator.init(keysize);
            return Base64.encodeToString(keyGenerator.generateKey().getEncoded(), Base64.CRLF);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 加密
     * @param data
     * @return
     */
    public static String encrypt(String secretkey, String data){
        return Base64.encodeToString(encrypt(Base64.decode(secretkey, Base64.CRLF), data.getBytes()), Base64.CRLF);
    }

    /**
     * 加密
     * @return
     */
    public static byte [] encrypt(byte [] secretBytes,byte [] data){
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(secretBytes,Algorithm),new IvParameterSpec(new byte[cipher.getBlockSize()]));
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     * @return
     */
    public static byte [] decrypt(byte [] secretBytes,byte [] enData){
        try {
            Cipher cipher= Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretBytes, Algorithm), new IvParameterSpec(new byte[cipher.getBlockSize()]));
            return cipher.doFinal(enData);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密
     * @param enData
     * @return
     */
    public static String decrypt(String secretkey, String enData){
        return new String(decrypt(Base64.decode(secretkey, Base64.DEFAULT), Base64.decode(enData, Base64.CRLF)));
    }
}
