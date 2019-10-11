package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Articulo;
import logic.ABMCArticulo;

/**
 * Servlet implementation class ModificarArticuloServlet
 */
@WebServlet("/ModificarArticuloServlet/*")
public class ModificarArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCArticulo abmcA = new ABMCArticulo();
		Articulo articulo = new Articulo();
		
		//terminar
		articulo.setDescripcion(request.getParameter("descripcion"));
		articulo.setCantAPedir(Integer.parseInt(request.getParameter("cantAPedir")));
		articulo.setPuntoPedido(Integer.parseInt(request.getParameter("puntoPedido")));
		articulo.setStock(Integer.parseInt(request.getParameter("stock")));
		articulo.setUrlImagen(request.getParameter("urlImagen"));
		
		abmcA.update(articulo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
