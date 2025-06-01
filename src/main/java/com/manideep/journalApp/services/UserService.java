package com.manideep.journalApp.services;

import com.manideep.journalApp.entities.JournalEntry;
import com.manideep.journalApp.entities.User;
import com.manideep.journalApp.repository.JournalEntryRepository;
import com.manideep.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
      return  userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId userId){
        return userRepository.findById(userId);

    }
    public User findByName(String userName){
        return userRepository.findByName(userName);
    }



    public void deleteById(ObjectId userId){
         userRepository.deleteById(userId);
    }

}
