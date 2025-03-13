package servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCCliente;
import util.DoniaMaryException;

@WebServlet("/ModificarClienteServlet")
public class ModificarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificarClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCliente abmcc = new ABMCCliente();

		Cliente client;
		try {
			String username = request.getParameter("username");
			if (username == null) {
				response.sendError(400, "username parameter is required");
				return;
			}
			client = abmcc.getOne(username);
			request.setAttribute("client", client );
			request.getRequestDispatcher("/WEB-INF/modificarCliente.jsp").forward(request, response);
			
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCliente abmcc = new ABMCCliente();
		
		String username = request.getParameter("username");
		if (username == null) {
			response.sendError(400, "username parameter is required");
			return;
		}
		
		Cliente client = null;
		try {
			client = abmcc.getOne(username);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
			return;
		}

		String email = request.getParameter("email");
		if (email != null) {
			client.setEmail(email);
		}
			
		String nombre = request.getParameter("nombre");
		if (nombre != null) {
			client.setNombre(nombre);
		}
			
		String apellido = request.getParameter("apellido");
		if (apellido != null) {
			client.setApellido(apellido);
		}
			
		String dni = request.getParameter("DNI");
		if (dni != null) {
			client.setDNI(dni);
		}
			
		String isAdmin = request.getParameter("isAdmin");
		if (isAdmin != null) {
			client.setAdmin(isAdmin.equals("on") ? true : false);
		}
			
		try {
			abmcc.update(client);
			response.sendRedirect("ListadoClientesServlet/todo");
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}

	}

}
