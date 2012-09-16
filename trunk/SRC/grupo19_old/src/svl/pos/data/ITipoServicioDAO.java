package svl.pos.data;

import java.util.List;

import svl.pos.dominio.TipoServicio;

public interface ITipoServicioDAO {

	public List<TipoServicio> recuperarTodosLosTipoServicio();
	public String selectServDescricoByTiseid(int tise_id);
	public String selectTiseDescricoByTiseid(int tise_id);
}

