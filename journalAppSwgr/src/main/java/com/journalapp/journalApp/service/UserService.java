package com.journalapp.journalApp.service;


import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repo;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public User saveNewUser(User user)
    {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            return repo.save(user);
        } catch (Exception e) {
            log.error("Error occured for {}:",user.getUsername(),e);
            log.warn("CHECKING WARN");
            log.info("CHECKING INFO");
            log.debug("CHECKING DEBUG");
            log.trace("CHECKING TRACE");
            return null;
        }
    }


    public User saveUser(User entry)
    {
        return repo.save(entry);
    }

    public boolean saveU(User user)
    {
        try {
            repo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }



    public List<User> getAll()
    {
        return repo.findAll();
    }

    public Optional<User> findById(ObjectId id)
    {
        return repo.findById(id);
    }

    public void DeleteById(ObjectId id)
    {
        repo.deleteById(id);
    }

    public User findByUsername(String username)
    {
        return repo.findByUsername(username).get();
    }

    public void saveAdmin(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        repo.save(user);
    }
}
