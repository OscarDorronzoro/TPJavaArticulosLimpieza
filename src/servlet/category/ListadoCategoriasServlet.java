package servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/ListadoCategoriasServlet")
public class ListadoCategoriasServlet extends HttpServlet {
    private static final long serialVersionUID = 9027345503938911724L;

	public ListadoCategoriasServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCCategoria abmcc = new ABMCCategoria();
		try {
			request.setAttribute("categorias", abmcc.getAll());
			request.getRequestDispatcher("WEB-INF/listadoCategorias.jsp").forward(request, response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
