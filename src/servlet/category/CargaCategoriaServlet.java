package servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Category;
import entities.Customer;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/CargaCategoriaServlet")
public class CargaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CargaCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cargaCategoria.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCCategoria abmcC = new ABMCCategoria();
		Category cat = new Category();
		
		String name = request.getParameter("nombre");
		if (name == null) {
			response.sendError(400, "Parameter 'name' is required");
			return;
		}
		cat.setNombre(name);
		
		String description = request.getParameter("descripcion");
		if (description == null) {
			response.sendError(400, "Parameter 'description' is required");
			return;
		}
		cat.setDescripcion(description);
		
		try {
			abmcC.add(cat);
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		response.sendRedirect("ListadoCategoriasServlet");
	}
}
