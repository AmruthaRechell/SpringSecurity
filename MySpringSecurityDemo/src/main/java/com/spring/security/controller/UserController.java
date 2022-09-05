package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.dto.AuthenticationRequest;
import com.spring.security.utils.JwtUtils;

@RestController
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value="/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException(authRequest.getUsername());
		}
		String jwtToken = jwtUtils.generateToken(authRequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public String viewAll() {
		return "Hello All";
	}
	
	@GetMapping("/user")
	public String viewUser() {
		return "Hello User";
	}
	
	@GetMapping("/admin")
	public String viewAdmin() {
		return "Hello Admin";
	}
}
