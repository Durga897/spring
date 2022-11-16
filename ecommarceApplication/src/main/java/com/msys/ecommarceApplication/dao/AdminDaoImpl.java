package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.Admin;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AdminDaoImpl implements AdminDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Admin login(final Admin admin) {
        final String sql = "SELECT * FROM admin WHERE id='" + admin.getId() + "' and password='"+admin.getPassword()+"'";
        final List<Admin> admins= jdbcTemplate.query(sql, (rs, rowNum) ->
                new Admin(rs.getString("id"),
                        rs.getString("password")));
        if (admins.size() > 0) {
            return admins.get(0);
        }
        return null;
    }
}
