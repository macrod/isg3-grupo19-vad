package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Solicitud;

public interface ISolicitudDAO {

	public void addSolicitud(Solicitud solicitud);
	public List<Solicitud> recuperarTodasLasSolicitudes();
	public Solicitud selectAlertaByAlerid(int aler_id);
	public Solicitud selectAlertaByContacto(String aler_nombrecontacto);
	public int selectIDAlertaByNombre(String aler_nombrecontacto);
}
