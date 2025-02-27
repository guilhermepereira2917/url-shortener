package com.guilhermepereira.urlshortener.exception;

public class InvalidURLException extends RuntimeException {
    public InvalidURLException(String url) {
        super("Invalid URL: " + url);
    }
}
