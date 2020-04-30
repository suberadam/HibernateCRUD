package org.apec.hibernate.entity;

import javax.persistence.*;

@Entity(name="employee")
@Table(name="employee")
public class Employee {
	@Id
	@Column(name="emp_id")
	int empId;
	@Column(name="emp_name")
	String empName;
	@Column(name="salary")
	int salary;
	
	public Employee(String empName, int salary) {
		// super();
		this.empName = empName;
		this.salary = salary;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
