package study.spring.myapp.hello.service;

import org.springframework.stereotype.Service;

import study.spring.myapp.util.HelloTime;

@Service
public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
		HelloTime.timeLog();
		System.out.println("HelloService.sayHello() 메서드 실행");
		String message = "Hello~~~" + name;
		return message;
	}

}
