/********************************************************************************
** @nombre      Log4JInit.java 
** @fecha       12-08-2011
** @descripcion Clase que implementa un servlet que se utiliza para configurar
**              el log de la aplicación con LOG4J
********************************************************************************/	
package svl.pos.util;

import java.io.IOException;
 
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Log4JInit extends HttpServlet 
{
  /*******************************************************************************
  ** Método que será invocado en la creación del servlet
  ** @param config Objeto que contiene la configuración del servlet
  ** @exception ServletException Se produce cuando la petición no puede ser atendida
  *******************************************************************************/ 
  public void init () throws ServletException 
  {    
    String nombreFichero = getInitParameter("fichero_configuracion");

    if(nombreFichero != null) 
    {
      DOMConfigurator.configureAndWatch(nombreFichero);
    }
  }

  /*******************************************************************************
  ** Método que procesa la petición HTTP con el método Get
  ** @param request  Objeto que contiene la petición
  ** @param response Objeto que almacenará la respuesta
  ** @exception ServletException Se produce cuando la petición no puede ser atendida
  ** @exception IOException Se produce cuando se detecta un error de entrada/salida
  **                        mientras el servlet atiende la petición
  *******************************************************************************/
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {}

  /*******************************************************************************
  ** Método que procesa la petición HTTP con el método Post
  ** @param request  Objeto que contiene la petición
  ** @param response Objeto que almacenará la respuesta
  ** @exception ServletException Se produce cuando la petición no puede ser atendida
  ** @exception IOException Se produce cuando se detecta un error de entrada/salida
  **                        mientras el servlet atiende la petición
  *******************************************************************************/
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {}

  /*******************************************************************************
  ** Método que será invocado en la destrucción del servlet
  *******************************************************************************/  
  public void destroy(){}

}
