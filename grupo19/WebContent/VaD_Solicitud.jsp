<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  VaD_Solicitud.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");
String perfil = (String)session.getAttribute("perfil");
String cabeceratexto = "SERVICIOS QUE QUIERE OFRECER COMO VOLUNTARIO";
//Obtenemos el usua_id guardado en sesión
Integer usua_id = (Integer)session.getAttribute("usua_id");
System.out.println(" --- Usuaid de sesion: " + usua_id);
//int usua_id = Integer.parseInt(usuaidses);

if (usuario !=null){
	IRegistroProcessor procesoregistro = new RegistroProcessor();
	ISolicitudProcessor procesosolicitud = new SolicitudProcessor();
	String usua_nombre  = usuario.getUsua_nombre();
	String usua_usuario = usuario.getUsua_usuario();
	String usua_clave   = usuario.getUsua_clave();
	
	int usuaid = procesoregistro.recuperarIDByUsername(usua_nombre);
	System.out.println(" --- usua_id de Sesion: " + usua_id);
	System.out.println(" --- Usuaid del  bbdd: " + usuaid);
	System.out.println(" --- usua_usuario: " + usua_usuario);
	System.out.println(" --- usua_clave: " + usua_clave);
	System.out.println(" --- Perfil de sesion: " + perfil);
	
	request.getSession().setAttribute("usua_id", usua_id);
	request.getSession().setAttribute("usua_usuario", usua_usuario);
	request.getSession().setAttribute("usua_clave", usua_clave);
	
	
	if(perfil != null && perfil.equals("demandante")){
		cabeceratexto = " SERVICIOS QUE QUIERE SOLICITAR COMO DEMANDANTE ";
	}
	
//usaid = (new Integer(request.getParameter("idusuario"))).intValue();
//int muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));

	
	String usua_email = "";
	String usua_nif = "";
	String usua_apellidos = "";
	
	
	int prov_provid = 0;
	int muni_muniid = 0;
	int usua_codpostal = 0;
	int tise_tiseid = 1;
	int serv_servid = 1;
	
	Usuario datosusuario = procesoregistro.obtenerMisdatos(usua_usuario,usua_clave);

	if (datosusuario !=null){
		usua_usuario = datosusuario.getUsua_usuario();
		usua_clave =  datosusuario.getUsua_clave();
		usua_email = datosusuario.getUsua_email();
		usua_nif = datosusuario.getUsua_nif();
		usua_nombre = datosusuario.getUsua_nombre();
		usua_apellidos = datosusuario.getUsua_apellidos();
		prov_provid = datosusuario.getProv_provid();
		muni_muniid = datosusuario.getMuni_muniid();
		
	
	}
	
	
	String provincia = procesoregistro.selectProviciaByProvid(prov_provid);
	String municipio = procesoregistro.selectMunicipioByMuniid(muni_muniid);
	String tiposervicio = procesosolicitud.selectTipoServicioByTiseid(tise_tiseid);
	String servicio = procesosolicitud.selectServDescricoByTiseid(tise_tiseid);
	

	
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
				window.document.formulario.action = "FrontController?accion=grabarsolicitud";
				window.document.formulario.submit();
				
			}	
		</script>
	</head>
  	<body leftmargin=0 topmargin=0>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td valign="top" colspan="4">
            	<IFRAME src="Cabecera_solicitud.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
            <form action="FrontController?accion=grabarsolicitud" name="formulario" method="post" target="_self">
			<input type="hidden" name="dedonde" id="dedonde" value="solicitud">
			<input type="hidden" name="usua_id" id="usua_id" value="<%=usuaid%>">
			<tr align="left">
            	<td width="200" align="left" valign="top">
					<IFRAME src="Menu_DE.jsp" scrolling="no" frameborder="0" width="200" height="565"></IFRAME> 
            	</td>
				<td width="1065" align="left" valign="top" colspan="2">
				<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr><td class="cabecera_pagina">DATOS DE LA SOLICITUD</td></tr>
					<tr><td>
						<table width="100%" style="border:1px solid #1a81b6;" cellpadding="2" cellspacing="5">
							<tr>
								<td width="15%" class="etiqueta" nowrap="nowrap">Nombre</td>
								<td width="35%" valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="soli_nombrecontacto" value="<%=usua_nombre%> &nbsp; <%=usua_apellidos%>"></td>
								<td width="15%" class="etiqueta" nowrap="nowrap">Email</td>
								<td width="35%" valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="soli_emailcontacto" value="<%=usua_email%>"></td>
							</tr>
							<tr><%
				List<TipoServicio> tiposervicios = procesosolicitud.recuperarTodosLosTiposServicios();
				 
			%>
								<td class="etiqueta" nowrap>Tipo de Servicios</td>
								<td valign="top" nowrap="nowrap">
									<select class="caja_texto" size="1" name="tise_tiseid" id="tise_tiseid" onchange="reloadServicios(this);">
										<option value="<%=tise_tiseid%>"><%=tiposervicio%></option>
										<% for ( TipoServicio tiposerv : tiposervicios ){%>
										<option value="<%=tiposerv.getTise_id()%>"  <% if (tiposervicio.equals(tiposerv.getTise_id())) {%> selected<%} %>><%=tiposerv.getTise_descrico()%></option>
										<%} %> 
									</select>
								</td>
								<td class="etiqueta" nowrap="nowrap">Servicios</td>
								<td>
									<select class="caja_texto" name="serv_servid" id="serv_servid">
										<option value="<%=serv_servid%>"><%=servicio%></option>
									</select>
								</td></tr>
							<tr>
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
							<tr>
								<td class="etiqueta" nowrap>Fecha</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="12" type="text" name="soli_fecha" value=""></td>
								<td class="etiqueta" nowrap="nowrap">Descripción</td>
								<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="soli_descripcion" value=""></td>
							</tr>
						</table>
					</td></tr>
					<tr><td colspan="4" height="10"></td></tr>
					<tr><td colspan="4">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="right"><button class="boton_general" type="button" onclick="javascript:enviar();">Grabar Solicitud</button></td>
								</tr>
							</table>							
						</td></tr>
					</table>
				</td>
			</tr>
			
		</form>
			<tr><td colspan="4" height="200"></td></tr>
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
		
	
<%
}else{
	request.getRequestDispatcher("VaD_Logar_Registrar.jsp").include(request,response);
}  
%> 	
  	</body>
</html>