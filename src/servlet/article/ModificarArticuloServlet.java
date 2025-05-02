package servlet.article;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;

import entities.Article;
import entities.Category;
import entities.Customer;
import entities.Price;
import logic.ABMCArticulo;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/ModificarArticuloServlet/*")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)
public class ModificarArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = -5631803109079992020L;

	public ModificarArticuloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCArticulo abmcA = new ABMCArticulo();
		ABMCCategoria abmcC = new ABMCCategoria();
		
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			response.sendError(404, "Resource not found");
			return;
		}
		
		switch (pathInfo) {
		case "/IniciarModificacion":
			try {
				int codArticulo = Integer.parseInt(request.getParameter("codArticulo"));
				request.setAttribute("articulo", abmcA.getOne(codArticulo));
				
				request.setAttribute("categorias", abmcC.getAll());
				
				request.getRequestDispatcher("../WEB-INF/modificarArticulo.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				//response.sendError(404, "Article not found");
				response.sendRedirect("../errorPage.jsp?mensaje=articulo no encontrado");
			} catch (DoniaMaryException e) {
				//response.sendError(400, e.getMessage());
				response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
			} catch (Exception e) {
				//response.sendError(500, e.getMessage());
				response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
			}
			break;
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCArticulo abmcA = new ABMCArticulo();
		
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			response.sendError(404, "Resource not found");
			return;
		}
		
		switch (pathInfo) {
			case "/Cargado":
				int codArticulo = Integer.parseInt(request.getParameter("codArticulo"));
				Article articulo = null;
				
				String imageUrl;
				try {
					articulo = abmcA.getOne(codArticulo);
					imageUrl = parseImage(request);
				} catch (DoniaMaryException e) {
					response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
					return;
				}
				
				if (imageUrl != null) {
					articulo.setUrlImagen(imageUrl);
				}
				
				String description = request.getParameter("descripcion");
				if (description != null) {
					articulo.setDescripcion(description);
				}
				
				String category = request.getParameter("categoria");
				if (category != null) {
					articulo.setCategoria(new Category(category));
				}
				
				String limitToOrder = request.getParameter("puntoPedido");
				if (limitToOrder != null) {
					articulo.setPuntoPedido(Integer.parseInt(limitToOrder));
				}
				
				String amountToOrder = request.getParameter("cantAPedir");
				if (amountToOrder != null) {
					articulo.setCantAPedir(Integer.parseInt(amountToOrder));
				}
				
				String stock = request.getParameter("stock");
				if (stock != null) {
					articulo.setStock(Integer.parseInt(stock));
				}
				
				String price = request.getParameter("precio");
				if (price != null) {
					articulo.setPrecio(new Price(Double.parseDouble(price)));
				}
				
				try {
					abmcA.update(articulo);
					response.sendRedirect("../ListadoArticulosEdicionServlet");
				} catch (DoniaMaryException e) {
					//response.sendError(400, e.getMessage());
					response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
				}
				catch(Exception e) {
					//response.sendError(500, e.getMessage());
					e.printStackTrace();
					response.sendRedirect("../errorPage.jsp?mensaje=Oops, ha ocurrido un error");
				}
				break;
			default:
			}
		}
	
	private String parseImage(HttpServletRequest request) throws DoniaMaryException {
		
		Part image;
		try {
			image = request.getPart("imagen");
		} catch (IOException | ServletException e) {
			throw new DoniaMaryException("Error when retrieving image");
		}
		
		if (image.getSize() == 0) {
			return null; // Keep previous image
		}
		
		String imageName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
		String url = "img-articulos/" + imageName; // url to return after save file
		
		String root = "";
		if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
			root = "/home/oscar/eclipse-workspace/";
		}
		else {
			root = "C:/Java/TP Articulos Limpieza/";
		}
		String absoluteUrl = root + "TPJavaArticulosLimpieza/WebContent/" + url;
		
		InputStream input = null;
		FileOutputStream output = null;
		try {
			input = image.getInputStream();
			output = new FileOutputStream(absoluteUrl);
			
		    int chr = 0;
		    chr = input.read();
		    while (chr != -1) {
		    	output.write(chr);
		    	chr = input.read();
		    }
		}
		catch (IOException e) {
			throw new DoniaMaryException("Error when loading image", e, Level.ERROR);
		}
		finally {
			try {
				if (output != null) {
					output.close();
				}
			}
			catch (IOException e) {
				// Safe to ignore
			}
		}
		return url;
	}

}
