<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  Registrar_Servicios.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");
String perfil = (String)session.getAttribute("perfil");
if (usuario !=null){
	int usuaid = usuario.getUsua_id();
	System.out.println("Usuaid: " + usuaid);
//usaid = (new Integer(request.getParameter("idusuario"))).intValue();
//int muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));

	String usua_usuario = usuario.getUsua_usuario();
	String usua_clave =  usuario.getUsua_clave();
	String usua_email = usuario.getUsua_email();
	String usua_nif = usuario.getUsua_nif();
	String usua_nombre = usuario.getUsua_nombre();
	int usua_genero = usuario.getUsua_genero();
	String usua_direccion = usuario.getUsua_direccion();
	String usua_numero = usuario.getUsua_numero();
	String usua_telefono = usuario.getUsua_telefono();
	int prov_provid = usuario.getProv_provid();
	int muni_muniid = usuario.getMuni_muniid();
	int usua_codpostal = usuario.getUsua_codpostal();
	
	//servicio = jdaotise.selectServDescricoByTiseid(tise_tiseid);
	//tise = jdaotise.selectTiseDescricoByServid(serv_servid);
	JDBCProvinciaDAO jdaoprov = new JDBCProvinciaDAO();
	JDBCMunicipioDAO jdaomuni = new JDBCMunicipioDAO(); 
	JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
	String provincia = jdaoprov.selectProviciaByProvid(prov_provid);
	String municipio = jdaomuni.selectMunicipioByMuniid(muni_muniid);
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
				alert("Sus datos se han grabado correctamente"):
				window.document.formulario.action = "FrontController?accion=insertarServiciosVO";
				window.document.formulario.submit();
				
			}	
		</script>
	</head>
  	<body leftmargin=0 topmargin=0>
   		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr><td valign="top" colspan="2">
           	<IFRAME src="Cabecera_buscador.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
           </td></tr>
           <form action="FrontController?accion=registrar" name="formulario" method="post" target="_blank">
		   <input type="hidden" name="dedonde" id="dedonde" value="registrar_servicios">
		<tr align="left">
           	<td width="1065" align="left" valign="top" colspan="2">
				<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left">
					<tr><td class="cabecera_pagina">REGISTRO DE VOLUNTARIOS</td></tr>
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
				ProvinciaStore pr = new ProvinciaStore();
				List<Provincia> provincias = pr.recuperarTodasLasProvincias();
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
						<tr><td colspan="6" class="cabecera_pagina">SERVICIOS QUE QUIERE OFRECER COMO VOLUNTARIO</td></tr>
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
				ServicioStore servstr = new ServicioStore();
				List<Servicio> servicios = servstr.recuperarTodosLosServicios();
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
}else{
	request.getRequestDispatcher("SVL_Logado.jsp").include(request,response);
}  
%> 	
  	</body>
</html>