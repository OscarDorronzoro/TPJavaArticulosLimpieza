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
 * Servlet implementation class ModificarCategoriaServlet
 */
@WebServlet("/ModificarCategoriaServlet/*")
public class ModificarCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		switch(request.getPathInfo()) {
		case "/iniciarModificacion":
			request.getRequestDispatcher("../WEB-INF/modificarCategoria.jsp").forward(request, response);;
			break;
		case "/modificar":
			ABMCCategoria abmcC = new ABMCCategoria();
			Categoria cat = new Categoria();
			
			cat.setNombre(request.getParameter("nombre"));
			cat.setDescripcion(request.getParameter("descripcion"));
			
			try {
				abmcC.update(cat);
				response.sendRedirect("../ListadoCategoriasServlet");
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.setStatus(400);
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			}
			catch(Exception e) {
				response.setStatus(500);
				response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
			}
			break;
		default:
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
