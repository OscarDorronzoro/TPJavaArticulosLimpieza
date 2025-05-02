package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Article;
import entities.Cart;
import entities.Customer;
import entities.Line;
import logic.ABMCCarrito;
import logic.ABMCLineaCarrito;
import util.CartException;
import util.CartLineException;

@WebServlet("/SwitchCartForLineServlet")
public class SwitchCartForLineServlet extends HttpServlet {
    private static final long serialVersionUID = -8615764502707166460L;

	public SwitchCartForLineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("cliente");
		if (customer == null) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		String currentCartType = request.getParameter("currentCartType");
		if (currentCartType == null) {
			response.sendError(400, "Parameter 'currentCartType' is required");
			return;
		}
		
		String articleCode = request.getParameter("articleCode");
		if (articleCode == null) {
			response.sendError(400, "Parameter 'articleCode' is required");
			return;
		}
		
		String newCartType = request.getParameter("newCartType");
		if (newCartType == null) {
			response.sendError(400, "Parameter 'newCartType' is required");
			return;
		}
	
		Cart currentCart = customer.getMiCarrito(currentCartType);
		if (currentCart == null) {
			response.sendError(400, "Current line's cart type not exists");
			return;
		}
		
		Cart newCart = customer.getMiCarrito(newCartType);
		boolean addNewCart = false;
		if (newCart == null) {
			addNewCart = true;
			newCart = new Cart(newCartType);
			newCart.setDescripcion(newCartType);
			customer.setMiCarrito(newCart);
		}
		
		Article article = new Article();
		try {
			article.setCodArticulo(Integer.parseInt(articleCode));
		}
		catch (NumberFormatException e) {
			response.sendError(400, "Article code is invalid");
			return;
		}

		Line linetoSearch = new Line();
		linetoSearch.setArticulo(article);
		
		ArrayList<Line> curretCartLines = currentCart.getLineas();
		ArrayList<Line> newCartLines = newCart.getLineas();
		
		int index = curretCartLines.indexOf(linetoSearch);
		if (index == -1) {
			response.sendError(400, "Cannot move cart line, doesnt' exists on origin cart");
			return;
		}
		
		try {
			if (addNewCart) {
				ABMCCarrito crudCart = new ABMCCarrito();
				crudCart.add(newCart, customer.getUsername());
			}
			
			// Remove from current Cart
			ABMCLineaCarrito crudCurrentCartLine = new ABMCLineaCarrito(customer, currentCart);
			Line line = curretCartLines.get(index);
			curretCartLines.remove(index);
			crudCurrentCartLine.delete(line.getArticulo().getCodArticulo());
			
			// Add to new Cart
			ABMCLineaCarrito crudNewCartLine = new ABMCLineaCarrito(customer, newCart);
			newCartLines.add(line);
			crudNewCartLine.add(line);
			
			response.sendRedirect("CarritoServlet/currentPurchase");
		}
		catch (CartLineException | CartException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}
}
