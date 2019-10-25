package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCLineaCarrito;
import util.ProviderException;
import entities.Carrito;
import entities.Cliente;

/**
 * Servlet implementation class EliminarDeCarritoServlet
 */
@WebServlet("/EliminarDeCarritoServlet")
public class EliminarDeCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarDeCarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		ABMCLineaCarrito abmcLinea= new ABMCLineaCarrito(cliente);
		
		try {
			int codArt=Integer.parseInt(request.getParameter("idArticulo"));
			cliente.getMiCarrito().getLineas().remove(abmcLinea.getOne(codArt));
			abmcLinea.delete(codArt);
		} catch (ProviderException e) {
			// TODO Auto-generated catch block
			request.setAttribute("mensaje",e.getMessage());
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
