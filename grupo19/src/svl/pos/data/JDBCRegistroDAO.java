package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Registro;

import com.mysql.jdbc.Connection;

public class JDBCRegistroDAO implements IRegistroDAO{

	private Connection conn;
	
	public JDBCRegistroDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public List<Registro> recuperarTodosLosRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Registro> recuperarTodosLosRegistrosByUsuaid(int usua_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Registro> recuperarTodosLosRegistrosByVolucod(String volu_cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectTotalHorasByVolucod(String volu_cod) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}