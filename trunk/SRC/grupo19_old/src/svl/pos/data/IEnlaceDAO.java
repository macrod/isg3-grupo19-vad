package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Enlace;

public interface IEnlaceDAO {

	public List<Enlace> recuperarTodosLosEnlaces();
	public String selectEnlaDescricoByEnlaid(int enla_id);
	public String selectURLByEnlaid(int enla_id);
	
}
