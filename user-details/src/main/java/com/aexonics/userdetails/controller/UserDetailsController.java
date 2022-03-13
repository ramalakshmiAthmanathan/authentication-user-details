package com.aexonics.userdetails.controller;

import com.aexonics.userdetails.api.UserDetailsRequest;
import com.aexonics.userdetails.api.UserDetailsResponse;
import com.aexonics.userdetails.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-details")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/create")
    public UserDetailsResponse createUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
        return userDetailsService.createUserDetails(userDetailsRequest);
    }
    @DeleteMapping("/{userId}")
    public void deleteUserDetails(@PathVariable String userId){
        userDetailsService.deleteUserDetails(userId);
    }
    @PutMapping
    public UserDetailsResponse updateUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
        return userDetailsService.updateUserDetails(userDetailsRequest);
    }
    @GetMapping
    public List<UserDetailsResponse> getAllUserDetails(){
        return userDetailsService.getAllUserDetails();
    }
}
