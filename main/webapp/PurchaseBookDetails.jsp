<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.BookDao,model.Book,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

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
}

</style>
<% Book book = (Book)request.getAttribute("book"); %>
<body>
<table class ="book">
		<tr bgcolor='black'>
			<th>Book id</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Category</th>
			<th>Price</th>
			<th>Stock</th>
			<th>Book Cover</th>
		</tr>
		
		<tr>
			<th><%=book.getBook_id()%></th>
			<th><%=book.getBook_name()%></th>
			<th><%=book.getBook_author()%></th>
			<th><%=book.getBook_cat()%></th>
			<th><%=book.getPrice()%></th>
			<th><%=book.getBook_stock()%></th>
			<th><img src="data:image/png;base64,<%=book.getEncodedImage()%>" width="80px" height="110px"></th>
			
		</tr>
	</table>
<form onsubmit="return checkForm();" action="PurchaseBookDetailsServlet" method="post">
Quantity <input type="number" id="quantity" name="quantity" min="1" max="<%=book.getBook_stock()%>">
<input type="hidden" id="bookid" name="bookid" value="<%=book.getBook_id()%>">
<input type="hidden" id="price" name="price" value="<%=book.getPrice()%>">

<input type="submit" value="Confirm">
</form>

<script>

function checkForm(){
	var quantity = document.getElementById("quantity");

	if(quantity.value.length ==0 ){
		alert("Please Enter The Quantity");
		return false;
	}

}

window.onload = function() {
	let msg = "<%=request.getAttribute("msg")%>";
	if (msg != "null") {
		alert(msg);
		<% request.removeAttribute("msg");%>
	}
};

</script>









</body>
</html>