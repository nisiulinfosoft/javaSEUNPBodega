package Presentacion.Administracion;

import DatosAcceso.DaoEmpleado;
import DatosAcceso.DaoUsuario;
import LogicaGetSet.Empleado;
import LogicaGetSet.Usuario;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.JP_Modelo;
import Utiles.Utiles_Panel;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.FrameView;

public class Usuario_Administrar extends JP_Modelo {

    JInternalFrame buscarEmpleado;

    DaoUsuario usuario=new DaoUsuario();
    DaoEmpleado empleado=new DaoEmpleado();

    String vCodigo,vCodigoPer,vLogin,vContrasena;

    String codigo="", nombre="";

    public Usuario_Administrar() {
        initComponents();

        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true, false);

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vCodigoPer=this.txtCodigoEmpleado.getText().trim();
        vLogin=this.txtLogin.getText().trim();
        vContrasena=this.txtPasswor.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtCodigoEmpleado.setText("");
        this.txtNombreEmpleado.setText("");
        this.txtLogin.setText("");
        this.txtPasswor.setText("");
        this.CkbAdministracion.setSelected(false);
        this.CkbVentas.setSelected(false);
        this.CkbConsultas.setSelected(false);
        this.CkbReportes.setSelected(false);
        this.txtLogin.requestFocus();
    }

   public void Habilitar_campos_textos(boolean habilita)
   {
        this.txtCodigo.setEnabled(false);
        this.txtCodigoEmpleado.setEnabled(false);
        this.txtNombreEmpleado.setEnabled(false);
        this.txtLogin.setEnabled(habilita);
        this.txtPasswor.setEnabled(habilita);
        this.CkbAdministracion.setEnabled(habilita);
        this.CkbVentas.setEnabled(habilita);
        this.CkbConsultas.setEnabled(habilita);
        this.CkbReportes.setEnabled(habilita);
   }

  public void Habilitar_botones(boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h)
  {
      this.btnNuevo.setEnabled(b);
      this.btnRegistrar.setEnabled(c);
      this.btnEditar.setEnabled(d);
      this.btnEliminar.setEnabled(e);
      this.btnCancelar.setEnabled(f);
      this.btnCerrar.setEnabled(g);
      this.btnBuscarEmpleado.setEnabled(h);
  }

  public void Formato_tabla(){
        this.JtUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtUsuario.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.JtUsuario.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.JtUsuario.getColumnModel().getColumn(3).setPreferredWidth(50);
  }

  public void RegistrarUsuario()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoPer.isEmpty() || vLogin.isEmpty() || vContrasena.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtLogin.getText().length() < 5){
                JOptionPane.showMessageDialog(this,"El campo LOGIN debe tener como mínimo 5 caracteres");
                this.txtLogin.requestFocus();
            }else if(this.txtPasswor.getText().length() < 5){
                JOptionPane.showMessageDialog(this,"El campo PASSWOR debe tener como mínimo 5 caracteres");
                this.txtPasswor.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de usuario?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Usuario objUsuario=null;
                    try {
                        objUsuario=new Usuario();
                        objUsuario.setCodigo_usu(this.txtCodigo.getText().trim());
                        objUsuario.setCodigo_per(this.txtCodigoEmpleado.getText().trim());
                        objUsuario.setLogin_usu(this.txtLogin.getText().trim());
                        objUsuario.setPass_usu(this.txtPasswor.getText().trim());

                        if(this.CkbAdministracion.isSelected()==true){
                            objUsuario.setAdministracion_usu("true");
                        }else{
                            objUsuario.setAdministracion_usu("false");
                        }

                        if(this.CkbVentas.isSelected()==true){
                            objUsuario.setVenta_per("true");
                        }else{
                            objUsuario.setVenta_per("false");
                        }

                        if(this.CkbConsultas.isSelected()==true){
                            objUsuario.setConsultas_usu("true");
                        }else{
                            objUsuario.setConsultas_usu("false");
                        }

                        if(this.CkbReportes.isSelected()==true){
                            objUsuario.setReportes_usu("true");
                        }else{
                            objUsuario.setReportes_usu("false");
                        }

                        if(usuario.usuario_registrar(objUsuario)==true){
                            this.ListarUsuario();
                            JOptionPane.showMessageDialog(this,"Regístro guardado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no registrados");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false);
                    this.Habilitar_botones(true, false, false, false, false, true, false);
                }
            }
        }
    }

  public void EditarUsuario()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoPer.isEmpty() || vLogin.isEmpty() || vContrasena.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtLogin.getText().length() < 5){
                JOptionPane.showMessageDialog(this,"El campo LOGIN debe tener como mínimo 5 caracteres");
                this.txtLogin.requestFocus();
            }else if(this.txtPasswor.getText().length() < 5){
                JOptionPane.showMessageDialog(this,"El campo PASSWOR debe tener como mínimo 5 caracteres");
                this.txtPasswor.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de editar datos de usuario?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Usuario objUsuario=null;
                    try {
                        objUsuario=new Usuario();
                        objUsuario.setCodigo_usu(this.txtCodigo.getText().trim());
                        objUsuario.setCodigo_per(this.txtCodigoEmpleado.getText().trim());
                        objUsuario.setLogin_usu(this.txtLogin.getText().trim());
                        objUsuario.setPass_usu(this.txtPasswor.getText().trim());

                        if(this.CkbAdministracion.isSelected()==true){
                            objUsuario.setAdministracion_usu("true");
                        }else{
                            objUsuario.setAdministracion_usu("false");
                        }

                        if(this.CkbVentas.isSelected()==true){
                            objUsuario.setVenta_per("true");
                        }else{
                            objUsuario.setVenta_per("false");
                        }

                        if(this.CkbConsultas.isSelected()==true){
                            objUsuario.setConsultas_usu("true");
                        }else{
                            objUsuario.setConsultas_usu("false");
                        }

                        if(this.CkbReportes.isSelected()==true){
                            objUsuario.setReportes_usu("true");
                        }else{
                            objUsuario.setReportes_usu("false");
                        }

                        if(usuario.usuario_editar(objUsuario)==true){
                            this.ListarUsuario();
                            JOptionPane.showMessageDialog(this,"Regístro actualizado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no actualizados");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false);
                    this.Habilitar_botones(true, false, false, false, false, true, false);
                }
            }
        }
    }

  public void Eliminar_usuario()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de eliminar datos de usuario?","Pregunta",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(usuario.usuario_eliminar(codigo)==true){
                    this.ListarUsuario();
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }

            this.Limpiar_campos_texto();
            this.Habilitar_campos_textos(false);
            this.Habilitar_botones(true, false, false, false, false, true, false);
        }
    }

  public void ObtenerUsuario(String codigo)
    {
        Usuario objUsuario=null;
        Empleado objEmpleado=null;
        try{
            objUsuario=usuario.usuario_obtener_por_codigo(codigo);
            if (objUsuario!=null){
                this.txtCodigo.setText(objUsuario.getCodigo_usu());
                this.txtLogin.setText(objUsuario.getLogin_usu());
                this.txtPasswor.setText(objUsuario.getPass_usu());

                objEmpleado=empleado.empleado_obtener_por_codigo(objUsuario.getCodigo_per());
                this.txtCodigoEmpleado.setText(objEmpleado.getCodigo_emp());
                this.txtNombreEmpleado.setText(objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp());

                this.CkbAdministracion.setSelected(Boolean.valueOf(objUsuario.getAdministracion_usu()));
                this.CkbVentas.setSelected(Boolean.valueOf(objUsuario.getVenta_per()));
                this.CkbConsultas.setSelected(Boolean.valueOf(objUsuario.getConsultas_usu()));
                this.CkbReportes.setSelected(Boolean.valueOf(objUsuario.getReportes_usu()));

                this.Habilitar_campos_textos(true);
                this.Habilitar_botones(false, false, true, true, true, false, false);
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ObtenerEmpleado(String codigo)
    {
        Empleado objEmpleado=null;
        try{
            objEmpleado=empleado.empleado_obtener_por_codigo(codigo);
            if (objEmpleado!=null){
                this.txtCodigoEmpleado.setText(objEmpleado.getCodigo_emp());
                this.txtNombreEmpleado.setText(objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ListarUsuario(){
        Usuario objUsuario=null;
        Empleado objEmpleado=null;
        List lista = null;
        String fila[];
        try {

            this.JrbNombre.setSelected(true);
            this.txtBuscar.setText("");

            lista=usuario.usuario_listar();

            DefaultTableModel modelo=(DefaultTableModel)this.JtUsuario.getModel();
            while(this.JtUsuario.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objUsuario=(Usuario)lista.get(i);
                fila=new String[4];
                fila[0]=objUsuario.getCodigo_usu();
                objEmpleado=empleado.empleado_obtener_por_codigo(objUsuario.getCodigo_per());
                fila[1]=objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp();
                fila[2]=objUsuario.getLogin_usu();
                fila[3]=objUsuario.getPass_usu();
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
                    lista=usuario.usuario_listar_por_codigo("");
                }
                else{
                    lista=usuario.usuario_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=usuario.usuario_listar_por_nombre("");
                }
                else{
                    lista=usuario.usuario_listar_por_nombre(nombre);
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
        Usuario objUsuario=null;
        Empleado objEmpleado=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtUsuario.getModel();
            while(this.JtUsuario.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objUsuario=(Usuario)lista.get(i);
                fila=new String[4];
                fila[0]=objUsuario.getCodigo_usu();
                objEmpleado=empleado.empleado_obtener_por_codigo(objUsuario.getCodigo_per());
                fila[1]=objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp();
                fila[2]=objUsuario.getLogin_usu();
                fila[3]=objUsuario.getPass_usu();
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
        txtPasswor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtUsuario = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();
        txtCodigoEmpleado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        CkbVentas = new javax.swing.JCheckBox();
        CkbReportes = new javax.swing.JCheckBox();
        CkbAdministracion = new javax.swing.JCheckBox();
        CkbConsultas = new javax.swing.JCheckBox();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Usuario_Administrar.class);
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

        txtPasswor.setName("txtPasswor"); // NOI18N
        txtPasswor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassworKeyTyped(evt);
            }
        });
        jPanel1.add(txtPasswor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 150, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 50, 45));

        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setToolTipText(resourceMap.getString("btnEditar.toolTipText")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 50, 45));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 50, 45));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JtUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre y apellido", "Usuario", "Contraseña"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtUsuario.setName("JtUsuario"); // NOI18N
        JtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtUsuario);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 110));

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, 170));

        txtLogin.setName("txtLogin"); // NOI18N
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoginKeyTyped(evt);
            }
        });
        jPanel1.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 140, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtNombreEmpleado.setName("txtNombreEmpleado"); // NOI18N
        jPanel1.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 200, -1));

        btnBuscarEmpleado.setIcon(resourceMap.getIcon("btnBuscarEmpleado.icon")); // NOI18N
        btnBuscarEmpleado.setText(resourceMap.getString("btnBuscarEmpleado.text")); // NOI18N
        btnBuscarEmpleado.setName("btnBuscarEmpleado"); // NOI18N
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 30, -1));

        txtCodigoEmpleado.setName("txtCodigoEmpleado"); // NOI18N
        jPanel1.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 100, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CkbVentas.setText(resourceMap.getString("CkbVentas.text")); // NOI18N
        CkbVentas.setName("CkbVentas"); // NOI18N
        jPanel3.add(CkbVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        CkbReportes.setText(resourceMap.getString("CkbReportes.text")); // NOI18N
        CkbReportes.setName("CkbReportes"); // NOI18N
        jPanel3.add(CkbReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        CkbAdministracion.setText(resourceMap.getString("CkbAdministracion.text")); // NOI18N
        CkbAdministracion.setName("CkbAdministracion"); // NOI18N
        jPanel3.add(CkbAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        CkbConsultas.setText(resourceMap.getString("CkbConsultas.text")); // NOI18N
        CkbConsultas.setName("CkbConsultas"); // NOI18N
        jPanel3.add(CkbConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 410, 80));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        Empleado_Buscar empleado=new Empleado_Buscar();
        buscarEmpleado=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), empleado, "Buscar empleados");
        empleado.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        empleado.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarEmpleado);
        empleado.valor=1;
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true);
        this.Habilitar_botones(false, true, false, false, true, false, true);

        String Codigo=usuario.usuario_generar_codigo();
        txtCodigo.setText(Codigo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarUsuario();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.EditarUsuario();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.Eliminar_usuario();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true, false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtUsuarioMouseClicked
        String ccodigo=(String)this.JtUsuario.getValueAt(this.JtUsuario.getSelectedRow(), 0);
        this.ObtenerUsuario(ccodigo);
    }//GEN-LAST:event_JtUsuarioMouseClicked

    private void txtLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtLogin, 20, evt);
    }//GEN-LAST:event_txtLoginKeyTyped

    private void txtPassworKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassworKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtPasswor, 20, evt);
    }//GEN-LAST:event_txtPassworKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CkbAdministracion;
    private javax.swing.JCheckBox CkbConsultas;
    private javax.swing.JCheckBox CkbReportes;
    private javax.swing.JCheckBox CkbVentas;
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtUsuario;
    private javax.swing.JButton btnBuscarEmpleado;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoEmpleado;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtPasswor;
    // End of variables declaration//GEN-END:variables

}
