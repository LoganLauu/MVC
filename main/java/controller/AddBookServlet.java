package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BookDao;
import db.MyDBConnect;
import model.Book;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = MyDBConnect.dbConnect();
		PrintWriter out = response.getWriter();

		InputStream inputStream = null;

		Part filePart = request.getPart("img");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			inputStream = filePart.getInputStream();
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}

		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		int stock = Integer.parseInt(request.getParameter("stock"));
		double price = Double.parseDouble(request.getParameter("price"));
		String category = request.getParameter("category");

		Book book = new Book(bookname, author, stock, price, category, os.toByteArray());

		if (bookDao.add(book) > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Book Added Succesfully');");
			out.println("location='AddBookPage.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Book Fail to be added');");
			out.println("location='AddBookPage.jsp';");
			out.println("</script>");
		}

	}
}
