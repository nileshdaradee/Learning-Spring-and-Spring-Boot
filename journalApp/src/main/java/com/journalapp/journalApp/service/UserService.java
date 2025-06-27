package com.journalapp.journalApp.service;


import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
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
public class UserService {

    @Autowired
    private UserRepository repo;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return repo.save(user);
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
