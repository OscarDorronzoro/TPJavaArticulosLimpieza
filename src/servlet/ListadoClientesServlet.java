package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCCliente;
import util.DoniaMaryException;

/**
 * Servlet implementation class ListadoClientesServlet
 */
@WebServlet("/ListadoClientesServlet/*")
public class ListadoClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCCliente abmcC = new ABMCCliente();
		
		try {
			switch (request.getPathInfo()) {
				case "/admin": request.setAttribute("clientes", abmcC.getAllByAdmin(true));
					break;
				case "/noadmin": request.setAttribute("clientes", abmcC.getAllByAdmin(false));
					break;
				default: request.setAttribute("clientes", abmcC.getAll());
					break;
					
				//el problema esta en los servlet que escuchan en Servlet/*	
				//default: System.out.println(request.getPathInfo());throw new ServletException("Path incorrecto (admin/no admin)");
			}
			
			request.getRequestDispatcher("/WEB-INF/listadoClientes.jsp").forward(request,response);
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
