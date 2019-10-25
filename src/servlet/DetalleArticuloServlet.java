package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.ProviderException;

/**
 * Servlet implementation class DetalleArticuloServlet
 */
@WebServlet("/DetalleArticuloServlet")
public class DetalleArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCArticulo abmcArticulo = new ABMCArticulo();
		try {
			request.setAttribute("articulo", abmcArticulo.getOne(Integer.parseInt(request.getParameter("idArticulo"))));
		} catch (ProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("detalleArticulo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
