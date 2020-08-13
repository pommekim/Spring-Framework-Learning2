package study.spring.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import study.spring.myapp.hr.model.DeptVO;
import study.spring.myapp.hr.model.EmpVO;

public interface IEmpRepository {
	
	int getEmpCount();
	int getEmpCount(@Param("deptId") int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(@Param("empId") int empId);
	void deleteJobHistory(int empId);
	List<Map<String,Object>> getAllDeptId();
	List<Map<String,Object>> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	
	//부서별로 최고 급여자
	List<EmpVO> getHigherSalary();
	
	DeptVO getDeptInfo(int deptId);
	void updateManager(int empId);

}
