package com.example.restAPI10.service;

import com.example.restAPI10.controllers.UserController;
import com.example.restAPI10.dao.UserMapper;
import com.example.restAPI10.entity.Users;
import com.example.restAPI10.services.UserService;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.example.restAPI10.services.UserServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTests {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserMapper userMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserGetAPI() {

        List<Users> list = new ArrayList<Users>();
        Users user1 = new Users(8, "kim", "k", "chicago", "usa");
        Users user2 = new Users(2, "Alex", "j", "new york","usa");

        list.add(user1);
        list.add(user2);

        when(userMapper.findAll()).thenReturn(list);

        //test
        List<Users> userList = userService.findAll();

        assertEquals(2, userList.size());

        verify(userMapper, times(1)).findAll();
    }

    @Test
    public void testSearchAPI(){
        String lastName="k";
        String city = "hyd";
        String country="india";
        Users user1 = new Users(1, "sanjana", lastName, city, country);
        List<Users> list = new ArrayList<Users>();
        list.add(user1);
        when(userMapper.search(lastName, city, country)).thenReturn(list);

        //test
        List<Users> userList = userService.search(lastName, city, country);

        assertNotNull(userList);
        assertEquals(userList,list);
    }

    @Test
    public void testFindByIdAPI(){

        when(userMapper.findById(2)).thenReturn(new Users(2,"kris","j","austin", "usa"));

        Users user = userService.findById(2);

        assertEquals("kris", user.getFirstName());
        assertEquals("j", user.getLastName());
        assertEquals("usa", user.getCountry());
    }

    @Test
    public void testUpdateUserAPI(){
        Users user = new Users();
        user.setFirstName("mary");
        int id=4;
        when(userMapper.findById(id)).thenReturn(user);
        when(userMapper.updateUser(id, user)).thenReturn(user);
        Users created = userService.updateUser(id, user);
        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
        verify(userMapper).updateUser(id, user);
    }

}

