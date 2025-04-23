package com.douglas.project.microservices_api_user.repository;

import com.douglas.project.microservices_api_user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

}
