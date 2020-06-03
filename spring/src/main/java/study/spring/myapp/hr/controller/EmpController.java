package study.spring.myapp.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import study.spring.myapp.hr.dao.IEmpService;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	@GetMapping("/hr/jiyoung")
	public String getHigherSalary(Model model) {
		model.addAttribute("higherSalary", empService.getHigherSalary());
		return "home";
	}
	

}
