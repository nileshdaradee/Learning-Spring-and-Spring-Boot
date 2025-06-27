package com.journalapp.journalApp.repository;

import com.journalapp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {


    Optional<User> findByUsername(String username);

    void deleteByUsername(String name);
}
