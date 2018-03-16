<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@include file="jspf/header.jspf"%>
  <br>
  <br>
  
 
  
  <div class="container" id="sortButtons">
  <script src="sortParam.js"></script>
  	<c:choose>
  		<c:when test="${not empty confirmOrder }">
  			<script>
	   			window.addEventListener("load",function(){
	         		alert("${confirmOrder}");
	    		})
    		</script>
    		<c:remove var="confirmOrder" scope="session"></c:remove>
  		</c:when>
  		<c:otherwise></c:otherwise>
  	</c:choose>
  	
  	<c:choose>
  		<c:when test="${not empty badOrder }">
  			<script>
	   			window.addEventListener("load",function(){
	         		alert("${badOrder}");
	    		})
    		</script>
    		<c:remove var="confirmOrder" scope="session"></c:remove>
  		</c:when>
  		<c:otherwise></c:otherwise>
  	</c:choose>
  	
  	<c:choose>
  		<c:when test="${not empty carUpdate }">
  			<script>
	   			window.addEventListener("load",function(){
	         		alert("${carUpdate}");
	    		})
    		</script>
    		<c:remove var="carUpdate" scope="session"></c:remove>
  		</c:when>
  		<c:otherwise></c:otherwise>
  	</c:choose>
  	<div class="controller">
  	<form action="CarSortController" method="post">
    <div class="btn-group">
      <button class="btn dropdown-toggle" data-toggle="dropdown" ><fmt:message key="markDropdown"></fmt:message><span class="caret"></span></button>
      <ul class="dropdown-menu">
      <%-- 
        <li><a onclick="return setAttr('mark','Chevrolet')">Chevrolet</a></li>
        <li><a onclick="return setAttr('mark','Mercedes')">Mercedes</a></li>
        <li><a onclick="return setAttr('mark','Honda')">Honda</a></li>
        <li><a onclick="return setAttr('mark','Mazda')">Mazda</a></li>
        <li><a onclick="return setAttr('mark','Toyota')">Toyota</a></li>
        <li><a onclick="return setAttr('mark','Volkswagen')">Volkswagen</a></li>
        <li><a onclick="return setAttr('mark','Ford')">Ford</a></li>
        <li><a onclick="return setAttr('mark','Hyundai')">Hyundai</a></li>
        <li><a onclick="return setAttr('mark','Kia')">Kia</a></li>
        <li><a onclick="return setAttr('mark','Mitsubishi')">Mitsubishi</a></li>
        <li><a onclick="return setAttr('mark','Audi')">Audi</a></li>
        <li><a onclick="return setAttr('mark','Jaguar')">Jaguar</a></li>
        <li><a onclick="return setAttr('mark','Mercedes')">Mercedes</a></li>
        <li><a onclick="return setAttr('mark','Porshe')">Porshe</a></li>
        --%>
        <input class="btn btn-primary" type="button" onclick="return setAttr('mark','Chevrolet')" name="carMark" value="Chevrolet"/>
      </ul>
    </div>
    <div class="btn-group">
      <button class="btn dropdown-toggle" data-toggle="dropdown" name="carClass"><fmt:message key="classDropdown"></fmt:message><span class="caret"></span></button>
      <ul class="dropdown-menu">
        <li><a onclick="return setAttr('carClass','Econom')"><fmt:message key="economClass"></fmt:message></a></li>
        <li><a onclick="return setAttr('carClass','Middle')"><fmt:message key="middleClass"></fmt:message></a></li>
        <li><a onclick="return setAttr('carClass','Business')"><fmt:message key="businessClass"></fmt:message></a></li>
        <li><a onclick="return setAttr('carClass','Premium')"><fmt:message key="premiumClass"></fmt:message></a></li>
        <li><a onclick="return setAttr('carClass','SUV')"><fmt:message key="suv"></fmt:message></a></li>
      </ul>
    </div>
    <div class="btn-group">
      <button class="btn dropdown-toggle" data-toggle="dropdown" name="sortByPrice">Сортировка по цене <span class="caret"></span></button>
      <ul class="dropdown-menu">
        <li><a onclick="return setAttr('sortByPrice','false')">По возрастанию цены</a></li>
        <li><a onclick="return setAttr('sortByPrice','true')">По убыванию цены</a></li>
      </ul>
    </div>
    <div class="btn-group">
      <button class="btn dropdown-toggle" data-toggle="dropdown" name="sortByName">Сортировка по имени <span class="caret"></span></button>
      <ul class="dropdown-menu">
        <li><a onclick="return setAttr('sortByName','false')">От A-Z</a></li>
        <li><a onclick="return setAttr('sortByName','true')">От Z-A</a></li>
      </ul>
    </div>
      <div class="form-group" id="submitSort">
        <input class="btn btn-primary" type="submit" value="Применить сортировку"/>
      </div>
    </form>
    </div>
 </div>
 <br>
 <br>
	<c:choose>
		<c:when test="${userType eq 'USER'}">
			  <c:forEach var="cars" items="${sessionScope.carList }">
			  	<div class="container">
		  		<br>
		  		<form action="OrderController" method="post">
			  		<input type="hidden" value="${cars.getId() }" name="carID">
				    <label><h3><c:out value="${cars.getCarName() }"></c:out></h3></label> <br>
				    <c:if test="${language == 'ru'}"><c:out value="${cars.getCarClassRU()}"></c:out></c:if> <br>
				    <c:if test="${language == 'en'}"><c:out value="${cars.getCarClassEN()}"></c:out></c:if> <br>
				    <img src="${cars.getImage() }" width="375" height="280" class="img-thumbnail" alt="Cinque Terre">
				    <ul class="list-group list-group-flush">
				      <li class="list-group-item">30 и более суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom30toMoreDaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 10 до 29 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom10to30DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 4 до 9 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom4to9DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 2 до 3 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom2to3DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">Залог : <h5 style="color: #6a5acd"><c:out value="${cars.getPledge() } $"></c:out></h5></li>
				    </ul>
				    <input class="btn btn-primary" type="submit" value="Сделать заказ"/>
			    </form>
			    <br>
		  		</div>
		  	</c:forEach>
		</c:when>
		
		<c:when test="${userType eq 'ADMIN' }">
			<c:forEach var="cars" items="${sessionScope.carList }">
			  	<div class="container">
		  		<br>
		  		<form action="EditCarController" method="post">
			  		<input type="hidden" value="${cars.getId() }" name="carID">
				    <label><h3><c:out value="${cars.getCarName() }"></c:out></h3></label> <br>
				    <c:if test="${language == 'ru'}"><c:out value="${cars.getCarClassRU()}"></c:out></c:if> <br>
				    <c:if test="${language == 'en'}"><c:out value="${cars.getCarClassEN()}"></c:out></c:if> <br>
				    <img src="${cars.getImage() }" width="375" height="280" class="img-thumbnail" alt="Cinque Terre">
				    <ul class="list-group list-group-flush">
				      <li class="list-group-item">30 и более суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom30toMoreDaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 10 до 29 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom10to30DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 4 до 9 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom4to9DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 2 до 3 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom2to3DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">Залог : <h5 style="color: #6a5acd"><c:out value="${cars.getPledge() } $"></c:out></h5></li>
				    </ul>
				    <input class="btn btn-primary" type="submit" value="Редактировать машину"/>
			    </form>
			    <br>
		  		</div>
		  	</c:forEach>
		</c:when>
		
		<c:when test="${userType eq 'MANAGER' }">
			<c:forEach var="cars" items="${sessionScope.carList }">
			  	<div class="container">
		  		<br>
		  		<form action="EditCarController" method="post">
			  		<input type="hidden" value="${cars.getId() }" name="carID">
				    <label><h3><c:out value="${cars.getCarName() }"></c:out></h3></label> <br>
				    <c:if test="${language == 'ru'}"><c:out value="${cars.getCarClassRU()}"></c:out></c:if> <br>
				    <c:if test="${language == 'en'}"><c:out value="${cars.getCarClassEN()}"></c:out></c:if> <br>
				    <img src="${cars.getImage() }" width="375" height="280" class="img-thumbnail" alt="Cinque Terre">
				    <ul class="list-group list-group-flush">
				      <li class="list-group-item">30 и более суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom30toMoreDaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 10 до 29 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom10to30DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 4 до 9 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom4to9DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 2 до 3 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom2to3DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">Залог : <h5 style="color: #6a5acd"><c:out value="${cars.getPledge() } $"></c:out></h5></li>
				    </ul>
			    </form>
			    <br>
		  		</div>
		  	</c:forEach>
		</c:when>
		
		<c:otherwise>
			<label><h2 style="padding-left: 115px">Для того чтобы сделать заказ — <a href="registration.jsp">зарегестрируйтесь</a></h2></label>
			<c:forEach var="cars" items="${sessionScope.carList }">
			  	<div class="container">
		  		<br>
		  		<form action="EditCarController" method="post">
			  		<input type="hidden" value="${cars.getId() }" name="carID">
				    <label><h3><c:out value="${cars.getCarName() }"></c:out></h3></label> <br>
				    <c:if test="${language == 'ru'}"><c:out value="${cars.getCarClassRU()}"></c:out></c:if> <br>
				    <c:if test="${language == 'en'}"><c:out value="${cars.getCarClassEN()}"></c:out></c:if> <br>
				    <img src="${cars.getImage() }" width="375" height="280" class="img-thumbnail" alt="Cinque Terre">
				    <ul class="list-group list-group-flush">
				      <li class="list-group-item">30 и более суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom30toMoreDaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 10 до 29 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom10to30DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 4 до 9 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom4to9DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">От 2 до 3 суток : <h5 style="color: #6a5acd"><c:out value="${cars.getFrom2to3DaysPrice() }$"></c:out></h5> за 1 сутки</li>
				      <li class="list-group-item">Залог : <h5 style="color: #6a5acd"><c:out value="${cars.getPledge() } $"></c:out></h5></li>
				    </ul>
			    </form>
			    <br>
		  		</div>
		  	</c:forEach>
		</c:otherwise>
	</c:choose>
	<script src="js/sortParam.js"></script>
	<%@include file="jspf/footer.jspf" %>