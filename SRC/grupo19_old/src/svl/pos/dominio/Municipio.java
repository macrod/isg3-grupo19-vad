package svl.pos.dominio;

public class Municipio {

	private String muni_id;
	private String muni_nombre;
	private String prov_provid;
	
	public Municipio(){
		super();
	}

	public String getMuni_id() {
		return muni_id;
	}

	public void setMuni_id(String muni_id) {
		this.muni_id =muni_id;
	}

	public String getMuni_nombre() {
		return muni_nombre;
	}

	public void setMuni_nombre(String muni_nombre) {
		this.muni_nombre = muni_nombre;
	}
	
	public String getProv_provid() {
		return prov_provid;
	}

	public void setProv_provid(String prov_provid) {
		this.prov_provid = prov_provid;
	}

}