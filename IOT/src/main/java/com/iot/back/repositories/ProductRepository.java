package com.iot.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.back.models.entities.Product;
import com.iot.back.models.entities.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	Optional<Product> findById(String id);
	Optional<Product> findByName(String name);
   


}
