package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Provincia;


public interface IProvinciaDAO {

	public List<Provincia> recuperarTodasLasProvincias();
	public String selectProviciaByProvid (int prov_provid);
}
