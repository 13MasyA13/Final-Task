<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  	<%@include file="jspf/header.jspf"%>
  	<jsp:useBean id="editCar" scope="session" class="ua.khpi.golik.bl.cars.AnyCar"></jsp:useBean>
  	<div class="container">
  		<label>Car ID - </label><jsp:getProperty name="editCar" property="id"></jsp:getProperty> <br> <br>
  		<label><h2><jsp:getProperty property="carName" name="editCar"/></h2></label> <br> <br>
  		<img src="<jsp:getProperty property="image" name="editCar"/>" width="375" height="280" class="img-thumbnail" alt="Cinque Terre"> <br> <br>
  		<ul class="list-group list-group-flush">
	      <li class="list-group-item">30 и более суток : <h5 style="color: #6a5acd"><jsp:getProperty property="from30toMoreDaysPrice" name="editCar"/></h5> за 1 сутки</li>
	      <li class="list-group-item">От 10 до 29 суток : <h5 style="color: #6a5acd"><jsp:getProperty property="from10to30DaysPrice" name="editCar"/></h5> за 1 сутки</li>
	      <li class="list-group-item">От 4 до 9 суток : <h5 style="color: #6a5acd"><jsp:getProperty property="from4to9DaysPrice" name="editCar"/></h5> за 1 сутки</li>
	      <li class="list-group-item">От 2 до 3 суток : <h5 style="color: #6a5acd"><jsp:getProperty property="from2to3DaysPrice" name="editCar"/></h5> за 1 сутки</li>
	      <li class="list-group-item">Залог : <h5 style="color: #6a5acd"><jsp:getProperty property="pledge" name="editCar"/></h5></li>
		</ul>
  	</div>
  	
  	  <div class="container">
      <form action="InsertNewCarController" method="post">
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
	        <input class="form-control" type="text" name="carPledge"/>
	      </div>
	
	      <div class="form-group">
	        <label><h3>Введите адресс картинки</h3></label> <br>
	        <label><h1>*NOTE*</h1></label> <br>
	        <label>Введите путь к картинке в формате WebContent/img/имя_класса/имя_машины.png</label>
	        <input class="form-control" type="text" name="carImage"/>
	      </div>
	
	      <div class="form-group">
	        <input class="btn btn-primary" type="submit"/>
	      </div>
    </form>
  </div>
  <%@include file="jspf/footer.jspf" %>
  	