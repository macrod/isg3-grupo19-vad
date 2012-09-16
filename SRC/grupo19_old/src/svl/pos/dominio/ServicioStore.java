package svl.pos.dominio;

import java.util.ArrayList;
import java.util.List;

import svl.pos.data.IServicioDAO;
import svl.pos.data.JDBCServicioDAO;

public class ServicioStore implements IServicioDAO {

	JDBCServicioDAO sdao = new JDBCServicioDAO();
	@Override
	public List<Servicio> recuperarTodosLosServicios() {
		return sdao.recuperarTodosLosServicios();
	}
	
	public List<Servicio> recuperarTodosLosServiciosporTipoServ(int tise_tiseid){
		return sdao.recuperarTodosLosServiciosporTipoServ(tise_tiseid);
	}
	
	public Servicio recuperarServicioByIdServicio(int serv_id){
		return sdao.recuperarServicioByIdServicio(serv_id);
	}
	
	public String selectServDescricoByServid (int serv_id){
		return sdao.selectServDescricoByServid(serv_id);
	}
	
	public String selectServDescrilaByServid (int serv_id){
		return sdao.selectServDescrilaByServid(serv_id);
	}
	
	public String selectTipoServicioByServid (int serv_id){
		return sdao.selectTipoServicioByServid(serv_id);
	}
	
	public void insertarServicio(Servicio servicio){
		sdao.insertarServicio(servicio);
	}
	
	public void borrarServicio(int serv_id){
		sdao.borrarServicio(serv_id);
	}
	
	public void actualizarServicio(Servicio servicio){
		sdao.actualizarServicio(servicio);
	}
	
	public void insertarVoluntariosServicios (int volu_voluid, List<Integer> serv_servid) {
		
		sdao.insertarVoluntariosServicios(volu_voluid, serv_servid);
	}
	
	public void insertarDemandantesServicios (int dema_demaid, List<Integer> serv_servid){
		sdao.insertarDemandantesServicios(dema_demaid, serv_servid);
	}

	public List<Servicio> listaServicioByVolId(int volu_id) {
		List<Servicio> listaservicios =  sdao.listaServicioByVolId(volu_id);
		return listaservicios;
	}
	
	public List<Servicio> listaServicioByDemId(int dema_id) {
		List<Servicio> listaservicios =  sdao.listaServicioByDemId(dema_id);
		return listaservicios;
	}
	
}