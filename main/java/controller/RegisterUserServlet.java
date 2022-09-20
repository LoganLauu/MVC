package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.OBSUser;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && password != null) {
			// Verify login credentials
			OBSUser user = new OBSUser(username, password, "customer");

			if (userDao.checkUnique(user) > 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Username Exist');");
				out.println("location='RegisterUserPage.jsp';");
				out.println("</script>");

			} else {
				if (userDao.registerCustomer(user) > 0) {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('User Successfully Registered');");
					out.println("location='RegisterUserPage.jsp';");
					out.println("</script>");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('User Fail to Register');");
					out.println("location='RegisterUserPage.jsp';");
					out.println("</script>");
				}

			}

		}
	}
}
