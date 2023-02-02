package com.example.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {
        // Spring Container 생성 Context 가 Container 임
        // 컨테이너 생성
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        // 오브젝트 (빈) 등록
        // HelloController 에서는 어떻게 Simple을 생성자에 넣을 것인가 ? (그냥 알아서 빈 중에 구현된 대상이 있다면, 관례적으로 넣는다.)
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        // 오브젝트 생성 요청
        applicationContext.refresh();


        // [Step 1] Servlet 컨테이너 생성
        // Factory 생성과 설정 작업등을 대신 해주는 대상
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(applicationContext)
            ).addMapping("/*");
        });
        webServer.start();

        // [Step 2] 서블릿 생성





    }
}
