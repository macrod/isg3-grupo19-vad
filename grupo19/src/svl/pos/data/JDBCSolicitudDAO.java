package svl.pos.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Provincia;
import svl.pos.dominio.Solicitud;
import svl.pos.dominio.Usuario;
import svl.pos.util.*;

import com.mysql.jdbc.Connection;

public class JDBCSolicitudDAO implements ISolicitudDAO{

	private Connection conn;
	
	
	public JDBCSolicitudDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public void addSolicitud(Solicitud solicitud) {
		System.out.println(" --- Estamos en  addSolicitud() --- " );
		
		String sql = "INSERT INTO solicitudes (soli_nombrecontacto, soli_emailcontacto, tise_tiseid, serv_servid,soli_descripcion, usua_usuaid, prov_provid, muni_muniid,soli_fecha) VALUES (?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, solicitud.getSoli_nombrecontacto());
			stmt.setString(2, solicitud.getSoli_emailcontacto());
			stmt.setInt(3, solicitud.getTise_tiseid());
			stmt.setInt(4, solicitud.getServ_servid());
			stmt.setString(5, solicitud.getSoli_descripcion());
			stmt.setInt(6, solicitud.getUsua_usuaid());
			stmt.setInt(7, solicitud.getProv_provid());
			stmt.setInt(8, solicitud.getMuni_muniid());
			stmt.setString(9, solicitud.getSoli_fecha());
			
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: addSolicitud  " + e.getMessage());
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
		System.out.println(" --- Salimos de  addSolicitud() --- " );
	}

		
	
	@Override
	public List<Solicitud> recuperarTodasLasSolicitudes() {
		System.out.println(" --- Estamos en  recuperarTodasLasSolicitudes() --- " );
		List<Solicitud> lista = new ArrayList<Solicitud>();
		
		String sql = "SELECT * FROM solicitudes";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				Solicitud soli = new Solicitud();
				soli.setSoli_id(result.getInt("soli_id"));
				soli.setSoli_nombrecontacto(result.getString("soli_nombrecontacto"));
				soli.setSoli_emailcontacto(result.getString("soli_emailcontacto"));
				soli.setTise_tiseid(result.getInt("tise_tiseid"));
				soli.setServ_servid(result.getInt("serv_servid"));
				soli.setSoli_descripcion(result.getString("soli_descripcion"));
				soli.setUsua_usuaid(result.getInt("usua_usuaid"));
				soli.setProv_provid(result.getInt("prov_provid"));
				soli.setMuni_muniid(result.getInt("muni_muniid"));
				soli.setSoli_fecha(result.getString("soli_fecha"));
				
				lista.add(soli);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodasLasSolicitudes:  " + e.getMessage());
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
	public Solicitud selectAlertaByAlerid(int soli_id) {
		 {
				
				PreparedStatement stmt = null;
				String sql = "SELECT * FROM solicitudes WHERE soli_id = ?   ";
				ResultSet result = null;
				Solicitud solicitud = new Solicitud();
				try{
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, soli_id);
					result = stmt.executeQuery();
					result.next();
					
					
					solicitud.setSoli_id(result.getInt("soli_id"));
					solicitud.setSoli_nombrecontacto(result.getString("soli_nombrecontacto"));
					
					solicitud.setSoli_emailcontacto(result.getString("soli_emailcontacto"));
					solicitud.setTise_tiseid(result.getInt("tise_tiseid"));
					solicitud.setServ_servid(result.getInt("serv_servid"));
					solicitud.setSoli_descripcion(result.getString("soli_descripcion"));
					solicitud.setUsua_usuaid(result.getInt("usua_usuaid"));
					
					solicitud.setProv_provid(result.getInt("prov_provid"));
					solicitud.setMuni_muniid(result.getInt("muni_muniid"));
					
					
		        } catch (SQLException e) {
		            System.out.println("Message: selectUserById: " + e.getMessage());
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
		        return solicitud;
		 }
	}

	@Override
	public Solicitud selectAlertaByContacto(String aler_nombrecontacto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectIDAlertaByNombre(String aler_nombrecontacto) {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Alertas WHERE aler_nombrecontacto = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aler_nombrecontacto);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("aler_id");
        } catch (SQLException e) {
            System.out.println("Message: selectIDAlertaByNombre: " + e.getMessage());
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