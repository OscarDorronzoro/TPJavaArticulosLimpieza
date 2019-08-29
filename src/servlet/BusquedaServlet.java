package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import logic.ABMCArticulo;

/**
 * Servlet implementation class BusquedaServlet
 */
@WebServlet("/BusquedaServlet")
public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ABMCArticulo abmca = new ABMCArticulo();
		
		if(request.getParameter("descBusqueda")!=null){
			request.setAttribute("articulos",abmca.getAllByDescripcion(request.getParameter("descBusqueda")));
		}
		else {
			request.setAttribute("articulos",abmca.getAll());
		}
		request.getRequestDispatcher("listadoArticulos.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
