package servlet.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import entities.Cliente;
import entities.Linea;
import logic.ABMCLineaCarrito;
import util.DoniaMaryException;

@WebServlet("/ModificarCarritoServlet")
public class ModificarCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 7190602277016267618L;

	public ModificarCarritoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCLineaCarrito abmcLinea = new ABMCLineaCarrito(currentUser);
		
		String articleCodeParam = request.getParameter("articleCode");
		if (articleCodeParam == null) {
			response.sendError(400, "Parameter 'articleCode' is required");
			return;
		}
		
		String amount = request.getParameter("amount");
		if (amount == null) {
			response.sendError(400, "Parameter 'amount' is required");
			return;
		}
		
		try {
			int articleCode = Integer.parseInt(articleCodeParam);
			Linea originalCartLine = abmcLinea.getOne(articleCode);
			
			int cartLineIndexOnCustomerCart = currentUser.getMiCarrito().getLineas().indexOf(originalCartLine);
			// Update cart line on memory
			Linea currentCartLine = currentUser.getMiCarrito().getLineas().get(cartLineIndexOnCustomerCart);
			currentCartLine.setCantidad(Integer.parseInt(amount));
			
			// Update cart line on DB
			abmcLinea.update(currentCartLine);
			response.sendRedirect("misCarritos.jsp");
			//request.getRequestDispatcher("misCarritos.jsp").forward(request, response);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch (NumberFormatException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=Numero invalido");
		}
		catch(Exception e) {
			response.sendRedirect("../errorPage.jsp?mensaje=Oops ha ocurrido un error");
		}
	}

}
