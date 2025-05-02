package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCLineaCarrito;
import util.DoniaMaryException;
import entities.Customer;

@WebServlet("/EliminarDeCarritoServlet")
public class EliminarDeCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 3936817201907405115L;

	public EliminarDeCarritoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		String cartType = request.getParameter("cartType");
		if (cartType == null) {
			response.sendError(400, "Parameter 'cartType' is required");
			return;
		}
		
		String articleCodeParam = request.getParameter("articleCode");
		if (articleCodeParam == null) {
			response.sendError(400, "Parameter 'articleCode' is required");
			return;
		}
		
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(currentUser, currentUser.getMiCarrito(cartType));
		try {
			int articleCode = Integer.parseInt(articleCodeParam);
			currentUser.getMiCarrito(cartType).getLineas().remove(abmcLineaCarrito.getOne(articleCode));
			abmcLineaCarrito.delete(articleCode);
			response.sendRedirect("CarritoServlet/currentPurchase");
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
