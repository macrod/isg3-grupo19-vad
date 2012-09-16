package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Perfil;

public interface IPerfilDAO {

	public List<Perfil> recuperarTodosLosPerfiles();
	public String selectPerfDescricoByPerfid(int perf_id);
	void insertarPerfil(int usua_id, int perf_id);
	public int selectPerfIDByUsuaid(int usua_id);
	
	
	
}
