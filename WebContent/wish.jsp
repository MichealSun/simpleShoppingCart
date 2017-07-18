<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div id="header">
		<div id="widgetBar">

			<div class="headerWidget">
				Welcome ${username} [ <a href="logout.jsp">Logout</a> ]
			</div>

			
		<h3>Dota Wishing List</h3>
	</div>

	<c:if test="${scopeSession.cart.numberOfItems == 0 }">
		<p>The Wish List is Empty</p>
	</c:if>
	<div>
		<table width="485" height="164" border="1">
			<tr>
				<td>SetName</td>
				<td>SetPrice</td>
				<td>SetQuality</td>
			</tr>
			<c:forEach var="set" items="${list}">
				<tr>
					<td>${set.setName}</td>
					<td>${set.setPrice}</td>
					<td>${set.setQuality}</td>
					<td><c:url var="url" value="/showwish">
							<c:param name="Remove" value="${set.setID}" />
						</c:url> <a href="${url}">Remove Item</a></td>
					<td><c:url var="url1" value="/showwish">
							<c:param name="Remove" value="${set.setID}" />
							<c:param name="Add" value="${set.setID}" />
							<c:param name="name" value="${set.setName}"/>
						</c:url> <a href="${url1}">Add to Cart</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="justify">
		<p>&nbsp;</p>
			<c:url var="url2" value="/displayitems" />
			<strong><a href="${url2}">Continue Shopping</a></strong>
			<c:url var="url3" value="/showcart">
				<c:param name="Clear" value="0" />
				<c:param name="Remove" value="0" />
			</c:url>
			<a href="${url3}">Show Cart</a>
			
		</p>
	</div>
</body>
</html>