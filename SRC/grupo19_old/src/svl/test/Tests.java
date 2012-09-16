package svl.test;

import svl.pos.data.*;
import svl.pos.dominio.*;


public class Tests {

	/**
	 * @param args
	 */
	public static void main() {
		MunicipioStore me = new MunicipioStore();
		JDBCUsuarioDAO jdbcuserdao = new JDBCUsuarioDAO();
		UsuarioStore us = new UsuarioStore();
		Usuario usuario = us.recuperarUsuarioByIdUsuario(2);
		System.out.println("Usuario : " + usuario.getUsua_nombre());
		
		String nombreus = usuario.getUsua_usuario();
		String claveus = usuario.getUsua_clave();
		System.out.println("Nombre : " + nombreus);
		System.out.println("Clave : " + claveus);
		String municipio = me.selectMunicipioByMuniid(2345);
		System.out.println("Municipio nº 2345 :" + municipio);
		
		//int id = jdbcuserdao.obtenerUsuaId(nombreus, claveus);
		//System.out.println("Id del usuario:  " + id);
		
		JDBCSolicitudDAO jdaosol = new JDBCSolicitudDAO();
		
		JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
		
		Solicitud solicitud = jdaosol.selectAlertaByAlerid(1);
		
		String soli_nombrecontacto = solicitud.getSoli_nombrecontacto();
		System.out.println("Nombrecontacto de la Solicitud:  " + soli_nombrecontacto);
		String soli_emailcontacto =  solicitud.getSoli_emailcontacto();
		System.out.println("Email de Contacto de la Solicitud : " + soli_emailcontacto);
		int serv_servid = solicitud.getServ_servid();
		System.out.println("Servicio:  " + serv_servid);
		int tise_tiseid = solicitud.getTise_tiseid();
		System.out.println("Tipo Servicio:  " + tise_tiseid);
		String servicio = jdaotise.selectServDescricoByTiseid(1);
		System.out.println("Nombre Servicio:  " + servicio);
		//String tise = jdaotise.(serv_servid);
		//System.out.println("Nombre Tipo ervicio: " + tise);
		String soli_descripcion =  solicitud.getSoli_descripcion();
		System.out.println("Descripción Solicitud: " + soli_descripcion);
		
		
		int prov_provid =  solicitud.getProv_provid();
		System.out.println("Provincia de la  Solicitud: " + prov_provid);
	}

}
