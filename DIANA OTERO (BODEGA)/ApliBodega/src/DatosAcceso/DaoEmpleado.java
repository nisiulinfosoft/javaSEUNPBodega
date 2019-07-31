/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Empleado;
import LogicaGetSet.Imagen;
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
public class DaoEmpleado extends Conexion{

    public boolean empleado_registrar(Empleado empleado)
    {
        boolean respuesta = false;
        int fialUpdate=0;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        FileInputStream fis = null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_registrar(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,empleado.getCodigo_emp());
            sentencia.setString(2,empleado.getCodigo_car());
            sentencia.setString(3,empleado.getDni_emp());
            sentencia.setString(4,empleado.getNombre_emp());
            sentencia.setString(5,empleado.getApellidos_emp());
            sentencia.setString(6,empleado.getSexo_emp());
            sentencia.setDate(7,new java.sql.Date(empleado.getFecha_nacimiento_emp().getTime()));
            sentencia.setString(8,empleado.getDireccion_emp());
            sentencia.setString(9,empleado.getTelefono_emp());
            sentencia.setString(10,empleado.getCelular_emp());
            sentencia.setString(11,empleado.getEmail_emp());

            if(empleado.getFoto_emp()==null)
            {
               sentencia.setBinaryStream(12, null, 0);
               sentencia.setString(13,empleado.getObservacion_emp());
               fialUpdate=sentencia.executeUpdate();
            }else{
               fis = new FileInputStream(empleado.getFoto_emp().getFileFoto());
               sentencia.setBinaryStream(12, fis, (int)empleado.getFoto_emp().getFileFoto().length());
               sentencia.setString(13,empleado.getObservacion_emp());
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

    public boolean empleado_editar(Empleado empleado)
    {
        boolean respuesta=false;
        int fialUpdate=0;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        FileInputStream fis = null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_editar(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,empleado.getCodigo_emp());
            sentencia.setString(2,empleado.getCodigo_car());
            sentencia.setString(3,empleado.getDni_emp());
            sentencia.setString(4,empleado.getNombre_emp());
            sentencia.setString(5,empleado.getApellidos_emp());
            sentencia.setString(6,empleado.getSexo_emp());
            sentencia.setDate(7,new java.sql.Date(empleado.getFecha_nacimiento_emp().getTime()));
            sentencia.setString(8,empleado.getDireccion_emp());
            sentencia.setString(9,empleado.getTelefono_emp());
            sentencia.setString(10,empleado.getCelular_emp());
            sentencia.setString(11,empleado.getEmail_emp());

            if(empleado.getFoto_emp()==null)
            {
               sentencia.setBinaryStream(12, null, 0);
               sentencia.setString(13,empleado.getObservacion_emp());
               fialUpdate=sentencia.executeUpdate();
            }else{
               fis = new FileInputStream(empleado.getFoto_emp().getFileFoto());
               sentencia.setBinaryStream(12, fis, (int)empleado.getFoto_emp().getFileFoto().length());
               sentencia.setString(13,empleado.getObservacion_emp());
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

    public boolean empleado_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_eliminar(?)}");
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

    public List empleado_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Empleado empleado=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(12)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(12);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              empleado=new Empleado(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getDate(7),resultadoQuery.getString(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),imagen,resultadoQuery.getString(13));
              lista.add(empleado);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List empleado_listar()throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Empleado empleado=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_listar()}");

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(12)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(12);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              empleado=new Empleado(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getDate(7),resultadoQuery.getString(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),imagen,resultadoQuery.getString(13));
              lista.add(empleado);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List empleado_listar_por_nombre(String nombre)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Empleado empleado=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_listar_por_nombre(?)}");
            sentencia.setString(1,nombre);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(12)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(12);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              empleado=new Empleado(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getDate(7),resultadoQuery.getString(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),imagen,resultadoQuery.getString(13));
                lista.add(empleado);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Empleado empleado_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Empleado empleado=null;
        Imagen imagen=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            ImageIcon imagenRecuperado = new ImageIcon();

            while(resultadoQuery.next())
            {
              if(resultadoQuery.getBytes(12)==null);
              else
              {
                  byte[] i = null;
                  i = resultadoQuery.getBytes(12);
                  imagenRecuperado = new ImageIcon(i);
                  imagen=new Imagen();
                  imagen.setImagenFoto(imagenRecuperado);
              }

              empleado=new Empleado(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getDate(7),resultadoQuery.getString(8),resultadoQuery.getString(9),resultadoQuery.getString(10),resultadoQuery.getString(11),imagen,resultadoQuery.getString(13));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return empleado;
    }

    public String empleado_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_empleado_generar_codigo()}");
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
