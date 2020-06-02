package study.spring.myapp.util;

import org.springframework.stereotype.Component;

@Component
public class HelloTime {
	
	public static void timeLog() {
		System.out.println(new java.util.Date());
	}

}
