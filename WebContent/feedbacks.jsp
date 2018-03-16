<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
	<%@include file="jspf/header.jspf"%>
	
	
	<c:choose>
		<c:when test="${not empty feedbackAnsw}">
			<script>
	   			window.addEventListener("load",function(){
	         	alert("${feedbackAnsw}");
	    		})
    		</script>
    		<c:remove var="feedbackAnsw" scope="session"></c:remove>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	<div class="container">
    <label id="main_label"><h1>Наша компания заботится о качестве предоставляемых услуг.</h1></label>
    	<div class="well" id="first_well"><img src="img/message_logo.png" id="message_logo"></img><label id="first_text"><h2>Здесь вы можете оставить свои отзывы и пожелания</h2></label><img src="img/message_logo.png" id="message_logo1"></img></div>
    	<div class="well" id="secon_well"><img src="img/znak_logo.png" id="znak_logo"></img><label id="opinion_text"><h1>Ваше мнение для нас очень важно!</h1></label><img src="img/znak_logo.png" id="znak_logo1"></img></div>
    </div>
	<div class="container">
	<label><h2>Отзывы </h2></label>
	<br>
	<br>
	<table class="table table-striped">
		  	<thead>
	        <tr>
	          <th>Пользователь</th>
	          <th>Комментарий</th>
	          <th>Дата</th>
	        </tr>
	        </thead>
	        <tbody>
	  		<c:forEach var="feedbacks" items="${sessionScope.feedbacks }">
	  			<tr>
	  				<td>
	  				<c:choose>
	  					<c:when test="${not empty feedbacks.getName() }"><c:out value="${feedbacks.getName() }"></c:out></c:when>
	  					<c:otherwise>Аноним</c:otherwise>
	  				</c:choose>
	  				</td>
	  				<td><c:out value="${feedbacks.getFeedback() }"></c:out></td>
	  				<td><c:out value="${feedbacks.getDate() }"></c:out></td>
	  			</tr>
	  		</c:forEach>
	  		</tbody>
	  	</table>
	  	<c:choose>
	  		<c:when test="${userType eq 'USER'}">
	  		  <form action="NewFeedbackUserController" method="post">
		  		  <div class="form-group">
			          <label>Введите текст комментария</label>
			          <textarea class="form-control" type="text" name="feedback" id="feedback"></textarea>
		          </div>
		          <div class="form-group" id="submitButton">
	          		  <input class="btn btn-primary" type="submit"/>
	        	  </div>
	  		  </form>
	  		</c:when>
	  		<c:when test="${userType eq 'ADMIN' }"></c:when>
	  		<c:when test="${userType eq 'MANAGER' }"></c:when>
	  		<c:otherwise>
	  			<form action="NewFeedbackAnonimController" method="post">
		  		  <div class="form-group">
			          <label>Введите текст комментария</label>
			          <textarea class="form-control" type="text" name="feedback" id="feedback"></textarea>
		          </div>
		          <div class="form-group" id="submitButton">
	          		  <input class="btn btn-primary" type="submit"/>
	        	  </div>
	  		  </form>
	  		</c:otherwise>
	  	</c:choose>
	  </div>
</body>
</html>