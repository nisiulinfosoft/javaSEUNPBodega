/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Boleta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisin Enrique
 */
public class DaoBoleta extends Conexion{

    public boolean boleta_registrar(Boleta boleta)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_registrar(?,?,?,?)}");
            sentencia.setString(1,boleta.getCodigo_bol());
            sentencia.setString(2,boleta.getCodigo_emp());
            sentencia.setString(3,boleta.getCodigo_cli());
            sentencia.setDate(4,new java.sql.Date(boleta.getFecha_bol().getTime()));

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

    /*public boolean boleta_editar(Boleta boleta)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_editar(?,?,?,?)}");
            sentencia.setString(1,boleta.getCodigo_bol());
            sentencia.setString(2,boleta.getCodigo_emp());
            sentencia.setString(3,boleta.getCodigo_cli());
            sentencia.setDate(4,new java.sql.Date(boleta.getFecha_bol().getTime()));

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

    /*public boolean boleta_eliminar(String codigo)
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

    public List boleta_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Boleta boleta=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              boleta=new Boleta(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getDate(4));
              lista.add(boleta);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Boleta boleta_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Boleta boleta=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              boleta=new Boleta(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getDate(4));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return boleta;
    }

    public String boleta_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_boleta_generar_codigo()}");
            resultadoQuery=sentencia.executeQuery();

            if(resultadoQuery.next())
            {
                codigo=resultadoQuery.getString(1);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
       return codigo;
    }
}
