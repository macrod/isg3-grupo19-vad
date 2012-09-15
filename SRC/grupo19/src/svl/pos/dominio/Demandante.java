package svl.pos.dominio;

import java.util.Date;

public class Demandante extends Usuario {

	private String dema_id;
	private String dema_cod;
	private String usua_usuaid;
	private Date dema_falta;
	private Date dema_fbaja;
	
	public Demandante(){
		super();
	}

	/**
	 * @return the dema_id
	 */
	public String getDema_id() {
		return dema_id;
	}

	/**
	 * @param dema_id the dema_id to set
	 */
	public void setDema_id(String dema_id) {
		this.dema_id = dema_id;
	}

	/**
	 * @return the dema_cod
	 */
	public String getDema_cod() {
		return dema_cod;
	}

	/**
	 * @param dema_cod the dema_cod to set
	 */
	public void setDema_cod(String dema_cod) {
		this.dema_cod = dema_cod;
	}

	/**
	 * @return the usua_usuaid
	 */
	public String getUsua_usuaid() {
		return usua_usuaid;
	}

	/**
	 * @param usua_usuaid the usua_usuaid to set
	 */
	public void setUsua_usuaid(String usua_usuaid) {
		this.usua_usuaid = usua_usuaid;
	}

	/**
	 * @return the dema_falta
	 */
	public Date getDema_falta() {
		return dema_falta;
	}

	/**
	 * @param dema_falta the dema_falta to set
	 */
	public void setDema_falta(Date dema_falta) {
		this.dema_falta = dema_falta;
	}

	/**
	 * @return the dema_fbaja
	 */
	public Date getDema_fbaja() {
		return dema_fbaja;
	}

	/**
	 * @param dema_fbaja the dema_fbaja to set
	 */
	public void setDema_fbaja(Date dema_fbaja) {
		this.dema_fbaja = dema_fbaja;
	}
	
	
	
}
