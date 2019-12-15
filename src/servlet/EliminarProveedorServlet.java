package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCProveedor;
import util.DoniaMaryException;


/**
 * Servlet implementation class EliminarProveedorServlet
 */
@WebServlet("/EliminarProveedorServlet")
public class EliminarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProveedorServlet() {
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
			abmcprov.delete(request.getParameter("cuit"));
			
			
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("/errorPage.jsp?mensaje="+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("/errorPage.jsp?mensaje=Oops, ha ocurrido un error");
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
