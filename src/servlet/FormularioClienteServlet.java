package servlet;

import entities.Cliente;
import logic.ABMCCliente;
import util.ClientAlreadyExistException;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormularioClienteServlet
 */
@WebServlet("/FormularioClienteServlet")
public class FormularioClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setDNI(request.getParameter("DNI"));
		cliente.setUsername(request.getParameter("username"));
		cliente.setPassword(request.getParameter("password"));
		//enviar mail de confirmacion
		
		ABMCCliente abmcc=new ABMCCliente();
		try{
			abmcc.add(cliente);
			response.sendRedirect("main.jsp");
		}
		catch(ClientAlreadyExistException e) {
			response.sendRedirect("errorRegitro.jsp");
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
