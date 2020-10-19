package com.chursinov.beautysalon.repository;

import com.chursinov.beautysalon.entity.User;


public interface UserRepository {
    User getUserByEmailAndPassword(String email, String password);
    void addUser (String name, String email, String password);
    User getUserByEmail (String email);
}
