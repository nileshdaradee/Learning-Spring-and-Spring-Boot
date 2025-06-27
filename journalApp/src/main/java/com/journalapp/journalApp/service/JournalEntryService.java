package com.journalapp.journalApp.service;
import com.journalapp.journalApp.entity.JournalEntry;
import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public User saveNewEntry(JournalEntry journalEntry, String username)
    {
        try {
            User user=userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved= journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            return user;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error Occured while saving the entry");
        }

    }
    public void saveEntry(JournalEntry journalEntry)
    {

        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> findAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean DeleteById(ObjectId id, String username)
    {
        boolean removed=false;
        try {

            User user=userService.findByUsername(username);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));

            if(removed)
            {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }



        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An Error occured while deleting the entry.",e);
        }

        return removed;

    }



    public List<JournalEntry> findByUserName(String userName)
    {
        return null;
    }





}
