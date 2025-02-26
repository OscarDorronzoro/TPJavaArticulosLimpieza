package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCCliente;
import util.DoniaMaryException;

/**
 * Servlet implementation class ModificarClienteServlet
 */
@WebServlet("/ModificarClienteServlet/*")
public class ModificarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarClienteServlet() {
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
		case "/IniciarModificacion": 
			Cliente cli;
			try {
				cli = abmcc.getOne(request.getParameter("username"));
				request.setAttribute("cli", cli );
				request.getRequestDispatcher("../WEB-INF/modificarCliente.jsp").forward(request, response);
				
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			
			break;
		case "/Modificar":
			Cliente cliente= new Cliente();

			cliente.setUsername(request.getParameter("username"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setDNI(request.getParameter("DNI"));
			cliente.setAdmin(request.getParameter("isAdmin").equals("on") ? true : false);
			
			try {
				abmcc.update(cliente);
				request.setAttribute("cliModificado",cliente);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/modificarCliente.jsp");
				dispatcher.forward(request, response);
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			break;
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
