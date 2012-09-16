package svl.pos.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Servicio;
import svl.pos.dominio.TipoServicio;
import svl.pos.data.JDBCTipoServicioDAO;


import com.mysql.jdbc.Connection;

public class JDBCTipoServicioDAO implements ITipoServicioDAO{

	private Connection conn;
	
	public JDBCTipoServicioDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public List<TipoServicio> recuperarTodosLosTipoServicio() {
		
		System.out.println(" --- Estamos en recuperarTodosLosTipoServicio() ---");
		
		List<TipoServicio> lista = new ArrayList<TipoServicio>();
		
		String sql = "SELECT * FROM tipo_servicios";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				TipoServicio tiposerv = new TipoServicio();
				tiposerv.setTise_id(result.getInt("tise_id"));
				tiposerv.setTise_descrico(result.getString("tise_descrico"));
				tiposerv.setTise_descrila(result.getString("tise_descrila"));
				lista.add(tiposerv);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosTipoServicio " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
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
	
	@Override
public String selectServDescricoByTiseid(int tise_id) {
		
		System.out.println(" --- Estamos en selectServDescricoByTiseid() ---");
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM servicios WHERE tise_tiseid = ?";
		ResultSet result = null;
		String servdescrico  =  null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tise_id);
			result = stmt.executeQuery();
			result.next();
			servdescrico = result.getString("serv_descrico");
        } catch (SQLException e) {
            System.out.println("Message: selectServDescricoByTiseid: " + e.getMessage());
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
        return servdescrico;
	}

	@Override
	public String selectTiseDescricoByTiseid(int tise_id) {
			
			System.out.println(" --- Estamos en selectTiseDescricoByTiseid() ---");
			
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM tipo_servicios WHERE tise_id = ?";
			ResultSet result = null;
			String tisedescrico  =  null;
			try{
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, tise_id);
				result = stmt.executeQuery();
				result.next();
				tisedescrico = result.getString("tise_descrico");
	        } catch (SQLException e) {
	            System.out.println("Message: selectTiseDescricoByTiseid: " + e.getMessage());
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
	        return tisedescrico;
		}

	
	

}

