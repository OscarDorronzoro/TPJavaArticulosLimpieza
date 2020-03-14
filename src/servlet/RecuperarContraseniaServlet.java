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
import util.MailSender;

/**
 * Servlet implementation class RecuperarContraseniaServlet
 */
@WebServlet("/RecuperarContraseniaServlet/*")
public class RecuperarContraseniaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarContraseniaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MailSender mailSender;
		try {
			mailSender = MailSender.getInstance();
			ABMCCliente abmcCliente= new ABMCCliente();
			Cliente cliente = abmcCliente.getOne(request.getParameter("username"));
			
			switch(request.getPathInfo()) {
			case "/iniciarRecuperacion":
				int codigo = (int)(Math.random()*1000000);
				//ver donde guardar codigo
				mailSender.send("agustinablanco2524@gmail.com","Recuperar contraseña de Doña Mary Limpieza",
						"su codigo para recuperar su cuenta es: "+codigo);
				response.sendRedirect("../recuperarContrasenia.jsp");
				break;
			case "/verificarCodigo":
				//recuperar codigo
				int cod =Integer.parseInt(request.getParameter("codigo"));
				//comparar
				response.sendRedirect("../iniciarSesion.jsp");
			default:
			}
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
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
