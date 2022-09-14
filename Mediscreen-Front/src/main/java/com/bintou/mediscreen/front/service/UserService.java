package com.bintou.mediscreen.front.service;

import com.bintou.mediscreen.front.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> findAll();
    User findById(int id);
    Boolean updateUser(int id, User user);
    void save(User user);
    void delete(User user);
}

