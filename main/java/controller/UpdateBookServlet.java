package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import model.Book;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		int bookid = Integer.parseInt(request.getParameter("bookid"));
		String selection = request.getParameter("updatetype");

		if (selection.equalsIgnoreCase("stock")) {
			int stock = Integer.parseInt(request.getParameter("stock"));
			Book book_stock = new Book(bookid, stock);
			if (bookDao.updateStock(book_stock) > 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Stock Updated Successfully');");
				out.println("location='UpdateBookPageServlet';");
				out.println("</script>");
			}
		} else if (selection.equalsIgnoreCase("price")) {
			double price = Double.parseDouble(request.getParameter("price"));
			Book book_price = new Book(bookid, price);
			if (bookDao.updatePrice(book_price) > 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Price Updated Successfully');");
				out.println("location='UpdateBookPageServlet';");
				out.println("</script>");
			}
		}
	}

}
