/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Imagen;
import LogicaGetSet.Producto;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisin Enrique
 */
public class DaoProducto extends Conexion{

    public boolean producto_registrar(Producto producto)
    {
        boolean respuesta = false;
        int fialUpdate=0;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        FileInputStream fis = null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_registrar(?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,producto.getCodigo_pro());
            sentencia.setString(2,producto.getCodigo_prov());
            sentencia.setString(3,producto.getCodigo_cat());
            sentencia.setString(4,producto.getCodigo_mar());
            sentencia.setString(5,producto.getCodigo_uni());
            sentencia.setString(6,producto.getNombre_pro());
            sentencia.setInt(7,producto.getStock_max_pro());
            sentencia.setInt(8,producto.getStock_min_pro());
            sentencia.setDouble(9,producto.getPrecio_compra_pro());
            sentencia.setDouble(10,producto.getPrecio_venta_pro());
            
            if(producto.getFoto_pro()==null)
            {
               sentencia.setBinaryStream(11, null, 0);
               sentencia.setString(12,producto.getObservacion_pro());
               fialUpdate=sentencia.executeUpdate();
            }else{
               fis = new FileInputStream(producto.getFoto_pro().getFileFoto());
               sentencia.setBinaryStream(11, fis, (int)producto.getFoto_pro().getFileFoto().length());
               sentencia.setString(12,producto.getObservacion_pro());
               fialUpdate=sentencia.executeUpdate();
               fis.close();
            }

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

    public boolean producto_editar(Producto producto)
    {
        boolean respuesta=false;
        int fialUpdate=0;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        FileInputStream fis = null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_editar(?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,producto.getCodigo_pro());
            sentencia.setString(2,producto.getCodigo_prov());
            sentencia.setString(3,producto.getCodigo_cat());
            sentencia.setString(4,producto.getCodigo_mar());
            sentencia.setString(5,producto.getCodigo_uni());
            sentencia.setString(6,producto.getNombre_pro());
            sentencia.setInt(7,producto.getStock_max_pro());
            sentencia.setInt(8,producto.getStock_min_pro());
            sentencia.setDouble(9,producto.getPrecio_compra_pro());
            sentencia.setDouble(10,producto.getPrecio_venta_pro());

            if(producto.getFoto_pro()==null)
            {
               sentencia.setBinaryStream(11, null, 0);
               sentencia.setString(12,producto.getObservacion_pro());
               fialUpdate=sentencia.executeUpdate();
            }else{
               fis = new FileInputStream(producto.getFoto_pro().getFileFoto());
               sentencia.setBinaryStream(11, fis, (int)producto.getFoto_pro().getFileFoto().length());
               sentencia.setString(12,producto.getObservacion_pro());
               fialUpdate=sentencia.executeUpdate();
               fis.close();
            }

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

    public boolean producto_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_eliminar(?)}");
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

    public boolean producto_actulizar_stock(String codigoPro,int cantidad){
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_actulizar_stock(?,?)}");
            sentencia.setString(1,codigoPro);
            sentencia.setInt(2,cantidad);

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

    public List producto_listar()throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Producto producto=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_listar()}");

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(11)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(11);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              producto=new Producto(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getInt(7),resultadoQuery.getInt(8),resultadoQuery.getDouble(9),resultadoQuery.getDouble(10),imagen,resultadoQuery.getString(12));
              lista.add(producto);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List producto_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Producto producto=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(11)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(11);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              producto=new Producto(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getInt(7),resultadoQuery.getInt(8),resultadoQuery.getDouble(9),resultadoQuery.getDouble(10),imagen,resultadoQuery.getString(12));
              lista.add(producto);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List producto_listar_por_nombre(String nombre)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Producto producto=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_listar_por_nombre(?)}");
            sentencia.setString(1,nombre);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(11)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(11);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              producto=new Producto(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getInt(7),resultadoQuery.getInt(8),resultadoQuery.getDouble(9),resultadoQuery.getDouble(10),imagen,resultadoQuery.getString(12));
                lista.add(producto);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Producto producto_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Producto producto=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(11)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(11);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              producto=new Producto(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getInt(7),resultadoQuery.getInt(8),resultadoQuery.getDouble(9),resultadoQuery.getDouble(10),imagen,resultadoQuery.getString(12));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return producto;
    }

    public String producto_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_producto_generar_codigo()}");
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
