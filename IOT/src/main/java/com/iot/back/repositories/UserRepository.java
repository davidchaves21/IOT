package com.iot.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.back.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findById(String id);
    User findByLogin_Username(String username);
   


}
