package svl.pos.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import svl.pos.data.IVoluntarioDAO;
import svl.pos.data.JDBCVoluntarioDAO;
import svl.pos.data.IDemandanteDAO;
import svl.pos.data.JDBCDemandanteDAO;
import svl.pos.data.IUsuarioDAO;
import svl.pos.data.JDBCUsuarioDAO;
import svl.pos.data.IPerfilDAO;
import svl.pos.data.JDBCPerfilDAO;
import svl.pos.data.IServicioDAO;
import svl.pos.data.JDBCServicioDAO;
import svl.pos.data.IMunicipioDAO;
import svl.pos.data.JDBCMunicipioDAO;
import svl.pos.data.IProvinciaDAO;
import svl.pos.data.JDBCProvinciaDAO;


import org.apache.log4j.Logger;

public class RegistroProcessor implements IRegistroProcessor {

	private IVoluntarioDAO vdao = new JDBCVoluntarioDAO();
	private IDemandanteDAO ddao = new JDBCDemandanteDAO();
	private IUsuarioDAO udao = new JDBCUsuarioDAO();
	private IPerfilDAO pdao = new JDBCPerfilDAO();
	private IServicioDAO sdao = new JDBCServicioDAO();
	private IMunicipioDAO mdao = new JDBCMunicipioDAO();
	private IProvinciaDAO provdao = new JDBCProvinciaDAO();
	
	
	//JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
	
	private static Logger logger = Logger.getLogger(RegistroProcessor.class.getName());
	
	@Override
	public boolean voluntarioRegistrado(String usua_usuario, String usua_email) {
		return false;
	}

	@Override
	public void registrarUsuario(Usuario usuario,String perfil) {
		System.out.println("--- Estamos en : registrarUsuario");
		System.out.println("--- Perfil : " + perfil  );
		
		// Primero se registra el usuario
		udao.insertarUsuario(usuario);
		// Obtenemos el nombre y el usua_id del usuario insertado
		String nombre = usuario.getUsua_nombre();
		
		int usua_id = udao.recuperarIDByUsername(nombre);
		
		
		
		usuario.setUsua_id(usua_id);
		int perf_id = 2;
		
		if(perfil != null && perfil.equals("voluntario"))
	    {
			perf_id = 2;
			//Insertamos en la tabla voluntarios
			vdao.insertarVoluntario(usuario);
	    }
		if(perfil != null && perfil.equals("demandante"))
	    {
			perf_id = 3;
			//Insertamos en la tabla demandantes
			ddao.insertarDemandante(usuario);
	    }
		
		
		
		// Segundo se guarda su perfil
		pdao.insertarPerfil(usua_id, perf_id);
		
		
		
	}

	public void guardarServicios(Usuario usuario,List<Integer> serv_id, String perfil, int usua_id) {
		System.out.println("--- Estamos en : guardarServicios");
		System.out.println("--- Perfil : " + perfil);
		// Obtenemos el ID del usuario
		//int usua_id = usuario.getUsua_id();
		
		
		
		if(perfil != null && perfil.equals("voluntario"))
	    {
			// Recuperamos el ID del Voluntario
			int volu_id = vdao.recuperarVoluidByUsuaID(usua_id);
			
			// Insertamos en la tabla servicios_voluntarios
			sdao.insertarVoluntariosServicios(volu_id, serv_id);
	    }
		
		if(perfil != null && perfil.equals("demandante"))
	    {
			// Recuperamos el ID del Demandante
			int dema_id = ddao.recuperarDemaidByUsuaID(usua_id);
			
			// Insertamos en la tabla servicios_demandantes
			sdao.insertarDemandantesServicios(dema_id, serv_id);
	    }
	
	}
		
	public Usuario obtenerMisdatos(String usua_usuario, String usua_clave) {
		// Obtenemos el usuario a partir de su usuario y clave
		System.out.println("--- Estamos en : obtenerMisdatos(usua_usuario, usua_clave) ---");
		Usuario usuario = udao.recuperarUsuarioByUsuarioClave(usua_usuario, usua_clave);
		return usuario;
	}
	
	public boolean isRegistrado(String usua_usuario, String usua_clave) {
		
		logger.debug(" --- Estamos en : isRegistrado");
		System.out.println("--- Estamos en : isRegistrado");
		return udao.isRegistrado(usua_usuario, usua_clave);
	}

	@Override
	public Usuario buscarDemandantes(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String selectProviciaByProvid(int prov_provid){
		String provincia = provdao.selectProviciaByProvid(prov_provid);
		return provincia;
	}
	
	public String selectMunicipioByMuniid(int muni_muniid){
		String municipio = mdao.selectMunicipioByMuniid(muni_muniid);
		return municipio;
	}
	
	public List<Servicio> recuperarTodosLosServicios() {
		List<Servicio> listaservicios =  sdao.recuperarTodosLosServicios();
		return listaservicios;
	}
	
	
	
	public List<Provincia> recuperarTodasLasProvincias() {
		return provdao.recuperarTodasLasProvincias();
	}
	
	public int recuperarIDByUsername(String username) {
		return udao.recuperarIDByUsername(username);
	}
	

	
}
