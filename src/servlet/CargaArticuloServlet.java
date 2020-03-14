package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.Articulo;
import entities.Precio;
import logic.ABMCArticulo;
import logic.ABMCCategoria;
import util.DoniaMaryException;

/**
 * Servlet implementation class CargaArticuloServlet
 */
@WebServlet("/CargaArticuloServlet/*")
@MultipartConfig
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
		ABMCCategoria abmcC = new ABMCCategoria();
		
		if(request.getPathInfo()==null) {
			try {
				request.setAttribute("categorias", abmcC.getAll());
			} catch (DoniaMaryException e) {
				response.setStatus(400);
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/cargaArticulo.jsp").forward(request, response);
		}else {
			ABMCArticulo abmcA = new ABMCArticulo();
			
			Articulo articulo = new Articulo();
			articulo.setDescripcion(request.getParameter("descripcion"));
			articulo.setPuntoPedido(Integer.parseInt(request.getParameter("puntoPedido")));
			articulo.setCantAPedir(Integer.parseInt(request.getParameter("cantAPedir")));
			articulo.setStock(Integer.parseInt(request.getParameter("stock")));
			
			Part imagen = request.getPart("imagen");
			String nombreImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
			InputStream input = imagen.getInputStream();
			//File file =new File(nombreImagen);
			
			String url = "img-articulos/"+nombreImagen;			
			articulo.setUrlImagen(url);
			url="C:/Java/TP Articulos Limpieza/TPJavaArticulosLimpieza/WebContent/"+url;
			
			FileOutputStream output = null;
		    output = new FileOutputStream(url);
		    int leido = 0;
		    leido = input.read();
		    while (leido != -1) {
		    	output.write(leido);
		        leido = input.read();
		    }
		    output.close();
			
			Precio precio = new Precio();
			precio.setValor(Double.parseDouble(request.getParameter("precio")));
			precio.setFechaDesde(new Date());
			articulo.setPrecio(precio);
			
			try {
				articulo.setCategoria(abmcC.getOne(request.getParameter("categorias")));
				abmcA.add(articulo);
			} catch (DoniaMaryException e) {
				response.setStatus(400);
				response.sendRedirect("errorPage.jsp?mensaje="+e.getMessage());
			}
			response.sendRedirect("../ListadoArticulosEdicionServlet");
			//request.getRequestDispatcher("WEB-INF/seccionAdmin.jps").forward(request, response);
			
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
