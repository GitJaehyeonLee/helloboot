package com.example.helloboot;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void HelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        // URI , Bing 정보
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name={name}",  String.class,  "Spring");

        //status code check 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }
    @Test
    void HelloAPIfailTest() {
        TestRestTemplate rest = new TestRestTemplate();

        // URI , Bing 정보
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name=",  String.class );

        //status code check 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
