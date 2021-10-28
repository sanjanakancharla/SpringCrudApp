package com.example.restAPI10.services;

import com.example.restAPI10.dao.UserMapper;
import com.example.restAPI10.entity.Users;
import com.example.restAPI10.exception.UserAlreadyPresentException;
import com.example.restAPI10.exception.UserInputIsEmpty;
import com.example.restAPI10.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public List<Users> findAll(){
        log.debug("getUserList()");
        List<Users> usersList = userMapper.findAll();

        if(CollectionUtils.isEmpty(usersList)){

            log.error("DB is Empty");
            throw new UserNotFoundException();
        }
       return usersList;
    }

    public List<Users> search( String lastName, String city, String country){

        if(lastName==null || city==null || country==null)
        {
            log.error("User input is missing");

            throw new UserInputIsEmpty();
        }
        return userMapper.search(lastName, city, country);
    }

    public Users findById(int id){

        Users users = userMapper.findById(id);
        if(ObjectUtils.isEmpty(users))
        {
            log.error("No user with id {} is present",id);

            throw new UserNotFoundException();
        }
        return users;
    }

    public Users updateUser(int id, Users user){

        //Checking if user input is empty
        if(ObjectUtils.isEmpty(user))
        {
            log.error("No user input is given");

            throw new UserInputIsEmpty();
        }

        //Checking if user is not created
        Users users = findById(id);

        if (ObjectUtils.isEmpty(user)) {
            log.info("No user with id {} is created, so creating now",id);

            return save(users);
        }
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setCity(users.getCity());
        user.setCountry(users.getCountry());

         return userMapper.updateUser(user);
    }
    public void deleteUserById(int id){

        if(findById(id)==null)
        {
            log.error("No such id {} is present",id);
            throw new UserNotFoundException();
        }

         userMapper.deleteUserById(id);
    }

    public Users save(Users user){

        int id = user.getId();
        if(findById(id) !=null)
        {
            log.error("Existing user can't be created again");
            throw new UserAlreadyPresentException();
        }
        user.setId(user.getId());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setCity(user.getCity());
        user.setCountry(user.getCountry());

        return userMapper.save(user);
    }

}
