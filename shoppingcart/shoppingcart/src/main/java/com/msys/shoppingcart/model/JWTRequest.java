package com.msys.shoppingcart.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JWTRequest {
    private String username;
    private String password;
}
