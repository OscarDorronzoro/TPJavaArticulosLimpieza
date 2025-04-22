package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrito;
import entities.Cliente;
import entities.Linea;
import logic.ABMCArticulo;
import logic.ABMCLineaCarrito;
import util.DoniaMaryException;

@WebServlet(
	urlPatterns = {
		"/CarritoServlet"
		,"/CarritoServlet/currentPurchase"
		,"/CarritoServlet/favorites"
		,"/CarritoServlet/wishList"
		,"/CarritoServlet/budget"
	}
)
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarritoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Cliente customer = (Cliente) request.getSession().getAttribute("cliente");
		
		if (customer == null) {
			response.sendRedirect("../iniciarSesion.jsp");
			return;
		}
		
		String servletPath = request.getServletPath();
		
		String cartType = "currentPurchase";
		switch (servletPath) {
		case "/CarritoServlet":
			response.sendRedirect("CarritoServlet/currentPurchase");
			break;
		case "/CarritoServlet/currentPurchase":
			cartType = "currentPurchase";
			break;
		case "/CarritoServlet/favorites":
			cartType = "favorites";
			break;
		case "/CarritoServlet/wishList":
			cartType = "wishList";
			break;
		case "/CarritoServlet/budget":
			cartType = "budget";
			break;
		}
		
		Carrito cart = customer.getMiCarrito(cartType);
		if (cart == null) {
			cart = new Carrito("dummy cart");
		}
		
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("../misCarritos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		
		if (cliente == null) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCLineaCarrito abmcLinea = new ABMCLineaCarrito(cliente);
		ABMCArticulo articuloLogic = new ABMCArticulo();
		Linea linea = new Linea();
		
		String amountParam = request.getParameter("amount");
		if (amountParam == null) {
			response.sendError(400, "Parameter 'amount' is required");
			return;
		}
		
		String articleCode = request.getParameter("articleCode");
		if (articleCode == null) {
			response.sendError(400, "Parameter 'articleCode' is required");
			return;
		}
		
		try {
			Integer amount = Integer.parseInt(amountParam);
			if (amount <= 0) {
				response.sendRedirect("errorPage.jsp?mensaje=error en la cantidad de articulos");
				return;
			}
			
			linea.setCantidad(amount);
			linea.setArticulo(articuloLogic.getOne(Integer.parseInt(articleCode)));
			ArrayList<Linea> lineas = cliente.getMiCarrito().getLineas();
			
			if (lineas.contains(linea)) { // equals overwritten
				int index = lineas.indexOf(linea);
				lineas.get(index).setCantidad(lineas.get(index).getCantidad() + amount);
				abmcLinea.update(lineas.get(index));
			}
			else {
				lineas.add(linea);
				abmcLinea.add(linea);
			}
			
			if (request.getParameter("comprar") != null) {
				response.sendRedirect("CarritoServlet");
				return;
			}
			response.sendRedirect("BusquedaServlet");
		}
		catch (DoniaMaryException e) {			
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch(NumberFormatException e) {
			response.sendRedirect("errorPage.jsp?mensaje=No ingreso un numero valido");
		}
	}
}
