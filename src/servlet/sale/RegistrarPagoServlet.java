package servlet.sale;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entities.Customer;
import entities.Sale;
import logic.ABMCVenta;
import util.DoniaMaryException;

@WebServlet(
	urlPatterns = {
		"/RegistrarPagoServlet"
		,"/RegistrarPagoServlet/IniciarRegistro"
		,"/RegistrarPagoServlet/Buscar"
		,"/RegistrarPagoServlet/RegistrarPago"
	}
)
public class RegistrarPagoServlet extends HttpServlet {
	private static final long serialVersionUID = 823128837518469823L;

	public RegistrarPagoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCVenta abmcVenta = new ABMCVenta();		
		
		String servletPath = request.getServletPath();
		
		switch (servletPath) {
		case "/RegistrarPagoServlet/IniciarRegistro":
			request.getRequestDispatcher("/WEB-INF/registrarPago.jsp").forward(request, response);
			break;
		case "/RegistrarPagoServlet/Buscar":
			try {
				String username = request.getParameter("username");
				if (username == null) {
					response.sendError(405, "Parameter 'username' is required");
					return;
				}
				
				ArrayList<Sale> ventas = abmcVenta.getAllPendingByCustomer(username);
				request.setAttribute("ventas", ventas);
				request.setAttribute("username", username);
				
				request.getRequestDispatcher("/WEB-INF/registrarPago.jsp").forward(request, response);
			}
			catch (DoniaMaryException e) {
				response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
			}
			break;
		case "/RegistrarPagoServlet/RegistrarPago":
			response.sendError(405, "Method not allowed");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCVenta abmcVenta = new ABMCVenta();		
		
		String servletPath = request.getServletPath();
		
		switch(servletPath) {
		case "/RegistrarPagoServlet/IniciarRegistro": 
			response.sendError(405, "Method not allowed");
			break;
		case "/RegistrarPagoServlet/Buscar":
			response.sendError(405, "Method not allowed");
			break;
		case "/RegistrarPagoServlet/RegistrarPago":
			String saleNumber = request.getParameter("saleNumber");
			if (saleNumber == null) {
				response.sendError(400, "Parameter 'saleNumber' is required");
				return;
			}
			
			LocalDateTime paymentDate = null;
			String paid = request.getParameter("paid");
			if (paid != null) {
				paymentDate = LocalDateTime.now();
			}
			
			try {
				Sale venta = abmcVenta.getOne(Integer.parseInt(saleNumber));
				venta.setfPago(paymentDate);
				venta.setfRetiro(paymentDate);
				
				abmcVenta.update(venta);
				
				request.setAttribute("venta", venta);
				request.getRequestDispatcher("/WEB-INF/registrarPago.jsp").forward(request, response);
			}
			catch (NumberFormatException e) {
				response.sendRedirect("../errorPage.jsp?mensaje=Numero de venta incorrecto");
			}
			catch (DoniaMaryException e) {
				response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
			}
			catch(Exception e) {
				new DoniaMaryException("Exception catched", e, Level.ERROR);
				response.sendRedirect("../errorPage.jsp?mensaje=Oops ha ocurrido un error");
			}
			break;
		}
	}
}
