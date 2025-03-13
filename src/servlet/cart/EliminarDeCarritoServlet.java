package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCLineaCarrito;
import util.DoniaMaryException;
import entities.Cliente;

@WebServlet("/EliminarDeCarritoServlet")
public class EliminarDeCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminarDeCarritoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(cliente);
		
		try {
			int codArt = Integer.parseInt(request.getParameter("codArticulo"));
			cliente.getMiCarrito().getLineas().remove(abmcLineaCarrito.getOne(codArt));
			abmcLineaCarrito.delete(codArt);
			response.sendRedirect("misCarritos.jsp");
		}
		catch(NumberFormatException e) {
			response.sendRedirect("errorPage.jsp?mensaje=Numero invalido");
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
