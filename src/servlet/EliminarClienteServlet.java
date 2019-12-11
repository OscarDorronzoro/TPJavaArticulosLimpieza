package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import logic.ABMCCliente;
import util.DoniaMaryException;

/**
 * Servlet implementation class EliminarClienteServlet
 */
@WebServlet("/EliminarClienteServlet")
public class EliminarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCCliente abmcc = new ABMCCliente();
		
		try {
			abmcc.delete(abmcc.getOne(request.getParameter("username")));
			response.sendRedirect("../ListadoClientesServlet/todo");
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../erorrPage.jsp?mensaje="+e.getMessage());
		}catch(Exception e) {
			new DoniaMaryException("Exception catched",e,Level.ERROR);
			response.sendRedirect("../erorrPage.jsp?mensaje=Oops ha ocurrido un error");
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
