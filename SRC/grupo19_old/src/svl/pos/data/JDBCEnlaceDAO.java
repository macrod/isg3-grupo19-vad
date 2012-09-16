package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Enlace;

import com.mysql.jdbc.Connection;

public class JDBCEnlaceDAO implements IEnlaceDAO{

	private Connection conn;
	
	public JDBCEnlaceDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

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