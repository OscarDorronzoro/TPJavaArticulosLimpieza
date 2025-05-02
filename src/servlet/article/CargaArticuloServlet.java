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

import entities.Article;
import entities.Customer;
import entities.Price;
import logic.ABMCArticulo;
import logic.ABMCCategoria;
import util.DoniaMaryException;

@WebServlet("/CargaArticuloServlet")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)
public class CargaArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CargaArticuloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCCategoria abmcC = new ABMCCategoria();

		try {
			request.setAttribute("categorias", abmcC.getAll());
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/cargaArticulo.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCArticulo abmcA = new ABMCArticulo();
		ABMCCategoria abmcC = new ABMCCategoria();
		
		Article articulo = new Article();
		
		String description = request.getParameter("descripcion");
		if (description == null) {
			response.sendError(400, "Parameter 'description' is required");
			return;
		}
		articulo.setDescripcion(description);
		
		String orderLimit = request.getParameter("puntoPedido");
		if (orderLimit == null) {
			response.sendError(400, "Parameter 'orderLimit' is required");
			return;
		}
		articulo.setPuntoPedido(Integer.parseInt(orderLimit));
		
		String amountToOrder = request.getParameter("cantAPedir");
		if (amountToOrder == null) {
			response.sendError(400, "Parameter 'amountToOrder' is required");
			return;
		}
		articulo.setCantAPedir(Integer.parseInt(amountToOrder));
		
		String stock = request.getParameter("stock");
		if (stock == null) {
			response.sendError(400, "Parameter 'stock' is required");
			return;
		}
		articulo.setStock(Integer.parseInt(stock));
		
		Part imagen = request.getPart("imagen");
		if (imagen.getSize() == 0) {
			response.sendError(400, "Parameter 'image' is required");
			return;
		}
		
		String nombreImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
		String url = "img-articulos/" + nombreImagen;
		articulo.setUrlImagen(url);
		
		String root;
		if(System.getProperty("os.name").equalsIgnoreCase("linux")) {
			root = "/home/oscar/eclipse-workspace/";
		}
		else {
			root = "C:/Java/TP Articulos Limpieza/";
		}
		
		String absoluteUrl = root + "TPJavaArticulosLimpieza/WebContent/" + url;
		
		InputStream input = imagen.getInputStream();
		FileOutputStream output = new FileOutputStream(absoluteUrl);
	    int chr = 0;
	    chr = input.read();
	    while (chr != -1) {
	    	output.write(chr);
	        chr = input.read();
	    }
	    output.close();
		
	    String price = request.getParameter("precio");
	    if (price == null) {
	    	response.sendError(400, "Parameter 'price' is required");
	    	return;
	    }
		Price precio = new Price(Double.parseDouble(price));
		articulo.setPrecio(precio);
		
		try {
			articulo.setCategoria(abmcC.getOne(request.getParameter("categorias")));
			abmcA.add(articulo);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
		response.sendRedirect("ListadoArticulosEdicionServlet");
	}

}
