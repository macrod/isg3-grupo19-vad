package svl.pos.data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager cm;
    private Driver dBDriver = null;
    private static Properties dbprops = new Properties();
    //private static final String dBUri = "jdbc:mysql://127.0.0.1:3306/isg3";
    //private static final String driverName = "com.mysql.jdbc.Driver";
    //private static final String password = "practica";
    //private static final String username = "root";
    
    
      private ConnectionManager() {
       

      try {
    	  InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfiguration.properties"); 
    	  dbprops.load(is);
	} catch (Exception e1) {
		System.err.println("No se ha encontrado el fichero de propiedades de la bb.dd ");
		e1.printStackTrace();
	}

     try {
            String driverName =  dbprops.getProperty("driverName");
        	dBDriver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(dBDriver);
        } catch (Exception e) {
            System.err.println("Imposible registrar el driver JDBC");
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (cm == null) {
            cm = new ConnectionManager();
        }
        return cm;
    }

    public Connection checkOut() {
        Connection conn = null;

        try {
        	String uri = dbprops.getProperty("dBUri");
        	String user = dbprops.getProperty("username");
        	String passwd  = dbprops.getProperty("password");
            conn = DriverManager.getConnection(uri,user ,passwd);
        } catch (Exception e) {
            System.err.println("Imposible abrir una nueva conexion JDBC");
            e.printStackTrace();
        }

        return conn;
    }

    public void checkIn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    protected void finalize() {
        try {
            DriverManager.deregisterDriver(dBDriver);
        } catch (SQLException e) {
            System.err.println("Imposible de desregistrar el driver JDBC");
            e.printStackTrace();
        }

    }
}
