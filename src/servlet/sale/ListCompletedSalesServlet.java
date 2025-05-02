package servlet.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entities.Cliente;
import logic.ABMCVenta;
import util.DoniaMaryException;

@WebServlet(
	urlPatterns = {
		"/ListCompletedSalesServlet/all"
		,"/ListCompletedSalesServlet/user"
	}
)
public class ListCompletedSalesServlet extends HttpServlet {
	private static final long serialVersionUID = 3081739671488121851L;

	public ListCompletedSalesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCVenta crudSale = new ABMCVenta();
		
		String username = null;
		String servletPath = request.getServletPath();
		
		switch (servletPath) {
		case "/ListCompletedSalesServlet/all":
			username = null;
		break;
		case "/ListCompletedSalesServlet/user":
			username = request.getParameter("username");
			if (username != null && username.equals("")) {
				username = null;
			}
		break;
		}
		
		try {
			// if username is null retrives sales for all customers
			request.setAttribute("sales", crudSale.getAllCompletedByCustomer(username));
			request.getRequestDispatcher("/WEB-INF/completedSalesList.jsp").forward(request, response);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch(Exception e) {
			new DoniaMaryException("Exception catched", e, Level.ERROR);
			response.sendRedirect("../errorPage.jsp?mensaje=Oops ha ocurrido un error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
