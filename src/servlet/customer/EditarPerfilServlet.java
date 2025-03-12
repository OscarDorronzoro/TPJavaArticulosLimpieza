package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCCliente;
import util.DoniaMaryException;

/**
 * Servlet implementation class EditarPerfilServlet
 */
@WebServlet("/EditarPerfilServlet/*")
public class EditarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				// TODO Auto-generated catch block
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			}
			break;
		default:
			throw new ServletException("Error en switch");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
