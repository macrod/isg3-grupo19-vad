package svl.pos.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import svl.pos.dominio.Perfil;

import com.mysql.jdbc.Connection;

public class JDBCPerfilDAO implements IPerfilDAO{

	private Connection conn;
	
	public JDBCPerfilDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public List<Perfil> recuperarTodosLosPerfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectPerfDescricoByPerfid(int perf_id) {
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM PERFILES WHERE perf_id = ?";
		ResultSet result = null;
		String perfil =  "Demandante";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, perf_id);
			result = stmt.executeQuery();
			result.next();
			perfil = result.getString("perf_descrico");
        } catch (SQLException e) {
            System.out.println("Message: selectPerfDescricoByPerfid : " + e.getMessage());
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
        return perfil;
	
	}

	@Override
	public void insertarPerfil(int usua_id,int perf_id) {
		System.out.println("Estamos en insertarPerfil()   ");
		
		String sql = "INSERT INTO usuarios_perfiles (usua_usuaid, perf_perfid) VALUES (?,?) ";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
	
			stmt.setInt(1, usua_id);
			stmt.setInt(2, perf_id);
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: insertarPerfil:  " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
		}
		System.out.println("Salimos de insertarPerfil()   ");	
	}
	
	public int selectPerfIDByUsuaid(int usua_id) {
		
		System.out.println("Estamos en selectPerfIDByUsuaid()   ");
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM USUARIOS_PERFILES WHERE usua_usuaid = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usua_id);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("perf_perfid");
			System.out.println("perf_perfid   " + id );
        } catch (SQLException e) {
            System.out.println("Message: selectPerfIDByUsuaid: " + e.getMessage());
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
        
        return id;
	}
	
}