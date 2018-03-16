<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@include file="jspf/header.jspf"%>
	  <script src="js/order.js"></script>
	  <c:set var="car" value="${sessionScope.orderCar }" scope="session"></c:set>
	  <input type="hidden" value="${car.getFrom30toMoreDaysPrice() }" id="from30toMore"/>
	  <input type="hidden" value="${car.getFrom10to30DaysPrice() }" id="from10to30"/>
	  <input type="hidden" value="${car.getFrom4to9DaysPrice() }" id="from4to9"/>
	  <input type="hidden" value="${car.getFrom2to3DaysPrice() }" id="from2to3"/>
	  <input type="hidden" value="${car.getPledge() }" id="carPledge"/>
	  <div class="container">
	    <form action="NewOrderController" method="post">
	      <label><h3>Оформление заказа</h3></label>
	      <br>
	      <label><h2><c:out value="${car.getCarName() }"></c:out></h2></label> <br> <br>
	      <img src="${car.getImage() }" width="375" height="280" class="img-thumbnail" alt="Cinque Terre"> <br> <br>
	      <label><h2>Залог : <c:out value="${car.getPledge() }"></c:out></h2></label>
	      <br>
	      <br>
	      <div class="form-group">
	        <label>Введите серию пасспорта</label>
	        <input class="form-control" type="text" name="passport" id="inputPassport" placeholder="АА-99-9999"/>
	      </div>
	      <div class="form-group">
	        <label>Введите адресс</label>
	        <input class="form-control" type="text" name="address" id="inputAddress" placeholder="ул. № Дома"/>
	      </div>
	      <div class="form-group">
	        <label>Введите номер карты</label>
	        <input class="form-control" type="text" name="card" id="inputCard" placeholder="XXXX-XXXX-XXXX-XXXX"/>
	      </div>
	      <div class="form-group">
	        <label>Введите дату рождения</label> <br>
	        <input type="date" name="bornDate" id="inputFirstDate" min="1968-01-01"/>
	      </div>
	      <div class="form-group">
	        <label>Когда забираете?</label> <br>
	        <input type="date" name="firstDate" id="inputFirstDate" max="2018-12-01"/>
	      </div>
	      <div class="form-group">
	        <label>Когда отдаете?</label> <br>
	        <input type="date" name="secondDate" id="inputSecondDate" max="2018-12-31"/>
	      </div>
	      <label>Личный водитель</label> <br>
	      Нужен <input type="radio" name="isWithDriver" id="inputWithDriver" value="25"/> <br>
	      Не нужен <input type="radio" name="isWithDriver" id="inputWithDriver" value="0"/> <br>
	      <label><h2>Цена :</h2></label><label id="answer"><h2></h2></label><label><h2> $</h2></label> <br>
	      <input type="text" id="answ" name="answ"/>
	      <input class="btn btn-primary" type="button" id="b" value="Расчитать"/>
	      <br>
	      <br>
	      <div class="form-group" id="submitOrderButton">
	        <input class="btn btn-success" type="submit"/>
	      </div>
	    </form>
	  </div>
	  <%@include file="jspf/footer.jspf" %>
