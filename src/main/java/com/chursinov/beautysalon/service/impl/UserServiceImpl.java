package com.chursinov.beautysalon.service.impl;
import com.chursinov.beautysalon.entity.user.User;
import com.chursinov.beautysalon.repository.UserRepository;
import com.chursinov.beautysalon.service.UserService;


import java.util.List;


public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return repository.getUserByEmailAndPassword(email, password);
    }
    @Override
    public void addUser(String name, String email, String password) {
        repository.addUser(name, email, password);
    }

    @Override
    public List<User> GetUsersEmailForSendMessage(String date) {
        return repository.GetUsersEmailForSendMessage(date);
    }
}
