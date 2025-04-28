package com.douglas.project.microservices_api_user.service;

import com.douglas.project.microservices_api_user.models.User;
import com.douglas.project.microservices_api_user.producer.UserProducer;
import com.douglas.project.microservices_api_user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository,UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User saveUser(User user) {
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }

}
