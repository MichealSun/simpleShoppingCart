<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="total" value="${sessionScope.cart.total}" />
<div align="justify">
<p>Total Price : ${total} USD</p>
<p>Please provide your Credit/Debit Card Details</p>
</div>

