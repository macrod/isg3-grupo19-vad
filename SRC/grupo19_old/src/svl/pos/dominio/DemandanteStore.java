package svl.pos.dominio;

import java.util.List;

import svl.pos.data.IDemandanteDAO;
import svl.pos.data.JDBCDemandanteDAO;


public class DemandanteStore implements IDemandanteDAO{

	JDBCDemandanteDAO dao = new JDBCDemandanteDAO();

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
		dao.insertarDemandante(usuario);
	}
	public int recuperarDemaidByUsuaID(int usua_id){
		return dao.recuperarDemaidByUsuaID(usua_id);
	}
	
}







	
