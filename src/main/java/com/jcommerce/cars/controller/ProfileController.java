package com.jcommerce.cars.controller;

import com.jcommerce.cars.domain.User;
import com.jcommerce.cars.security.services.UserPrinciple;
import com.jcommerce.cars.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal UserPrinciple userPrinciple){
        return profileService.getProfile(userPrinciple);
    }
}
