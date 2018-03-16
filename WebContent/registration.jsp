<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="ua.khpi.golik.i18n.resourses"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><fmt:message key="title"></fmt:message></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    
    <%@include file="jspf/header.jspf"%>
    <br>
    <br>
    <div class="container" id="reg_form">
	    <form action="RegistrationController" method="post">
	      <div class="form-group">
	        <label><fmt:message key="label.login"></fmt:message> :</label>
	        <input type="text" class="form-control" id="login_reg" placeholder="<fmt:message key="input.login"></fmt:message>" name="login">
	      </div>
	      <div class="form-group">
	        <label><fmt:message key="label.firstName"></fmt:message> :</label>
	        <input type="text" class="form-control" id="first_reg" placeholder="<fmt:message key="input.firstName"></fmt:message>" name="firstName">
	      </div>
	      <div class="form-group">
	        <label><fmt:message key="label.lastName"></fmt:message> :</label>
	        <input type="text" class="form-control" id="last_reg" placeholder="<fmt:message key="input.lastName"></fmt:message>" name="lastName">
	      </div>
	      <div class="form-group">
	        <label><fmt:message key="label.email"></fmt:message> :</label>
	        <input type="text" class="form-control" id="email_reg" placeholder="<fmt:message key="input.email"></fmt:message>" name="email">
	      </div>
	      <div class="form-group">
	        <label><fmt:message key="label.password"></fmt:message> :</label>
	        <input type="password" class="form-control" id="pass_reg" placeholder="<fmt:message key="input.password"></fmt:message>" name="password">
	      </div>
	      <div class="form-group">
	        <label><fmt:message key="label.confirmPassword"></fmt:message> :</label>
	        <input type="password" class="form-control" id="confirmPassword_reg" placeholder="<fmt:message key="input.confirmPassword"></fmt:message>" name="confirmPassword">
	        <br>
	        <button type="submit" class="btn btn-primary"><fmt:message key="button.register"></fmt:message></button><a href="index.jsp" style="padding-left: 25px"><label><fmt:message key="backLink"></fmt:message></label></a>
	      </div>
	    </form>
    </div>	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
</body>
</html>