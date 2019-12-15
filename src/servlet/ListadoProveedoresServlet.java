package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCProveedor;
import util.DoniaMaryException;
import util.ProviderException;

/**
 * Servlet implementation class ListadoProveedoresServlet
 */
@WebServlet("/ListadoProveedoresServlet")
public class ListadoProveedoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoProveedoresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCProveedor abmcprov = new ABMCProveedor();
		try {
			request.setAttribute("proveedores", abmcprov.getAll());
			request.getRequestDispatcher("WEB-INF/listadoProveedores.jsp").forward(request, response);
			
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
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
