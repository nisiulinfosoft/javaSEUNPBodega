package Presentacion.Administracion;

import Utiles.JP_Modelo;
import DatosAcceso.DaoMarca;
import LogicaGetSet.Marca;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Marca_Buscar extends JP_Modelo {

    public int valor;

    DaoMarca marca=new DaoMarca();

    String codigo="", nombre="";

    public Marca_Buscar() {
        initComponents();

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();
    }

    public void Formato_tabla(){
        this.JtMarca.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtMarca.getColumnModel().getColumn(1).setPreferredWidth(200);
  }

    public void Actualizar_busqueda(){
        List lista = null;
        try {
            if(this.JrbCodigo.isSelected())
            {
                codigo=this.txtBuscar.getText().trim();
                if(codigo.compareTo("")==0){
                    lista=marca.marca_listar_por_codigo("");
                }
                else{
                    lista=marca.marca_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=marca.marca_listar_por_nombre("");
                }
                else{
                    lista=marca.marca_listar_por_nombre(nombre);
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
        Marca objMarca=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtMarca.getModel();
            while(this.JtMarca.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objMarca=(Marca)lista.get(i);
                fila=new String[2];
                fila[0]=objMarca.getCodigo_mar();
                fila[1]=objMarca.getNombre_mar();
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
        JtMarca = new javax.swing.JTable();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Marca_Buscar.class);
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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 310, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        JtMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtMarca.setName("JtMarca"); // NOI18N
        JtMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JtMarca);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 440, 190));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 270));
    }// </editor-fold>//GEN-END:initComponents

    private void JrbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbCodigoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_JrbCodigoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtMarcaMouseClicked
        String ccodigo=(String)this.JtMarca.getValueAt(this.JtMarca.getSelectedRow(), 0);

        if(this.getRefDep() instanceof Producto_Administrar)//para ver quien fue quien lo llamo
        {
            ((Producto_Administrar)this.getRefDep()).ObtenerMarca(ccodigo);
        }
        this.cerrarPadre();
    }//GEN-LAST:event_JtMarcaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtMarca;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
