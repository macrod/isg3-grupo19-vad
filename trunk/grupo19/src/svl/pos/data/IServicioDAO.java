package svl.pos.data;

import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Servicio;


public interface IServicioDAO {

	public List<Servicio> recuperarTodosLosServicios();
	public List<Servicio> recuperarTodosLosServiciosporTipoServ(int tise_tiseid);
	public Servicio recuperarServicioByIdServicio(int serv_id);
	public String selectServDescricoByServid (int serv_id);
	public String selectServDescrilaByServid (int serv_id);
	public String selectTipoServicioByServid (int serv_id);
	public void insertarServicio(Servicio servicio);
	public void insertarVoluntariosServicios (int volu_voluid, List<Integer> serv_servid);
	public void insertarDemandantesServicios (int dema_demaid, List<Integer> serv_servid);
	public void borrarServicio(int serv_id);
	public void actualizarServicio(Servicio servicio);
	public List<Servicio> listaServicioByVolId(int volu_id);
	public List<Servicio> listaServicioByDemId(int dema_id);
}
