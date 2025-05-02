package servlet.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.DoniaMaryException;

@WebServlet("/ListadoArticulosEdicionServlet")
public class ListadoArticulosEdicionServlet extends HttpServlet {
	private static final long serialVersionUID = -7015671790796813357L;

	public ListadoArticulosEdicionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCArticulo ABMCarticulo = new ABMCArticulo(); 
		try {
			request.setAttribute("articulos", ABMCarticulo.getAll() );
			request.getRequestDispatcher("WEB-INF/listadoArticulosEdicion.jsp").forward(request, response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
