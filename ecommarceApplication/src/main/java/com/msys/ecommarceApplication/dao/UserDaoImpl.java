package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    final private JdbcTemplate jdbcTemplate;

    @Override
    public int saveUser(final User user) {
        final String sqlSelectStatement = "SELECT count(*) FROM user WHERE id = '" + user.getId() + "' or emailAddress='"
                + user.getEmailAddress() + "'";
        int count = jdbcTemplate.queryForObject(sqlSelectStatement, Integer.class);
        if (count == 0) {
            final Object[] sqlParameter = {user.getId(), user.getUserName(), user.getEmailAddress(), user.getMobileNumber(),
                    user.getPassword(), user.getAddress()};
            final String sqlInsertStatement = "insert into user(id,userName,emailAddress,mobileNumber,password,address)" +
                    " values(?,?,?,?,?,?)";
            jdbcTemplate.update(sqlInsertStatement, sqlParameter);
        }
        return count;
    }

    @Override
    public User login(final User user) {
        final String sqlSelectStatement = "SELECT * FROM user WHERE id = '" + user.getId() + "'";
        final List<User> users = jdbcTemplate.query(sqlSelectStatement, new UserMapper());
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void deleteUser(final String emailAddress) {
        final String SQL = "delete from user where emailAddress = ?";
        jdbcTemplate.update(SQL, emailAddress);
    }

    @Override
    public User fetchUser(final String userId) {
        final String sql = "select * from user where id='" + userId + "'";
        final List<User> user = jdbcTemplate.query(sql, new UserMapper());
        return user.size() > 0 ? user.get(0) : null;
    }

    @Override
    public void updateProfile(final User user) {
        final String sql = "update user set userName = '" + user.getUserName() + "',mobileNumber='" + user.getMobileNumber()
                + "' where emailAddress = '" + user.getEmailAddress() + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<User> fetchAllUser(final User user) {
        final String sql = "select * from product";
        final List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }
}
