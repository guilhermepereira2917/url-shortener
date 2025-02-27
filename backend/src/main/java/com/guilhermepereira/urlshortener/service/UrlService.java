package com.guilhermepereira.urlshortener.service;

import com.guilhermepereira.urlshortener.api.UrlShortener;
import com.guilhermepereira.urlshortener.entity.UrlEntity;
import com.guilhermepereira.urlshortener.exception.InvalidURLException;
import com.guilhermepereira.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlEntity createUrlEntity(String url) throws InvalidURLException {
        if (!isValidUrl(url)) {
            throw new InvalidURLException(url);
        }

        String shortenedUrl;
        do {
            shortenedUrl = UrlShortener.shortenUrl(5, 10);
        } while (urlRepository.existsById(shortenedUrl));

        return urlRepository.save(new UrlEntity(shortenedUrl, url, LocalDateTime.now().plusSeconds(30)));
    }

    private boolean isValidUrl(String url) {
        try {
            var uri = URI.create(url);
            var toUrl = uri.toURL();
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    public UrlEntity findById(String id) {
        return urlRepository.findById(id).orElse(null);
    }
}
