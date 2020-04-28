package com.demo.lab.repository;

import com.demo.lab.domain.Log;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Log entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogRepository extends MongoRepository<Log, String> {
}
