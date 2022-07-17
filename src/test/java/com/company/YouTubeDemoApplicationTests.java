package com.company;

import com.company.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YouTubeDemoApplicationTests {
@Autowired
private VideoRepository videoRepository;
    @Test
    void contextLoads() {
        boolean b = videoRepository.existsById("8a8a845c820147ff0182014cc59a0000");
        System.out.println(b);
    }

}
