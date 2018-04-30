package com.example.banktest.services;

import com.example.banktest.models.User;
import com.example.banktest.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findOneByName(name);
    }

    public void addUser(String name) {
        userRepository.save(new User(name));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
