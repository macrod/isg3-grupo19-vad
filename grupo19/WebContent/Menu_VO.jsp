<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*"%>
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
    	<link rel="stylesheet" type="text/css" href="css/vad2.css"></link>
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
		<script type="text/javascript" src="js/ddaccordion.js"></script>
		<script type="text/javascript">
			ddaccordion.init({
				headerclass: "submenuheader", //Shared CSS class name of headers group
				contentclass: "submenu", //Shared CSS class name of contents group
				revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
				mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
				collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
				defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
				onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
				animatedefault: false, //Should contents open by default be animated into view?
				persiststate: true, //persist state of opened contents within browser session?
				toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
				togglehtml: ["suffix", "<img src='img/plus.gif' class='statusicon' />", "<img src='img/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
				animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
				oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
					//do nothing
				},
				onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
					//do nothing
				}
			})
		</script>
		<style type="text/css">
			.glossymenu{ margin: 3px 0; padding: 0; width: 190px; border: 1px solid #9A9A9A; border-bottom-width: 0;}
			.glossymenu a.menuitem{ background: black url(img/glossyback.gif) repeat-x bottom left; font: bold 12px "Lucida Grande", "Trebuchet MS", Verdana, Helvetica, sans-serif; color: white;
									display: block; position: relative; width: auto; padding: 4px 0; padding-left: 10px; text-decoration: none;}
			.glossymenu a.menuitem:visited, .glossymenu .menuitem:active{color: white;}
			.glossymenu a.menuitem .statusicon{ position: absolute; top: 5px; right: 5px; border: none;}
			.glossymenu a.menuitem:hover{ background-image: url(img/glossyback2.gif);}
			.glossymenu div.submenu{ background: white;}
			.glossymenu div.submenu ul{ list-style-type: none; margin: 0; padding: 0;}
			.glossymenu div.submenu ul li{ border-bottom: 1px solid blue;}
			.glossymenu div.submenu ul li a{ display: block; font: normal 11px "Trebuchet MS"; color: black; text-decoration: none; padding: 2px 0; padding-left: 10px; }
			.glossymenu div.submenu ul li a:hover{ background: #DFDCCB; colorz: white;}
		</style>
  	</head>
  	<body leftmargin=0 topmargin=0>
		<table width="100%" cellpadding=0 cellspacing=0>
  			<tr><td align=left valign=top>
				<div class="glossymenu">
					<a class="menuitem" href="javascript:window.parent.location.href='VaD_Inicio_VO.jsp';">VOLUNTARIOS</a>
					<a class="menuitem" href="javascript:window.parent.location.href='FrontController?accion=misdatos';">&nbsp;&nbsp;MIS DATOS</a>
					<a class="menuitem" href="javascript:window.parent.location.href='FrontController?accion=bandejasolicitudes';">&nbsp;&nbsp;BANDEJA DE SOLICITUDES</a>
					<a class="menuitem" href="javascript:window.parent.location.href='FrontController?accion=publicaractividades';">&nbsp;&nbsp;PUBLICAR ACTIVIDADES</a>
					<a class="menuitem" href="javascript:window.parent.location.href='FrontController?accion=proponerservicios';">&nbsp;&nbsp;PROPONER SERVICIOS</a>
					
				</div>
			</td></tr>
		</table>
	</body>
</html>