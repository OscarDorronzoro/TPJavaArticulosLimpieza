package servlet.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.DoniaMaryException;

@WebServlet("/DetalleArticuloServlet")
public class DetalleArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetalleArticuloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCArticulo abmcArticulo = new ABMCArticulo();
		try {
			request.setAttribute("articulo", abmcArticulo.getOne(Integer.parseInt(request.getParameter("idArticulo"))));
		} catch (DoniaMaryException e) {
			response.sendRedirect("/errorPage.jsp?mensaje="+e.getMessage());
		}
		response.sendRedirect("detalleArticulo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
