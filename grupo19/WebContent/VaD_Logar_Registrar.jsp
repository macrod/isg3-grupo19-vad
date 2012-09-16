<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  VaD_Logar_Regirtar.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");
	

int usuaid = 0;
 
//usaid = (new Integer(request.getParameter("idusuario"))).intValue();

if (usuario !=null){
	usuaid = usuario.getUsua_id();
}
String usua_usuario = "";
String usua_clave = "";
String usua_email = "";
String usua_nif = "";
String usua_nombre = "";
int usua_genero = 0;
String usua_direccion = "";
String usua_numero = "";
String usua_telefono = "";
int usua_codpostal = 0;
//int tise_tiseid =0;
//int  serv_servid =0;
String aler_descripcion = "";
//int tibu_tibuid =0;
int prov_provid =41;
int muni_muniid =0;

String provincia="";
String municipio = "";
String tibubusqueda = "";
String tise = "";
String servicio = "";

IRegistroProcessor procesoregistro = new RegistroProcessor();

if (usuaid != 0) {
	
	usua_usuario = usuario.getUsua_usuario();
	usua_clave =  usuario.getUsua_clave();
	usua_email = usuario.getUsua_email();
	usua_nif = usuario.getUsua_nif();
	usua_nombre = usuario.getUsua_nombre();
	usua_genero = usuario.getUsua_genero();
	usua_direccion = usuario.getUsua_direccion();
	usua_numero = usuario.getUsua_numero();
	usua_telefono = usuario.getUsua_telefono();
	prov_provid = usuario.getProv_provid();
	muni_muniid = usuario.getMuni_muniid();
	
	provincia = procesoregistro.selectProviciaByProvid(prov_provid);
	municipio = procesoregistro.selectMunicipioByMuniid(muni_muniid);
	
	
	
}
	
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>SVL - Servicio de Voluntariado Local</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
  		<script type="text/javascript" src="js/prototype.js"></script>
    	<script type="text/javascript" src="js/reloaders.js"></script>
  		<script language="Javascript" type="text/javascript">
			function entrar(){
				//alert("Logando...");
				window.document.formulario.action = "FrontController?accion=entrar&adonde=inicio_vo";
				window.document.formulario.target = "_self";
				window.document.formulario.submit();
				
			}	
			function registrar(){
				//alert("Registrando...");
				//window.document.formulario.action = "FrontController?accion=grabarregistrovo";
				window.document.formulario2.action = "FrontController?accion=registrar";
				window.document.formulario2.target = "_self";
				window.document.formulario2.submit();
				
			}	
		</script>
  	</head>
  	<body leftmargin=0 topmargin=0>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td valign="top" colspan="3">
            	<IFRAME src="Cabecera_logar.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
            <tr>
              <td valign="top" colspan="3" height="200">
            	<BR>
				<BR>
				<p class="titulo_aplicacion">Bienvenidos a la Red Social de<font color="#B60026"><strong> VOLUNTARIOS A DOMICILIO. </strong></font> </p>
            	<p class="titulo_aplicacion">Logate y entra o registrate para dar de alta
				actividades o servicios que quieras compartir con los demas de tu pueblo o ciudad. Si tienes tiempo libre y quieres ofrecerlo en ayudar a los demas entonces... </p><br>
				<p class="titulo_aplicacion"><font color="#B60026"><strong>ESTA ES TU WEB!!</strong></font> </p>
             </td>
            </tr>
			<form action="" name="formulario" method="post">
			<input type="hidden" name="dedonde" id="dedonde" value="logar_registrar">
			<tr align="left">
           	  <td align="center" valign="top">
					<table align="center" width="317" cellspacing="0" cellpadding="0">
					<tr><td width="315" colspan="2" class="cabecera_pagina">CONTROL DE ACCESO USUARIOS REGISTRADOS</td></tr>
					<tr><td class="cuerpo_login" height="100">
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="30%" class="etiqueta_login" align="left"><span class="etiqueta">Usuario&nbsp;&nbsp;</span>&nbsp;</td>
								<td width="70%" align=left><input name="usua_usuario" type="text" class="texto_login" id="usua_usuario" size="30"></td>
							</tr>
							<tr>
								<td width="30%" class="etiqueta_login"><span class="etiqueta">Contraseña&nbsp;&nbsp;</span>&nbsp;</td>
								<td width="70%" align=left><input name="usua_clave" type="password" class="texto_login" id="usua_clave" size="30"></td>
							</tr>
							<tr><td colspan="2" height="30">&nbsp;</td></tr>
						</table>
					</td></tr>
					<tr><td align="center" colspan="2">
						<button class="boton_general" type="button" onclick="javascript:entrar();">Logar</button>
					</td></tr>
				</table></td>
				</form>
				<form action="" name="formulario2" method="post" id="formulario2">
				<input type="hidden" name="dedonde" id="dedonde" value="logar_registrar">
				<td width="30">&nbsp;</td>
            	<td width="730" align="left" valign="top">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr>
						  <td class="cabecera_pagina">REGISTRO DE USUARIOS NUEVOS </td>
						</tr>
						<tr><td>
							<table width="100%" style="border:1px solid #1a81b6;" cellpadding="2" cellspacing="5">
								<tr>
									<td width="10%" class="etiqueta" nowrap="nowrap">Nickname</td>
									<td width="16%" valign="top" nowrap="nowrap"><input class="caja_texto" size="20" type="text" name="usua_usuario" value="<%=usua_usuario%>"></td>
									<td width="10%" class="etiqueta" nowrap="nowrap">Clave</td>
									<td width="28%" valign="top" nowrap="nowrap"><input class="caja_texto" size="30" type="password" name="usua_clave" value="<%=usua_clave%>"></td>
									<td width="10%" class="etiqueta" nowrap="nowrap">Email</td>
									<td width="28%" valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="usua_email" value="<%=usua_email%>"></td>
								</tr>
								<tr>
									<td class="etiqueta" nowrap>Nombre</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="20" type="text" name="usua_nombre" value="<%=usua_nombre%>"></td>
									<td class="etiqueta" nowrap="nowrap">Apellidos</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="usua_apellidos" value="<%=usua_email%>"></td>
									<td class="etiqueta" nowrap="nowrap">Nif</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="12" type="text" name="usua_nif" value="<%=usua_nif%>">&nbsp;&nbsp;<font class="etiqueta" >Telefono</font>&nbsp;&nbsp;<input class="caja_texto" size="12" type="text" name="usua_telefono" id="usua_telefono" value="<%=usua_telefono%>"></td>
								</tr>
								<tr>
									<td class="etiqueta" nowrap>Género</td>
									<td valign="top" nowrap="nowrap">
										<select class="caja_texto" size="1" name="usua_genero">
											<option value="1" <% if (usua_genero==1) {%> selected<%} %>>Masculino</option>                                                     
											<option value="2" <% if (usua_genero==2) {%> selected<%} %>>Femenino</option>
										</select>
									</td>
									<td class="etiqueta" nowrap="nowrap">Dirección</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="usua_direccion" value="<%=usua_direccion%>"></td>
									<td class="etiqueta" nowrap="nowrap">Nº</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="5" type="text" name="usua_numero" maxlength="4" value="<%=usua_numero%>">&nbsp;&nbsp;<font class="etiqueta" >Perfil</font>&nbsp;&nbsp; <select class="caja_texto" name="usua_perfil" id="usua_perfil">
										<option value="voluntario">Voluntario</option><option value="demandante">Demandante</option></select>
									 </td>
								</tr>
								<tr>
									<td class="etiqueta" nowrap>Cod. Postal</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="7" type="text" name="usua_codpostal"  maxlength="5" value="<%=usua_codpostal%>"></td>
									<%
					//Obtenemos la lista de provincias
					List<Provincia> provincias = procesoregistro.recuperarTodasLasProvincias();
				%>
									<td class="etiqueta" nowrap="nowrap">Provincia</td>
									<td>
										<select class="caja_texto" name="prov_provid" id="prov_provid" onchange="reloadPoblaciones(this);">
										<option value="0">(Seleccione una provincia)</option>
										<% for ( Provincia pro : provincias ){ %>
											<option value="<%=pro.getProv_id()%>"  <% if (provincia.equals(pro.getProv_id())) {%> selected<%} %>><%=pro.getProv_nombre()%></option>
											<%} %>                                                                                                                   
										</select>
									</td>
									
									<td class="etiqueta" nowrap="nowrap">Municipio</td>
									<td>
										<select class="caja_texto" name="muni_muniid" id="muni_muniid">
											<option value="">(Seleccione una provincia)</option>
										</select>
									</td>
								</tr>
							</table>
						</td></tr>
						<tr><td>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="right"><button class="boton_general" type="button" onclick="javascript:registrar();">Grabar datos personales</button></td>
								</tr>
							</table>							
						</td></tr>
					</table>
				</td>
			</tr>
			</form>
			<tr><td valign="top" colspan="3" height="200">&nbsp;</td></tr>
			<tr><td valign="top" colspan="3">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
	</body>
</html>