package study.spring.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import study.spring.myapp.hr.dao.IEmpService;
import study.spring.myapp.hr.model.EmpVO;

@Controller
@RequestMapping("/hr")
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	
	
	@RequestMapping("/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0") int deptId, Model model) {
		if(deptId == 0) {
			model.addAttribute("count", empService.getEmpCount());
		} else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "hr/count";
	}
	
	//들어오는 주소 값과 반환하는 주소 값이 같으면 리턴타입을 void로 바꾸고 리턴 주소값을 안 넣어줘도 됨!!!
	@RequestMapping("/list")
	public void getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
	}
	
	@RequestMapping("/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		return "hr/view";
	}
	
	
	
	//입력 메서드
	@GetMapping("/insert")
	public String insertEmp(Model model) {
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
		return "hr/insertform";
	}
	
	//직접 적지 않아도 파라미터명과 vo의 변수명이 같을 때는 알아서 객체에 데이터를 집어 넣어줌!!!
	@PostMapping("/insert")
	public String insertEmp(EmpVO emp, Model model) {
		empService.insertEmp(emp);
		return "redirect:/hr/list";
	}
	
	
	
	@RequestMapping("/higherSalary")
	public String getHigherSalary(Model model) {
		List<EmpVO> higherList = empService.getHigherSalary();
		model.addAttribute("higherList", higherList);
		return "hr/list";
	}
	
	
	
//	//입력 메서드
//	@GetMapping("/insert")
//	public void insertEmp(Model model) {
//		model.addAttribute("emp", new EmpVO());
//		model.addAttribute("jobList", empService.getAllJobId());
//		model.addAttribute("manList", empService.getAllManagerId());
//		model.addAttribute("deptList", empService.getAllDeptId());
//		model.addAttribute("message", "insert");
//	}
//	
//	@PostMapping("/insert")
//	public String insertEmp(@ModelAttribute("emp") @Valid EmpVO emp, 
//			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
//		if(result.hasErrors()) {
//			model.addAttribute("jobList", empService.getAllJobId());
//			model.addAttribute("manList", empService.getAllManagerId());
//			model.addAttribute("deptList", empService.getAllDeptId());
//			model.addAttribute("message", "insert");
//			return "hr/insert";
//		}
//		empService.insertEmp(emp);
//		redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
//		return "redirect:/hr/list";
//	}
	
	
	
	//수정 메서드
	@GetMapping("/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insert";
	}
	
	@PostMapping("/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(HttpServletRequest request, Exception ex, Model model){
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
	
	
	
	//삭제 메서드
	@GetMapping("/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		return "hr/delete";
	}
	
	@PostMapping("/delete")
	public String deleteEmp(Model model, int empId) {
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}
	
	
	
	//JSON
	@RequestMapping("/json/list")
	public @ResponseBody List<EmpVO> getAllEmployees(){
		List<EmpVO> empList = empService.getEmpList();
		return empList;
	}
	
	@RequestMapping("/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	

}
