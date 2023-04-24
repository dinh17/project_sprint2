package com.example.jssport_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JssportBackEndApplication {

    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//      System.out.println(passwordEncoder.encode("123"));
        SpringApplication.run(JssportBackEndApplication.class, args);
    }

}
