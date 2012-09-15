package svl.pos.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Provincia;


import com.mysql.jdbc.Connection;

public class JDBCProvinciaDAO implements IProvinciaDAO{

	private Connection conn;
	
	public JDBCProvinciaDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}
	
	public List<Provincia> recuperarTodasLasProvincias(){
		List<Provincia> lista = new ArrayList<Provincia>();
		
		String sql = "SELECT * FROM provincias";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				Provincia prov = new Provincia();
				prov.setProv_id(result.getString("prov_id"));
				prov.setProv_nombre(result.getString("prov_nombre"));
				lista.add(prov);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodasLasProvincias" + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
		//ConnectionManager.getInstance().checkIn(conn);
			try {
				if (result != null) {
					result.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
		}

		return lista;
	}
	
	public String selectProviciaByProvid(int prov_id) {
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM provincias WHERE prov_id = ?";
		ResultSet result = null;
		String nombre =  null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prov_id);
			result = stmt.executeQuery();
			result.next();
			nombre = result.getString("prov_nombre");
        } catch (SQLException e) {
            System.out.println("Message: selectProviciaByProvid: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
        } finally {
        	//ConnectionManager.getInstance().checkIn(conn);
            try {
            	if (result != null)
            		result.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
            }
        }	
        return nombre;
	}
}
