<?xml version="1.0" encoding="ISO-8859-1"?>
 <%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*,java.util.List,java.util.ArrayList"%>
<% 
	response.setContentType("text/xml");
	String[] poblaciones = null;
	String prov_provid = request.getParameter("id");
	if (prov_provid == null) prov_provid = "1";
	
	MunicipioStore mst = new MunicipioStore();
	List<Municipio> municipios = mst.recuperarNombreMunicipiosByProId(prov_provid);
%>
<provincia id="<%=prov_provid%>">
<%
        
	for ( Municipio muni : municipios ) 	
//for(int i = 0; i < poblaciones.length; i++)
	{
%>
	<poblacion id="<%=muni.getMuni_id()%>"><%=muni.getMuni_nombre()%></poblacion>
<%
	}
%>
</provincia>