package com.example.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
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
@Configuration
public class HellobootApplication {
    // 팩토리 메서드 생성 (빈의 초기화 등을 볃도로 진행하는 것으로, 기존에는 빈이 등록되면 자동으로 맵핑하나, 복잡한 경우에는 별도로 하는 경우도 있음)
    // Bean 을 선언하여 이 함수는 호출되어야 한다고 표기
    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // 팩토리 메서드 : 오브젝트를 생성하는 메소드를 이야기 한다.
        // Spring Container 생성 Context 가 Container 임
        // 컨테이너 생성
        // Srping 컨테이너를 생성할 때, 서블릿 컨테이너 및 프론트 컨트롤러가 초기화 되는 것이 좋은 패턴이다.
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        // 오브젝트 (빈) 등록
        // HelloController 에서는 어떻게 Simple을 생성자에 넣을 것인가 ? (그냥 알아서 빈 중에 구현된 대상이 있다면, 관례적으로 넣는다.)
//        applicationContext.registerBean(HelloController.class);
//        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.register(HellobootApplication.class);
        // 컨테이너 초기화
        applicationContext.refresh();
    }
}
