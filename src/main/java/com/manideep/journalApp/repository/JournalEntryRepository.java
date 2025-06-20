package com.manideep.journalApp.repository;

import com.manideep.journalApp.entities.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
