package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.ABMCCliente;
import logic.ClientNotFoundException;
import logic.PasswordNotMatchException;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();

		cliente.setUsername(request.getParameter("username"));
		cliente.setPassword(request.getParameter("password"));
		
		ABMCCliente abmcc=new ABMCCliente();
		try {
			abmcc.completarCliente(cliente);
			response(response,"Cliente logeado exitosamente");
		} catch (ClientNotFoundException e) {
			// TODO Auto-generated catch block
			response(response,e.getMessage());
		} catch (PasswordNotMatchException e) {
			// TODO Auto-generated catch block
			response(response,e.getMessage());
		}
	}

	private void response(HttpServletResponse response, String mensaje)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.css\">"+ 
						  "<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap-theme.css\">"+
				    "</head>");
		out.println("<body class=\"bg-primary\">");
		out.println("<t1 class=\"text-center\"><b>"+mensaje+"</b></t1>");
		out.println("<button class=\"btn btn-success\"><a href=\"main.jsp\">Volver a inicio</a></button>");
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
