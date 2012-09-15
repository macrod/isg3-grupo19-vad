<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  Registrar_Servicios.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");
String perfil = "voluntario";
String sessperf = (String)session.getAttribute("perfil");
String cabeceratexto = "SERVICIOS QUE QUIERE OFRECER COMO VOLUNTARIO";
//Obtenemos el usua_id guardado en sesión
Integer usua_id = (Integer)session.getAttribute("usua_id");
//Integer param = (Integer) session.getAttribute("MySessionVariable");
//int usua_id = Integer.parseInt(usuaidses);
if (usuario !=null){
	IRegistroProcessor procesoregistro = new RegistroProcessor();
	String usua_nombre  = usuario.getUsua_nombre();
	String usua_usuario = usuario.getUsua_usuario();
	String usua_clave   = usuario.getUsua_clave();
	int usuaid = procesoregistro.recuperarIDByUsername(usua_nombre);
	
	System.out.println(" --- Usuaid de Sesion: " + usua_id);
	System.out.println(" --- Usuaid del  bbdd: " + usuaid);
	System.out.println(" --- Perfil de sesion: " + sessperf);
	System.out.println(" --- usua_usuario: " + usua_usuario);
	System.out.println(" --- usua_clave: " + usua_clave);
	
	if(sessperf != null){
		perfil = sessperf;
		if (perfil.equals("demandante")) {
			cabeceratexto = " SERVICIOS QUE QUIERE SOLICITAR COMO DEMANDANTE ";
		}
		
	}
	
//usaid = (new Integer(request.getParameter("idusuario"))).intValue();
//int muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));

	
	String usua_email = "";
	String usua_nif = "";
	
	String usua_apellidos = "";
	int usua_genero = 1;
	String usua_direccion = "";
	String usua_numero = "";
	String usua_telefono = "";
	int prov_provid = 0;
	int muni_muniid = 0;
	int usua_codpostal = 0;
	int usua_esvoluntario = 0;
	//int usua_id = 0;
	//Obtenemos el usuario de la base de datos
	Usuario datosusuario = procesoregistro.obtenerMisdatos(usua_usuario,usua_clave);

	if (datosusuario !=null){
		
		usua_usuario = datosusuario.getUsua_usuario();
		usua_clave =  datosusuario.getUsua_clave();
		usua_email = datosusuario.getUsua_email();
		usua_nif = datosusuario.getUsua_nif();
		usua_nombre = datosusuario.getUsua_nombre();
		usua_apellidos = datosusuario.getUsua_apellidos();
		usua_genero = datosusuario.getUsua_genero();
		usua_direccion = datosusuario.getUsua_direccion();
		usua_numero = datosusuario.getUsua_numero();
		usua_telefono = datosusuario.getUsua_telefono();
		prov_provid = datosusuario.getProv_provid();
		muni_muniid = datosusuario.getMuni_muniid();
		usua_codpostal = datosusuario.getUsua_codpostal();
		usua_esvoluntario = datosusuario.getUsua_esdemandante();
	
	}
	
	String provincia = procesoregistro.selectProviciaByProvid(prov_provid);
	String municipio = procesoregistro.selectMunicipioByMuniid(muni_muniid);
	
	//provincia = jdaomuni.selectProvinciaByMuniid(muni_muniid);
	
	

	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
  		<script type="text/javascript" src="js/prototype.js"></script>
    	<script type="text/javascript" src="js/reloaders.js"></script>
  		<script language="Javascript" type="text/javascript">
			function enviar(){
				//alert("Sus datos se han grabado correctamente"):
				window.document.formulario.action = "FrontController?accion=insertarServiciosVO";
				window.document.formulario.submit();
				
			}	
		</script>
	</head>
  	<body leftmargin=0 topmargin=0>
   		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr><td valign="top" colspan="2">
           	<IFRAME src="Cabecera_registrar.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
           </td></tr>
           <form action="FrontController?accion=insertarServiciosVO" name="formulario" method="post" target="_self">
		   <input type="hidden" name="dedonde" id="dedonde" value="registrar_servicios">
		   <input type="hidden" name=usua_id id="usua_id" value="<%=usuaid%>">
		<tr align="center">
           	<td width="1065" align="center" valign="top" colspan="2">
				<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr><td class="cabecera_pagina">DATOS PERSONALES</td></tr>
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
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="usua_apellidos" value="<%=usua_apellidos%>"></td>
								<td class="etiqueta" nowrap="nowrap">Nif</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="12" type="text" name="usua_nif" value="<%=usua_nif%>">&nbsp;&nbsp;<font class="etiqueta" >Telefono</font>&nbsp;&nbsp;<input class="caja_texto" size="12" type="text" name="usua_telefono" id="usua_telefono" value="<%=usua_telefono%>"></td>
								</tr>
							<tr>
								<td class="etiqueta" nowrap>Género</td>
								<td valign="top" nowrap="nowrap">
									<select class="caja_texto" size="1" name="usua_genero">
										<option value="0" <% if (usua_genero==0) {%> selected<%} %>>Masculino</option>                                                     
										<option value="1" <% if (usua_genero==1) {%> selected<%} %>>Femenino</option>
									</select>
								</td>
								<td class="etiqueta" nowrap="nowrap">Dirección</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="usua_direccion" value="<%=usua_direccion%>"></td>
								<td class="etiqueta" nowrap="nowrap">Nº</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="5" type="text" name="usua_numero" maxlength="4" value="<%=usua_numero%>">&nbsp;&nbsp;<font class="etiqueta" >Perfil</font>&nbsp;&nbsp;<input class="caja_texto" size="12" type="text" name="usua_perfil" maxlength="4" value="<%=perfil%>"></td>
							</tr>
							<tr>
								<td class="etiqueta" nowrap>Cod. Postal</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="7" type="text" name="usua_codpostal" maxlength="5" value="<%=usua_codpostal%>"></td>
								<%
				//Obtenemos la lista de provincias
				List<Provincia> provincias = procesoregistro.recuperarTodasLasProvincias();
			%>
								<td class="etiqueta" nowrap="nowrap">Provincia</td>
								<td>
									<select class="caja_texto" name="prov_provid" id="prov_provid" onchange="reloadPoblaciones(this);">
									<option value="<%=prov_provid%>"><%=provincia%></option>
									<% for ( Provincia pro : provincias ){ %>
										<option value="<%=pro.getProv_id()%>"  <% if (provincia.equals(pro.getProv_id())) {%> selected<%} %>><%=pro.getProv_nombre()%></option>
										<%} %>                                                                                                                   
									</select>
								</td>
								
								<td class="etiqueta" nowrap="nowrap">Municipio</td>
								<td>
									<select class="caja_texto" name="muni_muniid" id="muni_muniid">
										<option value="<%=muni_muniid%>"><%=municipio%></option>
									</select>
								</td>
							</tr>
						</table>
					</td></tr>
					<tr><td colspan="6" height="10"></td></tr>
						<tr><td colspan="6" class="cabecera_pagina"><%=cabeceratexto%></td></tr>
						<tr><td colspan="6" height="5"></td></tr>
						<tr><td width="80%" valign="top">
							<fieldset>
							<legend class="EtiquetaGeneral">Marque los servicios que quiera ofrecer</legend>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr><td height="5"></td></tr>
									<tr><td>
										<table class="x2b" border="0" cellpadding="1" cellspacing="0" width="100%">
											<tr>
												<th width="8%"  class="x2t">&nbsp;</th>
												<th width="30%" class="x2t">Nombre del Servicio </th>
												<th width="62%"  class="x2t">Descripción del Servicio</th>
												
											</tr>
											<%
				// Obtenemos la lista de servicios
				List<Servicio> servicios = procesoregistro.recuperarTodosLosServicios();
				int numservicios=0;
				for ( Servicio serv : servicios ){ 
			%>
											<tr>
												<td class="x2j x5y"><input name="chk_<%=serv.getServ_id()%>" value="<%=serv.getServ_id()%>" type="checkbox"></td>
												<td class="x2j x5y"><%=serv.getServ_descrico()%></td>
												<td class="x2j x5y"><%=serv.getServ_descrila()%></td>
											</tr>
											<% numservicios++;
											} %>  
										</table>
									<tr><td>
								</table> 
							</fieldset>
						</td>
						
						</tr>
						<tr><td colspan="6" height="10"></td></tr>
						<tr><td colspan="6">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="right"><button class="boton_general" type="button" onclick="javascript:enviar();">Grabar Servicios</button></td>
								</tr>
							</table>							
						</td></tr>
					</table>
				</td>
			</tr>
			<input type="hidden" name="numservicios" id="numservicios" value="<%=numservicios%>">
		</form>
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
		
	
<%
}else{ // Hemos perdido la sesion y no tenemos usuario en sesion
	request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
}  
%> 	
  	</body>
</html>