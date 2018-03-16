<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@include file="jspf/header.jspf"%>
	<br>
	<br>
	<div class="container">
		<h3><c:out value="${currentUser.getFirstName() }"></c:out> <c:out value="${currentUser.getLastName() }"></c:out></h3>
		<br>
		<h3>Мои заказы : </h3>
		<c:choose>
			<c:when test="${not empty currentUser.getOrder_id()}">Table</c:when>
			<c:otherwise>У вас пока нет заказов</c:otherwise>
		</c:choose>
		
		<br>
		
		<c:choose>
			<c:when test="${currentUser.isAccepted() == true }"><h3>Ваш заказ номер <c:out value="${currentUser.getOrder_id() }">
			подтвержден!</c:out></h3></c:when>
			<c:when test="${currentUser.isAccepted() == false and not empty currentUser.getDescription()}"><h3>Ваш заказ был отклонен по причине <c:out value="${currentUser.getDescription() }"></c:out></h3></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		
		<br>
		
		<c:choose>
			<c:when test="${currentUser.isSmallDamaged() eq true }"><h2>За легкие повреждения машины во время аренды залог за машину изымается</h2></c:when>
			<c:when test="${currentUser.isBrokenCar() eq true }"><h2>За повреждение машины вам будет начислен штраф, сумму которого ...</h2></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>


