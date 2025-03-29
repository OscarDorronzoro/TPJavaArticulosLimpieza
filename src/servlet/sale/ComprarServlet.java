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
	private static final long serialVersionUID = 1L;

    public ComprarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCVenta abmcv = new ABMCVenta();
		Venta venta = new Venta();
		
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		venta.setCliente(cliente);
		try {
			abmcv.registrarVenta(venta); // linea seteada en registrarVenta
			response.sendRedirect("main.jsp");
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
