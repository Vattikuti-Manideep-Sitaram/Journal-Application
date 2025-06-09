package com.manideep.journalApp.controllers;

import com.manideep.journalApp.entities.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.manideep.journalApp.services.JournalEntryService;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("getAllJournals")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntries(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<JournalEntry> journalEntries = journalEntryService.getAllEntries(userName);
        if(journalEntries != null){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("createJournal")
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try{
            JournalEntry savedJournalEntry = journalEntryService.saveJournalEntry(journalEntry,userName);
            return new ResponseEntity<>(savedJournalEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{userId}/journalId/{journalId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId userId, @PathVariable ObjectId journalId){
            JournalEntry journalEntry = journalEntryService.findById(userId,journalId);
            if(journalEntry != null){
                return new ResponseEntity<>(journalEntry,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userId}/journalId/{journalId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId userId, @PathVariable ObjectId journalId){
        try{
            journalEntryService.deleteById(journalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    @PutMapping("id/{userId}/journalId/{journalId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId journalId,@PathVariable ObjectId userId,@RequestBody JournalEntry newJournalEntry){
        JournalEntry journalEntry = journalEntryService.findById(userId, journalId);
        if(journalEntry != null){
            return new ResponseEntity<>(journalEntryService.updateById(journalId,newJournalEntry,journalEntry),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
