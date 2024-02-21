package com.rinhabackend.controller;

import com.rinhabackend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rest-test")
public class RestTest {

    @Autowired TestRepository testRepository;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        List<Map<String, Object>> list = testRepository.test();

        return ResponseEntity.ok(list);
    }
}
