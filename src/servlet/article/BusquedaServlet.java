package servlet.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCArticulo;
import util.DoniaMaryException;


@WebServlet("/BusquedaServlet")
public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BusquedaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCArticulo abmca = new ABMCArticulo();
		
		try {
			if (request.getParameter("descBusqueda") != null){
				request.setAttribute("articulos", abmca.getAllByDescripcion(request.getParameter("descBusqueda")));
			}
			else {
				request.setAttribute("articulos",abmca.getAll());
			}
			request.getRequestDispatcher("listadoArticulos.jsp").forward(request, response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
