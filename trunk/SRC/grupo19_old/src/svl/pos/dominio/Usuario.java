package svl.pos.dominio;

import java.util.Date;
//import svl.pos.data.IUsuarioDAO;
//import svl.pos.data.JDBCUsuarioDAO;

public class Usuario {
	
	private int usua_id;
	private String usua_usuario;
	private String usua_clave;
	private String usua_nombre;
	private String usua_apellidos;
	private String usua_email;
	private String usua_telefono;
	private int    usua_genero;
	private String usua_nif;
	private String usua_direccion;
	private String usua_numero;
	private int prov_provid;
	private int muni_muniid;
	private Integer usua_codpostal;
	private String usua_falta;
	private String usua_fbaja;
	private int usua_esvoluntario;
	private int usua_esdemandante;
	private int usua_esadmin;
	
	//private IUsuarioDAO udao = new JDBCUsuarioDAO();
	
	
	public Usuario(int usua_id, String usua_usuario, String usua_clave, String usua_nombre,
			String usua_apellidos, String usua_email, String usua_telefono, int usua_genero,
			String usua_nif, String usua_direccion, String usua_numero, int prov_provid, int muni_muniid, Integer usua_codpostal,
			String usua_falta, String usua_fbaja,int usua_esvoluntario, int usua_esdemandante, int usua_esadmin) {
		super();
		this.usua_id = usua_id;
		this.usua_usuario = usua_usuario;
		this.usua_clave = usua_clave;
		this.usua_nombre = usua_nombre;
		this.usua_apellidos = usua_apellidos;
		this.usua_email = usua_email;
		this.usua_telefono = usua_telefono;
		this.usua_genero = usua_genero;
		this.usua_nif = usua_nif;
		this.usua_direccion = usua_direccion;
		this.usua_numero = usua_numero;
		this.prov_provid = prov_provid;
		this.muni_muniid = muni_muniid;
		this.usua_codpostal = usua_codpostal;
		this.usua_falta = usua_falta;
		this.usua_fbaja = usua_fbaja;
		this.usua_esvoluntario = usua_esvoluntario;
		this.usua_esdemandante = usua_esdemandante;
		this.usua_esadmin = usua_esadmin;
		
		

	}
	
	public Usuario(){
		super();
	}
	
	
	public int getUsua_id() {
		return usua_id;
	}
	public void setUsua_id(int usua_id) {
		this.usua_id = usua_id;
	}
	public String getUsua_usuario() {
		return usua_usuario;
	}
	public void setUsua_usuario(String usua_usuario) {
		this.usua_usuario = usua_usuario;
	}
	public String getUsua_clave() {
		return usua_clave;
	}
	public void setUsua_clave(String usua_clave) {
		this.usua_clave = usua_clave;
	}
	public String getUsua_nombre() {
		return usua_nombre;
	}
	public void setUsua_nombre(String usua_nombre) {
		this.usua_nombre = usua_nombre;
	}
	public String getUsua_apellidos() {
		return usua_apellidos;
	}
	public void setUsua_apellidos(String usua_apellidos) {
		this.usua_apellidos = usua_apellidos;
	}
	
	public String getUsua_email() {
		return usua_email;
	}
	public void setUsua_email(String usua_email) {
		this.usua_email = usua_email;
	}
	
	public String getUsua_telefono() {
		return usua_telefono;
	}
	public void setUsua_telefono(String usua_telefono) {
		this.usua_telefono = usua_telefono;
	}
	public int getUsua_genero() {
		return usua_genero;
	}
	public void setUsua_genero(int usua_genero) {
		this.usua_genero = usua_genero;
	}
	
	public String getUsua_nif() {
		return usua_nif;
	}
	public void setUsua_nif(String usua_nif) {
		this.usua_nif = usua_nif;
	}
	
	public String getUsua_direccion() {
		return usua_direccion;
	}
	public void setUsua_direccion(String usua_direccion) {
		this.usua_direccion = usua_direccion;
	}
	
	public String getUsua_numero() {
		return usua_numero;
	}
	public void setUsua_numero(String usua_numero) {
		this.usua_numero = usua_numero;
	}
	
	public int getProv_provid() {
		return prov_provid;
	}
	public void setProv_provid(int prov_provid) {
		this.prov_provid = prov_provid;
	}
	
	
	public int getMuni_muniid() {
		return muni_muniid;
	}
	public void setMuni_muniid(int muni_muniid) {
		this.muni_muniid = muni_muniid;
	}
	
	public Integer getUsua_codpostal() {
		return usua_codpostal;
	}
	public void setUsua_codpostal(Integer usua_codpostal) {
		this.usua_codpostal = usua_codpostal;
	}
	
	public String getUsua_falta() {
		return usua_falta;
	}
	public void setUsua_falta(String usua_falta) {
		this.usua_falta = usua_falta;
	}
	
	public String getUsua_fbaja() {
		return usua_fbaja;
	}
	public void setUsua_fbaja(String usua_fbaja) {
		this.usua_fbaja = usua_fbaja;
	}
	
	public int getUsua_esvoluntario() {
		return usua_esvoluntario;
	}
	public void setUsua_esvoluntario(int usua_esvoluntario) {
		this.usua_esvoluntario = usua_esvoluntario;
	}
	
	public int getUsua_esdemandante() {
		return usua_esdemandante;
	}
	public void setUsua_esdemandante(int usua_esdemandante) {
		this.usua_esdemandante = usua_esdemandante;
	}
	
	public int getUsua_esadmin() {
		return usua_esadmin;
	}
	public void setUsua_esadmin(int usua_esadmin) {
		this.usua_esadmin = usua_esadmin;
	}
	

	
}

