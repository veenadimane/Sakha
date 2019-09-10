package com.ems.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;
import com.ems.service.EmployeeServiceImp;

public class EmployementManagementApp{
	EmployeeService es;
	BufferedReader in;
	
	public EmployementManagementApp() {
		es=new EmployeeServiceImp();
		in=new BufferedReader(new InputStreamReader(System.in));
	}
	
public void addExample() throws Exception{
	String empName;
	LocalDate DOB;
	float salary;
	System.out.println("enter the employee details (name,dob(dd-mm-yyyy),salary):");
	empName=in.readLine();
	String strDate=in.readLine();
	StringTokenizer stk=new StringTokenizer(strDate,"-");
	int date=Integer.parseInt(stk.nextToken());
	int month=Integer.parseInt(stk.nextToken());
	int year=Integer.parseInt(stk.nextToken());
	DOB=LocalDate.of(year, month, date);
	salary=Float.parseFloat(in.readLine());
	
	Employee emp=new Employee();
	emp.setEmpName(empName);
	emp.setBasicSalary(salary);
	emp.setDob(DOB);
	
	es.save(emp);
}
	public void deleteEmployee() {}
	public void showEmployee() {}
	public void displayAll() {}
	
	public static void main(String args[]) {
		EmployementManagementApp app = new EmployementManagementApp();
		try {
			app.addEmployee();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

	private void addEmployee() {
	
		
	}
}
