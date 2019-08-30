package servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Cliente;
import logic.ABMCCliente;
import util.ClientNotFoundException;

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
		HttpSession session=request.getSession(true);
		try {
			abmcc.completarCliente(cliente);		
			session.setAttribute("cliente", cliente);
			//request.setAttribute("mensaje", "Cliente logueado exitosamente");
			response.sendRedirect("main.jsp");
		} catch (ClientNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("mensaje", "Cliente no encontrado");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);;
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
