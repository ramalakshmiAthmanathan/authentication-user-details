package com.aexonics.userdetails.controller;

import com.aexonics.userdetails.api.EncodedUserCredentials;
import com.aexonics.userdetails.api.JwtResponse;
import com.aexonics.userdetails.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    AuthenticationService authService;
    /**
     * Accepts userName password of users and returns boolean
     * @param encodedUserCredentials
     * @return authenticationResult
     */
    @PostMapping
    public JwtResponse authenticateUser(@RequestBody EncodedUserCredentials encodedUserCredentials) {
        //Accepts user credentials as Request and provides JWT
        return authService.getJwtTokenIfCredentialsValid(encodedUserCredentials.getCredentials());
    }
}
