package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.LineaCarrito;
import logic.ABMCArticulo;


/**
 * Servlet implementation class CarritoServlet
 */
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
					
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		
		if(cliente!=null) {
			ABMCArticulo articuloLogic= new ABMCArticulo();
			LineaCarrito linea = new LineaCarrito();
			
			Integer cant=Integer.parseInt(request.getParameter("cantidad"));
			if(cant==null || cant<=0 ) {
				throw new ServletException("error en la cantidad de articulos");
			}	
			linea.setCantidad(cant);
			linea.setArticulo(articuloLogic.getOne(Integer.parseInt(request.getParameter("id"))));
			cliente.getMiCarrito().getLineas().add(linea); //agregar mas carritos
			
			if(request.getParameter("comprar")!=null) {
				response(response,"MisCarritos.jsp");
			}
			else {
				//actualizar contador de articulos en carrito
			}
			
		}
		else {
			response(response,"iniciarSesion.jsp");
		}
	
	}

	protected void response(HttpServletResponse response, String pagina) {
		try {
			response.sendRedirect(pagina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
