package com.jcommerce.cars.service.profile;

import com.jcommerce.cars.domain.User;
import com.jcommerce.cars.repository.UserRepository;
import com.jcommerce.cars.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getProfile(UserPrinciple userPrinciple) {
        return userRepository.findByUsername(userPrinciple.getUsername()).get();
    }
}
