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
import util.LogErrores;


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
		venta.setCliente(((Cliente)request.getSession().getAttribute("cliente")));
		abmcv.registrarVenta(venta);
		try {
			throw new DoniaMaryException("prueba",new Exception("nada"),Level.FATAL);
		}
		catch(DoniaMaryException e){
			
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
