package DatosAcceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DaoReporte extends Conexion {

    public void Reporte_empleado(){
	Connection conexion=null;
        JasperPrint reporte1=null;
        JasperViewer ver1=null;//visualizarlo con el jasperviewer
        Map parametros=new HashMap();
        //parametros.put("id_cli",this.txtCodigo.toString().trim());
        try{
                conexion=getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/Reportes/EMPLEADOS.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("REPORTE DE EMPLEADOS");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
        }catch (JRException e) {
            JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    public void Reporte_proveedor(){
	Connection conexion=null;
        JasperPrint reporte1=null;
        JasperViewer ver1=null;//visualizarlo con el jasperviewer
        Map parametros=new HashMap();
        //parametros.put("id_cli",this.txtCodigo.toString().trim());
        try{
                conexion=getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/Reportes/PROVEEDOR.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("REPORTE DE PROVEEDORES");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
        }catch (JRException e) {
            JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    public void Reporte_cliente(){
	Connection conexion=null;
        JasperPrint reporte1=null;
        JasperViewer ver1=null;//visualizarlo con el jasperviewer
        Map parametros=new HashMap();
        //parametros.put("id_cli",this.txtCodigo.toString().trim());
        try{
                conexion=getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/Reportes/CLIENTE.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("REPORTE DE CLIENTES");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
        }catch (JRException e) {
            JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    public void Reporte_producto(){
	Connection conexion=null;
        JasperPrint reporte1=null;
        JasperViewer ver1=null;//visualizarlo con el jasperviewer
        Map parametros=new HashMap();
        //parametros.put("id_cli",this.txtCodigo.toString().trim());
        try{
                conexion=getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/Reportes/PRODUCTO.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("REPORTE DE PRODUCTOS");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
        }catch (JRException e) {
            JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    public void Reporte_generar_boleta(String codigoBol,String fecha,String nomCliente,String totalPagar){
	Connection conexion=null;
        JasperPrint reporte1=null;
        JasperViewer ver1=null;//visualizarlo con el jasperviewer
        Map parametros=new HashMap();
        parametros.put("PR_codBOLETA",codigoBol);
        parametros.put("PR_FECHA",fecha);
        parametros.put("PR_nomCLIENTE",nomCliente);
        parametros.put("PR_totalBOLETA",totalPagar);
        try{
                conexion=getConnection();
                JasperReport masterReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/Reportes/BOLETA.jasper"));
                reporte1=JasperFillManager.fillReport(masterReport,parametros,conexion);
                conexion.close();
                ver1=new JasperViewer(reporte1,false);
                ver1.setSize(500,550);
                ver1.setTitle("BOLETA DE VENTA");
                ver1.setZoomRatio((float) 0.5);
                ver1.setLocationRelativeTo(null);
                ver1.setVisible(true);
        }catch(SQLException e){//PARA ERROR DE CODIGO SQL
            JOptionPane.showMessageDialog(null,"Error SQL :"+e.toString());
        }catch (JRException e) {
            JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro:" + e.getMessage());
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

}
