package servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entities.Cliente;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/EliminarCategoriaServlet")
public class EliminarCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = -7216654799308642111L;

	public EliminarCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCCategoria abmcC = new ABMCCategoria();
		
		String name = request.getParameter("name");
		if (name == null) {
			response.sendError(400, "Parameter 'name' is required");
		}
		
		try {	
			abmcC.delete(name);
			response.sendRedirect("ListadoCategoriasServlet");
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch(Exception e) {
			new DoniaMaryException("Exception catched", e, Level.ERROR);
			response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
