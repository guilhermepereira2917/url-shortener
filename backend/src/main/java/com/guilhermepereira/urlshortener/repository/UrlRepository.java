package com.guilhermepereira.urlshortener.repository;

import com.guilhermepereira.urlshortener.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
