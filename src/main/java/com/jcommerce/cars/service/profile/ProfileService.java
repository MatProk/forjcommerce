package com.jcommerce.cars.service.profile;

import com.jcommerce.cars.domain.User;
import com.jcommerce.cars.security.services.UserPrinciple;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    User getProfile(UserPrinciple userPrinciple);
}
