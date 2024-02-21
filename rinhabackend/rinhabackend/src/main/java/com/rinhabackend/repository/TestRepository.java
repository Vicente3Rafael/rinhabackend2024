package com.rinhabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Repository
public class TestRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> test() {
        String sql = "select * from countries";

        return jdbcTemplate.queryForList(sql);
    }
}
