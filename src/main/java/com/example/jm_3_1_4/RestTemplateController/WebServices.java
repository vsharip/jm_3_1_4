package com.example.jm_3_1_4.RestTemplateController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class WebServices {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("http://91.241.64.178:7081/api/users")
    public ResponseEntity<String> getAllUsers() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("http://91.241.64.178:7081/api/users")
    public ResponseEntity<String> createUser() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }


    @PutMapping("http://91.241.64.178:7081/api/users")
    public ResponseEntity<String> putMapping() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @DeleteMapping("http://91.241.64.178:7081/api/users/{id}")
    public ResponseEntity<String> deleteMapping(@PathVariable("id") Long id) {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
