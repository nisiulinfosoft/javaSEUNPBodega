/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatosAcceso;

import LogicaGetSet.Usuario;
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
public class DaoUsuario extends Conexion{

    public static String CODEmpleado="";

    public boolean usuario_registrar(Usuario usuario)
    {
        boolean respuesta = false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion=getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_registrar(?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,usuario.getCodigo_usu());
            sentencia.setString(2,usuario.getCodigo_per());
            sentencia.setString(3,usuario.getLogin_usu());
            sentencia.setString(4,usuario.getPass_usu());
            sentencia.setString(5,usuario.getAdministracion_usu());
            sentencia.setString(6,usuario.getVenta_per());
            sentencia.setString(7,usuario.getConsultas_usu());
            sentencia.setString(8,usuario.getReportes_usu());

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

    public boolean usuario_editar(Usuario usuario)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_editar(?,?,?,?,?,?,?,?)}");
            sentencia.setString(1,usuario.getCodigo_usu());
            sentencia.setString(2,usuario.getCodigo_per());
            sentencia.setString(3,usuario.getLogin_usu());
            sentencia.setString(4,usuario.getPass_usu());
            sentencia.setString(5,usuario.getAdministracion_usu());
            sentencia.setString(6,usuario.getVenta_per());
            sentencia.setString(7,usuario.getConsultas_usu());
            sentencia.setString(8,usuario.getReportes_usu());

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

    public boolean usuario_eliminar(String codigo)
    {
        boolean respuesta=false;
        Connection conexion=null;
        PreparedStatement sentencia=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_eliminar(?)}");
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

    public Usuario usuario_inicio_sesion(String login,String pass)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Usuario usuario=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_inicio_sesion(?,?)}");
            sentencia.setString(1,login);
            sentencia.setString(2,pass);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7),resultadoQuery.getString(8));
              this.CODEmpleado=resultadoQuery.getString(2);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return usuario;
    }

    public List usuario_listar()throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Usuario usuario=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_listar()}");

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7),resultadoQuery.getString(8));
              lista.add(usuario);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List usuario_listar_por_codigo(String codigo)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Usuario usuario=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_listar_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7),resultadoQuery.getString(8));
              lista.add(usuario);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public List usuario_listar_por_nombre(String nombre)throws IOException
    {
        List lista=new ArrayList();
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Usuario usuario=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_listar_por_nombre(?)}");
            sentencia.setString(1,nombre);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
                usuario=new Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7),resultadoQuery.getString(8));
                lista.add(usuario);
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();es.getMessage();}
        return lista;
    }

    public Usuario usuario_obtener_por_codigo(String codigo)
    {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        Usuario usuario=null;
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_obtener_por_codigo(?)}");
            sentencia.setString(1,codigo);

            resultadoQuery=sentencia.executeQuery();

            while(resultadoQuery.next())
            {
              usuario=new Usuario(resultadoQuery.getString(1),resultadoQuery.getString(2),resultadoQuery.getString(3),resultadoQuery.getString(4),resultadoQuery.getString(5),resultadoQuery.getString(6),resultadoQuery.getString(7),resultadoQuery.getString(8));
            }
            resultadoQuery.close();
            sentencia.close();
            conexion.close();
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.toString());
        }catch(Exception es){es.printStackTrace();}
        return usuario;
    }

    public String usuario_generar_codigo() {
        Connection conexion=null;
        ResultSet resultadoQuery=null;
        PreparedStatement sentencia=null;
        String codigo="";
        try
        {
            conexion =getConnection();
            sentencia=conexion.prepareStatement("{call pa_usuario_generar_codigo()}");
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
