package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

import entities.Cliente;
import logic.ABMCCliente;
import util.DoniaMaryException;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, getServletInfo());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();

		cliente.setUsername(request.getParameter("username"));
		cliente.setPassword(request.getParameter("password"));
		
		ABMCCliente abmcc=new ABMCCliente();
		try {
			abmcc.completarCliente(cliente);		
			request.getSession().setAttribute("cliente", cliente);
			
			String paginaARedirigir = request.getParameter("pagina");
			if (paginaARedirigir == null) {
				paginaARedirigir = "main.jsp";
			}
			response.sendRedirect(paginaARedirigir);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + URLEncoder.encode(e.getMessage(), "ISO-8859-1"));
		}
	}

}
