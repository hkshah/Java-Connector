package com.rapidvalue.master.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.rapidvalue.master.common.constants.CommonConstants;

/**
 * @author Eldho
 * @since May 18, 2016
 */
public class AES {

    private static final Logger LOG = Logger.getLogger(AES.class);

    /**
     * Private constructor to hide the implicit public one.
     */
    private AES() {

    }

    /**
     * Add padding to the string to make it a multiple of 16
     * 
     * @param source
     * @return
     */
    private static String padString(String source) {

        String paddedString = source;
        char paddingChar = ' ';
        int size = 16;
        int x = paddedString.length() % size;
        int padLength = size - x;
        for (int i = 0; i < padLength; i++) {
            paddedString += paddingChar;
        }

        return paddedString;
    }

    /**
     * Encrypt a string value
     * 
     * @param key
     * @param initVector
     * @param value
     * @return
     */
    public static String encrypt(final String key, final String initVector, String value) {

        String encryptedString = null;
        try {
            String padValue = padString(value);
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(CommonConstants.CHAR_ENCODE_UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CommonConstants.CHAR_ENCODE_UTF_8),
                    CommonConstants.ENCRYPT_TYPE_AES);
            Cipher cipher = Cipher.getInstance(CommonConstants.ENCRYPT_PADDING_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(padValue.getBytes());
            encryptedString = Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            LOG.error("Failed to encrypt string::" + ex);
        }

        return encryptedString;
    }

    /**
     * Decrypt a string value
     * 
     * @param key
     * @param initVector
     * @param encrypted
     * @return
     */
    public static String decrypt(final String key, final String initVector, String encrypted) {

        String decryptedString = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(CommonConstants.CHAR_ENCODE_UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CommonConstants.CHAR_ENCODE_UTF_8),
                    CommonConstants.ENCRYPT_TYPE_AES);
            Cipher cipher = Cipher.getInstance(CommonConstants.ENCRYPT_PADDING_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            decryptedString = new String(original);
        } catch (Exception ex) {
            LOG.error("Failed to decrypt string::" + ex);
        }

        return decryptedString;
    }

}
