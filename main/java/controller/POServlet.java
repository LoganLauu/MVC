package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MyDBConnect;
import model.OBSUser;

/**
 * Servlet implementation class POServlet
 */
@WebServlet("/POServlet")
public class POServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = MyDBConnect.dbConnect();
		PrintWriter out = response.getWriter();

		int bookid = Integer.parseInt(request.getParameter("bookid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		OBSUser user = (OBSUser) session.getAttribute("user");
		String username = user.getUser_id();

		try {
			PreparedStatement add_PO = conn.prepareStatement("insert into purchase_order values(po_seq.nextval,?,?,?)");
			PreparedStatement stockleft = conn
					.prepareStatement("update books set BOOK_STOCK = book_stock - ? where book_id = ?");
			stockleft.setInt(1, quantity);
			stockleft.setInt(2, bookid);

			add_PO.setInt(1, bookid);
			add_PO.setString(2, username);
			add_PO.setInt(3, quantity);

			int addPO = add_PO.executeUpdate();
			stockleft.executeUpdate();

			if (addPO > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("PurchaseBookDetails");
				rd.include(request, response);
				out.println("<h2><center><font color='Green'>Book Successfully Purchased!</font></center></h2>");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("PurchaseBookDetails");
				rd.include(request, response);
				out.println("<h2><center><font color='red'>Book Fail to Update!</font></center></h2>");
			}

		} catch (SQLException e) {

		}
	}

}
