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

	<c:if test="${scopeSession.cart.numberOfItems == 0 }">
		<p>The Shopping Cart is Empty</p>
	</c:if>
	<div>
		<table width="485" height="164" border="1">
			<tr>
				<td>Quantity</td>
				<td>SetName</td>
				<td>SetPrice</td>
				<td>SetQuality</td>
			</tr>
			<c:forEach var="item" items="${sessionScope.cart.items}">
				<c:set var="items" value="${item.items}" />
				<tr>
					<td>${item.quantity}</td>
					<td>${items.setName}</td>
					<td>${items.setPrice}</td>
					<td>${items.setQuality}</td>
					<td><c:url var="url" value="/showcart">
							<c:param name="Remove" value="${items.setID}" />
						</c:url> <a href="${url}">Remove Item</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="justify">
		<p>&nbsp;</p>
		
		<p>Total Amount: <fmt:formatNumber type = "number" 
         pattern = "###.00" value = "${sessionScope.cart.total}" /></p>
		<p>
			<c:url var="url" value="/cartStore.jsp?page=checkout" />
			<strong><a href="${url}">Checkout</a></strong> &nbsp;&nbsp;&nbsp;
			<c:url var="url2" value="/displayitems" />
			<strong><a href="${url2}">Continue Shopping</a></strong>
			
		</p>
	</div>
</body>
</html>