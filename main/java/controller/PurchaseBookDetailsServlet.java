package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.PurchaseOrderDao;
import model.Book;
import model.OBSUser;
import model.PurchaseOrder;

/**
 * Servlet implementation class PurchaseBookDetailsServlet
 */
@WebServlet("/PurchaseBookDetailsServlet")
public class PurchaseBookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;
	private PurchaseOrderDao purchaseOrderDao;

	public void init(ServletConfig config) throws ServletException {
		bookDao = new BookDao();
		purchaseOrderDao = new PurchaseOrderDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int book_id = Integer.parseInt(request.getParameter("bookid"));
		Book book = bookDao.bookChoosen(book_id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("PurchaseBookDetails.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		OBSUser user = (OBSUser) session.getAttribute("user");

		int book_id = Integer.parseInt(request.getParameter("bookid"));
		Book bookSelected = bookDao.bookChoosen(book_id);

		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("price"));
		double total_price = quantity * price;

		PurchaseOrder order = new PurchaseOrder(bookSelected, quantity, user, total_price, new Date());

		int purchaseBookCount = purchaseOrderDao.purchaseBook(order);

		if (purchaseBookCount > 0) {
			String msg = "Congratulations! You have successfully purchased the book.\\n";
			msg += "---------------------------------------------------\\n";
			msg += "Invoice\\n";
			msg += "---------------------------------------------------\\n";
			msg += "Book Name: " + bookSelected.getBook_name() + "\\n";
			msg += "Book Price: $ " + bookSelected.getPrice() + "\\n";
			msg += "Quantity: " + quantity + "\\n";
			msg += "---------------------------------------------------\\n";
			msg += "Total Price: $ " + total_price + "\\n";
			msg += "---------------------------------------------------\\n";
			request.setAttribute("msg", msg);
		} else {
			request.setAttribute("msg", "Failed to purchase the book " + bookSelected.getBook_name() + "...");
		}
		this.doGet(request, response);
	}

}
