<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,java.util.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList,com.mysql.jdbc.Connection"%>

<%


System.out.println("Estamos en  VAD_Registro_VO_1.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");



int usuaid = 0;
 
//usaid = (new Integer(request.getParameter("idusuario"))).intValue();

if (usuario !=null){
	usuaid = usuario.getUsua_id();
	//saco un mapa con los parametros del formulario
	Map datosForm = request.getParameterMap();
	String usua_usuario = request.getParameter("usua_usuario");
	String usua_clave =  request.getParameter("usua_clave");
	String usua_email = request.getParameter("usua_email");
	String usua_nif = request.getParameter("usua_nif");
	String usua_nombre = request.getParameter("usua_nombre");
	String usua_apellidos = request.getParameter("usua_apellidos");
	String usua_genero = request.getParameter("usua_genero");
	String usua_telefono = request.getParameter("usua_genero");
	String usua_direccion = request.getParameter("usua_direccion");
	String usua_numero = request.getParameter("usua_numero");
	
	//String usua_codpostal = request.getParameter("usua_codpostal");
	//int usua_codpostal = (new Integer(request.getParameter("usua_codpostal"))).intValue();
	int usua_codpostal = Integer.parseInt(request.getParameter("usua_codpostal"));
	int prov_provid = Integer.parseInt(request.getParameter("prov_provid"));
	
	
	System.out.println("Usuaid: " + usuaid);
	System.out.println("la provincia: " + prov_provid);
	System.out.println("cod postal: " + usua_codpostal);
	
	
	//int prov_provid = (new Integer(request.getParameter("prov_provid"))).intValue();
	int muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));
	
	//int codpostal = 29007;//Integer.parseInt(usua_codpostal);
	//int provid = 29;//Integer.parseInt(prov_provid);
	
	Connection conn =  (Connection) ConnectionManager.getInstance().checkOut();
	
	PreparedStatement stmt = null;
	//String sql = "SELECT * FROM provincias WHERE prov_id = ?";
	
	// Actualizamos la tabla Usuarios
	String sql2 = "UPDATE usuarios SET `usua_usuario` = '"  + usua_usuario;
	sql2 = sql2 + "', `usua_clave` = '" + usua_clave;
	sql2 = sql2 + "', `usua_nombre` = '" + usua_nombre;
	sql2 = sql2 + "', `usua_apellidos` = '" + usua_apellidos;
	sql2 = sql2 + "', `usua_email` = '" + usua_email;
	sql2 = sql2 + "', `usua_telefono` = '999999999'"; 
	sql2 = sql2 + ", `usua_genero` = " + usua_genero;
	sql2 = sql2 + ", `usua_nif` = '" + usua_nif;
	sql2 = sql2 + "', `usua_direccion` = '" + usua_direccion;
	sql2 = sql2 + "', `usua_numero` = '" + usua_numero;
	sql2 = sql2 + "', `prov_provid` = " + prov_provid;
	sql2 = sql2 + ", muni_muniid = " + muni_muniid;
	sql2 = sql2 + ", usua_codpostal = " + usua_codpostal;
	sql2 = sql2 + " WHERE usua_id = '" + usuaid + "';";

	System.out.println("La query: " +sql2 );
	
	
	ResultSet result = null;
	
	try{
		stmt = conn.prepareStatement(sql2);
		//stmt.setInt(1, prov_id);
		stmt.executeUpdate();
		
		System.out.println("Hemos ejecutado la actualizacion del usuario...");
		System.out.println("La query: " +sql2 );
		//result.next();
		
    } catch (SQLException e) {
        System.out.println("Message: Actualizacion Usuarios: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("ErrorCode: " + e.getErrorCode());
    } finally {
    	ConnectionManager.getInstance().checkIn(conn);
        try {
        	if (result != null)
        		result.close();
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
        }
    }	
   
    
    
   // AHora seleccionamos el usuario y lo metemos de nuevo en sesion
 
  	  JDBCUsuarioDAO jdaousuario = new JDBCUsuarioDAO();
	  Usuario user = jdaousuario.recuperarUsuarioByIdUsuario(usuaid);
	  System.out.println("Hemos recuperado el usuario con los datos actualizados...");
	  request.getSession().setAttribute("usuario", user);
		
	  int codpost = user.getUsua_codpostal();
	  System.out.println("Nuevo codpost : " + codpost);
    
	  //Comprobamos si existe en la tabla Voluntarios y si no está lo añadimos
	  
	
	  	PreparedStatement stmt2 = null;
	  	int voluid = 0;
	  String sql3 = "Select * from voluntarios where usua_usuaid = ?";
		ResultSet result2 = null;
		int tiseid =  0;
		String tisedescrico = null;
		try{
			stmt = conn.prepareStatement(sql3);
			stmt.setInt(1, user.getUsua_id());
			result2 = stmt.executeQuery();
			if (result.next()){
				voluid = result2.getInt("volu_id");
			}
		} catch (SQLException e) {
	        System.out.println("Message: Seleccionamos el cod voluntario: " + e.getMessage());
	        System.out.println("SQLState: " + e.getSQLState());
	        System.out.println("ErrorCode: " + e.getErrorCode());
	    } finally {
	    	ConnectionManager.getInstance().checkIn(conn);
	        try {
	        	if (result2 != null)
	        		result2.close();
	            if (stmt != null)
	                stmt.close();
	        } catch (SQLException e) {
	        }
	    }		
	    if (voluid==0){
	    	java.util.Date fecha = new java.util.Date();	
	    	String sql4 = "INSERT INTO `isg3_2`.`voluntarios` ('VOLU_COD`, `USUA_USUAID`, `VOLU_FALTA`) ";
			  			sql4 = sql4 + " VALUES ('"  + usua_usuario + "', ";
			  			sql4 = sql4 + usuaid + ","  ;
			  			sql4 = sql4 + " '" + fecha + "') " ;
			  			
			  	try{
	  				stmt = conn.prepareStatement(sql4);
	  				//stmt.setInt(1, prov_id);
	  				stmt.executeUpdate();
	  				
	  				System.out.println("Hemos ejecutado la inserción del voluntario...");
	  				System.out.println("La query: " +sql4 );
	  				//result.next();
	  				
	  		    } catch (SQLException e) {
	  		        System.out.println("Message: Insertando Voluntario : " + e.getMessage());
	  		        System.out.println("SQLState: " + e.getSQLState());
	  		        System.out.println("ErrorCode: " + e.getErrorCode());
	  		    } finally {
	  		    	ConnectionManager.getInstance().checkIn(conn);
	  		        try {
	  		        	if (result != null)
	  		        		result.close();
	  		            if (stmt != null)
	  		                stmt.close();
	  		        } catch (SQLException e) {
	  		        }
	  		    }	
	    }
	  request.getRequestDispatcher("VaD_Registro_VO_2.jsp").include(request,response);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
  		
  	</head>
  	<body leftmargin=0 topmargin=0>
<%   	
}else{//NO hay sesión
	request.getRequestDispatcher("Inicio.jsp").include(request,response);
}  
%> 	
  	</body>
</html>