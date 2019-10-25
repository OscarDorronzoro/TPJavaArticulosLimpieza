package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entities.Carrito;
import entities.Cliente;
import entities.Linea;
import logic.ABMCLineaCarrito;
import util.DoniaMaryException;
import util.ProviderException;

/**
 * Servlet implementation class ModificarCarritoServlet
 */
@WebServlet("/ModificarCarritoServlet/*")
public class ModificarCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCarritoServlet() {
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
			int indexlinea = cliente.getMiCarrito().getLineas().indexOf(abmcLinea.getOne(Integer.parseInt(request.getPathInfo())));
			Linea linea = cliente.getMiCarrito().getLineas().get(indexlinea);
			linea.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
			
			abmcLinea.update(linea);
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			request.setAttribute("mensaje",e.getMessage());
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		}
		catch(Exception e) {
			request.setAttribute("mensaje","Oops ha ocurrido un error");
			//request.getServletPath().
			response.sendRedirect(request.getContextPath()+"/errorPage.jsp?mensaje="
		            + URLEncoder.encode( e.getMessage(), "UTF8"));
			
			//request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
