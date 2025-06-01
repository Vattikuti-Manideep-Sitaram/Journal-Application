package com.manideep.journalApp.services;

import com.manideep.journalApp.entities.User;
import com.manideep.journalApp.repository.JournalEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JornalEntryServiceTest {

        @InjectMocks
        JournalEntryService journalEntryService;

        @Mock
        private JournalEntryRepository journalEntryRepository;

        @Mock
        private UserService userService;

        private User user;

    @BeforeEach
    void setup(){
         user = User.builder()
                .name("kanna")
                .password("password")
                .roles(List.of("ADMIN"))
                .build();
    }

    @Test
    void getAllEntriesTest(){
        when(userService.findByName(anyString())).thenReturn(user);
        assertEquals(new ArrayList<>(),journalEntryService.getAllEntries("Something"));
    }

}
