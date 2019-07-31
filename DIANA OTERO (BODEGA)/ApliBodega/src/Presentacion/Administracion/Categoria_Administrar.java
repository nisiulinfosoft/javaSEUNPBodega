package Presentacion.Administracion;

import DatosAcceso.DaoCategoria;
import LogicaGetSet.Categoria;
import Utiles.JP_Modelo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Categoria_Administrar extends JP_Modelo {

    DaoCategoria categoria=new DaoCategoria();

    String vCodigo,vNombre,vDescripcion;

    String codigo="", nombre="";

    public Categoria_Administrar() {
        initComponents();

        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true);

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vNombre=this.txtNombre.getText().trim();
        vDescripcion=this.txtaDescripcion.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtaDescripcion.setText("");
        this.txtNombre.requestFocus();
    }

   public void Habilitar_campos_textos(boolean habilita)
   {
        this.txtCodigo.setEnabled(false);
        this.txtNombre.setEnabled(habilita);
        this.txtaDescripcion.setEnabled(habilita);
   }

  public void Habilitar_botones(boolean b,boolean c,boolean d,boolean e,boolean f,boolean g)
  {
      this.btnNuevo.setEnabled(b);
      this.btnRegistrar.setEnabled(c);
      this.btnEditar.setEnabled(d);
      this.btnEliminar.setEnabled(e);
      this.btnCancelar.setEnabled(f);
      this.btnCerrar.setEnabled(g);
  }

  public void Formato_tabla(){
        this.JtCategoria.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtCategoria.getColumnModel().getColumn(1).setPreferredWidth(300);
  }

  public void RegistrarCategoria()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vNombre.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de categoria?","Pregunta",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Categoria objCategoria=null;
                try {
                    objCategoria=new Categoria();
                    objCategoria.setCodigo_cat(this.txtCodigo.getText().trim());
                    objCategoria.setNombre_cat(this.txtNombre.getText().trim());
                    objCategoria.setDescripcion_cat(this.txtaDescripcion.getText().trim());

                    if(categoria.categoria_registrar(objCategoria)==true){
                        this.ListarCategoria();
                        JOptionPane.showMessageDialog(this,"Regístro guardado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no registrados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }

                this.Limpiar_campos_texto();
                this.Habilitar_campos_textos(false);
                this.Habilitar_botones(true, false, false, false, false, true);
            }
        }
    }

  public void EditarCategoria()
    {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vNombre.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de editar datos de categoria?","Pregunta",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Categoria objCategoria=null;
                try {
                    objCategoria=new Categoria();
                    objCategoria.setCodigo_cat(this.txtCodigo.getText().trim());
                    objCategoria.setNombre_cat(this.txtNombre.getText().trim());
                    objCategoria.setDescripcion_cat(this.txtaDescripcion.getText().trim());

                    if(categoria.categoria_editar(objCategoria)==true){
                        this.ListarCategoria();
                        JOptionPane.showMessageDialog(this,"Regístro actualizado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no actualizados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }

                this.Limpiar_campos_texto();
                this.Habilitar_campos_textos(false);
                this.Habilitar_botones(true, false, false, false, false, true);
            }
        }
    }

  public void EliminarCategoria()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de eliminar datos de categoria?","Pregunta",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(categoria.categoria_eliminar(codigo)==true){
                    this.ListarCategoria();
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }

            this.Limpiar_campos_texto();
            this.Habilitar_campos_textos(false);
            this.Habilitar_botones(true, false, false, false, false, true);
        }
    }

  public void ObtenerCategoria(String codigo)
    {
        Categoria objCategoria=null;
        try{
            objCategoria=categoria.categoria_obtener_por_codigo(codigo);
            if (objCategoria!=null){
                this.txtCodigo.setText(objCategoria.getCodigo_cat());
                this.txtNombre.setText(objCategoria.getNombre_cat());
                this.txtaDescripcion.setText(objCategoria.getDescripcion_cat());

                this.Habilitar_campos_textos(true);
                this.Habilitar_botones(false, false, true, true, true, false);
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ListarCategoria(){
        Categoria objCategoria=null;
        List lista = null;
        String fila[];
        try {

            this.JrbNombre.setSelected(true);
            this.txtBuscar.setText("");

            lista=categoria.categoria_listar();

            DefaultTableModel modelo=(DefaultTableModel)this.JtCategoria.getModel();
            while(this.JtCategoria.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objCategoria=(Categoria)lista.get(i);
                fila=new String[2];
                fila[0]=objCategoria.getCodigo_cat();
                fila[1]=objCategoria.getNombre_cat();
                modelo.addRow(fila);
            }
        }catch(NullPointerException ne){//PARA ERROR DE CODIGO
            JOptionPane.showMessageDialog(null, "Error de Codigo :"+ne.toString());
        }catch(Exception es){//PARA QUE NOS ARROGE LOS ERRORES EN PANTALLA Y TENGO QUE OMITIR EL NullPointerException
            es.printStackTrace();
        }
    }

    public void Actualizar_busqueda(){
        List lista = null;
        try {
            if(this.JrbCodigo.isSelected())
            {
                codigo=this.txtBuscar.getText().trim();
                if(codigo.compareTo("")==0){
                    lista=categoria.categoria_listar_por_codigo("");
                }
                else{
                    lista=categoria.categoria_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=categoria.categoria_listar_por_nombre("");
                }
                else{
                    lista=categoria.categoria_listar_por_nombre(nombre);
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
        Categoria objCategoria=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtCategoria.getModel();
            while(this.JtCategoria.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objCategoria=(Categoria)lista.get(i);
                fila=new String[2];
                fila[0]=objCategoria.getCodigo_cat();
                fila[1]=objCategoria.getNombre_cat();
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
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtCategoria = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Categoria_Administrar.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 140, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtNombre.setName("txtNombre"); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 360, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        txtaDescripcion.setName("txtaDescripcion"); // NOI18N
        jScrollPane1.setViewportView(txtaDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 360, 50));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 50, 45));

        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setToolTipText(resourceMap.getString("btnEditar.toolTipText")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 50, 45));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 50, 45));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JtCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        JtCategoria.setName("JtCategoria"); // NOI18N
        JtCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtCategoriaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtCategoria);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 140));

        JrbNombre.setText(resourceMap.getString("JrbNombre.text")); // NOI18N
        JrbNombre.setName("JrbNombre"); // NOI18N
        jPanel2.add(JrbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        JrbCodigo.setText(resourceMap.getString("JrbCodigo.text")); // NOI18N
        JrbCodigo.setName("JrbCodigo"); // NOI18N
        jPanel2.add(JrbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 270, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 420, 200));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true);
        this.Habilitar_botones(false, true, false, false, true, false);

        String Codigo=categoria.categoria_generar_codigo();
        txtCodigo.setText(Codigo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarCategoria();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.EditarCategoria();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.EliminarCategoria();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtCategoriaMouseClicked
        String ccodigo=(String)this.JtCategoria.getValueAt(this.JtCategoria.getSelectedRow(), 0);
        this.ObtenerCategoria(ccodigo);
    }//GEN-LAST:event_JtCategoriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtCategoria;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables

}
