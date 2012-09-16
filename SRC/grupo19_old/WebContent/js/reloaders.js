function reloadPoblaciones(selector)
{
    // la url del servicio que nos devuelve el listado de poblaciones para una provincia
    var surl = "services/service_poblaciones.jsp";

    // parámetro con el identificador de la provincia
    var params = "id="+selector.value;
    // debugger;

    // prototype Ajax.Request: petición de servicio
    var response = new Ajax.Request(surl, {asynchronous: false, method: 'post', parameters: params}).transport;

    // obtenemos el listado de poblaciones del xml de respuesta del servicio
    var items = response.responseXML.getElementsByTagName("poblacion");

    // prototype getElementById: obtenemos el selector de poblaciones del árbol DOM
    //var select = $("poblaciones");
    var select = $("muni_muniid");
    select.options.length = 0;
    
    // si tenemos alguna población para la provincia
    if( items.length != 0 )
    {
        // asignamos un valor por defecto en el primer option del selector de poblaciones
        select.options.add(new Option("Seleccione una poblacion",""));
        // por cada una de las poblaciones obtenidas
        for(i = 0 ; i < items.length; i++ )
        {
            // añadimos un option al selector de poblaciones
            if (items[i].firstChild != null)
            {
                select.options.add(new Option(items[i].firstChild.data,items[i].attributes[0].value));
            }
        }
        // seleccionamos el option por defecto
        select.options[0].selected = "selected";
    }
}

function reloadServicios(selector)
{
    // la url del servicio que nos devuelve el listado de servicios para un tipo de servicios
    var surl = "services/service_servicios.jsp";

    // parámetro con el identificador del tipo de servicio
    var params = "id="+selector.value;
    // debugger;

    // prototype Ajax.Request: petición de servicio
    var response = new Ajax.Request(surl, {asynchronous: false, method: 'post', parameters: params}).transport;

    // obtenemos el listado de poblaciones del xml de respuesta del servicio
    var items = response.responseXML.getElementsByTagName("servicio");

    // prototype getElementById: obtenemos el selector de servicios del árbol DOM
    
    var select = $("serv_servid");
    select.options.length = 0;
    
    // si tenemos alguna población para la provincia
    if( items.length != 0 )
    {
        // asignamos un valor por defecto en el primer option del selector de servicios
        select.options.add(new Option("Seleccione un tipo de Servicio",""));
        // por cada una de los servicios obtenidas
        for(i = 0 ; i < items.length; i++ )
        {
            // añadimos un option al selector de servicios
            if (items[i].firstChild != null)
            {
                select.options.add(new Option(items[i].firstChild.data,items[i].attributes[0].value));
            }
        }
        // seleccionamos el option por defecto
        select.options[0].selected = "selected";
    }
}