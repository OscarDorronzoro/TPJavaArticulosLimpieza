package servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customer;
import logic.ABMCCliente;
import util.DoniaMaryException;

@WebServlet("/ListadoClientesServlet/*")
public class ListadoClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 4036022406348969830L;

	public ListadoClientesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCCliente abmcC = new ABMCCliente();
		
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			response.sendRedirect("ListadoClientesServlet/todo");
			return;
		}
		
		try {
			switch (request.getPathInfo()) {
				case "/admin": request.setAttribute("clientes", abmcC.getAllByAdmin(true));
					break;
				case "/noadmin": request.setAttribute("clientes", abmcC.getAllByAdmin(false));
					break;
				case "/todo":
				default: request.setAttribute("clientes", abmcC.getAll());
					break;
			}
			request.getRequestDispatcher("/WEB-INF/listadoClientes.jsp").forward(request,response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
