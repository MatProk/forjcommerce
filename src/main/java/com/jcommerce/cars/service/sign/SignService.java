package com.jcommerce.cars.service.sign;

import com.jcommerce.cars.api.request.RegisterRequest;
import com.jcommerce.cars.api.request.SignInRequest;
import com.jcommerce.cars.api.response.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public interface SignService {
    void signUp(RegisterRequest signUpRequest);

    JwtResponse signIn(SignInRequest signInRequest);
}
