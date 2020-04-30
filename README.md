# HibernateCRUD
This is CRUD application built on Hibernate Framework in Java. Here's step by step instruction I wrote if you, reader, want to re-create the app in your system. I used Eclipse IDE. 

CRUD Employee application using Hibernate. 

Create an Employee Project:

1) Open Eclipse. Select File Menu => New => Dynamic Web Project => Save as "Employee Project" and click Finish

2) Configure build path. Right click of project name => Build Path => Configure Buildpath. Click Add external JAR files => open tomcat=>lib folder. Select 
jsp-api.jar and servlet-api.jar files => Click Open => Click Apply => Apply and Close.

3) Expand project => Right click "WebContent" => New => JSP File => Save as "index.jsp" => Finish

<!-- source code of index.jsp -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Employee Data Entry Form</h3>
	<form action="InsertEmployee" method="post">
	  Enter Employee Name :
	  <input type="text" name="ename" required /><br /><br />
	  Enter Employee Salary :
	  <input type="number" min=10000 max=50000 name="esal" required /><br /><br />
	  <input type="submit" value="INSERT RECORD" />
	</form>
</body>
</html>
=> Run the JSP page and check whether it is properly loaded or not.
===============================================
4) Add a servlet class as below:
Expand Project folder => expand Java Resources => Select src => Right Click => new => servlet.
package-name : org.apec
class : InsertEmployee 
=> Click next => uncheck constructors, doGet => Finish
=> The servlet code looks as below:

package org.apec;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertEmployee")
public class InsertEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
===============================================
5) Create a Table in MySQL as below:

Table-name : employee
emp_id : int PK AI
emp_name : varchar NN
salary : int NN
===============================================
6) Integrate Hibernate with JSP/Servlets:
=> copy hibernate and mysql jar files => Copy them and paste in the "lib" folder of Webcontent => WEB-INF.
===============================================
7) Create the hibernate.cfg.xml by Right Clicking Java Resources => src folder => new => other => XML file.
===============================================
8) Configure build path. Right click project name => select Build Path => Configure Buildpath => select libraries tab => Add Jars => Expand project folder => web-content => WEB-INF => lib => select all jar files and click Ok
===============================================
9) Create an Entity class. Expand Java Resources => src => org.apec => Right click => New Class
package org.apec.hibernate.entity
Class Name: Employee(same as table name "employee" in MySQL 
Click Finish
=> Declare the annotations(@Entity,@table,@Id,@column...)
=> Add a constructor with two parameters: empName,salary
=> Add getters and setters for all attributes

/* source code of Employee.java */
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
		super();
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
===============================================
10) Add hibernate code.
Right click on org.apec.hibernate.entity => New Class => Remove entity from package => Save as App.java

/* source code of App.java */
package org.apec.hibernate;

import org.apec.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
  SessionFactory factory = new Configuration()
		  .configure("hibernate.cfg.xml")
		  .addAnnotatedClass(Employee.class)
		  .buildSessionFactory();
  public void insertEmployee(Employee emp) {
	  Session session = factory.getCurrentSession();
	  try {
	  session.beginTransaction();
	  session.save(emp);
	  session.getTransaction().commit();
	  System.out.println("Record Inserted Successfully");
	 }
	  finally {
		  session.close();
		  factory.close();
	 }
  }
}
===============================================
11) Modify InsertEmployee.java(servlet) as below:
package org.apec;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apec.hibernate.App;
import org.apec.hibernate.entity.Employee;

@WebServlet("/InsertEmployee")
public class InsertEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("ename");
			int sal = Integer.parseInt(request.getParameter("esal"));
			new App().insertEmployee(new Employee(name,sal));
		}
		catch(Exception e) {}
	}

}
===============================================
12) Run index.jsp file
===============================================
