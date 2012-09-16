package svl.servlets;

import java.io.IOException;


import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

import svl.pos.dominio.Usuario;
import svl.pos.dominio.RegistroProcessor;





/**
 * Servlet implementation class ServletGuardarRegistro
 */
public class ServletGuardarServicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private static Logger logger = Logger.getLogger(ServletGuardarServicios.class.getName());
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    
	public ServletGuardarServicios() {
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
		
		System.out.println("--- Estamos en ServletGuardarServicios() --- ");
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		logger.debug(" --- Estamos en : ServletGuardarRegistro");
		String usua_usuario = (String)session.getAttribute("usua_usuario");
		String usua_clave   = (String)session.getAttribute("usua_clave");
		String perfil       = (String)session.getAttribute("perfil");
		
		String dedonde = request.getParameter("dedonde");
		int usua_id = Integer.parseInt(request.getParameter("usua_id"));
		
		System.out.println("--- Usuario:  " + usua_usuario);
		System.out.println("--- Clave: " + usua_clave);
		System.out.println("--- dedonde: " + dedonde);
		System.out.println("--- perfil: " + perfil);
		System.out.println("--- usua_id: " + usua_id);
		
		RegistroProcessor procesoregistro = new RegistroProcessor();
		if ( usua_usuario != null && usua_clave != null && !"".equals(usua_usuario) && !"".equals(usua_clave) ){
			//Venimos del formulario de los servicios
			if(dedonde != null && dedonde.equals("registrar_servicios"))
		    {
				System.out.println("--- Venimos del formulario registrar_servicios --- ");
					List<Integer> servicios = new ArrayList<Integer>();
					int numservicios= Integer.parseInt(request.getParameter("numservicios"));
					int cheques = 0;
					for (int i = 0; i < numservicios; i++) {  
					      String chk_i = "chk_" + i;  
					      System.out.println("---Nombre del chk: " + chk_i); 
					      if(request.getParameter("chk_" + i)!=null){
					    	  cheques = Integer.parseInt(request.getParameter("chk_" + i));
					      
					    	  System.out.println("Cheque_" + i + ":  valor: " + cheques);
					    	  servicios.add(cheques); 
					      }
					    } 
					procesoregistro.guardarServicios(usuario, servicios, perfil, usua_id);
					// Hemos registrado los servicios del usuario
					System.out.println("--- Hemos registrado los servicios del usuario --- ");
					System.out.println("--- En funcion del perfil vamos a un inicio u a otro --- ");
					if ( perfil != null && !"".equals(perfil) && "voluntario".equals(perfil)){
						request.getRequestDispatcher("VaD_Inicio_VO.jsp").include(request,response);
					}
					if ( perfil != null && !"".equals(perfil) && "demandante".equals(perfil)){
						request.getRequestDispatcher("VaD_Inicio_DE.jsp").include(request,response);
					}
		    }
			
			
		}else{ // Si el usuario ya existe
			
			System.out.println("Hemos perdido la sesion ...");
			request.getRequestDispatcher("VaD_errorUsuario.jsp?error=nosesion").include(request,response);
		}
	}
	
}