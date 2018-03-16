<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/logging.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<%@include file="jspf/header.jspf"%>
	
	<c:choose>
		<c:when test="${not empty loginError }">
			<script>
	   			window.addEventListener("load",function(){
	         	alert("${loginError}");
	    		})
    		</script>
    		<c:remove var="loginError" scope="session"></c:remove>
		</c:when>
		
		<c:when test="${not empty blockUser}">
			<script>
	   			window.addEventListener("load",function(){
	         	alert("${blockUser}");
	    		})
    		</script>
    		<c:remove var="blockUser" scope="session"></c:remove>
		</c:when>
		
		<c:when test="${not empty nullParam}">
			<script>
	   			window.addEventListener("load",function(){
	         	alert("${nullParam}");
	    		})
    		</script>
    		<c:remove var="nullParam" scope="session"></c:remove>
		</c:when>
	</c:choose>

	<div class="container" id="log_form">
	    <form method="post" action="LoggingServlet">
	    <div class="form-group">
	      <label for="login"><h3>Login:</h3></label>
	      <input type="login" class="form-control" id="login" placeholder="Введите логин" name="login">
	    </div>
	    <div class="form-group">
	      <label for="pwd"><h3>Password:</h3></label>
	      <input type="password" class="form-control" id="pwd" placeholder="Введите пароль" name="password">
	    </div>
	    <div class="radio">
	      <label><input type="radio" name="userType" value="USER">Войти в качестве пользователя</label>
	    </div>
	    <div class="radio">
	      <label><input type="radio" name="userType" value="MANAGER">Войти в качестве менеджера</label>
	    </div>
	    <div class="radio">
	      <label><input type="radio" name="userType" value="ADMIN">Войти в качестве администратора</label>
	    </div>
	    <button type="submit" class="btn btn-primary">Submit</button>
	    </form>
    </div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
</body>
</html>