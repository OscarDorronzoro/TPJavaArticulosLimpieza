package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.Articulo;
import logic.ABMCArticulo;
import util.ArticleException;
import util.CategoryException;
import util.DoniaMaryException;
import util.PriceException;
import util.ProviderException;

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
		ABMCArticulo abmcA = new ABMCArticulo();
		
		switch (request.getPathInfo()) {
		case "/iniciarModificacion":
			try {
				request.setAttribute("articulo", abmcA.getOne(Integer.parseInt(request.getParameter("codigo"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				response.setStatus(404);
				response.sendRedirect("../errorPage.jsp?mensaje=articulo no encontrado");
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.setStatus(400);
				response.sendRedirect("../errorPage.jsp?mensaje="+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response.setStatus(500);
				response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
			}
			request.getRequestDispatcher("../WEB-INF/modificarArticulo.jsp").forward(request, response);
			break;
		case "/cargado":	
			Articulo articulo = new Articulo();
			//terminar (cargar imagen, en vez de url, precargar campos con los datos actuales)
			articulo.setDescripcion(request.getParameter("descripcion"));
			articulo.setCantAPedir(Integer.parseInt(request.getParameter("cantAPedir")));
			articulo.setPuntoPedido(Integer.parseInt(request.getParameter("puntoPedido")));
			articulo.setStock(Integer.parseInt(request.getParameter("stock")));
			
			
			Part imagen = request.getPart("imagen");
			String nombreImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
			InputStream input = imagen.getInputStream();
			
			String url = "img-articulos/"+nombreImagen;			
			articulo.setUrlImagen(url);
			
			String raiz;
			if(System.getProperty("os.name").equalsIgnoreCase("linux")) {
				raiz="/root/git/";
			}
			else {
				raiz="C:/Java/TP Articulos Limpieza/";
			}
			
			url=raiz+"TPJavaArticulosLimpieza/WebContent/"+url;
			
			FileOutputStream output = null;
		    output = new FileOutputStream(url);
		    int leido = 0;
		    leido = input.read();
		    while (leido != -1) {
		    	output.write(leido);
		        leido = input.read();
		    }
		    output.close();
			
			
			
			try {
				abmcA.update(articulo);
				response.sendRedirect("../ListadoArticulosServlet");
			} catch (DoniaMaryException e) {
				// TODO Auto-generated catch block
				response.setStatus(400);
				response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
			}
			catch(Exception e) {
				response.setStatus(500);
				response.sendRedirect("../errorPage.jsp?mensaje=Oops. ha ocurrido un error");
			}
			break;
		default:
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
