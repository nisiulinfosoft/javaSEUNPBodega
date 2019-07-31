package Presentacion.Administracion;

import Utiles.JP_Modelo;
import DatosAcceso.DaoEmpleado;
import LogicaGetSet.Empleado;
import DatosAcceso.DaoCargo;
import LogicaGetSet.Cargo;
import LogicaGetSet.Imagen;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.Utiles_Panel;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.FrameView;

public class Empleado_Administrar extends JP_Modelo {

    JInternalFrame buscarCargo;

    Imagen imagen=null;

    DaoEmpleado empleado=new DaoEmpleado();
    DaoCargo cargo=new DaoCargo();

    String vCodigo,vCodigoCar,vDNI,vNombre,vApellidos,vDireccion,vTelefono,vCelular,vEmail,vObservacion;

    String codigo="", nombre="";

    public Empleado_Administrar() {
        initComponents();

        this.Habilitar_campos_textos(false,false);
        this.Habilitar_botones(true, false, false, false, false, true,false,false);

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();

        this.Asignar_foto();
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vCodigoCar=this.txtCodigoCargo.getText().trim();
        vDNI=this.txtDNI.getText().trim();
        vNombre=this.txtNombre.getText().trim();
        vApellidos=this.txtApellidos.getText().trim();
        vDireccion=this.txtDireccion.getText().trim();
        vTelefono=this.txtTelefono.getText().trim();
        vCelular=this.txtCelular.getText().trim();
        vEmail=this.txtEmail.getText().trim();
        vObservacion=this.txtaObservacion.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtCodigoCargo.setText("");
        this.txtNombreCargo.setText("");
        this.txtDNI.setText("");
        this.txtNombre.setText("");
        this.txtApellidos.setText("");
        this.CmbSexo.setSelectedItem("Masculino");
        this.txtFechaNacimiento.setDate(null);
        this.txtDireccion.setText("");
        this.txtTelefono.setText("");
        this.txtCelular.setText("");
        this.txtEmail.setText("");
        this.txtaObservacion.setText("");

        this.Asignar_foto();

        this.txtDNI.requestFocus();
    }

   public void Habilitar_campos_textos(boolean a,boolean b)
   {
        this.txtCodigo.setEnabled(false);
        this.txtCodigoCargo.setEnabled(false);
        this.txtNombreCargo.setEnabled(false);
        this.txtDNI.setEnabled(a);
        this.txtNombre.setEnabled(b);
        this.txtApellidos.setEnabled(b);
        this.CmbSexo.setEnabled(b);
        this.txtFechaNacimiento.setEnabled(b);
        this.txtDireccion.setEnabled(b);
        this.txtTelefono.setEnabled(b);
        this.txtCelular.setEnabled(b);
        this.txtEmail.setEnabled(b);
        this.txtaObservacion.setEnabled(b);
   }

  public void Habilitar_botones(boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean l)
  {
      this.btnNuevo.setEnabled(b);
      this.btnRegistrar.setEnabled(c);
      this.btnEditar.setEnabled(d);
      this.btnEliminar.setEnabled(e);
      this.btnCancelar.setEnabled(f);
      this.btnCerrar.setEnabled(g);
      this.btnAgregarFoto.setEnabled(h);
      this.btnBuscarCargo.setEnabled(l);
  }

  public void Formato_tabla(){
        this.JtEmpleado.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtEmpleado.getColumnModel().getColumn(1).setPreferredWidth(50);
        this.JtEmpleado.getColumnModel().getColumn(2).setPreferredWidth(200);
  }

  public void setFotoLabel(String ruta)
  {
      ImageIcon image=new ImageIcon(ruta);
      Icon icono = new ImageIcon(image.getImage().getScaledInstance( 170, 170, Image.SCALE_DEFAULT));
      this.lblFoto.setIcon(icono);
      this.repaint();
  }

  public void Asignar_foto()
  {
      String path = "/Images/foto_defecto.png";
      URL url = this.getClass().getResource(path);
      ImageIcon image=new ImageIcon(url);
      Icon icono = new ImageIcon(image.getImage().getScaledInstance( 170, 170, Image.SCALE_DEFAULT));
      this.lblFoto.setIcon(icono);
      this.repaint();

      if(imagen!=null)
      {
        imagen=null;
      }
  }

  public void RegistrarEmpleado()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoCar.isEmpty() || vDNI.isEmpty() || vNombre.isEmpty() || vApellidos.isEmpty() || this.txtFechaNacimiento.getDate()==null || vDireccion.isEmpty() || vTelefono.isEmpty() || vCelular.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtDNI.getText().length() < 8){
                JOptionPane.showMessageDialog(this,"El campo DNI debe tener 8  digitos");
                this.txtDNI.requestFocus();
            }else if(this.txtTelefono.getText().length() < 7){
                JOptionPane.showMessageDialog(this,"El campo TELÉFONO debe tener como mínimo 7 digitos");
                this.txtTelefono.requestFocus();
            }else if(this.txtCelular.getText().length() < 8){
                JOptionPane.showMessageDialog(this,"El campo CELULAR debe tener 9  digitos");
                this.txtCelular.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de empleado?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Empleado objEmpleado=null;
                    try {
                        objEmpleado=new Empleado();
                        objEmpleado.setCodigo_emp(this.txtCodigo.getText().trim());
                        objEmpleado.setCodigo_car(this.txtCodigoCargo.getText().trim());
                        objEmpleado.setDni_emp(this.txtDNI.getText().trim());
                        objEmpleado.setNombre_emp(this.txtNombre.getText().trim());
                        objEmpleado.setApellidos_emp(this.txtApellidos.getText().trim());
                        objEmpleado.setSexo_emp(this.CmbSexo.getSelectedItem().toString());
                        objEmpleado.setFecha_nacimiento_emp(this.txtFechaNacimiento.getDate());
                        objEmpleado.setDireccion_emp(this.txtDireccion.getText().trim());
                        objEmpleado.setTelefono_emp(this.txtTelefono.getText().trim());
                        objEmpleado.setCelular_emp(this.txtCelular.getText().trim());
                        objEmpleado.setEmail_emp(this.txtEmail.getText().trim());

                        if(imagen!=null)
                        {
                            objEmpleado.setFoto_emp(imagen);
                        }

                        objEmpleado.setObservacion_emp(this.txtaObservacion.getText().trim());

                        if(empleado.empleado_registrar(objEmpleado)==true){
                            this.ListarEmpleado();
                            JOptionPane.showMessageDialog(this,"Regístro guardado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no registrados");
                        }

                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false,false);
                    this.Habilitar_botones(true, false, false, false, false, true,false,false);
                }
            }
        }
    }

  public void EditarEmpleado()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoCar.isEmpty() || vDNI.isEmpty() || vNombre.isEmpty() || vApellidos.isEmpty() || this.txtFechaNacimiento.getDate()==null || vDireccion.isEmpty() || vTelefono.isEmpty() || vCelular.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtDNI.getText().length() < 8){
                JOptionPane.showMessageDialog(this,"El campo DNI debe tener 8  digitos");
                this.txtDNI.requestFocus();
            }else if(this.txtTelefono.getText().length() < 7){
                JOptionPane.showMessageDialog(this,"El campo TELÉFONO debe tener como mínimo 7 digitos");
                this.txtTelefono.requestFocus();
            }else if(this.txtCelular.getText().length() < 8){
                JOptionPane.showMessageDialog(this,"El campo CELULAR debe tener 9  digitos");
                this.txtCelular.requestFocus();
            }else{

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de editar datos de empleado?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Empleado objEmpleado=null;
                    try {
                        objEmpleado=new Empleado();
                        objEmpleado.setCodigo_emp(this.txtCodigo.getText().trim());
                        objEmpleado.setCodigo_car(this.txtCodigoCargo.getText().trim());
                        objEmpleado.setDni_emp(this.txtDNI.getText().trim());
                        objEmpleado.setNombre_emp(this.txtNombre.getText().trim());
                        objEmpleado.setApellidos_emp(this.txtApellidos.getText().trim());
                        objEmpleado.setSexo_emp(this.CmbSexo.getSelectedItem().toString());
                        objEmpleado.setFecha_nacimiento_emp(this.txtFechaNacimiento.getDate());
                        objEmpleado.setDireccion_emp(this.txtDireccion.getText().trim());
                        objEmpleado.setTelefono_emp(this.txtTelefono.getText().trim());
                        objEmpleado.setCelular_emp(this.txtCelular.getText().trim());
                        objEmpleado.setEmail_emp(this.txtEmail.getText().trim());

                        if(imagen!=null)
                        {
                            objEmpleado.setFoto_emp(imagen);
                        }

                        objEmpleado.setObservacion_emp(this.txtaObservacion.getText().trim());

                        if(empleado.empleado_editar(objEmpleado)==true){
                            this.ListarEmpleado();
                            JOptionPane.showMessageDialog(this,"Regístro actualizado con exito");
                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no actualizados");
                        }

                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false,false);
                    this.Habilitar_botones(true, false, false, false, false, true,false,false);
                }
            }
        }
    }

  public void EliminarEmpleado()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de eliminar datos de empleado?","Pregunta",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(empleado.empleado_eliminar(codigo)==true){
                    this.ListarEmpleado();
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }

            this.Limpiar_campos_texto();
            this.Habilitar_campos_textos(false,false);
            this.Habilitar_botones(true, false, false, false, false, true,false,false);
        }
    }

  public void ObtenerEmpleado(String codigo)
    {
        Empleado objEmpleado=null;
        Cargo objCargo=null;
        try{
            objEmpleado=empleado.empleado_obtener_por_codigo(codigo);
            if (objEmpleado!=null){
               this.txtCodigo.setText(objEmpleado.getCodigo_emp());

                objCargo=cargo.cargo_obtener_por_codigo(objEmpleado.getCodigo_car());
                this.txtCodigoCargo.setText(objCargo.getCodigo_car());
                this.txtNombreCargo.setText(objCargo.getNombre_car());

                this.txtDNI.setText(objEmpleado.getDni_emp());
                this.txtNombre.setText(objEmpleado.getNombre_emp());
                this.txtApellidos.setText(objEmpleado.getApellidos_emp());
                this.CmbSexo.setSelectedItem(objEmpleado.getSexo_emp());
                this.txtFechaNacimiento.setDate(objEmpleado.getFecha_nacimiento_emp());
                this.txtDireccion.setText(objEmpleado.getDireccion_emp());
                this.txtTelefono.setText(objEmpleado.getTelefono_emp());
                this.txtCelular.setText(objEmpleado.getCelular_emp());
                this.txtEmail.setText(objEmpleado.getEmail_emp());

                if(objEmpleado.getFoto_emp()!=null)
                {
                    Icon icono = new ImageIcon(objEmpleado.getFoto_emp().getImagenFoto().getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
                    this.lblFoto.setIcon(icono);
                    this.repaint();
                }else{
                    this.Asignar_foto();
                }

                this.txtaObservacion.setText(objEmpleado.getObservacion_emp());

                this.Habilitar_campos_textos(false,true);
                this.Habilitar_botones(false, false, true, true, true, false,true,true);
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ObtenerCargo(String codigo)
  {
        Cargo objCargo=null;
        try{
            objCargo=cargo.cargo_obtener_por_codigo(codigo);
            if (objCargo!=null){
                this.txtCodigoCargo.setText(objCargo.getCodigo_car());
                this.txtNombreCargo.setText(objCargo.getNombre_car());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ListarEmpleado(){
        Empleado objEmpleado=null;
        List lista = null;
        String fila[];
        try {

            this.JrbNombre.setSelected(true);
            this.txtBuscar.setText("");

            lista=empleado.empleado_listar();

            DefaultTableModel modelo=(DefaultTableModel)this.JtEmpleado.getModel();
            while(this.JtEmpleado.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objEmpleado=(Empleado)lista.get(i);
                fila=new String[3];
                fila[0]=objEmpleado.getCodigo_emp();
                fila[1]=objEmpleado.getDni_emp();
                fila[2]=objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp();
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
                    lista=empleado.empleado_listar_por_codigo("");
                }
                else{
                    lista=empleado.empleado_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=empleado.empleado_listar_por_nombre("");
                }
                else{
                    lista=empleado.empleado_listar_por_nombre(nombre);
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
        Empleado objEmpleado=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtEmpleado.getModel();
            while(this.JtEmpleado.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objEmpleado=(Empleado)lista.get(i);
                fila=new String[3];
                fila[0]=objEmpleado.getCodigo_emp();
                fila[1]=objEmpleado.getDni_emp();
                fila[2]=objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp();
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
        txtDNI = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtEmpleado = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        btnAgregarFoto = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtCodigoCargo = new javax.swing.JTextField();
        txtNombreCargo = new javax.swing.JTextField();
        btnBuscarCargo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaObservacion = new javax.swing.JTextArea();
        CmbSexo = new javax.swing.JComboBox();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Empleado_Administrar.class);
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

        txtDNI.setName("txtDNI"); // NOI18N
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 140, -1));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 50, 45));

        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setToolTipText(resourceMap.getString("btnEditar.toolTipText")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 50, 45));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 50, 45));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JtEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "DNI", "Nombre y apellidos"
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
        JtEmpleado.setName("JtEmpleado"); // NOI18N
        JtEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtEmpleado);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 650, 90));

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
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 510, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 670, 150));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("lblFoto.border.lineColor"))); // NOI18N
        lblFoto.setName("lblFoto"); // NOI18N
        jPanel4.add(lblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 170));

        btnAgregarFoto.setIcon(resourceMap.getIcon("btnAgregarFoto.icon")); // NOI18N
        btnAgregarFoto.setName("btnAgregarFoto"); // NOI18N
        btnAgregarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFotoActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 35, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 290, 240));

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtCodigoCargo.setName("txtCodigoCargo"); // NOI18N
        jPanel1.add(txtCodigoCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 140, -1));

        txtNombreCargo.setName("txtNombreCargo"); // NOI18N
        jPanel1.add(txtNombreCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 250, -1));

        btnBuscarCargo.setIcon(resourceMap.getIcon("btnBuscarCargo.icon")); // NOI18N
        btnBuscarCargo.setName("btnBuscarCargo"); // NOI18N
        btnBuscarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCargoActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 50, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtaObservacion.setColumns(20);
        txtaObservacion.setRows(5);
        txtaObservacion.setName("txtaObservacion"); // NOI18N
        jScrollPane1.setViewportView(txtaObservacion);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 290, 90));

        CmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        CmbSexo.setName("CmbSexo"); // NOI18N
        jPanel1.add(CmbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 140, -1));

        txtFechaNacimiento.setDateFormatString(resourceMap.getString("txtFechaNacimiento.dateFormatString")); // NOI18N
        txtFechaNacimiento.setName("txtFechaNacimiento"); // NOI18N
        txtFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaNacimientoKeyTyped(evt);
            }
        });
        jPanel1.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 140, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtNombre.setName("txtNombre"); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 250, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtApellidos.setName("txtApellidos"); // NOI18N
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 250, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtDireccion.setName("txtDireccion"); // NOI18N
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 250, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txtTelefono.setName("txtTelefono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 90, -1));

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        txtCelular.setName("txtCelular"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 110, -1));

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        txtEmail.setName("txtEmail"); // NOI18N
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 250, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 530));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCargoActionPerformed
        Cargo_Buscar cargo=new Cargo_Buscar();
        buscarCargo=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), cargo, "Buscar cargos");
        cargo.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        cargo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarCargo);
        cargo.valor=1;
}//GEN-LAST:event_btnBuscarCargoActionPerformed

    private void txtFechaNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaNacimientoKeyTyped

}//GEN-LAST:event_txtFechaNacimientoKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true,true);
        this.Habilitar_botones(false, true, false, false, true, false, true, true);

        String Codigo=empleado.empleado_generar_codigo();
        this.txtCodigo.setText(Codigo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarEmpleado();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.EditarEmpleado();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.EliminarEmpleado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false,false);
        this.Habilitar_botones(true, false, false, false, false, true,false,false);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnAgregarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFotoActionPerformed
        File file;
        //creamos un objeto de JFILECHOOSER para que se abra una ventada de exploracion de archivos
        JFileChooser fileChooser = new JFileChooser();
        //Busque archivos con extension .jpg
        FileNameExtensionFilter tipoSeleccion = new FileNameExtensionFilter("Imagenes (JPG)", "jpg");
        fileChooser.addChoosableFileFilter(tipoSeleccion);
        //Abre la ventana de dialogo
        int seleccion = fileChooser.showOpenDialog(this);
          //Si hace click en el boton abrir del dialogo
          if (seleccion==JFileChooser.APPROVE_OPTION)
          {
            file=fileChooser.getSelectedFile();
            if(file==null);
            else
            {
                StringBuffer ruta=new StringBuffer(file.getPath());
                for(int i=0;i<file.getPath().length();i++)
                {
                    if(file.getPath().charAt(i)=='\\')
                    {
                        ruta=ruta.deleteCharAt(i);
                        ruta=ruta.insert(i, '/');
                    }
                }

                this.setFotoLabel(ruta.toString());

                //System.out.println("La ruta es: "+ruta.toString());
                //System.out.println("El file es: "+file.toString());

                imagen=new Imagen(null,file);
            }
          }
    }//GEN-LAST:event_btnAgregarFotoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtEmpleadoMouseClicked
        String ccodigo=(String)this.JtEmpleado.getValueAt(this.JtEmpleado.getSelectedRow(), 0);
        this.ObtenerEmpleado(ccodigo);
    }//GEN-LAST:event_JtEmpleadoMouseClicked

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtDNI, 8, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtDNI, evt);
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtTelefono, 9, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtTelefono, evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtCelular, 9, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtCelular, evt);
    }//GEN-LAST:event_txtCelularKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CmbSexo;
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtEmpleado;
    private javax.swing.JButton btnAgregarFoto;
    private javax.swing.JButton btnBuscarCargo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoCargo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCargo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtaObservacion;
    // End of variables declaration//GEN-END:variables

}
