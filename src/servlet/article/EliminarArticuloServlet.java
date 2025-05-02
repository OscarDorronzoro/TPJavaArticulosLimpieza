package servlet.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCArticulo;
import util.DoniaMaryException;

@WebServlet("/EliminarArticuloServlet/*")
public class EliminarArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EliminarArticuloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCArticulo abmcA = new ABMCArticulo();
		
		try {
			int codArticulo = Integer.parseInt(request.getParameter("codArticulo"));
			abmcA.delete(codArticulo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoArticulosEdicionServlet");
			dispatcher.forward(request, response);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
		}
		catch (Exception e) {
			response.sendRedirect("errorPage.jsp?mensaje=Oops, ha ocurrido un error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
