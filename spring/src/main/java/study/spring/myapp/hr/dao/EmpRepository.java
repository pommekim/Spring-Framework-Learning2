package study.spring.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import study.spring.myapp.hr.model.DeptVO;
import study.spring.myapp.hr.model.EmpVO;

@Repository
public class EmpRepository implements IEmpRepository {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	public class EmpMapper implements RowMapper<EmpVO> {
		
		@Override
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException {
			
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString(4));
			emp.setPhoneNumber(rs.getString(5));
			emp.setHireDate(rs.getDate(6));
			emp.setJobId(rs.getString(7));
			emp.setSalary(rs.getDouble(8));
			emp.setCommissionPct(rs.getDouble(9));
			emp.setManagerId(rs.getInt(10));
			emp.setDepartmentId(rs.getInt(11));
			return emp;
		}
	}
	
	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}
	
	@Override
	public List<EmpVO> getEmpList() {
		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}
	
	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select * from employees where employee_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
	}
	
	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql, emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(),
				emp.getEmail(), emp.getPhoneNumber(), emp.getJobId(), emp.getSalary(),
				emp.getCommissionPct(), emp.getManagerId(), emp.getDepartmentId());
	}
	
	@Override
	@Transactional("txManager")
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?, last_name=?, email=?, "
				+ "phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, "
				+ "manager_id=?, department_id=? where employee_id=?";
		jdbcTemplate.update(sql, emp.getFirstName(), emp.getLastName(),
				emp.getEmail(), emp.getPhoneNumber(), emp.getHireDate(), emp.getJobId(), emp.getSalary(),
				emp.getCommissionPct(), emp.getManagerId(), emp.getDepartmentId(), emp.getEmployeeId());
	}
	
	@Override
	@Transactional("txManager")
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId, "
				+ "department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle "
				+ "from jobs";
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, "
				+ "first_name||' '||last_name as managerName "
				+ "from employees "
				+ "where employee_id in "
				+ "(select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	
	@Override
	public DeptVO getDeptInfo(int deptId) {
		String sql = "select * from departments where department_id=?";
		return jdbcTemplate.queryForObject(sql,
				new RowMapper<DeptVO>() {

					@Override
					public DeptVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						DeptVO deptVO = new DeptVO();
						deptVO.setDepartmentId(rs.getInt("departmend_id"));
						deptVO.setDepartmentName(rs.getString("department_name"));
						deptVO.setManagerId(rs.getInt("manager_id"));
						deptVO.setLocationId(rs.getInt("location_id"));
						return deptVO;
					}
			
		});
	}

	
	
	@Override
	public List<EmpVO> getHigherSalary() {
		String sql = "select * from employees e " + 
				"where (e.department_id, e.salary) in " + 
				"(select department_id, max(salary) from employees " + 
				"group by department_id)";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public void updateManager(int empId) {
		String sql = "update employees e set e.manager_id=null where manager_id=(select employee_id from employees where employee_id=?)";
		jdbcTemplate.update(sql, empId);
		
	}
	
	
	
	
	
	
	
}
