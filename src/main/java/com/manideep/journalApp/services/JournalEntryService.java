package com.manideep.journalApp.services;

import com.manideep.journalApp.entities.JournalEntry;
import com.manideep.journalApp.entities.User;
import com.manideep.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public JournalEntry saveJournalEntry(JournalEntry journalEntry,String userName) throws  Exception{
        User user = userService.findByName(userName);
        if(user != null){
            journalEntry.setDate(LocalDate.now());
            JournalEntry savedJournalEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedJournalEntry);
            userService.save(user);
            return savedJournalEntry;
        }
      throw new UsernameNotFoundException("Please give valid User Name");
    }

    public List<JournalEntry> getAllEntries(String userName){
        User user = userService.findByName(userName);

        return user.getJournalEntries();
    }

    public JournalEntry findById(ObjectId userId, ObjectId journalId){
        User user = userService.findById(userId).orElse(null);
        if(user != null){
            JournalEntry journalEntry = user.getJournalEntries()
                                        .stream()
                                        .filter(x -> journalId.equals(x.getId()))
                                        .findFirst()
                                        .orElse(null);
            if(journalEntry != null){
                return journalEntry;
            }
        }
        return null;

    }

    public void deleteById(ObjectId journalId){
         journalEntryRepository.deleteById(journalId);
    }

    public JournalEntry updateById(ObjectId journalId,JournalEntry newJournalEntry, JournalEntry oldJournalEntry){
        oldJournalEntry.setTitle(newJournalEntry.getTitle() != null ? newJournalEntry.getTitle() : oldJournalEntry.getTitle());
        oldJournalEntry.setContent(newJournalEntry.getContent() !=null ? newJournalEntry.getContent() : oldJournalEntry.getContent());
        return journalEntryRepository.save(oldJournalEntry);
    }

}
