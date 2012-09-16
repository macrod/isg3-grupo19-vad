package svl.pos.dominio;

import java.util.List;

import svl.pos.data.IMunicipioDAO;
import svl.pos.data.JDBCMunicipioDAO;

public class MunicipioStore implements IMunicipioDAO {

	JDBCMunicipioDAO dao = new JDBCMunicipioDAO();
	@Override
	public List<Municipio> recuperarTodosLosMunicipios() {
		// TODO Auto-generated method stub
		return dao.recuperarTodosLosMunicipios();
	}
	
	public String selectMunicipioByMuniid (int muni_id){
		return dao.selectMunicipioByMuniid(muni_id);
	}
	
	public String selectProvinciaByMuniid (int muni_id){
		return dao.selectProvinciaByMuniid(muni_id);
	}

	@Override
	public List<Municipio> recuperarTodosLosMunicipiosByProId(int prov_id) {
		// TODO Auto-generated method stub
		return dao.recuperarTodosLosMunicipiosByProId(prov_id);
	}

	public List<Municipio> recuperarNombreMunicipiosByProId(String prov_id){
		return dao.recuperarNombreMunicipiosByProId(prov_id);
	}
}
