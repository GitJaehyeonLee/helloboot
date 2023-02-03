package com.example.config;

import com.example.config.EnableMyAutoConfiguration;
import com.example.config.autoconfig.DispatcherServletConfig;
import com.example.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// annotation 이 유지되는 범위
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
// Component 스캔은 현재 패키지 하단 부터 진행되기 떄문에, 별도로 추가하고자 하면, Import를 해줘야한다.
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {

}
