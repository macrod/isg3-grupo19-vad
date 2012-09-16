package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Registro;

public interface IRegistroDAO {

	public List<Registro> recuperarTodosLosRegistros();
	public List<Registro> recuperarTodosLosRegistrosByUsuaid(int usua_id);
	public List<Registro> recuperarTodosLosRegistrosByVolucod(String volu_cod);
	public int selectTotalHorasByVolucod(String volu_cod);
		
}
