package com.jcommerce.cars.service.sign;

import com.jcommerce.cars.api.request.RegisterRequest;
import com.jcommerce.cars.api.request.SignInRequest;
import com.jcommerce.cars.api.response.JwtResponse;
import com.jcommerce.cars.domain.Role;
import com.jcommerce.cars.domain.RoleName;
import com.jcommerce.cars.domain.User;
import com.jcommerce.cars.repository.RoleRepository;
import com.jcommerce.cars.repository.UserRepository;
import com.jcommerce.cars.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SignServiceImpl implements SignService{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public void signUp(RegisterRequest registerRequest){
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            //throw new UserExistException(translatorService.getMessage("user.already.exist.exception"));
            throw new RuntimeException();


        if (userRepository.existsByEmail(registerRequest.getEmail()))
            //throw new EmailExistException(translatorService.getMessage("email.already.exist.exception"));
            throw new RuntimeException();


        User user = new User(registerRequest.getName(), registerRequest.getUsername(), registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public JwtResponse signIn(SignInRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }
}
