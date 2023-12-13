package com.iot.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iot.back.config.ControllerResponse;
import com.iot.back.models.embedding.UserDTO;
import com.iot.back.models.entities.Product;
import com.iot.back.models.entities.User;
import com.iot.back.models.entities.user.AddCart;
import com.iot.back.models.entities.user.LoginRequest;
import com.iot.back.models.entities.user.ProductCart;
import com.iot.back.models.entities.user.PutCart;
import com.iot.back.models.entities.user.PutRegisterRequest;
import com.iot.back.models.entities.user.TokenResponse;
import com.iot.back.models.entities.user.UserRegisterRequest;
import com.iot.back.services.EmailService;
import com.iot.back.services.TokenService;
import com.iot.back.services.UserService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private EmailService emailService;

	/*
	 * POST
	 */
	@Tag(name = "User Controller - Free route")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) LoginRequest login) {
		HttpHeaders responseHeaders = new HttpHeaders();
		String authorization = "Bearer " + tokenService.createToken(login);
		TokenResponse token = new TokenResponse(authorization);
		responseHeaders.set("Authorization", authorization);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).headers(responseHeaders).body(token);

	}

	@Tag(name = "User Controller - Free route")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@PostMapping("/register")
	public ResponseEntity<Void> register(
			@RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) UserRegisterRequest user) {
		return ResponseEntity.created(service.save(user)).contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}


	@Tag(name = "User Controller - Free route")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@PutMapping("/putRegister")
	public ResponseEntity<Void> put(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) PutRegisterRequest request) {

		service.put(authorizationHeader,request);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}
	@Tag(name = "User Controller - Free route")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@GetMapping("/whoAmI")
	public ResponseEntity<User> whoAmI(@RequestHeader("Authorization") String authorizationHeader) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.loadUserSession(authorizationHeader)); // codigo 201
	}
	/*
	 * PRODUCTS
	 */
	@Tag(name = "Product Controller")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@GetMapping("/feed")
	public ResponseEntity<List<Product>> products(@RequestHeader("Authorization") String authorizationHeader) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.products()); // codigo 201
	}
	/*
	 * SHOPPINGCART
	 */
	@Tag(name = "Product Controller")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@GetMapping("/shoppingCart")
	public ResponseEntity<List<ProductCart>> shoppingCart(@RequestHeader("Authorization") String authorizationHeader) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.shoppingCart(authorizationHeader)); // codigo 201
	}
	
	@Tag(name = "Product Controller")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@PostMapping("/shoppingCart")
	public ResponseEntity<Void> addShoppingCart(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) AddCart product) {
		service.addCart(authorizationHeader, product);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}
	
	@Tag(name = "Product Controller")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@PutMapping("/shoppingCart")
	public ResponseEntity<Void> putShoppingCart(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) PutCart product) {
		service.putCart(authorizationHeader, product);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}
	
	@Tag(name = "Product Controller")
	@SecurityRequirement(name = ControllerResponse.SECURITY_REQUERIMENT)
	@DeleteMapping("/shoppingCart/{id}")
	public ResponseEntity<Void> deleteShoppingCart(@RequestHeader("Authorization") String authorizationHeader, @PathVariable() String id) {
		service.deleteCart(authorizationHeader, id);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}
	
	
}
