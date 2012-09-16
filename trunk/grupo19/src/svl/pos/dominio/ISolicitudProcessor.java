package svl.pos.dominio;


import java.util.List;


public interface ISolicitudProcessor {

	public void guardarSolicitud(Solicitud solicitud);
	public List<Integer> busquedaVoluntario(Solicitud solicitud);
	public void insertaBandejaVoluntario(Solicitud solicitud, int usua_id);
	public List<TipoServicio> recuperarTodosLosTiposServicios();
	public String selectTipoServicioByTiseid(int tise_id);
	public String selectServDescricoByTiseid(int tise_id);
	//void mandarCorreo(Busqueda busqueda);
	
}
