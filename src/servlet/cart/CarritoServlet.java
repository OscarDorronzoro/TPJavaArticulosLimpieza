package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Linea;
import logic.ABMCArticulo;
import logic.ABMCLineaCarrito;
import util.DoniaMaryException;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarritoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.sendError(405, "Method not allowed");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		
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
			
			if(lineas.contains(linea)) { // equals sobreescrito	
				int index = lineas.indexOf(linea);
				lineas.get(index).setCantidad(lineas.get(index).getCantidad() + amount);
				abmcLinea.update(lineas.get(index));
			} else {
				lineas.add(linea); // agregar mas carritos					
				abmcLinea.add(linea);
			}
			
			if(request.getParameter("comprar") != null) {
				response.sendRedirect("misCarritos.jsp");
				return;
			}
			response.sendRedirect("ListadoArticulosServlet");
		}
		catch (DoniaMaryException e) {			
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch(NumberFormatException e) {
			response.sendRedirect("errorPage.jsp?mensaje=No ingreso un numero valido");
		}
	}
}
