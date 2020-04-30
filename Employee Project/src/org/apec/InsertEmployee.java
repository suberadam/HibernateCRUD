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