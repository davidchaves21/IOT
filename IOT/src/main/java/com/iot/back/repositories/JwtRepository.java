package com.iot.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.back.models.entities.Jwt;

@Repository
public interface JwtRepository extends JpaRepository<Jwt, String>{


}
