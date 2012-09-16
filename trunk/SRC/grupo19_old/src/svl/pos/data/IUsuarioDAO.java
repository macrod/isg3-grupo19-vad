package svl.pos.data;

import java.sql.Connection;
import java.util.List;
import svl.pos.dominio.*;

public interface IUsuarioDAO {
	boolean comprobarUsuario(String nombreUsuario, String password);
	List<Usuario> selectAllUsers();
	Integer recuperarIdByUsuarioClave(String usua_usuario, String usua_clave);
	boolean isLogado(String usua_usuario, String usua_clave);
	boolean isRegistrado(String usua_usuario, String usua_clave);
	Integer obtenerUsuaId(String usua_usuario, String usua_clave);
	boolean singOut();
	boolean isAdmin(Usuario u);
	Usuario recuperarUsuarioByIdUsuario(int usua_id);
	//todo
	Usuario recuperarUsuarioByUsuarioClave(String usua_usuario, String usua_clave);
	void insertarUsuario(Usuario usuario);
	void borrarUsuario(int usua_id);
	void actualizarUsuario(Usuario usuario);
	void insertarUsuarioAlerta (Solicitud solicitud);
	int recuperarIDByUsername(String nombre);

}
