package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.ArticleException;
import util.DoniaMaryException;
import util.ProviderException;

/**
 * Servlet implementation class ListadoArticulosEdicionServlet
 */
@WebServlet("/ListadoArticulosEdicionServlet")
public class ListadoArticulosEdicionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoArticulosEdicionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCArticulo ABMCarticulo = new ABMCArticulo(); 
		try {
			request.setAttribute("articulos", ABMCarticulo.getAll() );
			request.getRequestDispatcher("WEB-INF/listadoArticulosEdicion.jsp").forward(request, response);
		} catch (DoniaMaryException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
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
