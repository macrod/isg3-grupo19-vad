package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Municipio;


public interface IMunicipioDAO {

	public List<Municipio> recuperarTodosLosMunicipios();
	public List<Municipio> recuperarTodosLosMunicipiosByProId(int prov_id);
	public List<Municipio> recuperarNombreMunicipiosByProId(String prov_id);
	public String selectMunicipioByMuniid (int muni_id);
	public String selectProvinciaByMuniid (int muni_id);
}
