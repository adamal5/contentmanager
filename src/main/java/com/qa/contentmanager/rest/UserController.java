package com.qa.contentmanager.rest;

import com.qa.contentmanager.domain.User;
import com.qa.contentmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //specifically used for read functionality/ browsers only able to work with getmapping
    @GetMapping("/user")
    public List<User> getAllUser(){
        return this.userService.readAllUser();
    }

    // url for adding notes.. requestbody should include a note could be void depends on what you want to di with your abb
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    //long name must be same as end of url e.g id here
    @DeleteMapping("/deleteUser/{id}")
    public Boolean deleteUser(@PathVariable Long id){
        return this.userService.deleteUserById(id);
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        return this.userService.findUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }


}
