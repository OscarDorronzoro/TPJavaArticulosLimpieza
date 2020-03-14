package servlet;

import entities.Cliente;
import logic.ABMCCliente;
import util.DoniaMaryException;
import util.MailSender;

import java.io.IOException;

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
		cliente.setEmail(request.getParameter("email"));
		
		ABMCCliente abmcc=new ABMCCliente();
		try{
			abmcc.add(cliente);
			
			MailSender ms = MailSender.getInstance();
			int codigo = (int)(Math.random()*1000000);
			//ver donde guardar codigo
			ms.send(cliente.getEmail(), "Confirmar E-Mail, Donia Mary Limpieza", 
					"Si usted se ha registrado en Donia Mary Limpieza, con el usuario: "+cliente.getUsername()+", por favor ingrese el siguiente codigo"
					+ " para terminar su registro:\n"+codigo+"\n\n Si usted no se ha registrado puede ignorar el mensaje");
			
			//redirigir a pagina de confirmacion
			response.sendRedirect("main.jsp");
		}
		catch(DoniaMaryException e) {
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
