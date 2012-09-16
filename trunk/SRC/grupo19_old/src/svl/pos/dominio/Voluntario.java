package svl.pos.dominio;

//import java.util.List;
import java.util.Date;

public class Voluntario extends Usuario {

	private String volu_id;
	private String volu_cod;
	private String usua_usuaid;
	private Date volu_falta;
	private Date volu_fbaja;
	
	public Voluntario(){
		super();
	}

	/**
	 * @return the volu_id
	 */
	public String getVolu_id() {
		return volu_id;
	}

	/**
	 * @param volu_id the volu_id to set
	 */
	public void setVolu_id(String volu_id) {
		this.volu_id = volu_id;
	}

	/**
	 * @return the volu_cod
	 */
	public String getVolu_cod() {
		return volu_cod;
	}

	/**
	 * @param volu_cod the volu_cod to set
	 */
	public void setVolu_cod(String volu_cod) {
		this.volu_cod = volu_cod;
	}

	/**
	 * @return the volu_falta
	 */
	public Date getVolu_falta() {
		return volu_falta;
	}

	/**
	 * @param volu_falta the volu_falta to set
	 */
	public void setVolu_falta(Date volu_falta) {
		this.volu_falta = volu_falta;
	}

	/**
	 * @return the volu_fbaja
	 */
	public Date getVolu_fbaja() {
		return volu_fbaja;
	}

	/**
	 * @param volu_fbaja the volu_fbaja to set
	 */
	public void setVolu_fbaja(Date volu_fbaja) {
		this.volu_fbaja = volu_fbaja;
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
	
	
		
}
