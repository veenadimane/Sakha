package com.ems.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.ems.model.Employee;
import com.ems.util.InvalidDOBException;
import com.ems.util.InvalidNameException;
import com.ems.util.InvalidSalaryException;
import com.ems.dao.EmployeeDao;
import com.ems.dao.EmployeeDaoImp;


public class EmployeeServiceImp implements EmployeeService {
	
	EmployeeDaoImp dao;
	public EmployeeServiceImp() {
		dao=new EmployeeDaoImp();
	}

	@Override
	public boolean validateEmployee(Employee emp) throws Exception {
		
		String empName=emp.getEmpName();
		LocalDate DOB=emp.getDob();
		float salary=emp.getBasicSalary();
		
		if(empName.length()<4 || empName.length()>15) {
			throw new InvalidNameException("name must be in 4-15 characters");
		}
		if(DOB.isAfter(LocalDate.of(1997,12,29)) || DOB.isBefore(LocalDate.of(1900,1,1))) {
			throw new InvalidDOBException("please enter date in dob in range 01-01-1900 to 31-12-1995.");
		}
		if(salary>90000 || salary<20000) {
			throw new InvalidSalaryException("salary should be in the range of 20000-90000");
		}
		return true;
	}

	@Override
	public String generateID(String empname) {
		String nameChar=empname.substring(0,2).toUpperCase();
		Random rand=new Random();
		int dgt=(int)(rand.nextDouble()*1000);
		return nameChar+dgt;
	}

	@Override
	public boolean save(Employee emp) throws Exception {
		if(validateEmployee(emp)) {
			emp.setEmpId(generateID(emp.getEmpName()));
			return dao.save(emp);
		}
		return false;
	}

	private String generateID(String empName) {
		String nameChar=empName.substring(0,2).toUpperCase();
		Random rand=new Random();
		int dgt=(int)(rand.nextDouble()*1000);
		
		return nameChar+dgt;
	}

	@Override
	public boolean delete(String empId) throws Exception {
		if(validateEmployee(emp)) {
			emp.setEmpId(generateId(emp.getEmpName()));
			
		}
		return dao.delete(empId);
	}

	@Override
	public boolean update(Employee emp) throws Exception {
		if(validateEmployee(emp)) {
			return(dao.update(emp));
		}
		return false;
	}

	@Override
	public Employee getEmployee(String empId) throws Exception {
		return dao.getEmployee(empId);
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		
		return dao.getAllEmployees();
	}
	

}
