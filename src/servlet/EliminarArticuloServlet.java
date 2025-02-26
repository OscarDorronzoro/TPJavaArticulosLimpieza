package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.DoniaMaryException;

/**
 * Servlet implementation class EliminarArticuloServlet
 */
@WebServlet("/EliminarArticuloServlet/*")
public class EliminarArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarArticuloServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCArticulo abmcA = new ABMCArticulo();
		
		try {
			int codArticulo = Integer.parseInt(request.getParameter("codArticulo"));
			abmcA.delete(codArticulo);
			//request.getRequestDispatcher("/WEB-INF/listadoArticulosEdicion.jsp").forward(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoArticulosEdicionServlet");
			dispatcher.forward(request, response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
		} catch (Exception e) {
			response.sendRedirect("errorPage.jsp?mensaje=Oops, ha ocurrido un error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
