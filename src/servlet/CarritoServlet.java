package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Linea;
import logic.ABMCArticulo;
import logic.ABMCLineaCarrito;
import util.DoniaMaryException;


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
			ABMCLineaCarrito abmcLinea=new ABMCLineaCarrito(cliente);
			ABMCArticulo articuloLogic= new ABMCArticulo();
			Linea linea = new Linea();
			
				
			
			try {
				
				Integer cant=Integer.parseInt(request.getParameter("cantidad"));
				if(cant==null || cant<=0) {
					response.sendRedirect("/errorPage.jsp?mensaje=error en la cantidad de articulos");
				}
				
				
				linea.setCantidad(cant);
				linea.setArticulo(articuloLogic.getOne(Integer.parseInt(request.getParameter("id"))));
				ArrayList<Linea> lineas = cliente.getMiCarrito().getLineas();
				if(lineas.contains(linea)) {    //equals sobreescrito
					
					int index = lineas.indexOf(linea);
					lineas.get(index).setCantidad(lineas.get(index).getCantidad()+cant);
					abmcLinea.update(lineas.get(index));
					
				} else {
					
					lineas.add(linea);    //agregar mas carritos					
					abmcLinea.add(linea);
				}
				
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block				
				response.sendRedirect("/errorPage.jsp?mensaje="+e.getMessage());
			}
			catch(NumberFormatException e) {
				response.sendRedirect("/errorPage.jsp?mensaje=No ingreso un numero valido");
			}
			
			if(request.getParameter("comprar")!=null) {
				response.sendRedirect("MisCarritos.jsp");
			}
			else {
				try {
					request.setAttribute("articulos", new ABMCArticulo().getAll());  //si no lo vuelvo a setear esta en null, como mantener?
					request.getRequestDispatcher("listadoArticulos.jsp").forward(request, response);
				} catch (DoniaMaryException e) {
					// TODO Auto-generated catch block
					response.sendRedirect("/errorPage.jsp?mensaje="+e.getMessage());
				}
				
				
			}
			
		}
		else {
			response.sendRedirect("iniciarSesion.jsp");
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
