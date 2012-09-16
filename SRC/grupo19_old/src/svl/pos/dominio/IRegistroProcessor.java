package svl.pos.dominio;

import java.util.ArrayList;
import java.util.List;

public interface IRegistroProcessor {

	boolean voluntarioRegistrado(String usua_usuario, String usua_email);
	void registrarUsuario(Usuario usuario, String perfil);
	Usuario obtenerMisdatos(String usua_usuario, String usua_email);
	Usuario buscarDemandantes(Usuario usuario);
	boolean isRegistrado(String usua_usuario, String usua_clave);
	public void guardarServicios(Usuario usuario,List<Integer> servicios, String perfil, int usua_id);
	public String selectProviciaByProvid(int prov_provid);
	public String selectMunicipioByMuniid(int muni_muniid);
	public List<Servicio> recuperarTodosLosServicios();
	public List<Provincia> recuperarTodasLasProvincias();
	public int recuperarIDByUsername(String username);
}
