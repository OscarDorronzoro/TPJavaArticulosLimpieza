package servlet.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import logic.ABMCCliente;
import util.DoniaMaryException;

@WebServlet("/EliminarClienteServlet")
public class EliminarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminarClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCliente abmcc = new ABMCCliente();
		
		try {
			abmcc.delete(abmcc.getOne(request.getParameter("username")));
			response.sendRedirect("ListadoClientesServlet/todo");
		} catch (DoniaMaryException e) {
			response.sendRedirect("erorrPage.jsp?mensaje=" + e.getMessage());
		}catch(Exception e) {
			new DoniaMaryException("Exception catched", e, Level.ERROR);
			response.sendRedirect("erorrPage.jsp?mensaje=Oops ha ocurrido un error");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
