<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dao.PurchaseOrderDao,model.PurchaseOrder,java.util.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ViewBooks</title>
<style>
body{
text-align:center;
color:white;
}	
table{
width:100%;
}
.table th,
.table td {
padding: 12px 15px;
font-family: sans-serif;
}

</style>
</head>
<% List<PurchaseOrder> orderList = (List<PurchaseOrder>)request.getAttribute("list"); %>
<body>
	<h1 style="text-align:center;">Transaction Page</h1>
	<hr>
	<%if(orderList.isEmpty()){ %>
	<div style="text-align:center;">No Transaction Found</div>
	<%} else{ %>
	<table class ="orderList">
		<tr bgcolor='black'>
			<th>Transaction id</th>
			<th>Book Title</th>
			<th>Author</th>
			<th>Quantity</th>
			<th>Total Price</th>
			<th>Purchase Date</th>
		</tr>
		
	<% for(PurchaseOrder order : orderList){ %>
		<tr>
			<th><%=order.getOrder_id()%></th>
			<th><%=order.getBook().getBook_name()%></th>
			<th><%=order.getBook().getBook_author()%></th>
			<th><%=order.getQuantity()%></th>
			<th><%=order.getPrice()%></th>
			<th><%=order.getPurchaseDate()%></th>
			
		</tr>
	<%}%>
	</table>
	<%}%>
	
</body>
</html>