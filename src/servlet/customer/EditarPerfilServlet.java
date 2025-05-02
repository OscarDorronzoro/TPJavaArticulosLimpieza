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

@WebServlet("/EditarPerfilServlet")
public class EditarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 980833155648429900L;

	public EditarPerfilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("../editarPerfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		String username = request.getParameter("username");
		if (username == null) {
			response.sendError(400, "Parameter 'username' is required");
			return;
		}
		
		if (!username.equals(currentUser.getUsername())) {
			response.sendError(403, "Access denied");
			return;
		}
		
		ABMCCliente abmcc = new ABMCCliente();
		try {
			Customer cliente = abmcc.getOne(username);

			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setDNI(request.getParameter("DNI"));
			
			abmcc.update(cliente);
			request.setAttribute("cliModificado",cliente);
			request.getRequestDispatcher("../editarPerfil.jsp").forward(request, response);;
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

}
