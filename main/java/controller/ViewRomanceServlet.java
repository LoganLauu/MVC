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
 * Servlet implementation class ViewRomanceServlet
 */
@WebServlet("/ViewRomanceServlet")
public class ViewRomanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> bookList = bookDao.viewBooksbyCategory("Romance");
		request.setAttribute("list", bookList);
		request.getRequestDispatcher("ViewBookCategory.jsp").forward(request, response);

	}

}
