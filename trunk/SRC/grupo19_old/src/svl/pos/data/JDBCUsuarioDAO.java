package svl.pos.data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import svl.pos.dominio.Solicitud;
import svl.pos.dominio.Usuario;

//import svl.pos.dominio.PerfilStore;


public class JDBCUsuarioDAO implements IUsuarioDAO {

	/**
	 * Atributo que crea la conexion a la bd
	 */
	private Connection conn;
	
	/**
	 * Constructor de la clase
	 */
	public JDBCUsuarioDAO(){
		conn = (Connection) ConnectionManager.getInstance().checkOut();
	}
	
		
	
	public boolean isLogado(String usua_usuario, String usua_clave) {
		System.out.println("Estamos en isLogado");
		boolean logado=false;
		String sql = "SELECT * FROM Usuarios WHERE (usua_usuario = ? ) ";
	    PreparedStatement stmt = null;
	    ResultSet result = null;
	    try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usua_usuario);

            result = stmt.executeQuery();

            result.next();
            if(usua_usuario.equals(result.getString("usua_usuario")) && usua_clave.equals(result.getString("usua_clave"))){
            	logado=true;
            }
        } catch (SQLException e) {
            System.out.println("Message: isLogado" + e.getMessage());
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

        return logado;
	    
	}

	
	public boolean isRegistrado(String usua_usuario, String usua_clave)  {
		
		
		System.out.println("Estamos en isRegistrado");
		System.out.println("--- usua_usuario : "   +usua_usuario );
		System.out.println("--- usua_clave   : "   +usua_clave   );
		
		boolean registrado=false;
		String sql = "SELECT * FROM usuarios WHERE (usua_usuario = ?  && usua_clave=?) ";
	    PreparedStatement stmt = null;
	    ResultSet result = null;
	    try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usua_usuario);
            stmt.setString(2, usua_clave);
            
            result = stmt.executeQuery();

            if(result.next()){
            	            
            	if(usua_usuario.equals(result.getString("usua_usuario")) && usua_clave.equals(result.getString("usua_clave"))){
            	registrado=true;
            	}
            }
        } catch (SQLException e) {
            System.out.println("Message: isRegistrado:   " + e.getMessage());
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
        System.out.println(" Registrado?..." + registrado);
        return registrado;

	}

	
	public Integer recuperarIdByUsuarioClave(String usua_usuario, String usua_clave) {
		Integer res=0;
		String sql = "SELECT * FROM usuarios WHERE (usua_usuario = ?  && usua_clave=?) ";
	        PreparedStatement stmt = null;
	        ResultSet result = null;
	       

	        try {
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, usua_usuario);
	            stmt.setString(2, usua_clave);

	            result = stmt.executeQuery();

	            result.next();
	            res= new Integer(result.getString("usua_id"));
	        } catch (SQLException e) {
	            System.out.println("Message: recuperarIdByUsuarioClave:  " + e.getMessage());
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

	        return res;

	}

	public boolean isAdmin(Usuario usuario){
		
		String sql = "SELECT * FROM Usuarios WHERE usua_id = ?";
		PreparedStatement stmt = null;
		int id = usuario.getUsua_id();
		boolean b = false;
		ResultSet result = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			result.next();
			if (result.getBoolean("usua_esadmin")){
				b = true;
			}
        } catch (SQLException e) {
            System.out.println("Message: IsAdmin: " + e.getMessage());
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
        return b;
	}

	//macarena
	public int recuperarIDByUsername(String username) {
		System.out.println(" --- Estamos en recuperarIDByUsername ()  --- ");
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM usuarios WHERE usua_nombre = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			result = stmt.executeQuery();
			if (result.next()){
				id = result.getInt("usua_id");
			}
			
        } catch (SQLException e) {
            System.out.println("Message: recuperarIDByUsername: " + e.getMessage());
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
        System.out.println(" id del usuario bb.dd : " + id);
        return id;
	}


	public Usuario recuperarUsuarioByIdUsuario(int usua_id) {
		
		System.out.println("--- Estamos en recuperarUsuarioByIdUsuario()");
		System.out.println("--- usua_id: "  + usua_id);
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM usuarios WHERE usua_id = ?";
		ResultSet result = null;
		Usuario usuario = new Usuario();
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usua_id);
			result = stmt.executeQuery();
			result.next();
			
			
			usuario.setUsua_id(result.getInt("usua_id"));
			usuario.setUsua_usuario(result.getString("usua_usuario"));
			usuario.setUsua_clave(result.getString("usua_clave"));
			usuario.setUsua_nombre(result.getString("usua_nombre"));
			usuario.setUsua_apellidos(result.getString("usua_apellidos"));
			usuario.setUsua_email(result.getString("usua_email"));
			usuario.setUsua_telefono(result.getString("usua_telefono"));
			usuario.setUsua_genero(result.getInt("usua_genero"));
			usuario.setUsua_nif(result.getString("usua_nif"));
			usuario.setUsua_direccion(result.getString("usua_direccion"));
			usuario.setUsua_numero(result.getString("usua_numero"));
			usuario.setProv_provid(result.getInt("prov_provid"));
			usuario.setMuni_muniid(result.getInt("muni_muniid"));
			usuario.setUsua_codpostal(result.getInt("usua_codpostal"));
			usuario.setUsua_falta(result.getString("usua_falta"));
			usuario.setUsua_fbaja(result.getString("usua_fbaja"));
			usuario.setUsua_esvoluntario(result.getInt("usua_esvoluntario"));
			usuario.setUsua_esdemandante(result.getInt("usua_esdemandante"));
			usuario.setUsua_esadmin(result.getInt("usua_esadmin"));
			
        } catch (SQLException e) {
            System.out.println("Message: recuperarUsuarioByIdUsuario:  " + e.getMessage());
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
        return usuario;
	}
	
	@Override
	public List<Usuario> selectAllUsers() {
		List<Usuario> p = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuarios";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			while(result.next()){
				Usuario usuario = new Usuario();
		            usuario.setUsua_id(result.getInt("usua_id"));
					usuario.setUsua_usuario(result.getString("usua_usuario"));
					usuario.setUsua_clave(result.getString("usua_clave"));
					usuario.setUsua_nombre(result.getString("usua_nombre"));
					usuario.setUsua_apellidos(result.getString("usua_apellidos"));
					usuario.setUsua_email(result.getString("usua_email"));
					usuario.setUsua_telefono(result.getString("usua_telefono"));
					usuario.setUsua_genero(result.getInt("usua_genero"));
					usuario.setUsua_nif(result.getString("usua_nif"));
					usuario.setUsua_direccion(result.getString("usua_direccion"));
					usuario.setUsua_numero(result.getString("usua_numero"));
					usuario.setProv_provid(result.getInt("prov_proid"));
					usuario.setMuni_muniid(result.getInt("muni_muniid"));
					usuario.setUsua_codpostal(result.getInt("usua_codpostal"));
					usuario.setUsua_falta(result.getString("usua_falta"));
					usuario.setUsua_fbaja(result.getString("usua_fbaja"));
					usuario.setUsua_esvoluntario(result.getInt("usua_esvoluntario"));
					usuario.setUsua_esdemandante(result.getInt("usua_esdemandante"));
		            usuario.setUsua_esadmin(result.getInt("usua_esadmin"));
		            // Recuperamos el Perfil
		         /*   if ( result.getString("IDPerfil") != null && !"".equals(result.getString("IDPerfil")) ){
			            IPerfilDAO daoP = new JDBCPerfilDAO();
			            u.setPerfil(daoP.recuperarPerfil(result.getString("IDPerfil")));
		            }else{
		            	u.setPerfil(null);
		            }
		         */  
				p.add(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Message: selectAllUsers" + e.getMessage());
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

		return p;
	}
	
	public boolean singOut() {
		return true ;
	}
	
	public void insertarUsuario(Usuario usuario) {
		
		System.out.println("Estamos en insertarUsuario()   ");
		
		String sql = "INSERT INTO usuarios (usua_usuario, usua_clave, usua_nombre, usua_apellidos,usua_email, usua_telefono, usua_genero, usua_nif,usua_direccion, usua_numero, prov_provid, muni_muniid,usua_codpostal, usua_falta, usua_fbaja, usua_esvoluntario, usua_esdemandante,usua_esadmin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		//String fechaactual = obtenerFechaActual();
		try {
			stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, usuario.getUsua_usuario());
			stmt.setString(2, usuario.getUsua_clave());
			stmt.setString(3, usuario.getUsua_nombre());
			stmt.setString(4, usuario.getUsua_apellidos());
			stmt.setString(5, usuario.getUsua_email());
			stmt.setString(6, usuario.getUsua_telefono());
			stmt.setInt(7, usuario.getUsua_genero());
			stmt.setString(8, usuario.getUsua_nif());
			stmt.setString(9, usuario.getUsua_direccion());
			stmt.setString(10, usuario.getUsua_numero());
			stmt.setInt(11, usuario.getProv_provid());
			stmt.setInt(12, usuario.getMuni_muniid());
			stmt.setInt(13, usuario.getUsua_codpostal());
			stmt.setString(14, usuario.getUsua_falta());
			stmt.setString(15, usuario.getUsua_fbaja());
			stmt.setInt(16, usuario.getUsua_esvoluntario());
			stmt.setInt(17, usuario.getUsua_esdemandante());
			stmt.setInt(18, usuario.getUsua_esadmin());
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: insertarUsuario:   " + e.getMessage());
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
	
	public String obtenerFechaActual() {
		String fechaactual = null;
		String sql = "select CURDATE()";
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();
			while(result.next())
            {
				fechaactual = result.getString("1");
            }
		} catch (SQLException e) {
            System.out.println("Message: obtenerFechaActual   " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
            }
        }
        return fechaactual;
	}	
			
	
	public void borrarUsuario(int usua_id) {
		
		String sql = "DELETE FROM usuarios WHERE (usua_id = ?) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usua_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Message: borrarUsuario" + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
            }
        }
		
	}
	
	public void actualizarUsuario(Usuario u){
		
	}



	@Override
	public boolean comprobarUsuario(String nombreUsuario, String password) {
		// TODO Auto-generated method stub
		return false;
	}



	



	@Override
	public Integer obtenerUsuaId(String usua_usuario, String usua_clave) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void insertarUsuarioAlerta(Solicitud solicitud) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Usuario recuperarUsuarioByUsuarioClave(String usua_usuario,String usua_clave) {
		 {
			System.out.println("Estamos en recuperarUsuarioByUsuarioClave()   ");
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM usuarios WHERE (usua_usuario = ?  && usua_clave=?) ";
			ResultSet result = null;
			Usuario usuario = new Usuario();
			try{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, usua_usuario);
				stmt.setString(2, usua_clave);
				result = stmt.executeQuery();
				result.next();
				
				
				usuario.setUsua_id(result.getInt("usua_id"));
				usuario.setUsua_usuario(result.getString("usua_usuario"));
				usuario.setUsua_clave(result.getString("usua_clave"));
				usuario.setUsua_nombre(result.getString("usua_nombre"));
				usuario.setUsua_apellidos(result.getString("usua_apellidos"));
				usuario.setUsua_email(result.getString("usua_email"));
				usuario.setUsua_telefono(result.getString("usua_telefono"));
				usuario.setUsua_genero(result.getInt("usua_genero"));
				usuario.setUsua_nif(result.getString("usua_nif"));
				usuario.setUsua_direccion(result.getString("usua_direccion"));
				usuario.setUsua_numero(result.getString("usua_numero"));
				usuario.setProv_provid(result.getInt("prov_provid"));
				usuario.setMuni_muniid(result.getInt("muni_muniid"));
				usuario.setUsua_codpostal(result.getInt("usua_codpostal"));
				usuario.setUsua_falta(result.getString("usua_falta"));
				usuario.setUsua_esvoluntario(result.getInt("usua_esvoluntario"));
				usuario.setUsua_esdemandante(result.getInt("usua_esdemandante"));
				usuario.setUsua_esadmin(result.getInt("usua_esadmin"));
				
	        } catch (SQLException e) {
	            System.out.println("Message: recuperarUsuarioByUsuarioClave: " + e.getMessage());
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
	        return usuario;
		}
	}
}