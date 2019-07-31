package Presentacion.Administracion;

import Utiles.JP_Modelo;
import DatosAcceso.DaoProveedor;
import LogicaGetSet.Proveedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proveedor_Buscar extends JP_Modelo {

    public int valor;

    DaoProveedor proveedor=new DaoProveedor();

    String codigo="", nombre="";

    public Proveedor_Buscar() {
        initComponents();

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();
    }

    public void Formato_tabla(){
        this.JtProveedor.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtProveedor.getColumnModel().getColumn(1).setPreferredWidth(50);
        this.JtProveedor.getColumnModel().getColumn(2).setPreferredWidth(150);
  }

    public void Actualizar_busqueda(){
        List lista = null;
        try {
            if(this.JrbCodigo.isSelected())
            {
                codigo=this.txtBuscar.getText().trim();
                if(codigo.compareTo("")==0){
                    lista=proveedor.proveedor_listar_por_codigo("");
                }
                else{
                    lista=proveedor.proveedor_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=proveedor.proveedor_listar_por_nombre("");
                }
                else{
                    lista=proveedor.proveedor_listar_por_nombre(nombre);
                }
            }
            this.Recargar_tabla(lista);
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }
    public void Recargar_tabla(List lista){
        Proveedor objProveedor=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtProveedor.getModel();
            while(this.JtProveedor.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objProveedor=(Proveedor)lista.get(i);
                fila=new String[3];
                fila[0]=objProveedor.getCodigo_prov();
                fila[1]=objProveedor.getRuc_prov();
                fila[2]=objProveedor.getRazon_social_prov();
                modelo.addRow(fila);
            }
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        JrbCodigo = new javax.swing.JRadioButton();
        JrbNombre = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtProveedor = new javax.swing.JTable();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Proveedor_Buscar.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JrbCodigo.setText(resourceMap.getString("JrbCodigo.text")); // NOI18N
        JrbCodigo.setName("JrbCodigo"); // NOI18N
        JrbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(JrbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        JrbNombre.setText(resourceMap.getString("JrbNombre.text")); // NOI18N
        JrbNombre.setName("JrbNombre"); // NOI18N
        jPanel1.add(JrbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 290, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        JtProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "RUC", "Razon social"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtProveedor.setName("JtProveedor"); // NOI18N
        JtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JtProveedor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 440, 190));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 270));
    }// </editor-fold>//GEN-END:initComponents

    private void JrbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbCodigoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_JrbCodigoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
         this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtProveedorMouseClicked
        String ccodigo=(String)this.JtProveedor.getValueAt(this.JtProveedor.getSelectedRow(), 0);

        if(this.getRefDep() instanceof Producto_Administrar)//para ver quien fue quien lo llamo
        {
            ((Producto_Administrar)this.getRefDep()).ObtenerProveedor(ccodigo);
        }
        this.cerrarPadre();
    }//GEN-LAST:event_JtProveedorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtProveedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
