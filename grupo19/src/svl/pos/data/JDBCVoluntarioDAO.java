package svl.pos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.util.Utiles;


import svl.pos.data.JDBCServicioDAO;
import svl.pos.data.JDBCTipoServicioDAO;
import svl.pos.dominio.Usuario;
import svl.pos.dominio.Solicitud;
import svl.pos.dominio.BandejaSolicitud;

public class JDBCVoluntarioDAO implements IVoluntarioDAO{

	private JDBCServicioDAO servdao = new JDBCServicioDAO();
	private JDBCTipoServicioDAO tisedao = new JDBCTipoServicioDAO();
	
		
	/**
	 * Atributo que crea la conexion a la bd
	 */
	private Connection conn;
	
	/**
	 * Constructor de la clase
	 */
	public JDBCVoluntarioDAO(){
		conn = (Connection) ConnectionManager.getInstance().checkOut();
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
	public void insertarVoluntario(Usuario usuario) {
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
		
	
	public int recuperarVoluidByUsuaID(int usua_id) {
		
		System.out.println(" --- Estamos en recuperarVoluidByUsuaID() ---  ");
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM voluntarios WHERE usua_usuaid = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usua_id);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("volu_id");
        } catch (SQLException e) {
            System.out.println("Message: recuperarVoluidByUsuaID: " + e.getMessage());
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
	
public List<Integer> buscarTodosVoluntariosSolicitudes(Solicitud solicitud) {
		
	System.out.println(" --- Estamos en buscarTodosVoluntariosSolicitudes() ---  ");
	
	List<Integer> lista = new ArrayList<Integer>();
	
	String sql = "SELECT usua.usua_id, usua.muni_muniid,volu.usua_usuaid, volu.volu_id, sevo.volu_voluid,sevo.serv_servid, serv.serv_id, serv.tise_tiseid, soli.muni_muniid,soli.serv_servid,soli.tise_tiseid ";
	sql = sql + " FROM isg3.usuarios usua,isg3.voluntarios volu, isg3.servicios_voluntarios sevo, isg3.servicios serv, isg3.solicitudes soli ";
	sql = sql + " WHERE ((usua.usua_id = volu.usua_usuaid) AND (volu.volu_id = sevo.volu_voluid) AND (sevo.serv_servid = serv.serv_id) AND (usua.muni_muniid = soli.muni_muniid) ";
	sql = sql + " AND (sevo.serv_servid = soli.serv_servid) AND (serv.tise_tiseid = soli.tise_tiseid) AND (soli.serv_servid = ?) AND (soli.tise_tiseid = ?)  AND (soli.muni_muniid = ?)) ORDER BY usua.usua_id ASC;";
	
	//AND (usua.prov_provid = soli.prov_provid) AND (soli.prov_provid = ?) usua.prov_provid, soli.prov_provid,
	PreparedStatement stmt = null;
	ResultSet result = null;
	
	try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, solicitud.getServ_servid());
		stmt.setInt(2, solicitud.getTise_tiseid());
		//stmt.setInt(3, solicitud.getProv_provid());
		stmt.setInt(3, solicitud.getMuni_muniid());
		result = stmt.executeQuery();
		
		while(result.next()){
			int usuaid = result.getInt("usua.usua_id");
			lista.add(usuaid);
		}
	} catch (SQLException e) {
		System.out.println("Message: buscarTodosVoluntariosSolicitudes:  " + e.getMessage());
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


public void insertaBandejaVoluntario(Solicitud solicitud,  int usua_id) {
	
	System.out.println("Estamos en insertaBandejaVoluntario()   ");
	
	if (solicitud !=null){
		
	int soli_servid = solicitud.getServ_servid();
	int soli_tiseid = solicitud.getTise_tiseid();
	System.out.println(" --- soli_servid :  " + soli_servid);
	System.out.println(" --- soli_tiseid :  " + soli_tiseid);
	
	
	String servdescrico = servdao.selectServDescricoByServid(soli_servid);
	String tisedescrico = tisedao.selectTiseDescricoByTiseid(soli_tiseid);
	
	String sql = "INSERT INTO bandejasolicitudes (usua_usuaid, baso_descrico, baso_descrila, baso_finicio, baso_contacto, baso_emailcontacto,prov_provid, muni_muniid) VALUES (?,?,?,?,?,?,?,?) ";
	
	String basodescrico = servdescrico + " - " + tisedescrico;
	String basodescrila = solicitud.getSoli_descripcion();
	String basofinicio  = solicitud.getSoli_fecha();
	String basocontacto = solicitud.getSoli_nombrecontacto();
	String basoemailcontacto = solicitud.getSoli_emailcontacto();
	int basoprovid = solicitud.getProv_provid();
	int basomuniid = solicitud.getMuni_muniid();
	
	String falta = Utiles.obtenerFechaActual();
	PreparedStatement stmt = null;
	
	try {
		stmt =  conn.prepareStatement(sql);

		stmt.setInt(1, usua_id);
		stmt.setString(2, basodescrico);
		stmt.setString(3, basodescrila);
		stmt.setString(4, basofinicio);
		stmt.setString(5, basocontacto);
		stmt.setString(6, basoemailcontacto);
		stmt.setInt(7, basoprovid);
		stmt.setInt(8, basomuniid);
		
		stmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("Message: insertaBandejaVoluntario : " + e.getMessage());
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
	System.out.println("Salimos de insertaBandejaVoluntario()   ");	
	}else{
		System.out.println("-- La solicitud es nula ---  ");
	}
	
	}

	public List<BandejaSolicitud> obtenerBandejaByUsuaID(int usua_id) {
	
	System.out.println("--- Estamos en obtenerBandejaByUsuaID() ---");
	
	List<BandejaSolicitud> listabandeja = new ArrayList<BandejaSolicitud>();
	String sql = "SELECT * FROM bandejasolicitudes where usua_usuaid = ?";
	PreparedStatement stmt = null;
	ResultSet result = null;
	
	try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, usua_id);
		result = stmt.executeQuery();

		while(result.next()){
			BandejaSolicitud bandeja = new BandejaSolicitud();
	            bandeja.setUsua_usuaid(result.getInt("usua_usuaid"));
				bandeja.setBaso_descrico(result.getString("baso_descrico"));
				bandeja.setBaso_descrila(result.getString("baso_descrila"));
				bandeja.setBaso_finicio(result.getString("baso_finicio"));
				bandeja.setBaso_contacto(result.getString("baso_contacto"));
				bandeja.setBaso_emailcontacto(result.getString("baso_emailcontacto"));
				bandeja.setProv_provid(result.getInt("prov_provid"));
				bandeja.setMuni_muniid(result.getInt("muni_muniid"));
				
				listabandeja.add(bandeja);
		}
	} catch (SQLException e) {
		System.out.println("Message: obtenerBandejaByUsuaID:   " + e.getMessage());
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

	return listabandeja;
}


	
}
	
	
	
	
	

	
			


	
