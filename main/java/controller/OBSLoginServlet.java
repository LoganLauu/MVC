package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.OBSUser;

/**
 * Servlet implementation class OBSLoginServlet
 */
@WebServlet("/OBSLoginServlet")
public class OBSLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String username = request.getParameter("uid");
		String password = request.getParameter("pwd");

		if (username != null && password != null) {
			// Verify login credentials
			OBSUser user = userDao.login(username, password);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				if (user.getUser_type().equalsIgnoreCase("admin"))
					response.sendRedirect("AdminPage.jsp");
				else if (user.getUser_type().equalsIgnoreCase("customer"))
					response.sendRedirect("CustomerPage.jsp");

			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='OBSLogin.jsp';");
				out.println("</script>");
			}
		}

	}
}
