package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import model.Book;

/**
 * Servlet implementation class OrderByAuthorServlet
 */
@WebServlet("/OrderByAuthorServlet")
public class OrderByAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> bookList = bookDao.viewAllBooksbyAuthor();
		request.setAttribute("list", bookList);
		request.getRequestDispatcher("PurchaseBookPage.jsp").forward(request, response);
	}

}
