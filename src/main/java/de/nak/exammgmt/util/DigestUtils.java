/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Operations to simplify common {@link MessageDigest} tasks.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DigestUtils {

    /**
     * Returns an digest instance for the given algorithm.
     *
     * @return A digest instance.
     * @throws RuntimeException when a {@link NoSuchAlgorithmException} is caught.
     */
    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Calculates the SHA digest and returns the value as a
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA digest
     */
    public static byte[] sha(byte[] data) {
        return getDigest("SHA-1").digest(data);
    }

    /**
     * Calculates the SHA digest and returns the value as a
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA digest
     */
    public static byte[] sha(String data) {
        return sha(data.getBytes());
    }

    /**
     * Calculates the SHA digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA digest as a hex string
     */
    public static String shaHex(byte[] data) {
        return new String(Hex.encode(sha(data)));
    }

    /**
     * Calculates the SHA digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA digest as a hex string
     */
    public static String shaHex(String data) {
        return new String(Hex.encode(sha(data)));
    }


    /**
     * Calculates the SHA-256 digest and returns the value as a
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    public static byte[] sha256(byte[] data) {
        return getDigest("SHA-256").digest(data);
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    public static byte[] sha256(String data) {
        return sha256(data.getBytes());
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     */
    public static String sha256Hex(byte[] data) {
        return new String(Hex.encode(sha256(data)));
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     */
    public static String sha256Hex(String data) {
        return new String(Hex.encode(sha256(data)));
    }

}
