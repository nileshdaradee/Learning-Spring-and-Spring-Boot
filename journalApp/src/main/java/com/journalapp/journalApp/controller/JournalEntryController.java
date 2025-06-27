package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntry;
import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.service.JournalEntryService;
import com.journalapp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

   @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user=userService.findByUsername(username);
        System.out.println("username hai"+username);
        List<JournalEntry> all= user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry)
    {
        try {
            Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
            String username= authentication.getName();
            return new ResponseEntity<>(journalEntryService.saveNewEntry(journalEntry,username),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id)
    {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user=userService.findByUsername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty())
        {
            Optional<JournalEntry> journalEntry= journalEntryService.findById(id);

            if(journalEntry.isPresent())
            {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id )
    {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
         boolean removed=journalEntryService.DeleteById(id,username);
         if(removed)
         {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }



    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,@RequestBody JournalEntry entry)
    {

        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user=userService.findByUsername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());


        if(!collect.isEmpty())
        {
            Optional<JournalEntry> journalEntry= journalEntryService.findById(id);

            if(journalEntry.isPresent())
            {
                JournalEntry old=journalEntry.get();
                old.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")? entry.getTitle() : old.getTitle());
                old.setContent(entry.getContent()!=null && !entry.getContent().equals("")? entry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(HttpStatus.OK);

            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
