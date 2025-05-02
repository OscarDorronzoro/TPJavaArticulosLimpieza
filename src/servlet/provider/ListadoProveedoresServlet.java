package servlet.provider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ABMCProveedor;
import util.DoniaMaryException;

@WebServlet("/ListadoProveedoresServlet")
public class ListadoProveedoresServlet extends HttpServlet {
    private static final long serialVersionUID = -4086216275109193528L;

	public ListadoProveedoresServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCProveedor abmcprov = new ABMCProveedor();
		
		try {
			request.setAttribute("proveedores", abmcprov.getAll());
			request.getRequestDispatcher("/WEB-INF/listadoProveedores.jsp").forward(request, response);
			
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Method not allowed");
	}
}
