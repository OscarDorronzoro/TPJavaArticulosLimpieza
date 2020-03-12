package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import logic.ABMCCategoria;
import util.DoniaMaryException;

/**
 * Servlet implementation class CargaCategoriaServlet
 */
@WebServlet("/CargaCategoriaServlet/*")
public class CargaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getPathInfo()) {
		case "/iniciarCarga":
			request.getRequestDispatcher("../WEB-INF/cargaCategoria.jsp").forward(request, response);
			break;
		case "/cargado":
			ABMCCategoria abmcC = new ABMCCategoria();
			Categoria cat = new Categoria();
			
			cat.setNombre(request.getParameter("nombre"));
			cat.setDescripcion(request.getParameter("descripcion"));
			
			try {
				abmcC.add(cat);
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			}
			response.sendRedirect("../ListadoCategoriasServlet");
			break;
		default: throw new ServletException("error switch");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
