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
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();

		cliente.setUsername(request.getParameter("username"));
		cliente.setPassword(request.getParameter("password"));
		
		ABMCCliente abmcc=new ABMCCliente();
		try {
			abmcc.completarCliente(cliente);		
			request.getSession().setAttribute("cliente", cliente);
			
			String paginaARedirigir=request.getParameter("pagina");
			if(paginaARedirigir==null) { paginaARedirigir="main.jsp"; }
			
			response.sendRedirect(paginaARedirigir);
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block			
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
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
