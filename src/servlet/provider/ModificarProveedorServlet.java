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

@WebServlet("/ModificarProveedorServlet")
public class ModificarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 3172224969569056327L;

	public ModificarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ABMCProveedor abmcP = new ABMCProveedor();

		Provider provider = null;
		String cuit = null;
		try {
			cuit = request.getParameter("cuit");
			if (cuit == null) {
				response.sendError(400, "Parameter 'cuit' is required");
				return;
			}
			provider = abmcP.getOne(cuit);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher("/WEB-INF/modificarProveedor.jsp").forward(request, response);
		}
		catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		
		ABMCProveedor abmcP = new ABMCProveedor();
		
		String cuit = request.getParameter("cuit");
		if (cuit == null) {
			response.sendError(400, "Parameter 'cuit' is required");
			return;
		}
		
		Provider provider = null;
		try {
			provider = abmcP.getOne(cuit);
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
			return;
		}

		String businessName = request.getParameter("businessName");
		if (businessName != null) {
			provider.setRazonSocial(businessName);
		}
		
		String address = request.getParameter("address");
		if (address != null) {
			provider.setDireccion(address);
		}
		
		String phoneNumber = request.getParameter("phoneNumber");
		if (phoneNumber != null) {
			provider.setTelefono(phoneNumber);
		}
		
		String mail = request.getParameter("mail");
		if (mail != null) {
			provider.setMail(mail);
		}
			
		try {
			abmcP.update(provider);
			response.sendRedirect("ListadoProveedoresServlet");
		} catch (DoniaMaryException e) {
			response.sendRedirect("errorPage.jsp?mensaje=" + e.getMessage());
		}
	}
}
