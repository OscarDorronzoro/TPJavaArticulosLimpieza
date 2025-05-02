package servlet.article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Article;
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
		
		String descToSearch = request.getParameter("descToSearch");
		
		ArrayList<Article> articles = new ArrayList<Article>();
		try {
			if (descToSearch != null){
				articles = abmca.getAllByDescription(descToSearch);
			}
			else {
				articles = abmca.getAll();
			}
			
			request.setAttribute("articulos", articles);
			request.getRequestDispatcher("listadoArticulos.jsp").forward(request, response);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}

}
