package study.spring.myapp.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import study.spring.myapp.hr.dao.IEmpService;
import study.spring.myapp.hr.model.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
//	@GetMapping("/hr/jiyoung")
//	public String getHigherSalary(Model model) {
//		model.addAttribute("higherSalary", empService.getHigherSalary());
//		return "home";
//	}
	
	@RequestMapping(value="/hr/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0") int deptId, Model model) {
		if(deptId == 0) {
			model.addAttribute("count", empService.getEmpCount());
		} else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "hr/count";
	}
	
	@RequestMapping(value="/hr/list")
	public String getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}
	
	@RequestMapping(value="/hr/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		return "hr/view";
	}
	

}
