package com.example.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// Dispatcher 의 경우, 기본적으로 클래스를 뒤진 후 메서드를 뒤진다.
// 그럼으로 클래스 레벨에도 붙이는 것이 좋다.
@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // RestController 인 경우, Body 는 ResponseBody 로 고정
    // 아닌 경우, 템플릿을 뒤져서 출력하는 편임.
    @GetMapping
    @ResponseBody
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
