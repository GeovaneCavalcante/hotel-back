package com.hoteis.apirest.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteis.apirest.message.request.LoginForm;
import com.hoteis.apirest.message.request.SignUpForm;
import com.hoteis.apirest.message.response.JwtResponse;
import com.hoteis.apirest.models.User;
import com.hoteis.apirest.repository.UserRepository;
import com.hoteis.apirest.security.jwt.JwtProvider;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	@ApiOperation(value = "Autenticação")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	@PostMapping("/signup")
	@ApiOperation(value = "Registrar usuário")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Usuário já registrado!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		userRepository.save(user);

		return ResponseEntity.ok().body("{\"msg\": \"Usuário registrado\"}");
	}
}