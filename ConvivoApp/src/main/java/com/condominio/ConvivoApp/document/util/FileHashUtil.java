package com.condominio.ConvivoApp.document.util;

import jakarta.xml.bind.DatatypeConverter;

import java.io.InputStream;
import java.security.MessageDigest;


/**
 * Utility to compute SHA-256 checksum
 */
public final class FileHashUtil {

    private FileHashUtil(){}

    public static String sha256Hex(InputStream is) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int read;
        while ((read = is.read(buffer)) > 0) {
            digest.update(buffer, 0, read);
        }
        byte[] hash = digest.digest();
        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }
}
