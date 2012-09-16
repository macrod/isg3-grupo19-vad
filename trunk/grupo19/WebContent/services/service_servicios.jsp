<?xml version="1.0" encoding="ISO-8859-1"?>
 <%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>
<% 
	response.setContentType("text/xml");
	String[] losservicios = null;
	//String tise_tiseid = request.getParameter("id");
	int tise_tiseid = Integer.parseInt(request.getParameter("id"));

	if (tise_tiseid == 0) tise_tiseid = 1;
	
	ServicioStore sst = new ServicioStore();
	List<Servicio> servicios = sst.recuperarTodosLosServiciosporTipoServ(tise_tiseid);	
%>
<tiposervicio id="<%=tise_tiseid%>">
<%
        
	for ( Servicio serv : servicios ) 	
//for(int i = 0; i < poblaciones.length; i++)
	{
%>
	<servicio id="<%=serv.getServ_id()%>"><%=serv.getServ_descrico()%></servicio>
<%
	}
%>
</tiposervicio>