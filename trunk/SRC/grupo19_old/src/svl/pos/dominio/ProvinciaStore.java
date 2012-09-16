package svl.pos.dominio;

import java.util.List;

import svl.pos.data.IProvinciaDAO;
import svl.pos.data.JDBCProvinciaDAO;

public class ProvinciaStore implements IProvinciaDAO {

	JDBCProvinciaDAO dao = new JDBCProvinciaDAO();
	@Override
	public List<Provincia> recuperarTodasLasProvincias() {
		return dao.recuperarTodasLasProvincias();
	}
	
	public String selectProviciaByProvid (int prov_provid){
		return dao.selectProviciaByProvid(prov_provid);
	}

}
