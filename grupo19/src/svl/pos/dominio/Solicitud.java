package svl.pos.dominio;


public class Solicitud {

	public int soli_id;
	public String soli_nombrecontacto;
	public String soli_emailcontacto;
	public int tise_tiseid;
	public int serv_servid ;
	public String soli_descripcion;
	public int usua_usuaid;
	public int prov_provid;
	public int muni_muniid;
	public String soli_fecha;
	

	public Solicitud(){
		super();
	}


	/**
	 * @return the soli_id
	 */
	public int getSoli_id() {
		return soli_id;
	}


	/**
	 * @param soli_id the soli_id to set
	 */
	public void setSoli_id(int soli_id) {
		this.soli_id = soli_id;
	}


	/**
	 * @return the soli_nombrecontacto
	 */
	public String getSoli_nombrecontacto() {
		return soli_nombrecontacto;
	}


	/**
	 * @param soli_nombrecontacto the soli_nombrecontacto to set
	 */
	public void setSoli_nombrecontacto(String soli_nombrecontacto) {
		this.soli_nombrecontacto = soli_nombrecontacto;
	}


	/**
	 * @return the soli_emailcontacto
	 */
	public String getSoli_emailcontacto() {
		return soli_emailcontacto;
	}


	/**
	 * @param soli_emailcontacto the soli_emailcontacto to set
	 */
	public void setSoli_emailcontacto(String soli_emailcontacto) {
		this.soli_emailcontacto = soli_emailcontacto;
	}


	/**
	 * @return the tise_tiseid
	 */
	public int getTise_tiseid() {
		return tise_tiseid;
	}


	/**
	 * @param tise_tiseid the tise_tiseid to set
	 */
	public void setTise_tiseid(int tise_tiseid) {
		this.tise_tiseid = tise_tiseid;
	}


	/**
	 * @return the serv_servid
	 */
	public int getServ_servid() {
		return serv_servid;
	}


	/**
	 * @param serv_servid the serv_servid to set
	 */
	public void setServ_servid(int serv_servid) {
		this.serv_servid = serv_servid;
	}


	/**
	 * @return the soli_descripcion
	 */
	public String getSoli_descripcion() {
		return soli_descripcion;
	}


	/**
	 * @param soli_descripcion the soli_descripcion to set
	 */
	public void setSoli_descripcion(String soli_descripcion) {
		this.soli_descripcion = soli_descripcion;
	}


	/**
	 * @return the usua_usuaid
	 */
	public int getUsua_usuaid() {
		return usua_usuaid;
	}


	/**
	 * @param usua_usuaid the usua_usuaid to set
	 */
	public void setUsua_usuaid(int usua_usuaid) {
		this.usua_usuaid = usua_usuaid;
	}


	/**
	 * @return the prov_provid
	 */
	public int getProv_provid() {
		return prov_provid;
	}


	/**
	 * @param prov_provid the prov_provid to set
	 */
	public void setProv_provid(int prov_provid) {
		this.prov_provid = prov_provid;
	}


	/**
	 * @return the muni_muniid
	 */
	public int getMuni_muniid() {
		return muni_muniid;
	}


	/**
	 * @param muni_muniid the muni_muniid to set
	 */
	public void setMuni_muniid(int muni_muniid) {
		this.muni_muniid = muni_muniid;
	}

	/**
	 * @return the soli_fecha
	 */
	public String getSoli_fecha() {
		return soli_fecha;
	}


	/**
	 * @param soli_fecha the soli_fecha to set
	 */
	public void setSoli_fecha(String soli_fecha) {
		this.soli_fecha = soli_fecha;
	}
	

}