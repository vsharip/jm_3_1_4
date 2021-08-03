package com.example.jm_3_1_4;



import com.example.jm_3_1_4.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;
import org.w3c.dom.Entity;

import java.util.Arrays;

@SpringBootApplication
public class Jm314Application {




    public static void main(String[] args) {

        User user = new User(3L, "James", "Brown", (byte) 44);
        User userPut = new User(3L, "Thomas", "Shelby", (byte) 44);

        SpringApplication.run(Jm314Application.class, args);

        String url = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        MediaType mediaType = response.getHeaders().getContentType();
        HttpHeaders status = response.getHeaders();
        System.out.println(response.getBody());
        System.out.println(mediaType);
        System.out.println(status);

        System.out.println("Поехали");

        String cookie = status.getFirst(HttpHeaders.SET_COOKIE);  // COOKIE

        System.out.println(cookie);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("COOKIE", cookie);

        HttpEntity<User> postEnt = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, postEnt, String.class);

        HttpHeaders httpHeaders1 = postForEntity.getHeaders();
        System.out.println("1 код: " + postForEntity.getBody());
        System.out.println(httpHeaders1.getFirst(HttpHeaders.SET_COOKIE));


        HttpEntity<User> putEnt = new HttpEntity<>(userPut, httpHeaders);
        ResponseEntity<String> putForEntity = restTemplate.exchange(url, HttpMethod.PUT, putEnt, String.class);
        HttpHeaders httpHeaders2 = putForEntity.getHeaders();
        String cookie2 = httpHeaders2.getFirst(HttpHeaders.SET_COOKIE);

        System.out.println("2 код" + putForEntity.getBody());
        System.out.println(cookie2);

        HttpEntity<User> deleteEnt = new HttpEntity<>(httpHeaders);


        ResponseEntity<String> deleteForEntity = restTemplate.exchange(url + "/3", HttpMethod.DELETE, deleteEnt, String.class);
        System.out.println("3 код: " + deleteForEntity.getBody());

        StringBuilder builder = new StringBuilder();
        builder.append(postForEntity.getBody());
        builder.append(putForEntity.getBody());
        builder.append(deleteForEntity.getBody());

        System.out.println(builder);

    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
