package study.spring.myapp.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.spring.myapp.member.Customer;

public class HelloMain {

	public static void main(String[] args) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		Customer cus = con.getBean(Customer.class);
		System.out.println(cus);
		con.close();

	}

}
