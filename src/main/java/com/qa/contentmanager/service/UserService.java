package com.qa.contentmanager.service;

import com.qa.contentmanager.domain.Content;
import com.qa.contentmanager.domain.User;
import com.qa.contentmanager.exceptions.ContentNotFoundException;
import com.qa.contentmanager.repo.ContentRepository;
import com.qa.contentmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService (UserRepository repo) {
        this.repo = repo;
    }

    //read all content entries
    public List<User> readAllUser() {
        return this.repo.findAll();
    }

    //create a content entry and save to repo
    public User createUser(User user) {
        return this.repo.save(user);
    }

    //read content found by id
    public User findUserById(Long id) {
        return this.repo.findById(id).orElseThrow(ContentNotFoundException::new);
    }

    //update content by id
    public User updateUser(Long id, User user){
        User update = findUserById(id);
        update.setFirstName(user.getFirstName());
        update.setLastName(user.getLastName());
        update.setUserName(user.getUserName());
        update.setEmail(user.getEmail());
        update.setPassword(user.getPassword());
        return this.repo.save(update);
    }

    public boolean deleteUserById(Long id){
        if(!this.repo.existsById(id)){
            throw new ContentNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
