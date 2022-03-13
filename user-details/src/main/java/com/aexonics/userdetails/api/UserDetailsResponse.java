package com.aexonics.userdetails.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
}
