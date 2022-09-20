package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PurchaseOrderDao;
import model.OBSUser;
import model.PurchaseOrder;

/**
 * Servlet implementation class TransactionHistoryServlet
 */
@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseOrderDao purchaseOrderDao;

	public void init(ServletConfig config) throws ServletException {
		purchaseOrderDao = new PurchaseOrderDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<PurchaseOrder> list;
		list = purchaseOrderDao.purchaseList((OBSUser) session.getAttribute("user"));

		request.setAttribute("list", list);
		request.getRequestDispatcher("TransactionHistoryPage.jsp").forward(request, response);
	}

}
