package study.spring.myapp.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import study.spring.myapp.hello.service.*;

@Controller
public class HelloController {
	
//	@Resource(name="helloServiceProxy")
//	IHelloService helloService = new HelloService();
	
	@Autowired
	@Qualifier("helloService")
	IHelloService helloService;
	
	public void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}
	
	public HelloController(IHelloService helloService) {
		this.helloService = helloService;
	}
	
	public void hello(String name) {
		System.out.println("HelloController : " + helloService.sayHello(name));
	}

}
