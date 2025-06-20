package com.manideep.journalApp.entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "journal_entries")
@Data

public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDate date;


}
