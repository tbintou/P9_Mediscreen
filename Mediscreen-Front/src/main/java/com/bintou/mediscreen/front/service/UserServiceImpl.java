package com.bintou.mediscreen.front.service;


import com.bintou.mediscreen.front.model.User;
import com.bintou.mediscreen.front.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null){
            log.info("Find a user by name" + username);
            return user;
        } else {
            log.error("This username cannot be found" + username);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            log.info("Find a user by id");
            return userOptional.get();
        } else {
            log.error("this id doesn't exist");
        }
        return null;
    }

    @Override
    public Boolean updateUser(int id, User user) {
        boolean updated = false;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User newUser = new User();
            newUser.setFullname(user.getFullname());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
            updated = true;
            log.info("User with id " + id + " is updated as " + user);
        } else {
            log.info("Failed to update User with id " + id + " as " + user);
        }
        return updated;
    }

    @Override
    public void save(User user) {
        User newUser = new User();
        newUser.setFullname(user.getFullname());
        newUser.setUsername(user.getUsername());
        newUser.setRole(user.getRole());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        log.info("New User " + newUser + " is created!");
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
        log.info("this user " + user + " is deleted !");
    }
}

