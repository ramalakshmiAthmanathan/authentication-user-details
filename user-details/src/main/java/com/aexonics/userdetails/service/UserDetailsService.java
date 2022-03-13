package com.aexonics.userdetails.service;

import com.aexonics.userdetails.api.UserDetailsRequest;
import com.aexonics.userdetails.api.UserDetailsResponse;
import com.aexonics.userdetails.entity.UserDetails;
import com.aexonics.userdetails.mapper.UserDetailsMapper;
import com.aexonics.userdetails.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    UserDetailsMapper userDetailsMapper;

    public UserDetailsResponse createUserDetails(UserDetailsRequest userDetailsRequest){
        return userDetailsMapper.convertUserEntityToUserResponse(
                userDetailsRepository.save(
                        userDetailsMapper.convertUserRequestToUserEntity(userDetailsRequest)));
    }
    public void deleteUserDetails(String userId){
        userDetailsRepository.deleteById(UUID.fromString(userId));
    }

    public UserDetailsResponse updateUserDetails(UserDetailsRequest userDetailsRequest) {
        List<UserDetails> userDetailsList = userDetailsRepository.findAllByEmail(userDetailsRequest.getEmail());
        UserDetails userDetails = userDetailsMapper.convertUserRequestToUserEntity(userDetailsRequest);
        userDetails.setUserId(userDetailsList.get(0).getUserId());

        return userDetailsMapper.convertUserEntityToUserResponse(userDetailsRepository.save(userDetails));
    }

    public List<UserDetailsResponse> getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        return userDetailsList.stream().map(
                userDetailsMapper :: convertUserEntityToUserResponse ).collect(Collectors.toList());
    }
}
