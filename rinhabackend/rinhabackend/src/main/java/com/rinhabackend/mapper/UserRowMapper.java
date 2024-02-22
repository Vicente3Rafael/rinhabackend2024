package com.rinhabackend.mapper;

import com.rinhabackend.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper  implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(1));
        user.setLimite(rs.getInt(2));
        user.setSaldoInicial(rs.getInt(3));

        return user;
    }
 }
