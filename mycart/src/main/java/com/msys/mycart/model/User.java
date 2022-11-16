package com.msys.mycart.model;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id;
    private String address;
    @Column(name = "email_address",unique=true)
    private String emailAddress;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
}
