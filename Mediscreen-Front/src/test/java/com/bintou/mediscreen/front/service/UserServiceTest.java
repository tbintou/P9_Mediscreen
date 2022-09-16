package com.bintou.mediscreen.front.service;

import com.bintou.mediscreen.front.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void registrationUser() {

        User newUser = new User();
        newUser.setId(1);
        newUser.setUsername("Username5");
        newUser.setFullname("Megane");
        newUser.setPassword("test#Bbbbbbbb7");
        newUser.setRole("USER");
        userService.save(newUser);
    }

    @Test
    public void updateUser() {

        //  User user = userService.findByUsername("Alexandre123");
        User user = new User();
        int idUser = 4;
        user.setUsername("Alexandre1");
        user.setFullname("Fullname");
        user.setPassword("Password56*");
        user.setRole("ADMIN");
        userService.updateUser(idUser, user);
        Assertions.assertEquals("Alexandre1", user.getUsername());
        Assertions.assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void findById() {

        int idUser = 46;
        userService.findByUsername("Username5");
        userService.findById(idUser);
        Assertions.assertNotNull(46);
    }
    @Test
    public void findByUsername(){

        User newUser = userService.findByUsername("Username5");
        Assertions.assertNotNull(newUser);
    }

    @Test
    public void deletedUser() {
        User user = userService.findByUsername("Username5");
        userService.delete(user);
        User userByUsername = userService.findByUsername("Username5");
        Assertions.assertNull(userByUsername);
    }
}

