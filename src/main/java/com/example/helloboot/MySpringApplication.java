package com.example.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                // Spring Container 정보 부여, 하지만 없어도, 알아서 찾는다...
//                dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            dispatcherServlet
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
