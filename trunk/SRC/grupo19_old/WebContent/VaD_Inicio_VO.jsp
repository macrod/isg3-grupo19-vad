<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1" language="java" import="java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*"%>
<%

Usuario usuario = (Usuario)session.getAttribute("usuario");
String nombre = "";
String perfil = "";
if (usuario!=null){
	nombre = usuario.getUsua_usuario();
	perfil = (String)session.getAttribute("perfil");
}

%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VaD - Red Social de Voluntarios a Domicilio</title>
<link href="css/vad_estilo.css"" rel="stylesheet" type="text/css" />

</head>

<body class="body2">

<div id="cabecera"></div><!--[if !IE]>Aqui termina #cabecera<![endif]-->
<table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr bgcolor="#3980F4">
		<td width="60%" height="20" align="left" class="ruta">&nbsp;Est Vd. en: INICIO - VOLUNTARIOS</td>
		<td width="30%" align="right" class="texto_min_1_b"><% if (usuario!=null) { %><%=nombre%>&nbsp; - <%=perfil%>&nbsp;&nbsp;<% } %>&nbsp;</td>
		<td width="10%" align="right"><a class="texto_min_1_b" href="javascript:window.parent.location.href='VaD_Ayuda.htm';">Ayuda</a>&nbsp;&nbsp;&nbsp;<a class="texto_min_1_b" href="javascript:window.parent.location.href='FrontController?accion=salir';">Salir</a>&nbsp;</td>
	</tr>
</table>

<div id="contenedorInicio">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td height="30" colspan="5"></td></tr>
	<tr>
    <td width="5%">&nbsp;</td>
    <td width="40%">&nbsp;</td>
    <td width="10%">&nbsp;</td>
    <td width="40%">&nbsp;</td>
    <td width="5%">&nbsp;</td>
    </tr>
    <tr>
    <td>&nbsp;</td>
    <td align="center" valign="middle"><span class="entrada"><a href="javascript:window.parent.location.href='FrontController?accion=misdatos';">Mis Datos </a></span></td>
    <td align="center" valign="middle">&nbsp;</td>
    <td align="center" valign="middle"><span class="demandantes"><a href="javascript:window.parent.location.href='FrontController?accion=bandejasolicitudes';">Bandeja de Solicitudes</a></span></td>
	
    <td>&nbsp;</td>
    </tr>
    <tr>
    <td height="25">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
	<td>&nbsp;</td>
    <tr>
    <td>&nbsp;</td>
    <td align="center" valign="middle"><span class="alertas"><a href="javascript:window.parent.location.href='FrontController?accion=publicaractividades';">Publicar Actividades </a></span></td>
    <td align="center" valign="middle">&nbsp;</td>
	<td align="center" valign="middle"><span class="admin"><a href="javascript:window.parent.location.href='FrontController?accion=proponerservicios';">Proponer Servicios</a></span></td>
	
	<td>&nbsp;</td>
    </tr>
    <tr>
    <td height="25">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
	<td>&nbsp;</td>
    <tr>
    </table>
  
</div><!--[if !IE]>Aqui termina #contenedorInicio<![endif]-->

</body>
</html>
