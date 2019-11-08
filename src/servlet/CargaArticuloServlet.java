package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Articulo;
import entities.Precio;
import logic.ABMCArticulo;
import util.ArticleException;

/**
 * Servlet implementation class CargaArticuloServlet
 */
@WebServlet("/CargaArticuloServlet/*")
public class CargaArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getPathInfo()==null) {
			request.getRequestDispatcher("WEB-INF/cargaArticulo.jsp").forward(request, response);
		}else {
			ABMCArticulo abmcA = new ABMCArticulo();
			
			Articulo articulo = new Articulo();
			articulo.setDescripcion(request.getParameter("descripcion"));
			articulo.setPuntoPedido(Integer.parseInt(request.getParameter("puntoPedido")));
			articulo.setCantAPedir(Integer.parseInt(request.getParameter("cantAPedir")));
			articulo.setStock(Integer.parseInt(request.getParameter("stock")));
			
			Precio precio = new Precio();
			precio.setValor(Double.parseDouble(request.getParameter("precio")));
			precio.setFechaDesde(new Date());
			articulo.setPrecio(precio);
			
			try {
				abmcA.add(articulo);
			} catch (ArticleException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			response.sendRedirect("SeccionAdminServlet");
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
