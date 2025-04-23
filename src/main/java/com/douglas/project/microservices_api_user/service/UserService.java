package com.douglas.project.microservices_api_user.service;

import com.douglas.project.microservices_api_user.models.User;
import com.douglas.project.microservices_api_user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
            return userRepository.save(user);
        }

}
