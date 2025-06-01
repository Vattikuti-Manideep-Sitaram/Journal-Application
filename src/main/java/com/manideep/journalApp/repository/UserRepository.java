package com.manideep.journalApp.repository;

import com.manideep.journalApp.entities.JournalEntry;
import com.manideep.journalApp.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByName(String username);
}
