package org.srm.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.srm.blog.model.T;

import java.util.List;

public interface TRepository extends MongoRepository<T, String> {
    List<T> findAllByAuthorId(String authorId);
}
