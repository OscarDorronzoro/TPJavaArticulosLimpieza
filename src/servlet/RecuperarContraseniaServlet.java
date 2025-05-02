package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customer;
import logic.ABMCCliente;
import util.DoniaMaryException;
import util.MailSender;


@WebServlet("/RecuperarContraseniaServlet")
public class RecuperarContraseniaServlet extends HttpServlet {
    private static final long serialVersionUID = 3633515640833565638L;

	public RecuperarContraseniaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MailSender mailSender;
		ABMCCliente abmcCliente = new ABMCCliente();
		
		String username = request.getParameter("username");
		if (username == null) {
			response.sendError(400, "Parameter 'username' is required");
			return;
		}
		
		try {
			mailSender = MailSender.getInstance();
			Customer customer = abmcCliente.getOne(username);
			
			int codigo = (int) (Math.random()*1000000);
			// save code somewhere accessible
			mailSender.send(
				customer.getEmail()
				,"Recuperar contraseña de Doña Mary Limpieza"
				,"su codigo para recuperar su cuenta es: " + codigo
			);
			response.sendRedirect("recuperarContrasenia.jsp");
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codeParam = request.getParameter("codigo");
		if (codeParam == null) {
			response.sendError(400, "Parameter 'code' is required");
			return;
		}
		
		try {
			int code = Integer.parseInt(codeParam);
			System.out.println(code);
			response.sendRedirect("iniciarSesion.jsp");
		}
		catch (NumberFormatException e) {
			response.sendRedirect("errorPage.jsp?mensaje=invalid%20code");
		}
	}

}
