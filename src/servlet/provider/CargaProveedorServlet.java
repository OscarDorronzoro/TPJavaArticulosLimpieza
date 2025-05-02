package servlet.provider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customer;
import entities.Provider;
import logic.ABMCProveedor;
import util.DoniaMaryException;

@WebServlet("/CargaProveedorServlet")
public class CargaProveedorServlet extends HttpServlet {
    private static final long serialVersionUID = -8717528801275085557L;

	public CargaProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cargaProveedor.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCProveedor abmcP = new ABMCProveedor();
		Provider prov = new Provider();
		
		String cuit = request.getParameter("cuit");
		if (cuit == null) {
			response.sendError(400, "Parameter 'cuit' is required");
			return;
		}
		prov.setCuit(cuit);
		
		String businessName = request.getParameter("businessName");
		if (businessName == null) {
			response.sendError(400, "Parameter 'businessName' is required");
			return;
		}
		prov.setRazonSocial(businessName);
		
		String address = request.getParameter("address");
		if (address == null) {
			response.sendError(400, "Parameter 'address' is required");
			return;
		}
		prov.setDireccion(address);
		
		String phoneNumber = request.getParameter("phoneNumber");
		if (phoneNumber == null) {
			response.sendError(400, "Parameter 'phoneNumber' is required");
			return;
		}
		prov.setTelefono(phoneNumber);
		
		String mail = request.getParameter("mail");
		if (mail == null) {
			response.sendError(400, "Parameter 'mail' is required");
			return;
		}
		prov.setMail(mail);
		
		
		try {
			abmcP.add(prov);
		} catch (DoniaMaryException e) {
			response.sendRedirect("../errorPage.jsp?mensaje=" + e.getMessage());
		}
		response.sendRedirect("../ListadoCategoriasServlet");
	}
}
