package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCCategoria;
import util.DoniaMaryException;

/**
 * Servlet implementation class ListadoCategoriasServlet
 */
@WebServlet("/ListadoCategoriasServlet")
public class ListadoCategoriasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoCategoriasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCCategoria abmcc = new ABMCCategoria();
		try {
			request.setAttribute("categorias", abmcc.getAll());
			request.getRequestDispatcher("WEB-INF/listadoCategorias.jsp").forward(request, response);
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
