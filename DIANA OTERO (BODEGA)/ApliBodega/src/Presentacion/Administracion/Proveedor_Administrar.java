package Presentacion.Administracion;

import DatosAcceso.DaoProveedor;
import LogicaGetSet.Proveedor;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.JP_Modelo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proveedor_Administrar extends JP_Modelo {

    DaoProveedor proveedor=new DaoProveedor();

    String vCodigo,vRUC,vRazonSocial,vDireccion,vTelefono;

    String codigo="", nombre="";

    public Proveedor_Administrar() {
        initComponents();

        this.Habilitar_campos_textos(false,false);
        this.Habilitar_botones(true, false, false, false, false, true);

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vRUC=this.txtRUC.getText().trim();
        vRazonSocial=this.txtRazonSocial.getText().trim();
        vDireccion=this.txtDireccion.getText().trim();
        vTelefono=this.txtTelefono.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtRUC.setText("");
        this.txtRazonSocial.setText("");
        this.txtDireccion.setText("");
        this.txtTelefono.setText("");
        this.txtEmail.setText("");
        this.txtaObservacion.setText("");
        this.txtRUC.requestFocus();
    }

   public void Habilitar_campos_textos(boolean a,boolean b)
   {
        this.txtCodigo.setEnabled(false);
        this.txtRUC.setEnabled(a);
        this.txtRazonSocial.setEnabled(b);
        this.txtDireccion.setEnabled(b);
        this.txtTelefono.setEnabled(b);
        this.txtEmail.setEnabled(b);
        this.txtaObservacion.setEnabled(b);
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
        this.JtProveedor.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtProveedor.getColumnModel().getColumn(1).setPreferredWidth(50);
        this.JtProveedor.getColumnModel().getColumn(2).setPreferredWidth(150);
  }

  public void RegistrarProveedor()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vRUC.isEmpty() || vRazonSocial.isEmpty() || vDireccion.isEmpty() || vTelefono.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtRUC.getText().length() < 11){
                JOptionPane.showMessageDialog(this,"El campo RUC debe tener 11  digitos");
                this.txtRUC.requestFocus();
            }else if(this.txtTelefono.getText().length() < 7){
                JOptionPane.showMessageDialog(this,"El campo TELÉFONO debe tener como mínimo 7 digitos");
                this.txtTelefono.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de proveedor?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Proveedor objProveedor=null;
                    try {
                        objProveedor=new Proveedor();
                        objProveedor.setCodigo_prov(this.txtCodigo.getText().trim());
                        objProveedor.setRuc_prov(this.txtRUC.getText().trim());
                        objProveedor.setRazon_social_prov(this.txtRazonSocial.getText().trim());
                        objProveedor.setDireccion_prov(this.txtDireccion.getText().trim());
                        objProveedor.setTelefono_prov(this.txtTelefono.getText().trim());
                        objProveedor.setEmail_prov(this.txtEmail.getText().trim());
                        objProveedor.setObservacion_prov(this.txtaObservacion.getText().trim());

                        if(proveedor.proveedor_registrar(objProveedor)==true){
                            this.ListarProveedor();
                            JOptionPane.showMessageDialog(this,"Regístro guardado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no registrados");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false,false);
                    this.Habilitar_botones(true, false, false, false, false, true);
                }
            }
        }
    }

  public void EditarProveedor()
    {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vRUC.isEmpty() || vRazonSocial.isEmpty() || vDireccion.isEmpty() || vTelefono.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtRUC.getText().length() < 11){
                JOptionPane.showMessageDialog(this,"El campo RUC debe tener 11  digitos");
                this.txtRUC.requestFocus();
            }else if(this.txtTelefono.getText().length() < 7){
                JOptionPane.showMessageDialog(this,"El campo TELÉFONO debe tener como mínimo 7 digitos");
                this.txtTelefono.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de editar datos de proveedor?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Proveedor objProveedor=null;
                    try {
                        objProveedor=new Proveedor();
                        objProveedor.setCodigo_prov(this.txtCodigo.getText().trim());
                        objProveedor.setRuc_prov(this.txtRUC.getText().trim());
                        objProveedor.setRazon_social_prov(this.txtRazonSocial.getText().trim());
                        objProveedor.setDireccion_prov(this.txtDireccion.getText().trim());
                        objProveedor.setTelefono_prov(this.txtTelefono.getText().trim());
                        objProveedor.setEmail_prov(this.txtEmail.getText().trim());
                        objProveedor.setObservacion_prov(this.txtaObservacion.getText().trim());

                        if(proveedor.proveedor_editar(objProveedor)==true){
                            this.ListarProveedor();
                            JOptionPane.showMessageDialog(this,"Regístro actualizado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no actualizados");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false,false);
                    this.Habilitar_botones(true, false, false, false, false, true);
                }
            }
        }
    }

    public void EliminarProveedor()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de eliminar datos de proveedor?","Pregunta",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(proveedor.proveedor_eliminar(codigo)==true){
                    this.ListarProveedor();
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }

            this.Limpiar_campos_texto();
            this.Habilitar_campos_textos(false,false);
            this.Habilitar_botones(true, false, false, false, false, true);
        }
    }

  public void ObtenerProveedor(String codigo)
    {
        Proveedor objProveedor=null;
        try{
            objProveedor=proveedor.proveedor_obtener_por_codigo(codigo);
            if (objProveedor!=null){
                this.txtCodigo.setText(objProveedor.getCodigo_prov());
                this.txtRUC.setText(objProveedor.getRuc_prov());
                this.txtRazonSocial.setText(objProveedor.getRazon_social_prov());
                this.txtDireccion.setText(objProveedor.getDireccion_prov());
                this.txtTelefono.setText(objProveedor.getTelefono_prov());
                this.txtEmail.setText(objProveedor.getEmail_prov());
                this.txtaObservacion.setText(objProveedor.getObservacion_prov());

                this.Habilitar_campos_textos(false,true);
                this.Habilitar_botones(false, false, true, true, true, false);
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ListarProveedor(){
        Proveedor objProveedor=null;
        List lista = null;
        String fila[];
        try {

            this.JrbNombre.setSelected(true);
            this.txtBuscar.setText("");

            lista=proveedor.proveedor_listar();

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
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaObservacion = new javax.swing.JTextArea();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtProveedor = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Proveedor_Administrar.class);
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

        txtRUC.setName("txtRUC"); // NOI18N
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUCKeyTyped(evt);
            }
        });
        jPanel1.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 140, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtaObservacion.setColumns(20);
        txtaObservacion.setRows(5);
        txtaObservacion.setName("txtaObservacion"); // NOI18N
        jScrollPane1.setViewportView(txtaObservacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 360, 50));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 50, 45));

        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setToolTipText(resourceMap.getString("btnEditar.toolTipText")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 50, 45));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 50, 45));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

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
        jScrollPane2.setViewportView(JtProveedor);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 120));

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 420, 180));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtRazonSocial.setName("txtRazonSocial"); // NOI18N
        jPanel1.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 360, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtDireccion.setName("txtDireccion"); // NOI18N
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 360, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtTelefono.setName("txtTelefono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 140, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        txtEmail.setName("txtEmail"); // NOI18N
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 180, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true,true);
        this.Habilitar_botones(false, true, false, false, true, false);

        String Codigo=proveedor.proveedor_generar_codigo();
        txtCodigo.setText(Codigo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarProveedor();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.EditarProveedor();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.EliminarProveedor();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false,false);
        this.Habilitar_botones(true, false, false, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtProveedorMouseClicked
        String ccodigo=(String)this.JtProveedor.getValueAt(this.JtProveedor.getSelectedRow(), 0);
        this.ObtenerProveedor(ccodigo);
    }//GEN-LAST:event_JtProveedorMouseClicked

    private void txtRUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtRUC, 11, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtRUC, evt);
    }//GEN-LAST:event_txtRUCKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtTelefono, 9, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtTelefono, evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtProveedor;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtaObservacion;
    // End of variables declaration//GEN-END:variables

}
