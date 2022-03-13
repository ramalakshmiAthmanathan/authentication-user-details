package com.aexonics.userdetails.mapper;


import com.aexonics.userdetails.api.UserDetailsRequest;
import com.aexonics.userdetails.api.UserDetailsResponse;
import com.aexonics.userdetails.entity.UserDetails;
import com.aexonics.userdetails.utils.PasswordUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {
    public UserDetails convertUserRequestToUserEntity(UserDetailsRequest userDetailsRequest){
        UserDetails userDetails = new UserDetails();

        userDetails.setFirstName(userDetailsRequest.getFirstName());
        userDetails.setLastName(userDetailsRequest.getLastName());
        userDetails.setEmail(userDetailsRequest.getEmail());
        userDetails.setMobileNumber(userDetailsRequest.getMobileNumber());
        userDetails.setAddress(userDetailsRequest.getAddress());
        userDetails.setPassword(PasswordUtils.encodePasswordWithHashAndSalt(userDetailsRequest.getPassword()));

        return userDetails;
    }
    public UserDetailsResponse convertUserEntityToUserResponse(UserDetails userDetails){
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

        userDetailsResponse.setFirstName(userDetails.getFirstName());
        userDetailsResponse.setLastName(userDetails.getLastName());
        userDetailsResponse.setEmail(userDetails.getEmail());
        userDetailsResponse.setMobileNumber(userDetails.getMobileNumber());
        userDetailsResponse.setAddress(userDetails.getAddress());

        return userDetailsResponse;
    }
}
