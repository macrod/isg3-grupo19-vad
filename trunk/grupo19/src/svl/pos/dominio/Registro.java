package svl.pos.dominio;

import java.util.Date;

public class Registro {

	public String regi_id;
	public String volu_volucod;
	public String usua_usuaid;
	public int regi_totalhoras;
	public Date regi_finicio ;
	public String regi_horainicio;
	public String regi_horafin;
	
	public Registro() {
		super();
	}

	/**
	 * @return the regi_id
	 */
	public String getRegi_id() {
		return regi_id;
	}

	/**
	 * @param regi_id the regi_id to set
	 */
	public void setRegi_id(String regi_id) {
		this.regi_id = regi_id;
	}

	/**
	 * @return the volu_volucod
	 */
	public String getVolu_volucod() {
		return volu_volucod;
	}

	/**
	 * @param volu_volucod the volu_volucod to set
	 */
	public void setVolu_volucod(String volu_volucod) {
		this.volu_volucod = volu_volucod;
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
	 * @return the regi_totalhoras
	 */
	public int getRegi_totalhoras() {
		return regi_totalhoras;
	}

	/**
	 * @param regi_totalhoras the regi_totalhoras to set
	 */
	public void setRegi_totalhoras(int regi_totalhoras) {
		this.regi_totalhoras = regi_totalhoras;
	}

	/**
	 * @return the regi_finicio
	 */
	public Date getRegi_finicio() {
		return regi_finicio;
	}

	/**
	 * @param regi_finicio the regi_finicio to set
	 */
	public void setRegi_finicio(Date regi_finicio) {
		this.regi_finicio = regi_finicio;
	}

	/**
	 * @return the regi_horainicio
	 */
	public String getRegi_horainicio() {
		return regi_horainicio;
	}

	/**
	 * @param regi_horainicio the regi_horainicio to set
	 */
	public void setRegi_horainicio(String regi_horainicio) {
		this.regi_horainicio = regi_horainicio;
	}

	/**
	 * @return the regi_horafin
	 */
	public String getRegi_horafin() {
		return regi_horafin;
	}

	/**
	 * @param regi_horafin the regi_horafin to set
	 */
	public void setRegi_horafin(String regi_horafin) {
		this.regi_horafin = regi_horafin;
	}

	
	
}
