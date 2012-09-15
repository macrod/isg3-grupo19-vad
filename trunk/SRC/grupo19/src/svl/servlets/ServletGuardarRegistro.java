package svl.servlets;

import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.*; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

import svl.pos.dominio.Usuario;
import svl.pos.dominio.RegistroProcessor;
import svl.pos.util.*;




/**
 * Servlet implementation class ServletGuardarRegistro
 */
public class ServletGuardarRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    
	public ServletGuardarRegistro() {
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
		
		
		String usua_usuario = (String)sesion.getAttribute("usua_usuario");
		String usua_clave   = (String)sesion.getAttribute("usua_clave");
		
		System.out.println("Usuario: " + usua_usuario);
		System.out.println("Clave: " + usua_clave);
		
		
		
		RegistroProcessor procesoregistro = new RegistroProcessor();
		Usuario user = new Usuario();
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy"); 
		if ( usua_usuario != null && usua_clave != null && !"".equals(usua_usuario) && !"".equals(usua_clave) ){
			if (!procesoregistro.isRegistrado(usua_usuario, usua_clave)){
				
			System.out.println("Si no está registrado...");
			//saco un mapa con los parametros del formulario
			//Map datosForm = request.getParameterMap();
			
			
			String usua_email = request.getParameter("usua_email");
			String usua_nif = request.getParameter("usua_nif");
			String usua_nombre = request.getParameter("usua_nombre");
			String usua_apellidos = request.getParameter("usua_apellidos");
			String usua_telefono = request.getParameter("usua_telefono");
			String usua_direccion = request.getParameter("usua_direccion");
			String usua_numero = request.getParameter("usua_numero");
			String usua_perfil = request.getParameter("usua_perfil");
			String usua_falta = Utiles.obtenerFechaActual();
			String usua_fbaja = "";
			int usua_codpostal = 0;
			int prov_provid = 0;
			int muni_muniid = 0;
			int usua_genero = 1;
			int usua_esvoluntario = 0;
			int usua_esdemandante = 0;
			int usua_esadmin = 0;
			//Boolean usua_esvoluntario  = new Boolean(false);
			//Boolean usua_esdemandante  = new Boolean(false);
			//Boolean usua_esadmin  = new Boolean(false);
			
			if(usua_perfil != null && usua_perfil.equals("voluntario"))
		    {
				usua_esvoluntario = 1;
		    }
			if(usua_perfil != null && usua_perfil.equals("demandante"))
		    {
				usua_esdemandante = 1;
		    }
			
			if (!"".equals(request.getParameter("usua_codpostal")) && request.getParameter("usua_codpostal") != null){
				usua_codpostal = Integer.parseInt(request.getParameter("usua_codpostal"));
			}
			if (!"".equals(request.getParameter("prov_provid")) && request.getParameter("prov_provid") != null){
				prov_provid = Integer.parseInt(request.getParameter("prov_provid"));
			}
			if (!"".equals(request.getParameter("muni_muniid")) && request.getParameter("muni_muniid") != null){
				muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));
			}
			if (!"".equals(request.getParameter("usua_genero")) && request.getParameter("usua_genero") != null){
				usua_genero = Integer.parseInt(request.getParameter("usua_genero"));
			}
				
			user.setUsua_usuario(usua_usuario);
			user.setUsua_clave(usua_clave);
			user.setUsua_email(usua_email);
			user.setUsua_nif(usua_nif);
			user.setUsua_nombre(usua_nombre);
			user.setUsua_apellidos(usua_apellidos);
			user.setUsua_telefono(usua_telefono);
			user.setUsua_direccion(usua_direccion);
			user.setUsua_numero(usua_numero);
			user.setUsua_codpostal(usua_codpostal);
			user.setProv_provid(prov_provid);
			user.setMuni_muniid(muni_muniid);
			user.setUsua_genero(usua_genero);
			user.setUsua_falta(usua_falta);
			user.setUsua_fbaja(usua_fbaja);
			user.setUsua_esvoluntario(usua_esvoluntario);
			user.setUsua_esdemandante(usua_esdemandante);
			user.setUsua_esadmin(usua_esadmin);
			
			request.getSession().setAttribute("usuario", user);
			request.getSession().setAttribute("perfil", usua_perfil);
			String dedonde = request.getParameter("dedonde");
			System.out.println("De donde viene: .." + dedonde );
			//Registramos el usuario
			if(dedonde != null && dedonde.equals("logar_registrar"))
		    {
				try {
					procesoregistro.registrarUsuario(user,usua_perfil);
					// quizas recuperar en registrarUsuario el id_usuario
					//Metemos el usua_id en sesion
					int usua_id = procesoregistro.recuperarIDByUsername(usua_nombre);
					//int usua_id = 23;
					request.getSession().setAttribute("usua_id", usua_id);
					System.out.println("--- usua_id: metido en sesión .." + usua_id );
					
					
					// Nos vamos a la segunda parte del registro
					System.out.println("Nos vamos a registrar los servicios.." );
					
					if(usua_perfil != null && usua_perfil.equals("voluntario"))
				    {
						request.getRequestDispatcher("VaD_Registrar_Servicios.jsp").include(request,response);
				    }
					if(usua_perfil != null && usua_perfil.equals("demandante"))
				    {
						request.getRequestDispatcher("VaD_Registrar_Servicios.jsp").include(request,response);
				    }
					
					//request.getRequestDispatcher("VaD_Usuario_Registrado.jsp?dedonde=guardaregistro").include(request,response);
		        
				} catch (ServletException e) {
		        	 System.out.println("Message: isRegistrado" + e.getMessage());
		        	 System.out.println("Error al registrar el usuario en los datos básicos");
		        }
		    }
			
			
			/*
			
			
			System.out.println("Usuaid: " + usuaid);
			System.out.println("la provincia: " + prov_provid);
			System.out.println("cod postal: " + usua_codpostal);
			
			
			//int prov_provid = (new Integer(request.getParameter("prov_provid"))).intValue();
			
			
			//int codpostal = 29007;//Integer.parseInt(usua_codpostal);
			//int provid = 29;//Integer.parseInt(prov_provid);
			*/
			
		}else{ // Si el usuario ya existe
			System.out.println("Si ya existe el usuario");
						
			request.getRequestDispatcher("VaD_errorUsuario.jsp?error=yaexiste").include(request,response);
		}
	}else{
		System.out.println("Si el usuario o la clave no son correctas");
		
		request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
	}
	}

}
