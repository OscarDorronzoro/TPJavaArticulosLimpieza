package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Venta;
import logic.ABMCCliente;
import logic.ABMCVenta;
import util.DoniaMaryException;

/**
 * Servlet implementation class RegistrarPagoServlet
 */
@WebServlet("/RegistrarPagoServlet/*")
public class RegistrarPagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarPagoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCVenta abmcVenta = new ABMCVenta();		
		
		switch(request.getPathInfo()) {
		case "/buscar":
			try {
				request.setAttribute("ventas", abmcVenta.getAllPendientesByCliente(request.getParameter("username")));
				request.getRequestDispatcher("/WEB-INF/registrarPago.jsp").forward(request, response);
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			break;
		case "/registrar":
			//buscar venta, asignarle fecha de pago, guardarla
			try {
				Venta venta = abmcVenta.getOne(Integer.parseInt(request.getParameter("nroVenta")));
				request.getRequestDispatcher("WEB-INF/registrarPago.jsp");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("../errorPage.jsp?mensaje=Numero de venta incorrecto");
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			}
			break;
		default: throw new ServletException("error en switch");
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
