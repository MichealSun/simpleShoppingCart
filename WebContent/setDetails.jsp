<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DotaStore</title>
</head>
<body>
	<c:if test="${!empty param.Add}">
		<c:set var="id" value="${param.Add}" />
		<c:set var="setName" value="${param.name}" />
		<p>
			You added <font color="red" size="+1"> <strong>${setName}</strong>
			</font>to your shopping cart.
		</p>
	</c:if>
	<c:if test="${!empty param.setID}">
		<c:set var="id" value="${param.setID}" />
		<c:set var="setName" value="${param.name1}" />
		<p>
			You added <font color="red" size="+1"> <strong>${setName}</strong>
			</font>to your Wish List.
		</p>
	</c:if>

	
	
		<p>
			<c:url var="url" value="/showcart">
				<c:param name="Clear" value="0" />
				<c:param name="Remove" value="0" />
			</c:url>
			<a href="${url}">Show Cart</a>
			<c:url var="url1" value="/showwish">
				<c:param name="Clear" value="0" />
				<c:param name="Remove" value="0" />
				<c:param name="ID" value="${id}" />
			</c:url>
			<a href="${url1}">Show WishList</a>

		</p>
		<p>&nbsp;</p>

	<c:forEach var="setList" items="${setDetailsList}">
		<div>

			<table width="541" height="201" border="1">
				<tr>
					<td width="335" height="33"><c:url var="url"
							value="/itemDetails">
							<c:param name="setID" value="${setList.setID}" />
						</c:url> <a href="${url}">${setList.setName}</a></td>
					<td width="355" rowspan="4"><img src="${setList.setImg}" width="300" height="200" ></td>
				</tr>
				<tr>
					<td height="42">Price: ${setList.setPrice}</td>
				</tr>
				<tr>
					<td height="70">Quality: ${setList.setQuality}</td>
				</tr>
				<tr>
					<td height="42"><div align="center">

							<c:url var="url2" value="/itemcatalog">
								<c:param name="Add" value="${setList.setID}" />
								<c:param name="name" value="${setList.setName}" />
							</c:url>
							<a href="${url2}">Add to Cart</a>
							<c:url var="url3" value="/wishlist">
								<c:param name="setID" value="${setList.setID}" />
								<c:param name="userID" value="${userid}" />
								<c:param name="name1" value="${setList.setName}" />
							</c:url>
							<a href="${url3}">Add to WishList</a>
						</div></td>
				</tr>
			</table>
		</div>

	</c:forEach>
	<p>&nbsp;</p>
</body>
</html>