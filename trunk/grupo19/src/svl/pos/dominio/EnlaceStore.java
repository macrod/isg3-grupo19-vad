package svl.pos.dominio;



import java.util.List;

import svl.pos.data.IEnlaceDAO;
import svl.pos.data.JDBCEnlaceDAO;

public class EnlaceStore implements IEnlaceDAO{

	JDBCEnlaceDAO dao = new JDBCEnlaceDAO();

	@Override
	public List<Enlace> recuperarTodosLosEnlaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectEnlaDescricoByEnlaid(int enla_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectURLByEnlaid(int enla_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
