package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entities.Articulo;
import entities.Cliente;
import entities.Venta;
import logic.ABMCVenta;
import util.DoniaMaryException;


/**
 * Servlet implementation class ComprarServlet
 */
@WebServlet("/ComprarServlet")
public class ComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCVenta abmcv = new ABMCVenta();
		Venta venta = new Venta();
		
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		venta.setCliente(cliente);
		//linea seteada en registrarVenta
		try {
			abmcv.registrarVenta(venta);
			response.sendRedirect("main.jsp");
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
		}


		
	}

	protected void response(HttpServletResponse response, Articulo articulo, int cant) {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
