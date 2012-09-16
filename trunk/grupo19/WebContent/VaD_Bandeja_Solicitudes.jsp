<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>

<%


System.out.println("Estamos en  VaD_MisDatos.jsp");
Usuario usuario = (Usuario)session.getAttribute("usuario");
String perfil = (String)session.getAttribute("perfil");

Integer usua_id = (Integer)session.getAttribute("usua_id");
System.out.println(" --- Usuaid de sesion: " + usua_id);



if (usuario !=null){
	UsuarioStore almacenusuarios = new UsuarioStore();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
  	</head>
  	<body leftmargin=0 topmargin=0>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td width="100%" valign="top" colspan="2">
            	<IFRAME src="Cabecera_bandejasol.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
			<tr align="left">
            	<td align="left" valign="top">
					<IFRAME src="Menu_VO.jsp" scrolling="no" frameborder="0" width="200" height="565"></IFRAME> 
            	</td>
            	<td width="1065" align="center" valign="top">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left" class="TablaGeneral">
						<tr>
						  <td colspan="2" class="cabecera_pagina">BANDEJA DE SOLICITUDES </td>
						</tr>
						<tr><td colspan="2" height="5"></td></tr>
						<tr><td colspan="2">
							<table class="x2b" border="0" cellpadding="1" cellspacing="0" width="100%">
								<form name="formulario1" action="" method="post">
									<tr>
										<th width="20%" class="x2t x5k" scope="col" nowrap>Descripción</th>
										<th width="30%" class="x2t x5k" scope="col" nowrap>Observaciones</th>
										<th width="14%" class="x2t x5k" scope="col" nowrap>Fecha Inicio </th>
										<th width="20%" class="x2t x5k" scope="col" nowrap>Nombre</th>
										<th width="16%" class="x2t x5k" scope="col" nowrap>Email</th>
									</tr>
									<%
				//Obtenemos la lista de solicitudes del usuario
				List<BandejaSolicitud> solicitudes = almacenusuarios.obtenerBandejaByUsuaID(usua_id);
			%>
			<% for ( BandejaSolicitud band : solicitudes ){ %>
									<tr>
										<td class="x2j x5y" align="left"><%=band.getBaso_descrico()%></td>
										<td class="x2j x5y" align="left"><%=band.getBaso_descrila()%></td>
										<td class="x2j x5y" align="left"><%=band.getBaso_finicio()%></td>
										<td class="x2j x5y" align="left"><%=band.getBaso_contacto()%></td>
										<td class="x2j x5y" align="left"><%=band.getBaso_emailcontacto()%></td>
									</tr>
									<%} %> 
									
								</form>
		           			</table>
           				</td></tr>
						<tr><td height="10"></td></tr>
						<tr>
							<td width="50%" align="left" valign="top">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td width="100%" valign="top" colspan="2">
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