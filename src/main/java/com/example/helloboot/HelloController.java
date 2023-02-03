package com.example.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// Dispatcher 의 경우, 기본적으로 클래스를 뒤진 후 메서드를 뒤진다.
// 그럼으로 클래스 레벨에도 붙이는 것이 좋다.
// 메테 에노테이션 (에노테이션 위에 에노테이션) 에 컴포넌트 에노테이션이 포함된 경우 이 메타 에노테이션을 붙이면, 컴포넌트로 인식해서 진행한다.
// 보통 정하는 경우는, 이 컴포넌트가 어떤 대상인지 명시하기 위해서 주로 사용한다.

// RestController 와 controller 의 차이점은 RestContoller는 컨트롤러를 상속 받아 활용하며, 추가적으로  Responsebody 를 기록한다.
@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    // LifeCycle 과 관련된 대상들은 스프링이 자체적으로 매개변수를 선택하여 입력해준다.
    public HelloController(HelloService  helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }

    // RestController 인 경우, Body 는 ResponseBody 로 고정
    // 아닌 경우, 템플릿을 뒤져서 출력하는 편임.
    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
