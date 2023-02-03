package com.example.helloboot;

import com.example.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;

// 컴포넌트를 표기하여, 현재 클래스 내에 빈이 있음을 인지
// ComponentScan 을 표기하여, 하위 패키지 대상 전체를 컴포넌트로 표기함
// 단점은 컴포넌트가 많아질떄 의존 관계를 쉽게 판단하기 어려움.
@MySpringBootApplication
public class HellobootApplication {
    public static void main(String[]  args) {
        SpringApplication.run(HellobootApplication.class, args);
    }
}
