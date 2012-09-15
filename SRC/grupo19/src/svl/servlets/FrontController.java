package svl.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import svl.pos.dominio.Usuario;
import svl.pos.dominio.UsuarioStore;
import svl.pos.dominio.Perfil;
import svl.pos.dominio.PerfilStore;
import svl.test.Tests;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(FrontController.class.getName());
	
	String user = "practica";

	String passwd = "practica";
	
	public void init() throws ServletException {
	}
       
    

	//destino = "../../servletAdministracion?accion=consulta
    
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
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		procesarAccion(accion,request,response);
		//procesarAccion(request,response);
	}
	
	public void procesarAccion(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("procesarAccion...accion: " + accion);
		logger.debug(" --- Estamos en : procesarAccion");
		
		if ( accion.equals("entrar") ){
			entrar(request,response);
		}else if ( accion.equals("registrar") ){
			registrar(request,response);
		}else if ( accion.equals("salir") ){
			salir(request,response);
		}else if ( accion.equals("buscar") ){
			buscar(request,response);
		}else if ( accion.equals("insertarServiciosVO") ){
			insertarServiciosVO(request,response);
		}else if ( accion.equals("inicioVO") ){
			inicioVO(request,response);
			
			
		// Opciones Voluntario
		}else if ( accion.equals("inicioDE") ){
			inicioDE(request,response);
		
		}else if ( accion.equals("misdatos") ){
			misDatos(request,response);
		}else if ( accion.equals("bandejasolicitudes") ){
			bandejaSolicitudes(request,response);
		}else if ( accion.equals("publicaractividades") ){
			publicarActividades(request,response);
		}else if ( accion.equals("proponerservicios") ){
			proponerServicios(request,response);
		//Opciones Demandante
			
		}else if ( accion.equals("solicitudservicio") ){
			solicitudServicio(request,response);
		}else if ( accion.equals("grabarsolicitud") ){
			grabarSolicitud(request,response);
		}else if ( accion.equals("valorarservicios") ){
			valorarServicios(request,response);
		}else if ( accion.equals("grabarregistro") ){
			grabarRegistro(request,response);
			
		}else if ( accion.equals("test") ){
			Test(request,response);
		}else if ( accion.equals("logar") ){
			logarUsuario(request,response);
		
		
		}else if ( accion.equals("MisDatos_VO") ){
			misDatos(request,response);
		}else if ( accion.equals("grabarmisdatos") ){
			grabarMisDatos(request,response);
		}else if ( accion.equals("SVL_Alertas") ){
			addAlerta(request,response);
		}else if ( accion.equals("insertarAlerta") ){
			insertarAlerta(request,response);
		}else if ( accion.equals("insertarUsuario") ){
			insertarUsuario(request,response);
		}else if ( accion.equals("actualizarUsuario") ){
			actualizarUsuario(request,response);
		}else if ( accion.equals("nuevoPerfil") ){
			guardarNuevoPerfil(request,response);
		}else if ( accion.equals("consultarUsuario") ){
			consultarUsuario(request,response);
		}
	}
	
	public void procesarAccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (logado(request)) {
			String accion = request.getParameter("accion");
			RequestDispatcher d = request.getRequestDispatcher(accion);
			if(d!=null){
				d.forward(request,response);
			}
			System.out.println("LOGADO ACCEDIENDO A " + accion);
		} else {
			response.sendRedirect("errorUsuario.htm");
		}
	}
	
	/**
	 * Clase que me loga o me registra en el sistema
	 */
	public void entrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("Procedimiento: entrar()" );
		
		HttpSession session = request.getSession();
		String adonde = request.getParameter("adonde");
		
		System.out.println("adonde: " + adonde);
		
		if ( "si".equals((String)request.getAttribute("salir") )){
			session.invalidate();
			request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
		}else{
		String usua_usuario = request.getParameter("usua_usuario");
		String usua_clave = request.getParameter("usua_clave");
		
		System.out.println("Usuario: " + usua_usuario);
		System.out.println("Clave: " + usua_clave);
		
		UsuarioStore usuariostore = new UsuarioStore();
		PerfilStore perfilstore = new PerfilStore();
		if ( usua_usuario != null && usua_clave != null && !"".equals(usua_usuario) && !"".equals(usua_clave) ){
			if ( usuariostore.isRegistrado(usua_usuario,usua_clave) ){
				
				Usuario user = usuariostore.recuperarUsuarioByUsuarioClave(usua_usuario,usua_clave);
				System.out.println("--- Hemos obtenido el usuario de  recuperarUsuarioByUsuarioClave()   " );
				int usuaid = user.getUsua_id();
				int perf_id = perfilstore.selectPerfIDByUsuaid(usuaid);
				
				String perfil = perfilstore.selectPerfDescricoByPerfid(perf_id);
				
				System.out.println("--- usuaid usuario bb.dd :  " + usuaid);
				System.out.println("--- perf_id usuario bb.dd :  " + perf_id);
				System.out.println("--- perfil usuario bb.dd: " + perfil);
				
				request.getSession().setAttribute("usua_id", usuaid);
				request.getSession().setAttribute("usuario", user);
				request.getSession().setAttribute("perfil", perfil);
				// esta es la opción por defecto
				if ( "inicio_vo".equals(adonde)) {
					if (perfil != null && "Voluntario".equals(perfil)) {
							request.getRequestDispatcher("VaD_Inicio_VO.jsp").include(request,response);
					}else if (perfil != null && "Demandante".equals(perfil)){
						request.getRequestDispatcher("VaD_Inicio_DE.jsp").include(request,response);
					}else{
						request.getRequestDispatcher("VaD_errorUsuario.jsp?error=sinperfil").include(request,response);
					}
				}
				
				if ( "admin".equals(adonde)) {
					request.getRequestDispatcher("VAD_Incio_AD.jsp").include(request,response);
				}
				// esta es la opción por defecto
				if ( "contregistro".equals(adonde)) {
					System.out.println("Vamos a VaD_Registro_VO_2.jsp ");
					request.getRequestDispatcher("VaD_Registro_VO_2.jsp").include(request,response);
				}
				if ( "alertas".equals(adonde)) {
					request.getRequestDispatcher("SVL_Alerta_1.jsp").include(request,response);
				}
				if ( "misdatos".equals(adonde)) {
					request.getRequestDispatcher("VaD_Misdatos.jsp").include(request,response);
				}
				
			}else{
				request.getRequestDispatcher("VaD_errorUsuario.jsp?error=usuariooclave").include(request,response);
			}
		}
	}
		
	}
	
	private void registrar(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException {
		System.out.println("Estoy en registrar()....");
		//logger.debug(" --- Estamos en : registrar");
		
		HttpSession session = request.getSession();
		
		Usuario user = (Usuario)session.getAttribute("usuario");
		String usua_usuario = request.getParameter("usua_usuario");
		String usua_clave   = request.getParameter("usua_clave");
		
		
		System.out.println("Usuario: " + usua_usuario);
		System.out.println("Clave: " + usua_clave);
		//logger.debug("Usuario: " + usua_usuario);
		//logger.debug("Clave: " + usua_clave);
		
		if ( usua_usuario != null && usua_clave != null && !"".equals(usua_usuario) && !"".equals(usua_clave) ){
			//los metemos en sesion
			request.getSession().setAttribute("usua_usuario", usua_usuario);
			request.getSession().setAttribute("usua_clave", usua_clave);
			request.getRequestDispatcher("ServletGuardarRegistro").include(request,response);
		}else{
			request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
		}
		
	}
	
	private void salir(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
		System.out.println("--- EStamos en Salir () --- ");
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
	}
	
	private void buscar(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException {
	request.getRequestDispatcher("VaD_Buscador.jsp").include(request,response);
	}
	
	private void insertarServiciosVO(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		
		System.out.println("Estoy en insertarServiciosVO()....");
		logger.debug(" --- Estamos en : insertarServiciosVO()...");
		
		
		
		HttpSession session = request.getSession();
		String dedonde = request.getParameter("dedonde");
		System.out.println("--- dedonde...." + dedonde);
		
		request.getSession().setAttribute("dedonde", dedonde);
		
		request.getRequestDispatcher("ServletGuardarServicios").include(request,response);
	}
	
	
	
	
	private void grabarSolicitud(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		
		System.out.println("Estoy en grabarSolicitud()....");
				
		
		HttpSession session = request.getSession();
		String dedonde = request.getParameter("dedonde");
		
		Usuario user = (Usuario)session.getAttribute("usuario");
		String perfil = (String)session.getAttribute("perfil");
		Integer usua_id = (Integer)session.getAttribute("usua_id");
		String usua_usuario = (String)session.getAttribute("usua_usuario");
		String usua_clave = (String)session.getAttribute("usua_clave");
		
		System.out.println("--- dedonde...." + dedonde);
		System.out.println("--- perfil...." + perfil);
		System.out.println("--- usua_id...." + usua_id);
		System.out.println("--- usua_usuario...." + usua_usuario);
		System.out.println("--- usua_clave...." + usua_clave);
		
		
		request.getSession().setAttribute("perfil", perfil);
		request.getSession().setAttribute("usua_id", usua_id);
		request.getSession().setAttribute("usua_usuario", usua_usuario);
		request.getSession().setAttribute("usua_clave", usua_clave);
		request.getSession().setAttribute("dedonde", dedonde);
		request.getSession().setAttribute("usuario", user);
		
		request.getRequestDispatcher("ServletGuardarSolicitud").include(request,response);
	}
	
	private void misDatos(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		request.getRequestDispatcher("VaD_Misdatos.jsp").include(request,response);
	}
	
	private void solicitudServicio(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		
		System.out.println("--- Estoy en solicitudServicio() ---");
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("usuario");
		request.getSession().setAttribute("usuario", user);
		request.getRequestDispatcher("VaD_Solicitud.jsp").include(request,response);
	}
	
	
	
	private void bandejaSolicitudes(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
	request.getRequestDispatcher("VaD_Bandeja_Solicitudes.htm").include(request,response);
	}
	
	
	
	private void publicarActividades(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		request.getRequestDispatcher("VaD_Publicar_Actividades.htm").include(request,response);
	}
	
	private void proponerServicios(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		request.getRequestDispatcher("VaD_Proponer_Servicios.htm").include(request,response);
	}
	
	
	private void valorarServicios(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		request.getRequestDispatcher("VaD_Proponer_Servicios.htm").include(request,response);
	}
	
	private void grabarRegistro(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("grabarRegistro...");
		//int  prov_provid =  Integer.parseInt(request.getParameter("prov_provid"));
		//int usua_codpostal = Integer.parseInt(request.getParameter("usua_codpostal"));
		String usua_usuario = request.getParameter("usua_usuario");
		System.out.println("El usua_usuario" + usua_usuario);
		
		//System.out.println("prov_provid " + prov_provid);
		//System.out.println("usua_codpostal " + usua_codpostal);
		request.getRequestDispatcher("VaD_Grabar_RegistroVO.jsp").include(request,response);
}
	
	
	
	public void logarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String accion = request.getParameter("accion");
		String adonde = request.getParameter("adonde");
		
		System.out.println("logarUsuario...");
		System.out.println("adonde " + adonde);
		System.out.println("accion " + accion);
		request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
	}
	
	public void inicioVO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		String perfil = (String)session.getAttribute("perfil");
		
		String accion = request.getParameter("accion");
		String dedonde = request.getParameter("dedonde");
		
		System.out.println("Estamos en inicioVO()...");
		System.out.println("--- adonde :" + dedonde);
		System.out.println("--- accion :" + accion);
		System.out.println("--- perfil :" + perfil);
		System.out.println("accion " + accion);
		request.getRequestDispatcher("VaD_Inicio_VO.jsp").include(request,response);
	}
	
public void inicioDE(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		String perfil = (String)session.getAttribute("perfil");
		
		String accion = request.getParameter("accion");
		String dedonde = request.getParameter("dedonde");
		
		System.out.println("Estamos en inicioDE() ...");
		System.out.println("--- adonde :" + dedonde);
		System.out.println("--- accion :" + accion);
		System.out.println("--- perfil :" + perfil);
		System.out.println("accion " + accion);
		request.getRequestDispatcher("VaD_Inicio_DE.jsp").include(request,response);
	}

	
	
	private void grabarMisDatos(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
	request.getRequestDispatcher("VaD_Grabar_Misdatos.jsp").include(request,response);
}
	
	
	
	private void insertarAlerta(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("insertarAlerta...");
		//String usua_usuario = request.getParameter("usua_usuario");
		//System.out.println("la usua_usuario" + usua_usuario);
		
		//System.out.println("prov_provid " + prov_provid);
		//System.out.println("usua_codpostal " + usua_codpostal);
		request.getRequestDispatcher("InsertarAlerta.jsp").include(request,response);
}
	
	
	
	private void consultarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ConsultarUsuario.jsp").include(request,response);
	}
	
	private void insertarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("InsertarUsuario.jsp").include(request,response);
	}
	
	private void actualizarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ActualizarUsuario.jsp").include(request,response);
	}

	
	
	
	
	private void Test(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setAttribute("salir", "si");
		//request.getRequestDispatcher("ServletLogar").include(request,response);
		Tests.main();
	}
	
	private void guardarNuevoPerfil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("nuevoPerfil").include(request,response);
		
	}
	
	public void addAlerta(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		request.getRequestDispatcher("SVL_Alerta_1.jsp").include(request,response);
		
	}

	
	
	private boolean logado(HttpServletRequest request) {
		boolean logado = false;

		HttpSession session = request.getSession(false);
		
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		String userForm = user.getUsua_usuario();
		//request.getParameter("usuario");
		String passwdForm = user.getUsua_clave();
			//request.getParameter("clave");

		
		if (session == null) {
			session = request.getSession();
			if (userForm == null || passwdForm == null || userForm.length() == 0 || passwdForm.length() == 0) {
				logado = false;

			} else {
				if (valido(userForm, passwdForm)) {
					logado = true;
					session.setAttribute("session.user", userForm);
				} else {
					logado = false;
				}

			}

		} else {
			if (userForm == null || passwdForm == null) {
				logado = true;
			} else {
				if (valido(userForm, passwdForm)) {
					logado = true;
					session.setAttribute("session.user", userForm);
				} else {
					logado = false;
				}

			}
		}
		return logado;
	}

	public boolean valido(String userForm, String passwdForm) {
		boolean res = false;
		res = (userForm.equals(this.user) && passwdForm.equals(this.passwd));
		return res;

	}
	
	
}
