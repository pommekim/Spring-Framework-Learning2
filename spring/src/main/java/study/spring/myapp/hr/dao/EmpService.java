package study.spring.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import study.spring.myapp.hr.model.DeptVO;
import study.spring.myapp.hr.model.EmpVO;

@Service
public class EmpService implements IEmpService {
	
	@Autowired
	@Qualifier("IEmpRepository")
	IEmpRepository empRepository;

	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public int getEmpCount(int deptId) {
		return empRepository.getEmpCount(deptId);
	}

	@Override
	public List<EmpVO> getEmpList() {
		return empRepository.getEmpList();
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		return empRepository.getEmpInfo(empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		empRepository.deleteJobHistory(emp.getEmployeeId());
		empRepository.updateEmp(emp);
	}

	@Override
	public void insertEmp(EmpVO emp) {
		empRepository.insertEmp(emp);
	}

	@Override
	public void deleteEmp(int empId) {
		empRepository.deleteJobHistory(empId);
		empRepository.deleteEmp(empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		empRepository.deleteJobHistory(empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}

	@Override
	public List<EmpVO> getHigherSalary() {
		return empRepository.getHigherSalary();
	}

	@Override
	public DeptVO getDeptInfo(int deptId) {
		return empRepository.getDeptInfo(deptId);
	}

	@Override
	public void updateManager(int empId) {
		empRepository.updateManager(empId);
	}
	
	

}
