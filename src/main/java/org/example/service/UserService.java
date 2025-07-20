package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public String addUser(User user){
        userRepo.save(user);
        return "User Added!";
    }
}
