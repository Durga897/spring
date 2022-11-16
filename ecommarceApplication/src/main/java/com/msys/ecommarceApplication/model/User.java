package com.msys.ecommarceApplication.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String id;
    private String userName;
    private String emailAddress;
    private String mobileNumber;
    private String password;
    private String address;
}
