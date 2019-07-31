/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Luisin Enrique
 */
public class Conexion {

    public Connection con = null;

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String databaseName = "bdBodega";
            String Login = "PROEDUNP";
            String password = "123456";
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  "databaseName="+databaseName+";user="+Login+";password="+password+";";

            con = java.sql.DriverManager.getConnection(connectionUrl);

        } catch (ClassNotFoundException e) {
            con=null;
            System.out.println("Error no se puede cargar el driver:" + e.getMessage());
        }catch (SQLException e) {
            con=null;
            System.out.println("Error no se establecer la conexion:" + e.getMessage());
        }catch (Exception e) {
            con=null;
            System.out.println("Error de seguimiento en getConnection() : " + e.getMessage());
        }
        return con;
    }
}
