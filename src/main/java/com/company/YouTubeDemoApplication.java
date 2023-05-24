package com.company;

import com.company.util.MD5Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YouTubeDemoApplication {

    public static void main(String[] args) {
        System.out.println(MD5Util.getMd5("karimxon"));
        SpringApplication.run(YouTubeDemoApplication.class, args);
    }

}
