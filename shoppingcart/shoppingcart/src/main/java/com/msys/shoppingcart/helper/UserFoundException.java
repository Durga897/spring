package com.msys.shoppingcart.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("user with this Username already exist");
    }
}
