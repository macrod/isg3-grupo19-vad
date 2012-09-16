<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  SVL_Registro_VO_1.jsp");
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
int usua_codpostal = 0;
int tise_tiseid =0;
int  serv_servid =0;
String aler_descripcion = "";
int tibu_tibuid =0;
int prov_provid =41;
int muni_muniid =0;

String provincia="";
String municipio = "";
String tibubusqueda = "";
String tise = "";
String servicio = "";



if (usuaid != 0) {
	JDBCUsuarioDAO jdaousuario = new JDBCUsuarioDAO();
	JDBCProvinciaDAO jdaoprov = new JDBCProvinciaDAO();
	JDBCMunicipioDAO jdaomuni = new JDBCMunicipioDAO(); 
	JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
	
	 
	
	//Usuario user = jdaousuario.recuperarUsuarioByIdUsuario(usuaid);
	
	usua_usuario = usuario.getUsua_usuario();
	usua_clave =  usuario.getUsua_clave();
	usua_email = usuario.getUsua_email();
	usua_nif = usuario.getUsua_nif();
	usua_nombre = usuario.getUsua_nombre();
	usua_genero = usuario.getUsua_genero();
	usua_direccion = usuario.getUsua_direccion();
	usua_numero = usuario.getUsua_numero();
	prov_provid = usuario.getProv_provid();
	muni_muniid = usuario.getMuni_muniid();
	
	
	
	//servicio = jdaotise.selectServDescricoByTiseid(tise_tiseid);
	//tise = jdaotise.selectTiseDescricoByServid(serv_servid);
	
	
	
	provincia = jdaoprov.selectProviciaByProvid(prov_provid);
	municipio = jdaomuni.selectMunicipioByMuniid(muni_muniid);
	//provincia = jdaomuni.selectProvinciaByMuniid(muni_muniid);
	
	
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
			function enviar(){
				//alert("Enviando formulario");
				window.document.formulario.action = "FrontController?accion=grabarregistrovo";
				window.document.formulario.target = "_self";
				window.document.formulario.submit();
				
			}	
		</script>
  	</head>
  	<body leftmargin=0 topmargin=0>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Cabecera_buscador.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
            <form action="" name="formulario" method="post" target="_blank">
			<tr align="left">
            	<td width="200" align="left" valign="top">
					<IFRAME src="Menu.jsp" scrolling="no" frameborder="0" width="200" height="565"></IFRAME> 
            	</td>
				<td width="1065" align="left" valign="top">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr><td class="cabecera_pagina">ALTA DE VOLUNTARIOS</td></tr>
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
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="12" type="text" name="usua_nif" value="<%=usua_nif%>"></td>
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
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="5" type="text" name="usua_numero" maxlength="4" value="<%=usua_numero%>"></td>
								</tr>
								<tr>
									<td class="etiqueta" nowrap>Cod. Postal</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="7" type="text" name="usua_codpostal"  maxlength="5" value="<%=usua_codpostal%>"></td>
									<%
					ProvinciaStore pr = new ProvinciaStore();
					List<Provincia> provincias = pr.recuperarTodasLasProvincias();
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
									<td align="right"><button class="boton_general" type="button" onclick="javascript:enviar();">Grabar datos personales</button></td>
								</tr>
							</table>							
						</td></tr>
					</table>
				</td>
			</tr>
			</form>
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
	</body>
</html>