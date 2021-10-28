package com.example.restAPI10.dao;

import com.example.restAPI10.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Users> findAll();
    List<Users> search (String lastName, String city, String country);
    Users findById (int id);
    Users updateUser(Users user);
    void deleteUserById(int id);
    Users save(Users user);

}


