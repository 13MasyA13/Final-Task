<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%@include file="jspf/header.jspf"%>
	<div class="container">
	<label><h2>All orders</h2></label>
	<br>
	<br>
	<table class="table table-striped">
		  	<thead>
	        <tr>
	          <th>Order ID</th>
	          <th>User ID</th>
	          <th>Car ID</th>
	          <th>First Name</th>
	          <th>Last Name</th>
	          <th>isAccepted</th>
	          <th>Description</th>
	        </tr>
	        </thead>
	        <tbody>
	  		<c:forEach var="orders" items="${sessionScope.allOrders }">
	  			<tr>
	  				<td><c:out value="${orders.getId() }"></c:out></td>
	  				<td><c:out value="${orders.getUser_id() }"></c:out></td>
	  				<td><c:out value="${orders.getCar_id() }"></c:out></td>
	  				<td><c:out value="${orders.getFirstName() }"></c:out></td>
	  				<td><c:out value="${orders.getLastName() }"></c:out></td>
	  				<td><c:out value="${orders.isAccepted() }"></c:out></td>
	  				<td><c:out value="${orders.getDescription() }"></c:out></td>
	  			</tr>
	  		</c:forEach>
	  		</tbody>
	  	</table>
	  </div>
	  
	  <br>
	  <br>
	  
	  <div class="container">
	  <label><h2>Users</h2></label>
	  <table class="table table-striped">
		  	<thead>
	        <tr>
	          <th>User ID</th>
	          <th>First Name</th>
	          <th>Last Name</th>
	          <th>Order ID</th>
	          <th>isAccepted</th>
	          <th>Description</th>
	        </tr>
	        </thead>
	        <tbody>
	  		<c:forEach var="users" items="${sessionScope.users }">
	  			<tr>
	  				<td><c:out value="${users.getId() }"></c:out></td>
	  				<td><c:out value="${users.getFirstName() }"></c:out></td>
	  				<td><c:out value="${users.getLastName() }"></c:out></td>
	  				<td><c:out value="${users.getOrder_id() }"></c:out></td>
	  				<td><c:out value="${users.isAccepted() }"></c:out></td>
	  				<td><c:out value="${users.getDescription() }"></c:out></td>
	  			</tr>
	  		</c:forEach>
	  		</tbody>
	  	</table>
	  </div>
	  
	  <div class="container">
	  	<label><h2>Подтвердить заказ по ID</h2></label>
	  	  <form action="AcceptOrderController" method="post">
		  	  <div class="form-group">
		          <label>Введите ID</label>
		          <input class="form-control" type="text" name="acceptID" id="acceptID"/>
	          </div>
	          <div class="form-group" id="submitManButton">
          		  <input class="btn btn-primary" type="submit"/>
        	  </div>
          </form>
	  </div>
	  	  
	  <br>
	  <br>
	  
	  <div class="container">
	  	<label><h2>Отменить заказ по ID</h2></label>
	  	  <form action="NotAcceptOrderController" method="post">
		  	  <div class="form-group">
		          <label>Введите ID</label>
		          <input class="form-control" type="text" name="acceptID" id="acceptID"/>
	          </div>
	          <div class="form-group">
		          <label>Введите причину</label>
		          <input class="form-control" type="text" name="reason" id="acceptID"/>
	          </div>
	          <div class="form-group" id="submitManButton">
          		  <input class="btn btn-primary" type="submit"/>
        	  </div>
          </form>
	  </div>
</body>
</html>