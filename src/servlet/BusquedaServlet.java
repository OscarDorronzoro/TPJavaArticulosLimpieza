package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Articulo;
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
		
		response(response,abmca.getAllByDescripcion(request.getParameter("desc")));
	}

	private void response(HttpServletResponse response, ArrayList<Articulo> articulos)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.css\">"+ 
						  "<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap-theme.css\">"+
				    "</head>");
		out.println("<body class=\"bg-primary\"><br><br>");
		for(Articulo art : articulos) {
			out.println(
			" <div class=\"row\"> "+
			" <div class=\"col-md-2\"><img height=150 src="+art.getUrlImagen()+"></div> "+
			" <div class=\"col-md-10\"> "+
			"	<div class=\"row\"> "+
			"		<div class=\"col-md-1\">"+art.getCodArticulo()+"</div> "+
			"		<div class=\"col-md-8\"> "+
			"			<ul class=\"list-unstyled\"> "+							
			"				<li>Descripción: "+art.getDescripcion()+"</li>"+
			"				<li>Stock: "+art.getStock()+"</li> "+
			"				<li>Precio: "+art.getPrecio()+"</li> "+
			"			</ul> "+
			"		</div> "+
			"		<div class=\"col-md-3\"> "+
			"			<div class=\"row\"> "+
			"				<button class=\"btn btn-success\">Comprar</button> "+
			"			</div> "+
			"			<br> "+
			"			<div class=\"row\"> "+
			"				<button class=\"btn btn-success\">Añadir al carrito</button> "+
			"			</div> "+
			"		</div> "+
			"	</div> "+
			"	</div> "+
			"	</div> "+
			"	<br><br>"
			);
		}	
		
		out.println("<br><button class=\"btn btn-success\"><a href=\"main.jsp\">Volver a inicio</a></button>");
		out.println("<script src=\"bootstrap/js/jquery-3.4.1.js\"></script>" + 
					"<script src=\"bootstrap/js/bootstrap.js\"></script>");
		out.println("</body>");
		out.println("</html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
