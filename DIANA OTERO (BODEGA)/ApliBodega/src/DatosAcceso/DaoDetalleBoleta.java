/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.DetalleBoleta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisin Enrique
 */
public class DaoDetalleBoleta extends Conexion{

    public boolean detalleBoleta_registrar(DetalleBoleta detalleBoleta)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_detalleBoleta_registrar(?,?,?,?)}");
            sentencia.setString(1,detalleBoleta.getCodigo_bol());
            sentencia.setString(2,detalleBoleta.getCodigo_pro());
            sentencia.setDouble(3,detalleBoleta.getPrecio_bol());
            sentencia.setInt(4,detalleBoleta.getCantidad_bol());

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            if(fialUpdate > 0)
            {
                respuesta = true;
            }else{
                respuesta = false;
            }
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
    }

    /*public boolean detalleBoleta_editar(DetalleBoleta detalleBoleta)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_detalleBoleta_editar(?,?,?,?)}");
            sentencia.setString(1,detalleBoleta.getCodigo_bol());
            sentencia.setString(2,detalleBoleta.getCodigo_pro());
            sentencia.setDouble(3,detalleBoleta.getPrecio_bol());
            sentencia.setInt(4,detalleBoleta.getCantidad_bol());

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            if(fialUpdate > 0)
            {
                respuesta=true;
            }else{
                respuesta=false;
            }
         }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
     }*/

    /*public boolean detalleBoleta_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_eliminar(?)}");
            sentencia.setString(1,codigo);

            int fialUpdate=sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
            if(fialUpdate > 0)
            {
                respuesta=true;
            }else{
                respuesta=false;
            }
       }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
        return respuesta;
    }*/

    public DetalleBoleta detalleBoleta_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        DetalleBoleta detalleBoleta=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_detalleBoleta_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              detalleBoleta=new DetalleBoleta(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getDouble(3),resultadoQuery.getInt(4));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return detalleBoleta;
    }
}
