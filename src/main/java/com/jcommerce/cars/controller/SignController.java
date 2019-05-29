package com.jcommerce.cars.controller;

import com.jcommerce.cars.api.request.RegisterRequest;
import com.jcommerce.cars.api.request.SignInRequest;
import com.jcommerce.cars.api.response.JwtResponse;
import com.jcommerce.cars.service.sign.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SignController {

	@Autowired
	SignService signService;

	public SignController(SignService signService) {
		this.signService = signService;
	}


	@PostMapping("/signin")
	public JwtResponse authenticateUser(@RequestBody SignInRequest loginRequest) {
		return signService.signIn(loginRequest);
	}

	@PostMapping("/signup")
	public void signUp(@RequestBody RegisterRequest signUpRequest) {
		signService.signUp(signUpRequest);
	}
}