package svl.pos.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import svl.pos.dominio.Usuario;
import svl.pos.dominio.Demandante;
import svl.pos.dominio.Servicio;
import svl.pos.util.Utiles;

import com.mysql.jdbc.Connection;

public class JDBCDemandanteDAO implements IDemandanteDAO{

	private Connection conn;
	
	public JDBCDemandanteDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public void registarDemandante() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario consultarMisdatos(String usua_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAlerta(String usua_usuario, String usua_email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Servicio> listaServicioByDemId(int dema_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void insertarDemandante(Usuario usuario) {
		System.out.println("Estamos en insertarVoluntario()   ");
		
		String sql = "INSERT INTO voluntarios (volu_cod, usua_usuaid, volu_falta) VALUES (?,?, ?) ";
		String nombre = usuario.getUsua_nombre();
		int usua_id = usuario.getUsua_id();
		String falta = Utiles.obtenerFechaActual();
		PreparedStatement stmt = null;
		
		try {
			stmt =  conn.prepareStatement(sql);
	
			stmt.setString(1, nombre);
			stmt.setInt(2, usua_id);
			stmt.setString(3, falta);
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: insertarVoluntario" + e.getMessage());
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
		System.out.println("Salimos de insertarVoluntario()   ");	
	}
	
	public int recuperarDemaidByUsuaID(int usua_id) {
		
		System.out.println(" --- Estamos en recuperarDemaidByUsuaID()   ");
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM demandantes WHERE usua_usuaid = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usua_id);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("dema_id");
        } catch (SQLException e) {
            System.out.println("Message: recuperarDemaidByUsuaID: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
        } finally {
        	ConnectionManager.getInstance().checkIn(conn);
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
