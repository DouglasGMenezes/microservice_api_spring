package com.douglas.project.microservices_api_user.controller;

import com.douglas.project.microservices_api_user.dto.UserDTO;
import com.douglas.project.microservices_api_user.models.User;
import com.douglas.project.microservices_api_user.repository.UserRepository;
import com.douglas.project.microservices_api_user.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userDTO) {
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        } else {
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
        }
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
