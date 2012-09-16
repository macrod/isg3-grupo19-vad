package svl.pos.dominio;

import java.sql.Connection;
import java.util.List;

import svl.pos.data.IUsuarioDAO;
import svl.pos.data.JDBCUsuarioDAO;
import svl.pos.data.IVoluntarioDAO;
import svl.pos.data.JDBCVoluntarioDAO;
import svl.pos.data.IDemandanteDAO;
import svl.pos.data.JDBCDemandanteDAO;

public class UsuarioStore implements IUsuarioDAO {
	
	private JDBCUsuarioDAO userDAO;
	private JDBCVoluntarioDAO volDAO;
	private JDBCDemandanteDAO demDAO;
	
	/**
	 * 	CONSTRUCTOR DE LA CLASE
	 */
	public UsuarioStore(){
		userDAO = new JDBCUsuarioDAO();
		volDAO = new JDBCVoluntarioDAO();
		demDAO = new JDBCDemandanteDAO();
	}
	
	public boolean comprobarUsuario(String idUser, String password){
		return userDAO.comprobarUsuario(idUser,password);
	}
	
	
	public Usuario recuperarUsuarioByIdUsuario(int idUser){
		return userDAO.recuperarUsuarioByIdUsuario(idUser);
	}
	
	public void insertarUsuario(Usuario user){
		userDAO.insertarUsuario(user);
	}
	
	public List<Usuario> selectAllUsers(){
		return userDAO.selectAllUsers();
	}
	
	public void borrarUsuario(int usua_id){
		userDAO.borrarUsuario(usua_id);
	}
	
	public void actualizarUsuario(Usuario u){
		userDAO.actualizarUsuario(u);
	}
	
	

	@Override
	public Integer recuperarIdByUsuarioClave(String usua_usuario,
			String usua_clave) {
		
		return null;
	}

	

	@Override
	public boolean isLogado(String usua_usuario, String usua_clave) {
		
		return userDAO.isLogado(usua_usuario, usua_clave);
	}

	@Override
	public boolean isRegistrado(String usua_usuario, String usua_clave) {
		// TODO Auto-generated method stub
		return userDAO.isRegistrado(usua_usuario, usua_clave);
	}

	@Override
	public Integer obtenerUsuaId(String usua_usuario, String usua_clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean singOut() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdmin(Usuario usuario) {
		// TODO Auto-generated method stub
		return userDAO.isAdmin(usuario);
	}

	@Override
	public void insertarUsuarioAlerta(Solicitud solicitud) {
		userDAO.insertarUsuarioAlerta(solicitud);
	}

	@Override
	public Usuario recuperarUsuarioByUsuarioClave(String usua_usuario,String usua_clave) {
		
		return userDAO.recuperarUsuarioByUsuarioClave(usua_usuario, usua_clave);
	}

	public int recuperarIDByUsername(String username) {
	
		return userDAO.recuperarIDByUsername(username);
	}
	
	
	public int recuperarVoluidByUsuaID(int usua_id){
		return volDAO.recuperarVoluidByUsuaID(usua_id);
	}
	public int recuperarDemaidByUsuaID(int usua_id){
		return demDAO.recuperarDemaidByUsuaID(usua_id);
	}
	
	public  List<BandejaSolicitud> obtenerBandejaByUsuaID(int usua_id) {
		return volDAO.obtenerBandejaByUsuaID(usua_id);
	}
	
}
