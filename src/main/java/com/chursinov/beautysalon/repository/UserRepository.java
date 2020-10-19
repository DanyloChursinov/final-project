package com.chursinov.beautysalon.repository;

import com.chursinov.beautysalon.entity.User;

import java.util.List;


public interface UserRepository {
    User getUserByEmailAndPassword(String email, String password);
    void addUser (String name, String email, String password);
    User getUserByEmail (String email);
    List<User> GetUsersEmailForSendMessage (String date);
}
