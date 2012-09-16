<?xml version="1.0" encoding="ISO-8859-1"?>
<% 
	response.setContentType("text/xml");
	String[] poblaciones = null;
	String id = request.getParameter("id");
	if (id == null) id = "1";
%>
<provincia id="<%=id%>">
<%
        if (id.equals("1"))
        {
            poblaciones = new String[]{"Alcobendas","Miraflores de la Sierra","San Fernando de Henares"};
        }
	else if (id.equals("2"))
	{
            poblaciones = new String[]{"El Palo","La Cala del Moral","Rincon de la Victoria"};
	}
	else
	{
            poblaciones = new String[]{""};
	}
	for(int i = 0; i < poblaciones.length; i++)
	{
%>
	<poblacion id="<%=i%>"><%=poblaciones[i]%></poblacion>
<%
	}
%>
</provincia>