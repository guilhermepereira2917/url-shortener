package com.guilhermepereira.urlshortener.controller;

import com.guilhermepereira.urlshortener.dto.ShortenUrlRequest;
import com.guilhermepereira.urlshortener.dto.ShortenUrlResponse;
import com.guilhermepereira.urlshortener.exception.InvalidURLException;
import com.guilhermepereira.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest httpServletRequest) {
        var newUrlEntity = urlService.createUrlEntity(request.url());
        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorten-url", newUrlEntity.getId());

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl, newUrlEntity.getExpiresAt()));
    }

    @ExceptionHandler(InvalidURLException.class)
    public ResponseEntity<String> handleInvalidUrlException(InvalidURLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        var urlEntity = urlService.findById(id);
        if (urlEntity == null) {
            return ResponseEntity.notFound().build();
        }

        var headers = new HttpHeaders();
        headers.setLocation(URI.create(urlEntity.getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping("/health")
    public ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }
}
