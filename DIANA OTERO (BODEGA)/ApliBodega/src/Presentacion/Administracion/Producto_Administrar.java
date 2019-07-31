package Presentacion.Administracion;

import DatosAcceso.DaoCategoria;
import DatosAcceso.DaoMarca;
import DatosAcceso.DaoProducto;
import DatosAcceso.DaoProveedor;
import DatosAcceso.DaoUnidadMedida;
import LogicaGetSet.Categoria;
import LogicaGetSet.Imagen;
import LogicaGetSet.Marca;
import LogicaGetSet.Producto;
import LogicaGetSet.Proveedor;
import LogicaGetSet.UnidadMedida;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.JP_Modelo;
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

public class Producto_Administrar extends JP_Modelo {

    JInternalFrame buscar,buscarProveedor,buscarCategoria,buscarMarca,buscarUnidadMedida;
    
    Imagen imagen=null;

    DaoProducto producto=new DaoProducto();
    DaoProveedor proveedor=new DaoProveedor();
    DaoCategoria categoria=new DaoCategoria();
    DaoMarca marca=new DaoMarca();
    DaoUnidadMedida unidad=new DaoUnidadMedida();

    String vCodigo,vCodigoProv,vCodigoCat,vCodigoMar,vCodigoUni,vNombre,vStockMax,vStockMin,vPrecioCom,vPrecioVen,vObservacion;

    String codigo="", nombre="";

    public Producto_Administrar() {
        initComponents();

        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true,false,false,false,false,false);

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);
        this.JrbNombre.setSelected(true);

        this.Formato_tabla();
        this.Actualizar_busqueda();

        this.Asignar_foto();
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vCodigoProv=this.txtCodigoProveedor.getText().trim();
        vCodigoCat=this.txtCodigoCategoria.getText().trim();
        vCodigoMar=this.txtCodigoMarca.getText().trim();
        vCodigoUni=this.txtCodigoUnidadMedida.getText().trim();
        vNombre=this.txtNombre.getText().trim();
        vStockMax=this.txtStockMax.getText().trim();
        vStockMin=this.txtStockMin.getText().trim();
        vPrecioCom=this.txtPrecioCompra.getText().trim();
        vPrecioVen=this.txtPrecioVenta.getText().trim();
        vObservacion=this.txtaObservacion.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtCodigoProveedor.setText("");
        this.txtNombreProveedor.setText("");
        this.txtCodigoCategoria.setText("");
        this.txtNombreCategoria.setText("");
        this.txtCodigoMarca.setText("");
        this.txtNombreMarca.setText("");
        this.txtCodigoUnidadMedida.setText("");
        this.txtNombreUnidadMedida.setText("");
        this.txtNombre.setText("");
        this.txtStockMax.setText("");
        this.txtStockMin.setText("");
        this.txtPrecioCompra.setText("");
        this.txtPrecioVenta.setText("");
        this.txtaObservacion.setText("");

        this.Asignar_foto();

        this.txtNombre.requestFocus();
    }

   public void Habilitar_campos_textos(boolean a)
   {
        this.txtCodigo.setEnabled(false);
        this.txtCodigoProveedor.setEnabled(false);
        this.txtNombreProveedor.setEnabled(false);
        this.txtCodigoCategoria.setEnabled(false);
        this.txtNombreCategoria.setEnabled(false);
        this.txtCodigoMarca.setEnabled(false);
        this.txtNombreMarca.setEnabled(false);
        this.txtCodigoUnidadMedida.setEnabled(false);
        this.txtNombreUnidadMedida.setEnabled(false);
        this.txtNombre.setEnabled(a);
        this.txtStockMax.setEnabled(a);
        this.txtStockMin.setEnabled(a);
        this.txtPrecioCompra.setEnabled(a);
        this.txtPrecioVenta.setEnabled(a);
        this.txtaObservacion.setEnabled(a);
   }

  public void Habilitar_botones(boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean k,boolean l,boolean m,boolean n)
  {
      this.btnNuevo.setEnabled(b);
      this.btnRegistrar.setEnabled(c);
      this.btnEditar.setEnabled(d);
      this.btnEliminar.setEnabled(e);
      this.btnCancelar.setEnabled(f);
      this.btnCerrar.setEnabled(g);
      this.btnAgregarFoto.setEnabled(h);
      this.btnBuscarProveedor.setEnabled(k);
      this.btnBuscarCategoria.setEnabled(l);
      this.btnBuscarMarca.setEnabled(m);
      this.btnBuscarUnidadMedida.setEnabled(n);
  }

  public void Formato_tabla(){
        this.JtProducto.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtProducto.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.JtProducto.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.JtProducto.getColumnModel().getColumn(3).setPreferredWidth(50);
  }

  public void setFotoLabel(String ruta)
  {
      ImageIcon image=new ImageIcon(ruta);
      Icon icono = new ImageIcon(image.getImage().getScaledInstance( 200, 200, Image.SCALE_DEFAULT));
      this.lblFoto.setIcon(icono);
      this.repaint();
  }

  public void Asignar_foto()
  {
      String path = "/Images/foto_defecto.png";
      URL url = this.getClass().getResource(path);
      ImageIcon image=new ImageIcon(url);
      Icon icono = new ImageIcon(image.getImage().getScaledInstance( 200, 200, Image.SCALE_DEFAULT));
      this.lblFoto.setIcon(icono);
      this.repaint();

      if(imagen!=null)
      {
        imagen=null;
      }
  }

  public void RegistrarProducto()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoProv.isEmpty() || vCodigoCat.isEmpty() || vCodigoMar.isEmpty() || vCodigoUni.isEmpty() || vNombre.isEmpty() || vStockMax.isEmpty() || vStockMin.isEmpty() || vPrecioCom.isEmpty() || vPrecioVen.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de producto?","Pregunta",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Producto objProducto=null;
                try {
                    objProducto=new Producto();
                    objProducto.setCodigo_pro(this.txtCodigo.getText().trim());
                    objProducto.setCodigo_prov(this.txtCodigoProveedor.getText().trim());
                    objProducto.setCodigo_cat(this.txtCodigoCategoria.getText().trim());
                    objProducto.setCodigo_mar(this.txtCodigoMarca.getText().trim());
                    objProducto.setCodigo_uni(this.txtCodigoUnidadMedida.getText().trim());
                    objProducto.setNombre_pro(this.txtNombre.getText().trim());
                    objProducto.setStock_max_pro(Integer.valueOf(this.txtStockMax.getText().trim()));
                    objProducto.setStock_min_pro(Integer.valueOf(this.txtStockMin.getText().trim()));
                    objProducto.setPrecio_compra_pro(Double.valueOf(this.txtPrecioCompra.getText().trim()));
                    objProducto.setPrecio_venta_pro(Double.valueOf(this.txtPrecioVenta.getText().trim()));

                    if(imagen!=null)
                    {
                        objProducto.setFoto_pro(imagen);
                    }

                    objProducto.setObservacion_pro(this.txtaObservacion.getText().trim());

                    if(producto.producto_registrar(objProducto)==true){
                        this.ListarProducto();
                        JOptionPane.showMessageDialog(this,"Regístro guardado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no registrados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }

                this.Limpiar_campos_texto();
                this.Habilitar_campos_textos(false);
                this.Habilitar_botones(true, false, false, false, false, true,false,false,false,false,false);
            }
        }
    }

  public void EditarProducto()
    {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoProv.isEmpty() || vCodigoCat.isEmpty() || vCodigoMar.isEmpty() || vCodigoUni.isEmpty() || vNombre.isEmpty() || vStockMax.isEmpty() || vStockMin.isEmpty() || vPrecioCom.isEmpty() || vPrecioVen.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{
            int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de editar datos de producto?","Pregunta",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.OK_OPTION)
            {
                Producto objProducto=null;
                try {
                    objProducto=new Producto();
                    objProducto.setCodigo_pro(this.txtCodigo.getText().trim());
                    objProducto.setCodigo_prov(this.txtCodigoProveedor.getText().trim());
                    objProducto.setCodigo_cat(this.txtCodigoCategoria.getText().trim());
                    objProducto.setCodigo_mar(this.txtCodigoMarca.getText().trim());
                    objProducto.setCodigo_uni(this.txtCodigoUnidadMedida.getText().trim());
                    objProducto.setNombre_pro(this.txtNombre.getText().trim());
                    objProducto.setStock_max_pro(Integer.valueOf(this.txtStockMax.getText().trim()));
                    objProducto.setStock_min_pro(Integer.valueOf(this.txtStockMin.getText().trim()));
                    objProducto.setPrecio_compra_pro(Double.valueOf(this.txtPrecioCompra.getText().trim()));
                    objProducto.setPrecio_venta_pro(Double.valueOf(this.txtPrecioVenta.getText().trim()));

                    if(imagen!=null)
                    {
                        objProducto.setFoto_pro(imagen);
                    }

                    objProducto.setObservacion_pro(this.txtaObservacion.getText().trim());

                    if(producto.producto_editar(objProducto)==true){
                        this.ListarProducto();
                        JOptionPane.showMessageDialog(this,"Regístro actualizado con exito");
                    }else{
                        JOptionPane.showMessageDialog(this,"Error datos no actualizados");
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }

                this.Limpiar_campos_texto();
                this.Habilitar_campos_textos(false);
                this.Habilitar_botones(true, false, false, false, false, true,false,false,false,false,false);
            }
        }
    }

  public void EliminarProducto()
    {
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de eliminar datos de producto?","Pregunta",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            try
            {
                String codigo=this.txtCodigo.getText();
                if(producto.producto_eliminar(codigo)==true){
                    this.ListarProducto();
                    JOptionPane.showMessageDialog(this, "Registro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "Error datos no eliminados");
                }
            }catch (Exception ex) {
               ex.printStackTrace();
            }

            this.Limpiar_campos_texto();
            this.Habilitar_campos_textos(false);
            this.Habilitar_botones(true, false, false, false, false, true,false,false,false,false,false);
        }
    }

  public void ObtenerProducto(String codigo)
    {
        Producto objProducto=null;
        Proveedor objProveedor=null;
        Categoria objCategoria=null;
        Marca objMarca=null;
        UnidadMedida objUnidad=null;
        try{
            objProducto=producto.producto_obtener_por_codigo(codigo);
            if (objProducto!=null){
                this.txtCodigo.setText(objProducto.getCodigo_pro());

                objProveedor=proveedor.proveedor_obtener_por_codigo(objProducto.getCodigo_prov());
                this.txtCodigoProveedor.setText(objProveedor.getCodigo_prov());
                this.txtNombreProveedor.setText(objProveedor.getRazon_social_prov());

                objCategoria=categoria.categoria_obtener_por_codigo(objProducto.getCodigo_cat());
                this.txtCodigoCategoria.setText(objCategoria.getCodigo_cat());
                this.txtNombreCategoria.setText(objCategoria.getNombre_cat());

                objMarca=marca.marca_obtener_por_codigo(objProducto.getCodigo_mar());
                this.txtCodigoMarca.setText(objMarca.getCodigo_mar());
                this.txtNombreMarca.setText(objMarca.getNombre_mar());

                objUnidad=unidad.unidadMedida_obtener_por_codigo(objProducto.getCodigo_uni());
                this.txtCodigoUnidadMedida.setText(objUnidad.getCodigo_uni());
                this.txtNombreUnidadMedida.setText(objUnidad.getNombre_uni());

                this.txtNombre.setText(objProducto.getNombre_pro());
                this.txtStockMax.setText(String.valueOf(objProducto.getStock_max_pro()));
                this.txtStockMin.setText(String.valueOf(objProducto.getStock_min_pro()));
                this.txtPrecioCompra.setText(String.valueOf(objProducto.getPrecio_compra_pro()));
                this.txtPrecioVenta.setText(String.valueOf(objProducto.getPrecio_venta_pro()));

                if(objProducto.getFoto_pro()!=null)
                {
                    Icon icono = new ImageIcon(objProducto.getFoto_pro().getImagenFoto().getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                    this.lblFoto.setIcon(icono);
                    this.repaint();
                }else{
                    this.Asignar_foto();
                }

                this.txtaObservacion.setText(objProducto.getObservacion_pro());

                this.Habilitar_campos_textos(true);
                this.Habilitar_botones(false, false, true, true, true, false,true,true,true,true,true);
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ObtenerProveedor(String codigo)
  {
        Proveedor objProveedor=null;
        try{
            objProveedor=proveedor.proveedor_obtener_por_codigo(codigo);
            if (objProveedor!=null){
                this.txtCodigoProveedor.setText(objProveedor.getCodigo_prov());
                this.txtNombreProveedor.setText(objProveedor.getRazon_social_prov());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ObtenerCategoria(String codigo)
  {
        Categoria objCategoria=null;
        try{
            objCategoria=categoria.categoria_obtener_por_codigo(codigo);
            if (objCategoria!=null){
                this.txtCodigoCategoria.setText(objCategoria.getCodigo_cat());
                this.txtNombreCategoria.setText(objCategoria.getNombre_cat());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ObtenerMarca(String codigo)
  {
        Marca objMarca=null;
        try{
            objMarca=marca.marca_obtener_por_codigo(codigo);
            if (objMarca!=null){
                this.txtCodigoMarca.setText(objMarca.getCodigo_mar());
                this.txtNombreMarca.setText(objMarca.getNombre_mar());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

  public void ObtenerUnidadMedida(String codigo)
  {
        UnidadMedida objUnidad=null;
        try{
            objUnidad=unidad.unidadMedida_obtener_por_codigo(codigo);
            if (objUnidad!=null){
                this.txtCodigoUnidadMedida.setText(objUnidad.getCodigo_uni());
                this.txtNombreUnidadMedida.setText(objUnidad.getNombre_uni());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ListarProducto(){
        Producto objProducto=null;
        List lista = null;
        String fila[];
        try {

            this.JrbNombre.setSelected(true);
            this.txtBuscar.setText("");

            lista=producto.producto_listar();

            DefaultTableModel modelo=(DefaultTableModel)this.JtProducto.getModel();
            while(this.JtProducto.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objProducto=(Producto)lista.get(i);
                fila=new String[4];
                fila[0]=objProducto.getCodigo_pro();
                fila[1]=objProducto.getNombre_pro();
                fila[2]=String.valueOf(objProducto.getStock_max_pro());
                fila[3]=String.valueOf(objProducto.getPrecio_venta_pro());
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
                    lista=producto.producto_listar_por_codigo("");
                }
                else{
                    lista=producto.producto_listar_por_codigo(codigo);
                }
            }else if(this.JrbNombre.isSelected())
            {
                nombre=this.txtBuscar.getText().trim();
                if(nombre.compareTo("")==0){
                    lista=producto.producto_listar_por_nombre("");
                }
                else{
                    lista=producto.producto_listar_por_nombre(nombre);
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
        Producto objProducto=null;
        String fila[];
        try {
            DefaultTableModel modelo=(DefaultTableModel)this.JtProducto.getModel();
            while(this.JtProducto.getRowCount()>0){
                modelo.removeRow(0);
            }
            for(int i=0;i<lista.size();i++){
                objProducto=(Producto)lista.get(i);
                fila=new String[4];
                fila[0]=objProducto.getCodigo_pro();
                fila[1]=objProducto.getNombre_pro();
                fila[2]=String.valueOf(objProducto.getStock_max_pro());
                fila[3]=String.valueOf(objProducto.getPrecio_venta_pro());
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
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtProducto = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStockMax = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStockMin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        btnAgregarFoto = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoProveedor = new javax.swing.JTextField();
        btnBuscarProveedor = new javax.swing.JButton();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCodigoCategoria = new javax.swing.JTextField();
        btnBuscarCategoria = new javax.swing.JButton();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCodigoMarca = new javax.swing.JTextField();
        txtNombreMarca = new javax.swing.JTextField();
        btnBuscarMarca = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtCodigoUnidadMedida = new javax.swing.JTextField();
        txtNombreUnidadMedida = new javax.swing.JTextField();
        btnBuscarUnidadMedida = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaObservacion = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Producto_Administrar.class);
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
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 250, -1));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 50, 45));

        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setToolTipText(resourceMap.getString("btnEditar.toolTipText")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 50, 45));

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setToolTipText(resourceMap.getString("btnEliminar.toolTipText")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 50, 45));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JtProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Stock", "Precio"
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
        JtProducto.setName("JtProducto"); // NOI18N
        JtProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtProductoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtProducto);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 650, 80));

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 670, 140));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtStockMax.setName("txtStockMax"); // NOI18N
        txtStockMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMaxKeyTyped(evt);
            }
        });
        jPanel1.add(txtStockMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 90, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        txtStockMin.setName("txtStockMin"); // NOI18N
        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMinKeyTyped(evt);
            }
        });
        jPanel1.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 100, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtPrecioCompra.setName("txtPrecioCompra"); // NOI18N
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 90, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        txtPrecioVenta.setName("txtPrecioVenta"); // NOI18N
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 100, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("lblFoto.border.lineColor"))); // NOI18N
        lblFoto.setName("lblFoto"); // NOI18N
        jPanel4.add(lblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 200));

        btnAgregarFoto.setIcon(resourceMap.getIcon("btnAgregarFoto.icon")); // NOI18N
        btnAgregarFoto.setName("btnAgregarFoto"); // NOI18N
        btnAgregarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFotoActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 35, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 290, 270));

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtCodigoProveedor.setName("txtCodigoProveedor"); // NOI18N
        jPanel1.add(txtCodigoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 140, -1));

        btnBuscarProveedor.setIcon(resourceMap.getIcon("btnBuscarProveedor.icon")); // NOI18N
        btnBuscarProveedor.setName("btnBuscarProveedor"); // NOI18N
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 50, -1));

        txtNombreProveedor.setName("txtNombreProveedor"); // NOI18N
        jPanel1.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 250, -1));

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtCodigoCategoria.setName("txtCodigoCategoria"); // NOI18N
        jPanel1.add(txtCodigoCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 140, -1));

        btnBuscarCategoria.setIcon(resourceMap.getIcon("btnBuscarCategoria.icon")); // NOI18N
        btnBuscarCategoria.setName("btnBuscarCategoria"); // NOI18N
        btnBuscarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 50, -1));

        txtNombreCategoria.setName("txtNombreCategoria"); // NOI18N
        jPanel1.add(txtNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 250, -1));

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        txtCodigoMarca.setName("txtCodigoMarca"); // NOI18N
        jPanel1.add(txtCodigoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 140, -1));

        txtNombreMarca.setName("txtNombreMarca"); // NOI18N
        jPanel1.add(txtNombreMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 250, -1));

        btnBuscarMarca.setIcon(resourceMap.getIcon("btnBuscarMarca.icon")); // NOI18N
        btnBuscarMarca.setName("btnBuscarMarca"); // NOI18N
        btnBuscarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMarcaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 50, -1));

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        txtCodigoUnidadMedida.setName("txtCodigoUnidadMedida"); // NOI18N
        jPanel1.add(txtCodigoUnidadMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 140, -1));

        txtNombreUnidadMedida.setName("txtNombreUnidadMedida"); // NOI18N
        jPanel1.add(txtNombreUnidadMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 250, -1));

        btnBuscarUnidadMedida.setIcon(resourceMap.getIcon("btnBuscarUnidadMedida.icon")); // NOI18N
        btnBuscarUnidadMedida.setName("btnBuscarUnidadMedida"); // NOI18N
        btnBuscarUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUnidadMedidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarUnidadMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 50, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtaObservacion.setColumns(20);
        txtaObservacion.setRows(5);
        txtaObservacion.setName("txtaObservacion"); // NOI18N
        jScrollPane1.setViewportView(txtaObservacion);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 290, 90));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 540));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
        Proveedor_Buscar Proveedor=new Proveedor_Buscar();
        buscarProveedor=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), Proveedor, "Buscar proveedores");
        Proveedor.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        Proveedor.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarProveedor);
        Proveedor.valor=1;
}//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void btnBuscarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCategoriaActionPerformed
        Categoria_Buscar Categoria=new Categoria_Buscar();
        buscarCategoria=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), Categoria, "Buscar categorias");
        Categoria.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        Categoria.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarCategoria);
        Categoria.valor=1;
}//GEN-LAST:event_btnBuscarCategoriaActionPerformed

    private void btnBuscarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMarcaActionPerformed
        Marca_Buscar marca=new Marca_Buscar();
        buscarMarca=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), marca, "Buscar marcas");
        marca.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        marca.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarMarca);
        marca.valor=1;
}//GEN-LAST:event_btnBuscarMarcaActionPerformed

    private void btnBuscarUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUnidadMedidaActionPerformed
        UnidadMedida_Buscar unidadMedida=new UnidadMedida_Buscar();
        buscarUnidadMedida=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), unidadMedida, "Buscar unidad de medida");
        unidadMedida.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        unidadMedida.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarUnidadMedida);
        unidadMedida.valor=1;
}//GEN-LAST:event_btnBuscarUnidadMedidaActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true);
        this.Habilitar_botones(false, true, false, false, true, false, true, true, true,true,true);

        String Codigo=producto.producto_generar_codigo();
        this.txtCodigo.setText(Codigo);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarProducto();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.EditarProducto();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.EliminarProducto();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, false, false, true, false, false, false,false,false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtProductoMouseClicked
        String ccodigo=(String)this.JtProducto.getValueAt(this.JtProducto.getSelectedRow(), 0);
        this.ObtenerProducto(ccodigo);
    }//GEN-LAST:event_JtProductoMouseClicked

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

    private void txtStockMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMaxKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtStockMax, evt);
    }//GEN-LAST:event_txtStockMaxKeyTyped

    private void txtStockMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtStockMin, evt);
    }//GEN-LAST:event_txtStockMinKeyTyped

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnterosDecimales(txtPrecioCompra, evt);
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnterosDecimales(txtPrecioVenta, evt);
    }//GEN-LAST:event_txtPrecioVentaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtProducto;
    private javax.swing.JButton btnAgregarFoto;
    private javax.swing.JButton btnBuscarCategoria;
    private javax.swing.JButton btnBuscarMarca;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnBuscarUnidadMedida;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoCategoria;
    private javax.swing.JTextField txtCodigoMarca;
    private javax.swing.JTextField txtCodigoProveedor;
    private javax.swing.JTextField txtCodigoUnidadMedida;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtNombreMarca;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreUnidadMedida;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStockMax;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextArea txtaObservacion;
    // End of variables declaration//GEN-END:variables

}
