package com.iot.back.services;

import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iot.back.config.ControllerResponse;
import com.iot.back.config.ControllerResponse.RegisterExceptionCode;
import com.iot.back.config.ControllerResponse.ResourceNotFoundExceptionCode;
import com.iot.back.config.ControllerResponse.TokenExceptionCode;
import com.iot.back.config.ControllerResponse.UserExceptionCode;
import com.iot.back.models.embedding.Login;
import com.iot.back.models.embedding.ShoppingCart;
import com.iot.back.models.embedding.UserDTO;
import com.iot.back.models.entities.Product;
import com.iot.back.models.entities.User;
import com.iot.back.models.entities.user.AddCart;
import com.iot.back.models.entities.user.ProductCart;
import com.iot.back.models.entities.user.PutCart;
import com.iot.back.models.entities.user.PutRegisterRequest;
import com.iot.back.models.entities.user.UserRegisterRequest;
import com.iot.back.models.services.exceptions.RegisterException;
import com.iot.back.models.services.exceptions.ResourceNotFoundException;
import com.iot.back.models.services.exceptions.TokenException;
import com.iot.back.repositories.ProductRepository;
import com.iot.back.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AuthenticationService authenticationService;

	protected BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User user(String userId) {
		Optional<User> find = userRepository.findById(userId);
		if (find.isPresent()) {
			User user = find.get();
			return user;
		}
		throw new ResourceNotFoundException(userId);
	}

	@Transactional
	public URI save(UserRegisterRequest userRequest) {
		User user = convertUserRequestToUser(userRequest);

		Login login = user.getLogin();
		login.setPassword(passwordEncoder.encode(login.getPassword()));

		user.setLogin(login);
		URI newUri = null;

		user = userRepository.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}").buildAndExpand(user.getUser_id())
				.toUri();

		String path = uri.getPath().replace("/register", ""); // Remove o segmento "/register" do caminho
		try {
			newUri = new URI(uri.getScheme(), uri.getAuthority(), path, uri.getQuery(), uri.getFragment());
		} catch (Exception e) {

		}

		return newUri;
	}

	private User convertUserRequestToUser(UserRegisterRequest request) {
		User user = new User();

		if (request.getNome() == null) {
			throw new RegisterException(RegisterExceptionCode.NAME);
		}
		if (request.getEmail() == null) {
			throw new RegisterException(RegisterExceptionCode.EMAIL);
		}
		if (request.getPassword() == null || (request.getPassword().length() < 6)) {
			throw new RegisterException(RegisterExceptionCode.PASSWORD +request.getPassword() );
		}
		if (request.getUsername() == null || (request.getUsername().length() < 6)) {
			throw new RegisterException(RegisterExceptionCode.USERNAME + request.getUsername()  );
		}

		user.setNome(request.getNome());
		user.setEmail(request.getEmail().toLowerCase());
		user.setLogin(new Login(request.getUsername().toLowerCase(), request.getPassword()));

		return user;
	}

	public User loadUserSession(String authorizationHeader) throws TokenException {
		if (authorizationHeader != null) {
			String token = authorizationHeader.replace("Bearer ", "");
			var subject = tokenService.getSubject(token);
			var user = authenticationService.loadUserByUsername(subject);
			return (User) user;
		}
		throw new TokenException(TokenExceptionCode.INVALID);

	}

	public void put(String authorizationHeader, PutRegisterRequest request) {
		User user = loadUserSession(authorizationHeader);
		if (request.getImage() != null) {
			user.setImage(request.getImage());
			userRepository.save(user);

		}

	}

	public UserDTO readID(String id) {
		return new UserDTO(user(id));
	}

	/*
	 * PRODUTOS
	 */
	public Product product(String id) {
		Optional<Product> find = productRepository.findById(id);
		if (find.isPresent()) {

			return find.get();
		}
		throw new ResourceNotFoundException(ResourceNotFoundExceptionCode.PRODUCT_ID + id);
	}

	public List<Product> products() {
		return productRepository.findAll();
	}
	/*
	 * Shopping cart
	 * 
	 */

	public void addCart(String authorizationHeader, AddCart product) {
		User user = loadUserSession(authorizationHeader);

		// TokenException(TokenExceptionCode.INVALID);
		Product item = product(product.getProduct_id());
		ShoppingCart cart = new ShoppingCart(item.getId(), 1);
		if (user.getShoppingCart() == null) {
			user.getShoppingCart().add(cart);
			userRepository.save(user);
		} else if (!user.getShoppingCart().contains(cart)) {
			user.getShoppingCart().add(cart);
			userRepository.save(user);
		} else {
			throw new RegisterException(UserExceptionCode.PRODUTO_NO_CART);
		}

	}
	public void deleteCart(String authorizationHeader, String product) {
		User user = loadUserSession(authorizationHeader);

		// TokenException(TokenExceptionCode.INVALID);
		Product item = product(product);
		ShoppingCart cart = new ShoppingCart(item.getId(), 1);
		if (user.getShoppingCart() == null) {
			
		} else if (user.getShoppingCart().contains(cart)) {
			user.getShoppingCart().remove(cart);
			userRepository.save(user);
		} else {
			throw new RegisterException(UserExceptionCode.PRODUTO_IN_CART);
		}

	}

	public void putCart(String authorizationHeader, PutCart product) {
		User user = loadUserSession(authorizationHeader);
		// TokenException(TokenExceptionCode.INVALID);
		Boolean adicionado = false;
		Product item = product(product.getProduct_id());
		// ResourceNotFoundException( ResourceNotFoundExceptionCode.PRODUCT_ID + id);
		for (ShoppingCart cart : user.getShoppingCart()) {
			if (cart.getProductId().equals(product.getProduct_id())) {
				if (product.getAction().equals("+")) {
					if (cart.getQuantity() + 1 > item.getQuantity()) {
						throw new RegisterException(UserExceptionCode.STOCK);
					} else {
						cart.setQuantity(cart.getQuantity() + 1);
						userRepository.save(user);
						break;
					}
				} else {
					if (cart.getQuantity() -1  == 0) {
						throw new RegisterException(UserExceptionCode.ZERO);
					} else {
						cart.setQuantity(cart.getQuantity() - 1);
						userRepository.save(user);
						break;
					}
				}
			}
		}
	}

	public List<ProductCart> shoppingCart(String authorizationHeader) {
		User user = loadUserSession(authorizationHeader);
		// TokenException(TokenExceptionCode.INVALID);
		List<ProductCart> products = new ArrayList<>();

		for (ShoppingCart cart : user.getShoppingCart()) {
			ProductCart product = new ProductCart();
			Product item = product(cart.getProductId());

			product.setId(item.getId());
			product.setImage(item.getImage());
			product.setName(item.getName());
			product.setPrice(item.getPrice() * cart.getQuantity());
			product.setQuantity(cart.getQuantity());
			products.add(product);
		}

		return products;
	}
}
