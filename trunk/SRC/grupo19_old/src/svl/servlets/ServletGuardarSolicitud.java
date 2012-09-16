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
import svl.pos.dominio.Solicitud;
import svl.pos.dominio.RegistroProcessor;
import svl.pos.dominio.SolicitudProcessor;




/**
 * Servlet implementation class ServletGuardarSolicitud
 */
public class ServletGuardarSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	//private static Logger logger = Logger.getLogger(ServletGuardarSolicitud.class.getName());
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    
	public ServletGuardarSolicitud() {
        super();
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("--- Estamos en ServletGuardarSolicitud() --- ");
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		//logger.debug(" --- Estamos en : ServletGuardarSolicitud");
		String usua_usuario = (String)session.getAttribute("usua_usuario");
		String usua_clave   = (String)session.getAttribute("usua_clave");
		String perfil       = (String)session.getAttribute("perfil");
		int prov_provid = 0;
		int muni_muniid = 0;
		int tise_tiseid = 0;
		int serv_servid = 0;
		
		String dedonde = request.getParameter("dedonde");
		int usua_id = Integer.parseInt(request.getParameter("usua_id"));
		
		System.out.println("--- Usuario:  " + usua_usuario);
		System.out.println("--- Clave: " + usua_clave);
		System.out.println("--- dedonde: " + dedonde);
		System.out.println("--- perfil: " + perfil);
		System.out.println("--- usua_id: " + usua_id);
		
		SolicitudProcessor procesosolicitud = new SolicitudProcessor();
		Solicitud solicitud = new Solicitud();
		if ( usua_usuario != null && usua_clave != null && !"".equals(usua_usuario) && !"".equals(usua_clave) ){
			//Venimos del formulario de los servicios
			if(dedonde != null && dedonde.equals("solicitud"))
		    {
				System.out.println("--- Venimos del formulario alta de solicitud --- ");
					
					//int volu_id = 0;
					String soli_nombrecontacto = request.getParameter("soli_nombrecontacto");
					String soli_emailcontacto  = request.getParameter("soli_emailcontacto");
					String soli_descripcion  = request.getParameter("soli_descripcion");
					String soli_fecha  = request.getParameter("soli_fecha");
					
					
					if (!"".equals(request.getParameter("tise_tiseid")) && request.getParameter("tise_tiseid") != null){
						tise_tiseid = Integer.parseInt(request.getParameter("tise_tiseid"));
					}
					if (!"".equals(request.getParameter("serv_servid")) && request.getParameter("serv_servid") != null){
						serv_servid = Integer.parseInt(request.getParameter("serv_servid"));
					}
					if (!"".equals(request.getParameter("prov_provid")) && request.getParameter("prov_provid") != null){
						prov_provid = Integer.parseInt(request.getParameter("prov_provid"));
					}
					if (!"".equals(request.getParameter("muni_muniid")) && request.getParameter("muni_muniid") != null){
						muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));
					}
					
					solicitud.setSoli_nombrecontacto(soli_nombrecontacto);
					solicitud.setSoli_emailcontacto(soli_emailcontacto);
					solicitud.setTise_tiseid(tise_tiseid);
					solicitud.setServ_servid(serv_servid);
					solicitud.setSoli_descripcion(soli_descripcion);
					solicitud.setUsua_usuaid(usua_id);
					solicitud.setProv_provid(prov_provid);
					solicitud.setMuni_muniid(muni_muniid);
					solicitud.setSoli_fecha(soli_fecha);
					
					//Guarda la solicitud
					System.out.println("--- Vamos a dar de alta la solicitud del demandante --- ");
					procesosolicitud.guardarSolicitud(solicitud);
					
					System.out.println("--- Hemos dado de alta la solicitud del demandante --- ");
					
					//Hacemos la busqueda del voluntario por si cumple con el criterio
					System.out.println("--- Buscamos la lista de  voluntarios que cumplan los criterios de la solicitud --- ");
					List<Integer> usuaids = procesosolicitud.busquedaVoluntario(solicitud);
					
					// insertamos en la bandeja de solicitudes
					for ( Integer usua : usuaids ){ 
						
							System.out.println("--- Por cada uno de los voluntarios encontrados, si exiten, insertamos la solicitd en su bandejasol --- ");
							procesosolicitud.insertaBandejaVoluntario(solicitud,usua);
						}
										
					System.out.println("--- Nos vamos al menu inicio del demandante --- ");
					
						request.getRequestDispatcher("VaD_Inicio_DE.jsp").include(request,response);
					
		    }
			
			
		}else{ // Si el usuario ya existe
			
			System.out.println("Hemos perdido la sesion ...");
			request.getRequestDispatcher("VaD_errorUsuario.jsp?error=nosesion").include(request,response);
		}
	}
	
}