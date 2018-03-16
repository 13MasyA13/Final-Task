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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title><fmt:message key="title"></fmt:message></title>
</head>
<body>
	
	<%@include file="jspf/header.jspf"%>

	 <div class="container">
     <label><h3>Зарегистрировать нового менеджера</h3></label>
      <form class="newManagerForm" action="NewManagerController" method="post">
        <div class="form-group">
          <label>Введите логин</label>
          <input class="form-control" type="text" name="managerLogin" id="manLog"/>
        </div>

        <div class="form-group">
          <label>Введите пароль</label>
          <input class="form-control" type="password" name="managerPassword" id="manPas"/>
        </div>

        <div class="form-group">
          <label>Подтвердите пароль</label>
          <input class="form-control" type="password" name="confirmPassword" id="confPass"/>
        </div>

        <div class="form-group" id="submitManButton">
          <input class="btn btn-primary" type="submit"/>
        </div>
      </form>
  	  </div>
	  
	  <div class="container">
	    <label><h3>Users</h3></label>
    	<br>
	  	<table class="table table-striped">
		  	<thead>
	        <tr>
	          <th>User ID</th>
	          <th>User Login</th>
	          <th>User First name</th>
	          <th>User Last Name</th>
	          <th>isBlocked</th>
	        </tr>
	        </thead>
	        <tbody>
	  		<c:forEach var="users" items="${applicationScope.userList }">
	  			<tr>
	  				<td><c:out value="${users.getId() }"></c:out></td>
	  				<td><c:out value="${users.getLogin() }"></c:out></td>
	  				<td><c:out value="${users.getFirstName() }"></c:out></td>
	  				<td><c:out value="${users.getLastName() }"></c:out></td>
	  				<td><c:out value="${users.isBlocked() }"></c:out></td>
	  			</tr>
	  		</c:forEach>
	  		</tbody>
	  	</table>
	  </div>
	  
  	  <div class="container">
      <label><h3>Заблокировать пользователя по ID</h3></label>
      <form class="" action="BlockUserController" method="post">

      <div class="form-group">
        <label>Введите ID</label>
        <input class="form-control" type="text" name="userBlockID" id="userID"/>
      </div>

      <div class="form-group">
        <label>Укажите причину</label>
          <textarea type="text" class="form-control" name="reason"></textarea>
      </div>

      <div class="form-group" id="submitDelUserButton">
        <input class="btn btn-primary" type="submit"/>
      </div>

    </form>
  </div>

  <div class="container">
    <label><h3>Разблокировать пользователя по ID</h3></label>
    <form class="" action="UnlockUserController" method="post">

      <div class="form-group">
        <label>Введите ID</label>
        <input class="form-control" type="text" name="userUnlockID" id="userID"/>
      </div>

      <div class="form-group" id="submitDelUserButton">
        <input class="btn btn-primary" type="submit"/>
      </div>
    </form>
  </div>
  
  <div class="container">
    <form action="NewCarController" method="post">
      <label><h2>Добавить новую машину</h2></label>
      <div class="form-group">
        <label><h3>Введите название машины в формате : марка, модель, год выпуска</h3></label>
        <input class="form-control" type="text" name="carName"/>
      </div>

      <div class="form-group">
        <label><h3>Введите марку</h3></label>
        <input class="form-control" type="text" name="carMark"/>
      </div>

      <div class="form-group">
        <label><h3>Введите класс машины (на русском)</h3></label>
        <input class="form-control" type="text" name="carClassRU"/>
      </div>

      <div class="form-group">
        <label><h3>Введите класс машины (на английском)</h3></label>
        <input class="form-control" type="text" name="carClassEN"/>
      </div>

      <div class="form-group">
        <label><h3>Введите цену в сутки (30 дней и более)</h3></label>
        <input class="form-control" type="text" name="carPrice"/>
      </div>

      <div class="form-group">
        <label><h3>Введите залог за машину</h3></label>
        <input class="form-control" type="text" name="carPladge"/>
      </div>

      <div class="form-group">
        <label><h3>Введите адресс картинки</h3></label> <br>
        <label><h1 style="color: #ff0000">*NOTE*</h1></label> <br>
        <label style="color: #ff0000">Введите путь к картинке в формате WebContent/img/имя_класса/имя_машины.png</label>
        <input class="form-control" type="text" name="carImage"/>
      </div>

      <div class="form-group">
        <input class="btn btn-primary" type="submit"/>
      </div>
      
    </form>
    </div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
</body>
</html>