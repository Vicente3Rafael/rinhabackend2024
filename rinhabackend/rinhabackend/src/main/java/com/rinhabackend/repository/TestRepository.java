package com.rinhabackend.repository;

import com.rinhabackend.entity.User;
import com.rinhabackend.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@Repository
public class TestRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    public User getUserById(int id) {
        String sql = "select * from USERS where id = ?";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), id);

        return users.isEmpty() ? null: users.get(0);
    }
}
