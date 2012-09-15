package svl.pos.dominio;


import java.util.List;

import svl.pos.data.IPerfilDAO;
import svl.pos.data.JDBCPerfilDAO;

public class PerfilStore implements IPerfilDAO{

	JDBCPerfilDAO perfildao = new JDBCPerfilDAO();

	@Override
	public List<Perfil> recuperarTodosLosPerfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectPerfDescricoByPerfid(int perf_id) {
				
		return perfildao.selectPerfDescricoByPerfid(perf_id);
	}
	
	@Override
	public int selectPerfIDByUsuaid(int usua_id) {
		return perfildao.selectPerfIDByUsuaid(usua_id);
	}

	@Override
	public void insertarPerfil(int usua_id,int perf_id) {
		perfildao.insertarPerfil(usua_id, perf_id);
		
	}

	
}