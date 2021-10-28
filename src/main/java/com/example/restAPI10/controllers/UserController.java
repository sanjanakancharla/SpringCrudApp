package com.example.restAPI10.controllers;

import com.example.restAPI10.entity.Users;
import com.example.restAPI10.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        log.info("WELCOME TO THE APP");
        return "WELCOME";
    }

    // -------------------Retrieve All Users--------------------------------------------
    @GetMapping("/users")
    public List<Users> getAll() {
        log.info("Fetching Users");
        return userService.findAll();
    }


   //Search users with firstname, city and country
    @RequestMapping("/search")
    public List<Users> search( @RequestParam(value="lastName", required = false) String lastName, @RequestParam(value="city", required = false) String city,
                               @RequestParam(value="country",required = false) String country)

    {
        log.info("Searching users....");
        return userService.search(lastName, city, country);
    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Users getUser(@PathVariable("id") int id) {
        log.info("Fetching User with id {}", id);
        return userService.findById(id);

    }

    // ------------------- Create a User ------------------------------------------------

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Users createUser(@RequestBody Users user) {

        log.info("Creating a new user");

        return userService.save(user);

    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Users updateUser(@PathVariable("id") int id, @RequestBody Users user) {

        log.info("Updating Product with id {}", id);

        return userService.updateUser(id,user);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        log.info("Fetching & Deleting User with id {}", id);

        userService.deleteUserById(id);
        log.info("Deleted User with id {} successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}