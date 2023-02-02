package com.example.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 컴포넌트를 표기하여, 현재 클래스 내에 빈이 있음을 인지
// ComponentScan 을 표기하여, 하위 패키지 대상 전체를 컴포넌트로 표기함
// 단점은 컴포넌트가 많아질떄 의존 관계를 쉽게 판단하기 어려움.
@Configuration
@ComponentScan
public class HellobootApplication {
    @Bean
    public ServletWebServerFactory serverWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        MySpringApplication.run(HellobootApplication.class, args);
    }
}
