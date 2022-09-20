<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dao.BookDao,model.Book,java.util.*"%>



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
<% List<Book> bookList = (List<Book>)request.getAttribute("list"); %>
<body>
	
	<hr>
	<%if(bookList.isEmpty()){ %>
	<div style="text-align:center;">No Book Found</div>
	<%} else{ %>
	<h1 style="text-align:center;"><%= bookList.get(0).getBook_cat()%></h1>
	<table class ="bookList">
		<tr bgcolor='black'>
			<th>Book id</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Category</th>
			<th>Price</th>
			<th>Stock</th>
			<th>Book Cover</th>
		</tr>
		
	<% for(Book book : bookList){ %>
		<tr>
			<th><%=book.getBook_id()%></th>
			<th><%=book.getBook_name()%></th>
			<th><%=book.getBook_author()%></th>
			<th><%=book.getBook_cat()%></th>
			<th><%=book.getPrice()%></th>
			<th><%=book.getBook_stock()%></th>
			<th><img src="data:image/png;base64,<%=book.getEncodedImage()%>" width="80px" height="110px"></th>
			
		</tr>
	<%}%>
	</table>
	<%}%>
	
</body>
</html>