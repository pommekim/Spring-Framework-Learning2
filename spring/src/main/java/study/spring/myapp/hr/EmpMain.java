package study.spring.myapp.hr;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.spring.myapp.hr.dao.IEmpService;

public class EmpMain {
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
		IEmpService empService = context.getBean("empService", IEmpService.class);
		
		System.out.println(empService.getEmpCount());
		System.out.println(empService.getEmpCount(30));
		System.out.println(empService.getEmpList());
		System.out.println(empService.getAllManagerId());
		
		empService.updateEmp(empService.getEmpInfo(108));
		
		System.out.println(empService.getHigherSalary());
		
		context.close();
	}

}
