package svl.pos.data;

import java.util.List;


import svl.pos.dominio.Usuario;
import svl.pos.dominio.Demandante;
import svl.pos.dominio.Servicio;

public interface IDemandanteDAO {
	
	void registarDemandante();
	Usuario consultarMisdatos(String usua_id);
	void addAlerta(String usua_usuario, String usua_email);
    List<Servicio> listaServicioByDemId(int dema_id);
    void insertarDemandante(Usuario usuario);
    public int recuperarDemaidByUsuaID(int usua_id);
	
	
}
