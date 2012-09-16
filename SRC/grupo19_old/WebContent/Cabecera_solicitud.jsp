<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,java.util.List,java.util.ArrayList,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*"%>
<%

Usuario usuario = (Usuario)session.getAttribute("usuario");
String nombre = "";
String perfil = "";
if (usuario!=null){
	nombre = usuario.getUsua_usuario();
	perfil = (String)session.getAttribute("perfil");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
	</head>
	<body leftmargin=0 topmargin=0>
		<div id="cabecera"></div>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr bgcolor="#3980F4">
				<td width="60%" height="20" align="left" class="ruta">&nbsp;Está Vd. en: <a href="javascript:window.parent.location.href='Inicio.jsp';" class="texto_min_1_b">INICIO</a> - DEMANDANTES - Alta de Solicitud</td>
				<td width="30%" align="right" class="texto_min_1_b"><% if (usuario!=null) { %><%=nombre%>&nbsp; - <%=perfil%>&nbsp;&nbsp;<a class="texto_min_1_b" href="javascript:window.parent.location.href='FrontController?accion=salir';">Logout</a>&nbsp;&nbsp;&nbsp;<% }else { %><a class="texto_min_1_b" href="javascript:window.parent.location.href='FrontController?accion=logar&adonde=inicio';">Login</a><% } %>&nbsp;</td>
				<td width="10%" align="right"><a class="texto_min_1_b" href="javascript:window.parent.location.href='VaD_Ayuda.htm';">Ayuda</a>&nbsp;&nbsp;&nbsp;<a class="texto_min_1_b" href="javascript:window.parent.location.href='FrontController?accion=salir';">Salir</a>&nbsp;</td>
			</tr>
		</table>
	</body>
</html>