<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/res/css/bootstrap.css"/>"/>
<link rel="stylesheet" href="<c:url value="/res/css/style.css"/>"/>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">		
			<form action="./login" method="post">
			  <div class="form-group">
			    <label>Email address</label>
			    <input type="email" name="email" class="form-control">
			  </div>
			  <div class="form-group">
			    <label for="InputPassword1">Password</label>
			    <input name="password" type="password" class="form-control">
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Login</button>
			  <a href="./signup" class="btn btn-primary">SignUp</a><br>
			  <a href="#" style="color: blue; text-decoration: none"><b>Forgot Password? Click to reset</b></a>
			</form>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div style="color: red">${error}</div>
		</div>
	</div>
</div>
</body>
</html>