package servlet.customer;

import entities.Customer;
import logic.ABMCCliente;
import util.DoniaMaryException;
//import util.MailSender;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormularioClienteServlet")
public class FormularioClienteServlet extends HttpServlet {
	private static final long serialVersionUID = -3473164304773346902L;

	public FormularioClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == null) {
			response.sendError(400, "Parameter 'name' is required");
			return;
		}
		
		String lastname = request.getParameter("lastname");
		if (lastname == null) {
			response.sendError(400, "Parameter 'lastname' is required");
			return;
		}
		
		String dni = request.getParameter("dni");
		if (dni == null) {
			response.sendError(400, "Parameter 'dni' is required");
			return;
		}
		
		String username = request.getParameter("username");
		if (username == null) {
			response.sendError(400, "Parameter 'username' is required");
			return;
		}
		
		String password = request.getParameter("password");
		if (password == null) {
			response.sendError(400, "Parameter 'password' is required");
			return;
		}
		
		String email = request.getParameter("email");
		if (email == null) {
			response.sendError(400, "Parameter 'email' is required");
			return;
		}
		
		Customer cliente = new Customer();
		cliente.setNombre(name);
		cliente.setApellido(lastname);
		cliente.setDNI(dni);
		cliente.setUsername(username);
		cliente.setPassword(password);
		cliente.setEmail(email);
		
		ABMCCliente abmcc = new ABMCCliente();
		try{
			abmcc.add(cliente);
			
			//MailSender ms = MailSender.getInstance();
			//int codigo = (int)(Math.random()*1000000);
			
			//ver donde guardar codigo
			
			//ms.send(cliente.getEmail(), "Confirmar E-Mail, Donia Mary Limpieza", 
			//		"Si usted se ha registrado en Donia Mary Limpieza, con el usuario: "+cliente.getUsername()+", por favor ingrese el siguiente codigo"
			//		+ " para terminar su registro:\n"+codigo+"\n\n Si usted no se ha registrado puede ignorar el mensaje");
			
			//redirigir a pagina de confirmacion
			response.sendRedirect("main.jsp");
		}
		catch(DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}
}
