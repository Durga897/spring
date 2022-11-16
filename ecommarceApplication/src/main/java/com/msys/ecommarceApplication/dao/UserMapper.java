package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserMapper implements RowMapper<User> {
    public User mapRow(final ResultSet resultSet,final int rowNumber) throws SQLException {
       final User user = new User();
        user.setId(resultSet.getString("id"));
        user.setUserName(resultSet.getString("userName"));
        user.setEmailAddress(resultSet.getString("emailAddress"));
        user.setMobileNumber(resultSet.getString("mobileNumber"));
        user.setPassword(resultSet.getString("password"));
        user.setAddress(resultSet.getString("address"));
        return user;
    }

}
