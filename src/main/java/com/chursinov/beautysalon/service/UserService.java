package com.chursinov.beautysalon.service;

import com.chursinov.beautysalon.entity.User;

import java.util.List;


public interface UserService {

    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);
    void addUser (String name, String email, String password);
    List<User> GetUsersEmailForSendMessage (String date);



}
