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
