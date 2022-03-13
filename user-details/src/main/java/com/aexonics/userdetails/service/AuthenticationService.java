package com.aexonics.userdetails.service;

import com.aexonics.userdetails.api.JwtResponse;
import com.aexonics.userdetails.api.UserCredentials;
import com.aexonics.userdetails.entity.UserDetails;
import com.aexonics.userdetails.repository.UserDetailsRepository;
import com.aexonics.userdetails.utils.JwtTokenUtil;
import com.aexonics.userdetails.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public JwtResponse getJwtTokenIfCredentialsValid(String encodedUserCredentials ){
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedCredentials = new String(decoder.decode(encodedUserCredentials));
        System.out.println(decodedCredentials);
        JwtResponse jwtResponse = new JwtResponse();

        List<UserDetails> userDetails = userDetailsRepository.findAllByEmail(
                decodedCredentials.split(":")[0]);
        String hashedPassword = userDetails.get(0).getPassword();

        if(PasswordUtils.validatePassword(decodedCredentials.split(":")[1],hashedPassword)){
            UserCredentials userCredentials = new UserCredentials(
                    decodedCredentials.split(":")[0],decodedCredentials.split(":")[1]);
            jwtResponse = new JwtResponse(
                    jwtTokenUtil.generateToken(userCredentials),JwtTokenUtil.JWT_TOKEN_VALIDITY,"bearer-token"
            );
        }
        return jwtResponse;
    }
}
