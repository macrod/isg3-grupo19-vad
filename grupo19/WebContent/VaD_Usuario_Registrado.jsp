<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,java.util.List,java.util.ArrayList,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*"%>
<%

String usuario = (String)session.getAttribute("usua_usuario");
String perfil = (String)session.getAttribute("perfil");
String mensaje ="";
String dedonde = request.getParameter("dedonde");

System.out.println(" --- perfil: " + perfil);
System.out.println(" --- usuario: " + usuario);

if ("guardaregistro".equals(dedonde)){
	mensaje = " El usuario  "  + usuario + " se ha registrado correctamente ";
}


if (usuario!=null){
	
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
    	<script language="Javascript" type="text/javascript">
			function enviar(){
				<%if(perfil != null && perfil.equals("demandante")){ %>
					window.document.formulario.action = "FrontController?accion=inicioDE";
				<%}%>
				window.document.formulario.submit();
				
			}	
			
		</script>
  	</head>
  	<body leftmargin=0 topmargin=0>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Cabecera.htm" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
            <tr align="left">
            	<form action="FrontController?accion=inicioVO" name="formulario" method="post">
            	<input type="hidden" name="dedonde" id="dedonde" value="VaD_Usuario_Registrado.jsp">
            	<td width="1065" align="left" valign="top" colspan="2">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr><td	colspan="2" class="" align="center"><span class="titulo_aplicacion"><%=mensaje%></span><br><br>
						<button class="boton_general" type="button" onclick="javascript:enviar();">Aceptar</button>
						</td></tr>
						<tr><td height="10"></td></tr>
						
					</table>
				</td>
				</form>
			</tr>
			<tr><td valign="top" colspan="2" height="200">&nbsp;</td></tr>
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
	</body>
</html>