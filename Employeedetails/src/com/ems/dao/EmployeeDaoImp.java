package com.ems.dao;

import com.ems.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ems.model.Employee;

public class EmployeeDaoImp implements EmployeeDao {

	Connection con;

	@Override
	public Connection getConnection() throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ems", "sakha-lenovo-3", "welcome");
		return con;
	}

	@Override
	public String generateId(String empName) throws Exception {
		
		return null;
	}

	@Override
	public boolean save(Employee emp) throws Exception {
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into Employee values(?,?,?,?)");
		ps.setString(1,emp.getEmpId());
		ps.setString(2,emp.getEmpName());
		LocalDate dobRaw=emp.getDob();
		ps.setDate(3,new java.sql.Date(dobRaw.getYear()-1900,dobRaw.getMonthValue()-1,dobRaw.getDayOfMonth()));
		ps.setFloat(4, emp.getBasicSalary());
		int r=ps.executeUpdate();
		return r>0?true:false;
	}

	@Override
	public boolean delete(String empId) throws Exception {
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from the employee where empid=?");
		ps.setString(1,empId);
		return ps.executeUpdate()>0?true:false;
	}

	@Override
	public boolean update(Employee emp) throws Exception {
       con=getConnection();
       PreparedStatement ps=con.prepareStatement("update employee set empName=?,DOB=?, basicsalary=? where");
		ps.setString(1,emp.getEmpId());
		ps.setString(2,emp.getEmpName());
		LocalDate dobRaw=emp.getDob();
		ps.setDate(3, new java.sql.Date(dobRaw.getYear()-1900,dobRaw.getMonthValue(),dobRaw.getDayOfMonth()));
		ps.setFloat(4,emp.getBasicSalary());
       
		return ps.executeUpdate()>0?true:false;
	}

	@Override
	public Employee getEmployee(String empId) throws Exception {
		con=getConnection();
	       PreparedStatement ps=con.prepareStatement("select * from employee where empId=?");
		ps.setString(1,empId);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			java.sql.Date rawDate=rs.getDate(3);
			Employee emp=new Employee(rs.getString(1),rs.getString(2),LocalDate.of(rawDate.getYear(),rawDate.getMonth(), rawDate.getDay()), rs.getFloat(4));
		return emp;
		}
	       return null;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		con=getConnection();
	       PreparedStatement ps=con.prepareStatement("select * from Employee");
	       
	       List<Employee>empList=new ArrayList();
	       ResultSet rs=ps.executeQuery();
	       if(rs.next()) {
	    	   java.sql.Date rawDate=rs.getDate(3);
				Employee emp=new Employee(rs.getString(1),rs.getString(2),LocalDate.of(rawDate.getYear(),rawDate.getMonth(), rawDate.getDay()), rs.getFloat(4));
	    	   
				empList.add(emp);
	       }
	       
		return null;
	}

}
