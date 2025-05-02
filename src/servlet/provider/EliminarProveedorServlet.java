package servlet.provider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCProveedor;
import util.DoniaMaryException;

@WebServlet("/EliminarProveedorServlet")
public class EliminarProveedorServlet extends HttpServlet {       
    private static final long serialVersionUID = -1898149411785664838L;

	public EliminarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCProveedor abmcProv = new ABMCProveedor();
		try {
			String cuit = request.getParameter("cuit");
			if (cuit == null) {
				response.sendError(400, "Parameter 'cuit' is required");
				return;
			}
			abmcProv.delete(cuit);
			response.sendRedirect("ListadoProveedoresServlet");
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch (Exception e) {
			response.sendRedirect("errorPage.jsp?mensaje=Oops, ha ocurrido un error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
