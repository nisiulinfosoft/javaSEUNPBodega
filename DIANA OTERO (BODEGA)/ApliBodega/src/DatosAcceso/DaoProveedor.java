/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Proveedor;
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
public class DaoProveedor extends Conexion{

    public boolean proveedor_registrar(Proveedor proveedor)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_registrar(?,?,?,?,?,?,?)}");
            sentencia.setString(1,proveedor.getCodigo_prov());
            sentencia.setString(2,proveedor.getRuc_prov());
            sentencia.setString(3,proveedor.getRazon_social_prov());
            sentencia.setString(4,proveedor.getDireccion_prov());
            sentencia.setString(5,proveedor.getTelefono_prov());
            sentencia.setString(6,proveedor.getEmail_prov());
            sentencia.setString(7,proveedor.getObservacion_prov());

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

    public boolean proveedor_editar(Proveedor proveedor)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_editar(?,?,?,?,?,?,?)}");
            sentencia.setString(1,proveedor.getCodigo_prov());
            sentencia.setString(2,proveedor.getRuc_prov());
            sentencia.setString(3,proveedor.getRazon_social_prov());
            sentencia.setString(4,proveedor.getDireccion_prov());
            sentencia.setString(5,proveedor.getTelefono_prov());
            sentencia.setString(6,proveedor.getEmail_prov());
            sentencia.setString(7,proveedor.getObservacion_prov());

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
     }

    public boolean proveedor_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_eliminar(?)}");
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
    }

    public List proveedor_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Proveedor proveedor=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              proveedor=new Proveedor(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7));
              lista.add(proveedor);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List proveedor_listar()throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Proveedor proveedor=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_listar()}");

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              proveedor=new Proveedor(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7));
              lista.add(proveedor);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List proveedor_listar_por_nombre(String nombre)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Proveedor proveedor=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_listar_por_nombre(?)}");
            sentencia.setString(1,nombre);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
                proveedor=new Proveedor(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7));
                lista.add(proveedor);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Proveedor proveedor_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Proveedor proveedor=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              proveedor=new Proveedor(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return proveedor;
    }

    public String proveedor_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_proveedor_generar_codigo()}");
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
