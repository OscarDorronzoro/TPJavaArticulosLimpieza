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

@WebServlet("/EditarPerfilServlet/*")
public class EditarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarPerfilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCliente abmcc = new ABMCCliente();
		
		switch(request.getPathInfo()) {
		case "/iniciarModificacion": 
			request.getRequestDispatcher("../editarPerfil.jsp").forward(request, response);			
			break;
		case "/modificar":
			try {
				Cliente cliente= abmcc.getOne(request.getParameter("username"));

				cliente.setNombre(request.getParameter("nombre"));
				cliente.setApellido(request.getParameter("apellido"));
				cliente.setDNI(request.getParameter("DNI"));
				
				abmcc.update(cliente);
				request.setAttribute("cliModificado",cliente);
				request.getRequestDispatcher("../editarPerfil.jsp").forward(request, response);;
			} catch (DoniaMaryException e) {
				response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
			}
			break;
		default:
			throw new ServletException("Error en switch");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
