package com.example.restAPI10.services;

import com.example.restAPI10.entity.Users;
import com.example.restAPI10.exception.UserInputIsEmpty;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    List<Users> findAll();
    List<Users> search (String lastName, String city, String country);
    Users findById(int id);
    Users updateUser(int id, Users user);
    void deleteUserById(int id);
    Users save(Users user);

}
