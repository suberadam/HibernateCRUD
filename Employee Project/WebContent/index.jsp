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