package servlet.sale;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Venta;
import logic.ABMCVenta;
import util.DoniaMaryException;

@WebServlet("/ComprarServlet")
public class ComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 8005251996258642053L;

	public ComprarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null) {
			response.sendRedirect("/TP_Articulos_Limpieza/iniciarSesion.jsp");
			return;
		}
		
		ABMCVenta abmcv = new ABMCVenta();
		Venta venta = new Venta();
		
		venta.setCliente(currentUser);
		try {
			abmcv.registerSale(venta);
			response.sendRedirect("main.jsp");
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
