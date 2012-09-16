package svl.pos.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import svl.pos.dominio.Servicio;
import svl.pos.dominio.Usuario;
import svl.pos.data.JDBCTipoServicioDAO;


import com.mysql.jdbc.Connection;

public class JDBCServicioDAO implements IServicioDAO{

	private Connection conn;
	
	public JDBCServicioDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}
	
	public List<Servicio> recuperarTodosLosServicios(){
		
		System.out.println("Estamos en recuperarTodosLosServicios()   ");
		
		List<Servicio> lista = new ArrayList<Servicio>();
		
		String sql = "SELECT * FROM servicios";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				Servicio serv = new Servicio();
				serv.setServ_id(result.getInt("serv_id"));
				serv.setTise_tiseid(result.getInt("tise_tiseid"));
				serv.setServ_descrico(result.getString("serv_descrico"));
				serv.setServ_descrila(result.getString("serv_descrila"));
				lista.add(serv);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosServicios" + e.getMessage());
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
	
	
	public List<Servicio> recuperarTodosLosServiciosporTipoServ(int tise_tiseid){
		
		System.out.println("Estamos en recuperarTodosLosServiciosporTipoServ()   ");
		
		List<Servicio> lista = new ArrayList<Servicio>();
		
		String sql = "SELECT * FROM servicios where tise_tiseid= ?";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tise_tiseid);
			result = stmt.executeQuery();

			while(result.next()){
				Servicio serv = new Servicio();
				serv.setServ_id(result.getInt("serv_id"));
				serv.setTise_tiseid(result.getInt("tise_tiseid"));
				serv.setServ_descrico(result.getString("serv_descrico"));
				serv.setServ_descrila(result.getString("serv_descrila"));
				lista.add(serv);
			}
		} catch (SQLException e) {
			System.out.println("Message: recuperarTodosLosServiciosporTipoServ:  " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
			ConnectionManager.getInstance().checkIn(conn);
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

	
	
	
	public Servicio recuperarServicioByIdServicio(int serv_id) {
		
		System.out.println("Estamos en recuperarServicioByIdServicio()   ");
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM servicios WHERE serv_id = ?";
		ResultSet result = null;
		Servicio servicio = new Servicio();
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serv_id);
			result = stmt.executeQuery();
			result.next();
			
			servicio.setServ_id(result.getInt("serv_id"));
			servicio.setTise_tiseid(result.getInt("tise_tiseid"));
			servicio.setServ_descrico(result.getString("serv_descrico"));
			servicio.setServ_descrila(result.getString("serv_descrila"));
						
        } catch (SQLException e) {
            System.out.println("Message: recuperarServicioByIdServicio: " + e.getMessage());
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
        return servicio;
	}
	
	
	public String selectServDescricoByServid(int serv_id) {
		
		System.out.println("Estamos en selectServDescricoByServid()   ");
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Servicios WHERE serv_id = ?";
		ResultSet result = null;
		String servdescrico =  null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serv_id);
			result = stmt.executeQuery();
			result.next();
			servdescrico = result.getString("serv_descrico");
        } catch (SQLException e) {
            System.out.println("Message: selectServDescricoByServid: " + e.getMessage());
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
	
	public String selectServDescrilaByServid(int serv_id) {
		
		System.out.println("Estamos en selectServDescrilaByServid()   ");
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Servicios WHERE serv_id = ?";
		ResultSet result = null;
		String servdescrila =  null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serv_id);
			result = stmt.executeQuery();
			result.next();
			servdescrila = result.getString("serv_descrila");
        } catch (SQLException e) {
            System.out.println("Message: selectServDescrilaByServid: " + e.getMessage());
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
        return servdescrila;
	}
	
	public String selectTipoServicioByServid (int serv_id){
		
		System.out.println("Estamos en selectTipoServicioByServid()   ");
		
		JDBCTipoServicioDAO daoTipoServicio = new JDBCTipoServicioDAO();
		PreparedStatement stmt = null;
		String sql = "SELECT tise_tiseid FROM Servicios WHERE serv_id = ?";
		ResultSet result = null;
		int tise_id =  1;
		String tisedescrico = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serv_id);
			result = stmt.executeQuery();
			result.next();
			tise_id = result.getInt("tise_tiseid");
        } catch (SQLException e) {
            System.out.println("Message: selectTipoServicioByServid: " + e.getMessage());
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
		tisedescrico = daoTipoServicio.selectTiseDescricoByTiseid(tise_id);
		return tisedescrico;
	}

	//Devuelve todos los servicios en los que está dado de alta un voluntario. 
	public List<Servicio> listaServicioByVolId(int volu_id) {
		System.out.println("Estamos en listaServicioByVolId()   ");
		List<Servicio> lista = new ArrayList<Servicio>();
		
		String sql = "SELECT * FROM servicios_voluntarios where volu_voluid= ?";
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, volu_id);
			result = stmt.executeQuery();

			while(result.next()){
				Servicio serv2 = new Servicio();
				int serv_id = result.getInt("serv_servid");
				Servicio serv = recuperarServicioByIdServicio(serv_id);
				serv2.setServ_id(serv.getServ_id());
				serv2.setTise_tiseid(serv.getTise_tiseid());
				serv2.setServ_descrico(serv.getServ_descrico());
				serv2.setServ_descrila(serv.getServ_descrila());
				lista.add(serv2);
			}
		} catch (SQLException e) {
			System.out.println("Message: listaServicioByVolId:  " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
		 	ConnectionManager.getInstance().checkIn(conn);
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

	//Devuelve todos los servicios en los que está dado de alta un demandante. 
	public List<Servicio> listaServicioByDemId(int dema_id) {
		System.out.println("Estamos en listaServicioByDemId()   ");
		List<Servicio> lista = new ArrayList<Servicio>();
		
		String sql = "SELECT * FROM servicios_demandantes where dema_demaid= ?";
		PreparedStatement stmt = null; 
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dema_id);
			result = stmt.executeQuery();

			while(result.next()){
				Servicio serv2 = new Servicio();
				int serv_id = result.getInt("serv_servid");
				Servicio serv = recuperarServicioByIdServicio(serv_id);
				serv2.setServ_id(serv.getServ_id());
				serv2.setTise_tiseid(serv.getTise_tiseid());
				serv2.setServ_descrico(serv.getServ_descrico());
				serv2.setServ_descrila(serv.getServ_descrila());
				lista.add(serv2);
			}
		} catch (SQLException e) {
			System.out.println("Message: listaServicioByDemId:  " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
		 	ConnectionManager.getInstance().checkIn(conn);
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
	public void insertarServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertarVoluntariosServicios (int volu_voluid, List<Integer> serv_servid) {
		
		System.out.println("Estamos en insertarVoluntariosServicios()   ");
		for (Iterator iter = serv_servid.iterator(); iter.hasNext();) {
			
			int serv_id = (Integer)iter.next();
			System.out.println("ServicioID :  " + serv_id);
			String sql = "INSERT INTO servicios_voluntarios (volu_voluid, serv_servid) VALUES (?,?) ";
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(sql);
		
				stmt.setInt(1, volu_voluid);
				stmt.setInt(2, serv_id);
				
				
				stmt.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println("Message: insertarVoluntariosServicios " + e.getMessage());
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
		}
	}
	
	public void insertarDemandantesServicios (int dema_demaid, List<Integer> serv_servid) {
		
		System.out.println("--- Estamos en insertarDemandantesServicios()   ");
		for (Iterator iter = serv_servid.iterator(); iter.hasNext();) {
			
			int serv_id = (Integer)iter.next();
			System.out.println("ServicioID :  " + serv_id);
			String sql = "INSERT INTO servicios_demandantes (dema_demaid, serv_servid) VALUES (?,?) ";
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(sql);
		
				stmt.setInt(1, dema_demaid);
				stmt.setInt(2, serv_id);
				
				
				stmt.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println("Message: insertarDemandantesServicios   " + e.getMessage());
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
		}
	}

	@Override
	public void borrarServicio(int serv_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		
	}
	
}
