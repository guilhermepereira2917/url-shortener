package com.guilhermepereira.urlshortener.api;

import java.security.SecureRandom;

public class UrlShortener {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String shortenUrl(int minLength, int maxLength) {
        if (minLength < 1 || maxLength < minLength) {
            throw new IllegalArgumentException("Invalid length range");
        }

        int length = RANDOM.nextInt(maxLength - minLength + 1) + minLength;
        var shortenedUrl = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int charIndex = RANDOM.nextInt(ALPHANUMERIC.length());
            shortenedUrl.append(ALPHANUMERIC.charAt(charIndex));
        }

        return shortenedUrl.toString();
    }
}
