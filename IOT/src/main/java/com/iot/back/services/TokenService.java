package com.iot.back.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.iot.back.config.ControllerResponse;
import com.iot.back.config.ControllerResponse.RegisterExceptionCode;
import com.iot.back.config.ControllerResponse.TokenExceptionCode;
import com.iot.back.models.entities.User;
import com.iot.back.models.entities.user.LoginRequest;
import com.iot.back.models.services.exceptions.FilterExceptionHandler;
import com.iot.back.models.services.exceptions.RegisterException;
import com.iot.back.models.services.exceptions.TokenException;
import com.iot.back.repositories.JwtRepository;

@Service
public class TokenService extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager authentication;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtRepository jwtRepository;

	protected String secret = "B13djlmcdll032023";
	protected String issuer = "Companies";

	public String createToken(LoginRequest login) {
		if (login.getUsername()== null || login.getUsername().length()<4 ) {
			throw new RegisterException(RegisterExceptionCode.USERNAME);
		}
		if(login.getPassword()==null || login.getPassword().length()<6) {
			throw new RegisterException(ControllerResponse.RegisterExceptionCode.PASSWORD);
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername().toLowerCase(), login.getPassword());

		Authentication authenticate = authentication.authenticate(usernamePasswordAuthenticationToken);

		var user = (User) authenticate.getPrincipal();

		return JWT.create().withIssuer(issuer) // oq pertence
				.withSubject(user.getLogin().getUsername()) // quem é
				.withClaim("id", user.getUser_id())// id od user
				.withExpiresAt(LocalDateTime.now().plusMinutes(60) // tempo em minutos da sessao do token
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256(secret)); // Algoritmo para gerar, quanto mais complicada a palavra mais dificil
													// o segredo
	}

	public String getTokenId(String token) {
		String id = null;
		try {
			id = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build().verify(token).getId();

		} catch (TokenExpiredException e) {
			throw new TokenException(TokenExceptionCode.EXPIRED);
		}
		return id;
	}

	public String getSubject(String token) throws TokenException {
		String subject;

		try {
			subject = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build().verify(token).getSubject();

		} catch (TokenExpiredException e) {
			throw new TokenException(TokenExceptionCode.EXPIRED);
		}
		return subject;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		// sempre que for passar por alguma rota, ele vai passar por esse filtro
		// primeiro
		try {
			String token;

			var authorizationHeader = request.getHeader("Authorization");
			if (authorizationHeader != null) {
				token = authorizationHeader.replace("Bearer ", "");
				if (getTokenId(token) != null) {
					if (!("POST".equals(request.getMethod())
							&& "/resetPassword".equals(request.getServletPath()))) {
						throw new TokenException( TokenExceptionCode.TOKEN_FOR_RESET);

					}
				}

				String subject = getSubject(token);

				User user = (User) authenticationService.loadUserByUsername(subject);

				var authentication = new UsernamePasswordAuthenticationToken(user, user.isCredentialsNonExpired(),
						user.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			FilterExceptionHandler filterExceptionHandler = new FilterExceptionHandler();
			filterExceptionHandler.resolveException(request, response, filterExceptionHandler, e);
		}
	}

}
