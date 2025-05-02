package servlet.category;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Category;
import entities.Customer;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/ModificarCategoriaServlet/*")
public class ModificarCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 7744035676213866338L;

	public ModificarCategoriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCategoria abmcC = new ABMCCategoria();
		
		String name = request.getParameter("name");
		
		if (name == null) {
			response.sendError(400, "Parameter 'name' is required");
			return;
		}
		name = URLDecoder.decode(name, "ISO-8859-1");
		try {
			request.setAttribute("category", abmcC.getOne(name));
			request.getRequestDispatcher("/WEB-INF/modificarCategoria.jsp").forward(request, response);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCCategoria abmcC = new ABMCCategoria();
		
		Category category = null;
		try {
			String name = request.getParameter("name");
			if (name == null) {
				response.sendError(400, "Parameter 'name' is required");
				return;
			}
			name = URLDecoder.decode(name, "ISO-8859-1");
			category = abmcC.getOne(name);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("..errorPage.jsp?mensaje=" + e.getMessage());
			return;
		}
		
		String description = request.getParameter("description");
		if (description != null) {
			category.setDescripcion(description);
		}
		
		try {
			abmcC.update(category);
			response.sendRedirect("ListadoCategoriasServlet");
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		catch(Exception e) {
			response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
		}
	}
}
