package svl.pos.dominio;

import java.util.List;

import svl.pos.data.ISolicitudDAO;
import svl.pos.data.ITipoServicioDAO;
import svl.pos.data.JDBCSolicitudDAO;
import svl.pos.data.IVoluntarioDAO;
import svl.pos.data.JDBCTipoServicioDAO;
import svl.pos.data.JDBCVoluntarioDAO;

public class SolicitudProcessor implements ISolicitudProcessor {

	private ISolicitudDAO daoSol = new JDBCSolicitudDAO();
	private IVoluntarioDAO vdao = new JDBCVoluntarioDAO();
	private ITipoServicioDAO tisidao = new JDBCTipoServicioDAO();
	

	@Override
	public void guardarSolicitud(Solicitud solicitud) {
		// Damos de alta la alerta
		System.out.println("--- Estamos en  SolicitudProcessor.guardarSolicitud() --- ");
		daoSol.addSolicitud(solicitud);
	}
	
	public  List<Integer> busquedaVoluntario(Solicitud solicitud) {
		// Buscamos el voluntario que cumple con la solicitud
		//daoAler.addAlerta(alerta);
		return vdao.buscarTodosVoluntariosSolicitudes(solicitud);
	}
	
	public void insertaBandejaVoluntario(Solicitud solicitud, int usua_id){
		//insertamos en la bandeja de voluntario
		vdao.insertaBandejaVoluntario(solicitud, usua_id);
	}
	
	public List<TipoServicio> recuperarTodosLosTiposServicios() {
		return tisidao.recuperarTodosLosTipoServicio();
	}
	
	public String selectTipoServicioByTiseid(int tise_id){
		return tisidao.selectTiseDescricoByTiseid(tise_id);
	}

	public String selectServDescricoByTiseid(int tise_id){
		return tisidao.selectServDescricoByTiseid(tise_id);
	}
}
