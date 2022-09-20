<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Base64" %>
<%@ page import="dao.BookDao,model.Book,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
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

input {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  margin: 0 10px;
  width: 200px;
  padding: 10px 0;
  box-shadow: 0 0 20px rgba(104, 85, 224, 0.2);
  transition: 0.4s;
  color: purple;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(104, 85, 224, 1);
}

input:hover{
 color: white;
  width:;
  box-shadow: 0 0 20px rgba(104, 85, 224, 0.6);
  background-color: lightgreen;
}
input
</style>

<% List<Book> bookList = (List<Book>)request.getAttribute("list"); %>

<body>

<a href="OrderByNameServlet" style="padding-right:20px;">Book Name</a>
<a href="OrderByAuthorServlet" style="padding-right:20px;">Book Author</a>
<a href="OrderByPriceServlet" style="padding-right:20px;">Book Price</a>
<a href="OrderByStockServlet" style="padding-right:20px;">Book Stock</a>

<center><h2>Purchase Books</center></h2>

	<h1 style="text-align:center;"></h1>
	<hr>
	<%if(bookList.isEmpty()){ %>
	<div style="text-align:center;">No Book Found</div>
	<%} else{ %>
	<form action="PurchaseBookDetailsServlet" method="get">
	<table class ="bookList">
		<tr bgcolor='black'>
			<th>Purchase</th>
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
			<th><input type='radio' id='bookid' name='bookid' value='<%=book.getBook_id()%>' checked></th>
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
	<input type="submit" value="Purchase">
	</form>
	
</body>
</html>