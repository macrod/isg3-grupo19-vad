package svl.pos.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Municipio;
import svl.pos.data.JDBCProvinciaDAO;


import com.mysql.jdbc.Connection;

public class JDBCMunicipioDAO implements IMunicipioDAO{

	private Connection conn;
	
	public JDBCMunicipioDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}
	
	public List<Municipio> recuperarTodosLosMunicipios(){
		List<Municipio> lista = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipios";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				Municipio muni = new Municipio();
				muni.setMuni_id(result.getString("muni_id"));
				muni.setMuni_nombre(result.getString("muni_nombre"));
				muni.setProv_provid(result.getString("prov_provid"));
				lista.add(muni);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosMunicipios" + e.getMessage());
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
	
	public String selectMunicipioByMuniid(int muni_id) {
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Municipios WHERE muni_id = ?";
		ResultSet result = null;
		String nombre =  null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, muni_id);
			result = stmt.executeQuery();
			result.next();
			nombre = result.getString("muni_nombre");
        } catch (SQLException e) {
            System.out.println("Message: selectMunicipioByMuniid: " + e.getMessage());
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
	
	public String selectProvinciaByMuniid (int muni_id){
		JDBCProvinciaDAO daoProv = new JDBCProvinciaDAO();
		PreparedStatement stmt = null;
		String sql = "SELECT prov_provid FROM Municipios WHERE muni_id = ?";
		ResultSet result = null;
		int provid = 0;
		String nombreprov = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, muni_id);
			result = stmt.executeQuery();
			result.next();
			provid = result.getInt("prov_provid");
        } catch (SQLException e) {
            System.out.println("Message: selectMunicipioByMuniid: " + e.getMessage());
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
		nombreprov = daoProv.selectProviciaByProvid(provid);
		return nombreprov;
	}

	@Override
	public List<Municipio> recuperarTodosLosMunicipiosByProId(int prov_id) {
		
		System.out.println("Estamos en recuperarTodosLosMunicipiosByProId()");
		System.out.println(" Id de prov recibido: " + prov_id);
		
		List<Municipio> lista = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipios WHERE prov_provid = ?";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prov_id);
			result = stmt.executeQuery();

			while(result.next()){
				Municipio muni = new Municipio();
				muni.setMuni_id(result.getString("muni_id"));
				muni.setMuni_nombre(result.getString("muni_nombre"));
				muni.setProv_provid(result.getString("prov_provid"));
				lista.add(muni);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosMunicipiosByProId " + e.getMessage());
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
	
	public List<Municipio> recuperarNombreMunicipiosByProId(String prov_id) {
		
		List<Municipio> lista = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipios WHERE prov_provid = ?";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, prov_id);
			result = stmt.executeQuery();

			while(result.next()){
				Municipio muni = new Municipio();
				muni.setMuni_id(result.getString("muni_id"));
				muni.setMuni_nombre(result.getString("muni_nombre"));
				muni.setProv_provid(result.getString("prov_provid"));
				//municipios = new String[]{result.getString("muni_nombre")};
				lista.add(muni);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosMunicipiosByProId" + e.getMessage());
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

	
}
