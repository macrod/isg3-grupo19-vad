package svl.servlets;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svl.pos.dominio.Usuario;
import svl.pos.dominio.UsuarioStore;

/**
 * Servlet implementation class Logear
 */
public class ServletLogar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		
		if ( "si".equals((String)request.getAttribute("salir") )){
			sesion.invalidate();
			request.getRequestDispatcher("Inicio.jsp").include(request,response);
		}else{
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");

		UsuarioStore usuariostore = new UsuarioStore();
		if ( usuario != null && clave != null && !"".equals(usuario) && !"".equals(clave) ){
			if ( usuariostore.comprobarUsuario(usuario,clave) ){
				Usuario user = usuariostore.recuperarUsuarioByUsuarioClave(usuario,clave);
				request.getSession().setAttribute("usuario", user);
				request.getRequestDispatcher("Inicio.jsp").include(request,response);
			}else{
				request.getRequestDispatcher("errorUsuario.htm").include(request,response);
			}
		}
	}
	}

}
