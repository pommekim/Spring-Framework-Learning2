package study.spring.myapp.member;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		Customer cus = con.getBean(Customer.class);
		System.out.println(cus);
		con.close();
	}
}
