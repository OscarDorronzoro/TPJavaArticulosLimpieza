package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entities.Venta;
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
		case "/iniciarRegistro": 
			request.getRequestDispatcher("../WEB-INF/registrarPago.jsp").forward(request, response);
			break;
		case "/buscar":
			try {
				request.setAttribute("ventas", abmcVenta.getAllPendientesByCliente(request.getParameter("username")));
				request.getRequestDispatcher("../WEB-INF/registrarPago.jsp").forward(request, response);
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			}
			break;
		case "/RegistrarPagado":
			registrarPago(new Date(),abmcVenta,request,response);
			break;
		case "/RegistrarNoPagado": 
			registrarPago(null,abmcVenta,request,response);
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

	private void registrarPago(Date fechaPago,ABMCVenta abmcVenta,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Venta venta = abmcVenta.getOne(Integer.parseInt(request.getParameter("nroVenta")));
			venta.setfPago(fechaPago);
			venta.setfRetiro(fechaPago);
			abmcVenta.update(venta);
			request.setAttribute("venta", venta);
			request.getRequestDispatcher("../WEB-INF/registrarPago.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../errorPage.jsp?mensaje=Numero de venta incorrecto");
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
		}catch(Exception e) {
			new DoniaMaryException("Exception catched",e,Level.ERROR);
			response.sendRedirect("../errorPage.jsp?mensaje=Oops ha ocurrido un error");
		}
	}
}
