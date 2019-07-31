/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Categoria;
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
public class DaoCategoria extends Conexion {

    public boolean categoria_registrar(Categoria categoria)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_registrar(?,?,?)}");
            sentencia.setString(1,categoria.getCodigo_cat());
            sentencia.setString(2,categoria.getNombre_cat());
            sentencia.setString(3,categoria.getDescripcion_cat());

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

    public boolean categoria_editar(Categoria categoria)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_editar(?,?,?)}");
            sentencia.setString(1,categoria.getCodigo_cat());
            sentencia.setString(2,categoria.getNombre_cat());
            sentencia.setString(3,categoria.getDescripcion_cat());

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

    public boolean categoria_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_eliminar(?)}");
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

    public List categoria_listar()throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Categoria categoria=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_listar()}");

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              categoria=new Categoria(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
              lista.add(categoria);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List categoria_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Categoria categoria=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              categoria=new Categoria(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
              lista.add(categoria);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List categoria_listar_por_nombre(String nombre)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Categoria categoria=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_listar_por_nombre(?)}");
            sentencia.setString(1,nombre);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              categoria=new Categoria(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
                lista.add(categoria);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Categoria categoria_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Categoria categoria=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              categoria=new Categoria(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return categoria;
    }

    public String categoria_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_categoria_generar_codigo()}");
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
