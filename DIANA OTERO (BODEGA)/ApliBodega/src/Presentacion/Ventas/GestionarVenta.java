package Presentacion.Ventas;

import DatosAcceso.DaoBoleta;
import DatosAcceso.DaoCliente;
import DatosAcceso.DaoDetalleBoleta;
import DatosAcceso.DaoProducto;
import DatosAcceso.DaoReporte;
import DatosAcceso.DaoUsuario;
import LogicaGetSet.Boleta;
import LogicaGetSet.Cliente;
import LogicaGetSet.DetalleBoleta;
import LogicaGetSet.Producto;
import Presentacion.Administracion.Cliente_Buscar;
import Presentacion.Administracion.Cliente_Nuevo;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.JP_Modelo;
import Utiles.Utiles_Panel;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.FrameView;

public class GestionarVenta extends JP_Modelo {

    JInternalFrame buscar,buscarCliente,nuevoCliente;

    DaoBoleta boleta=new DaoBoleta();
    DaoDetalleBoleta detalleBoleta=new DaoDetalleBoleta();
    DaoProducto producto=new DaoProducto();
    DaoCliente cliente=new DaoCliente();
    DaoUsuario usuario=new DaoUsuario();
    DaoReporte REPORTE=new DaoReporte();

    String vCodigo,vCodigoCli;

    String codigo="", nombre="";

    public GestionarVenta() {
        initComponents();

        this.buttonGroup1.add(this.JrbCodigo);
        this.buttonGroup1.add(this.JrbNombre);

        this.Formato_tabla();
        this.Formato_tabla_venta();

        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, true, false, false, false);
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vCodigoCli=this.txtCodigoCliente.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtCodigo.setText("");
        this.txtCodigoCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txtFechaVenta.setDate(null);
        this.txtTotalPagar.setText("");
        this.txtBuscar.setText("");

        if(this.JtProducto.getRowCount()>0){
            int limite1 = this.JtProducto.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
            DefaultTableModel modelo1=(DefaultTableModel)this.JtProducto.getModel();
            for (int i = 0; i < limite1; i++) {
                modelo1.removeRow(0);
            }
        }

        if(this.JtVenta.getRowCount()>0){
            int limite2 = this.JtVenta.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
            DefaultTableModel modelo2=(DefaultTableModel)this.JtVenta.getModel();
            for (int j = 0; j < limite2; j++) {
                modelo2.removeRow(0);
            }
        }
    }

    public void Limpiar_campos_producto()
    {
        this.txtCodigoProducto.setText("");
        this.txtNombreProducto.setText("");
        this.txtStock.setText("");
        this.txtPrecio.setText("");
        this.txtCantidad.setText("");
    }

    public void Habilitar_campos_textos(boolean a)
   {
        this.txtCodigo.setEnabled(false);
        this.txtCodigoCliente.setEnabled(false);
        this.txtNombreCliente.setEnabled(false);
        this.txtCodigoProducto.setEnabled(false);
        this.txtNombreProducto.setEnabled(false);
        this.txtStock.setEnabled(false);
        this.txtPrecio.setEnabled(false);
        this.txtCantidad.setEnabled(false);
        this.txtTotalPagar.setEnabled(false);
        this.txtFechaVenta.setEnabled(a);
        this.txtBuscar.setEnabled(a);
        this.JrbCodigo.setEnabled(a);
        this.JrbNombre.setEnabled(a);
   }

    public void Habilitar_botones(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean h)
  {
      this.btnNuevo.setEnabled(a);
      this.btnRegistrar.setEnabled(b);
      this.btnCancelar.setEnabled(c);
      this.btnCerrar.setEnabled(d);
      this.btnBuscarCliente.setEnabled(e);
      this.btnNuevoCliente.setEnabled(f);
      this.btnAgregarArticulo.setEnabled(false);
      this.btnCancelarArticulo.setEnabled(false);
      this.btnEliminarArticulo.setEnabled(h);
  }

    public void Formato_tabla(){
        this.JtProducto.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtProducto.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.JtProducto.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.JtProducto.getColumnModel().getColumn(3).setPreferredWidth(50);
  }

    public void Formato_tabla_venta(){
        this.JtVenta.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JtVenta.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.JtVenta.getColumnModel().getColumn(2).setPreferredWidth(25);
        this.JtVenta.getColumnModel().getColumn(3).setPreferredWidth(25);
        this.JtVenta.getColumnModel().getColumn(4).setPreferredWidth(50);
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

    public int Actualizar_stock(String codigoArt,String stock) {
        int StockSobrante=0;
        if(this.JtVenta.getRowCount()>0){
            int StockRegistrado=0;
            boolean respuesta=false;
            int fila = this.JtVenta.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
            for (int f = 0; f < fila; f++) {
                if (codigoArt.equals(String.valueOf(modelo.getValueAt(f,0)))){
                    StockRegistrado=StockRegistrado+Integer.parseInt(String.valueOf(modelo.getValueAt(f,3)));
                    respuesta=true;
                }
            }
            if(respuesta==true){
                StockSobrante=Integer.parseInt(stock)- StockRegistrado;
            }else{
                 StockSobrante=Integer.parseInt(stock);
            }
        }else{
            StockSobrante=Integer.parseInt(stock);
        }

        return StockSobrante;
    }

    public void Validar_cantidad_vender(){
        if(this.txtCantidad.getText().trim().equals("")){
                this.txtCantidad.setText("");
        }else{
           int vStock=Integer.parseInt(this.txtStock.getText().trim());
           int vCantidad=Integer.parseInt(this.txtCantidad.getText().trim());

           if(vCantidad>vStock){
                JOptionPane.showMessageDialog(this, "No existe cantidad sufiente de articulos");
                this.txtCantidad.setText("");
           }else{
                if(vCantidad==0){
                    JOptionPane.showMessageDialog(this, "Como mínimo debe ingresar 1 articulo");
                    this.txtCantidad.setText("");
                }
           }
        }
    }

    public boolean Verificar_articulo_registrado() {
        boolean valor = false;
        if(this.JtVenta.getRowCount()>0){
            int fila = this.JtVenta.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
            for (int f = 0; f < fila; f++) {
                if (this.txtCodigoProducto.getText().trim().equals(String.valueOf(modelo.getValueAt(f,0)))){
                    valor = true;
                }
            }
        }
        return valor;
    }

    public void Actualizar_cantidad_registrada(String codigo,String cantidad) {
        int SumaActualizada=0;
        double importe=0;
        int fila = this.JtVenta.getRowCount();
        DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
        for (int f = 0; f < fila; f++) {
           if (codigo.equals(String.valueOf(modelo.getValueAt(f,0)))){
               SumaActualizada=Integer.parseInt(String.valueOf(cantidad))+Integer.parseInt(String.valueOf(modelo.getValueAt(f,3)));
               modelo.setValueAt(String.valueOf(SumaActualizada),f,3);

               importe=Double.parseDouble(String.valueOf(modelo.getValueAt(f,2))) * Integer.parseInt(String.valueOf(modelo.getValueAt(f,3)));
               modelo.setValueAt(String.valueOf(importe),f,4);
           }
        }
    }

    public void Calcular_total_pagar() {
        if(this.JtVenta.getRowCount()>0){
            double total_pagar=0;
            int fila = this.JtVenta.getRowCount();
            DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
            for (int f = 0; f < fila; f++) {
                total_pagar=total_pagar+Double.parseDouble(String.valueOf(modelo.getValueAt(f,4)));
            }

            this.txtTotalPagar.setText(String.valueOf(total_pagar));

        }else{
            this.txtTotalPagar.setText("");
        }
    }

    public void Gestionar_venta(){
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vCodigoCli.isEmpty() || this.txtFechaVenta.getDate()==null){
            JOptionPane.showMessageDialog(this,"Porfavor llenar los campos necesarios");
        }else{
            if(this.JtVenta.getRowCount()>0){
                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de gestionar venta?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    boolean respuesta1=false;
                    boolean respuesta2=false;
                    boolean respuesta3=false;
                    Boleta objBoleta=null;
                    DetalleBoleta objDetalleBoleta=null;
                    try {
                        //PARA LA TABLA PEDIDO DE PRESTAMO
                        objBoleta=new Boleta();
                        objBoleta.setCodigo_bol(this.txtCodigo.getText().trim());
                        objBoleta.setCodigo_emp(usuario.CODEmpleado);
                        objBoleta.setCodigo_cli(this.txtCodigoCliente.getText().trim());
                        objBoleta.setFecha_bol(this.txtFechaVenta.getDate());

                        if(boleta.boleta_registrar(objBoleta)==true){
                            respuesta1=true;

                            //PARA LA TABLA DETALLE DE PRESTAMO
                            objDetalleBoleta=new DetalleBoleta();
                            int limite = this.JtVenta.getRowCount();//CAPTURAMOS LA CANTIDAD DE FILAS QUE TIENE LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ
                            DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
                            for (int f = 0; f < limite; f++) {  //RACORREMOS LA TABLA DETALLE DE ARTICULOS DE LA INTERFAZ, PARA IR REGISTRANDO UNO POR UNO AL TABLA DETALLEPEDIDO DE LA BD

                                objDetalleBoleta.setCodigo_bol(this.txtCodigo.getText().trim());
                                objDetalleBoleta.setCodigo_pro(String.valueOf(modelo.getValueAt(f,0)));
                                objDetalleBoleta.setPrecio_bol(Double.parseDouble(String.valueOf(modelo.getValueAt(f,2))));
                                objDetalleBoleta.setCantidad_bol(Integer.parseInt(String.valueOf(modelo.getValueAt(f,3))));

                                if(detalleBoleta.detalleBoleta_registrar(objDetalleBoleta)==true){
                                    respuesta2=true;

                                    //POR ARTICULO PRESTADO SE DEBE REALIZAR UNA ACTUALIZACION DE LA CANTIDAD EXISTEN EN AL TABLA ARTICULO DE LA BD
                                    if(producto.producto_actulizar_stock(String.valueOf(modelo.getValueAt(f,0)),Integer.parseInt(String.valueOf(modelo.getValueAt(f,3))))==true){
                                        respuesta3=true;
                                    }else{
                                        JOptionPane.showMessageDialog(this, "Error al actualizar stock de producto");
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(this, "Error al registrar detalle boleta");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "Error al registrar boleta");
                        }

                        if(respuesta1==true && respuesta2==true && respuesta3==true){
                            //IMPRESION DE REPORTE
                            int RespVenta=JOptionPane.showConfirmDialog(null,"La venta se gestiono con exito, ¿Desea generar boleta?","VENTA",JOptionPane.YES_NO_OPTION);
                            if(RespVenta==JOptionPane.OK_OPTION)
                            {
                                String prCodigoBol=this.txtCodigo.getText().trim();
                                String prFecha=this.txtFechaVenta.getDate().toString();
                                String prNombreCli=this.txtNombreCliente.getText().trim();
                                String prTotalPagar=this.txtTotalPagar.getText().trim();

                                REPORTE.Reporte_generar_boleta(prCodigoBol, prFecha, prNombreCli, prTotalPagar);
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "Error venta no gestionada");
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                    this.Limpiar_campos_producto();
                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(false);
                    this.Habilitar_botones(true, false, false, true, false, false, false);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay productos en la lista detalle");
            }
        }
    }

    public void ObtenerProducto(String codigo)
    {
        Producto objProducto=null;
        try{
            objProducto=producto.producto_obtener_por_codigo(codigo);
            if (objProducto!=null){
                this.txtCodigoProducto.setText(objProducto.getCodigo_pro());
                this.txtNombreProducto.setText(objProducto.getNombre_pro());

                String cCodigoArt=String.valueOf(objProducto.getCodigo_pro());
                String cStockArt=String.valueOf(objProducto.getStock_max_pro());

                this.txtStock.setText(String.valueOf(this.Actualizar_stock(cCodigoArt, cStockArt)));

                this.txtPrecio.setText(String.valueOf(objProducto.getPrecio_venta_pro()));

                this.txtCantidad.setEnabled(true);
                this.btnAgregarArticulo.setEnabled(true);
                this.btnCancelarArticulo.setEnabled(true);
                this.txtCantidad.requestFocus();

            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ObtenerCliente(String codigo)
    {
        Cliente objCliente=null;
        try{
            objCliente=cliente.cliente_obtener_por_codigo(codigo);
            if (objCliente!=null){
                this.txtCodigoCliente.setText(objCliente.getCodigo_cli());
                this.txtNombreCliente.setText(objCliente.getRazon_social_cli());
            }else{
                JOptionPane.showMessageDialog(this, "Error datos no se pudieron obtener");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtProducto = new javax.swing.JTable();
        JrbNombre = new javax.swing.JRadioButton();
        JrbCodigo = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtFechaVenta = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAgregarArticulo = new javax.swing.JButton();
        btnCancelarArticulo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        btnEliminarArticulo = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(GestionarVenta.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 570, 90));

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
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 430, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 590, 150));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtCodigo.setName("txtCodigo"); // NOI18N
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 130, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        txtNombreCliente.setName("txtNombreCliente"); // NOI18N
        jPanel3.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 310, -1));

        txtCodigoCliente.setName("txtCodigoCliente"); // NOI18N
        jPanel3.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 130, -1));

        btnBuscarCliente.setIcon(resourceMap.getIcon("btnBuscarCliente.icon")); // NOI18N
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 30, -1));

        btnNuevoCliente.setIcon(resourceMap.getIcon("btnNuevoCliente.icon")); // NOI18N
        btnNuevoCliente.setToolTipText(resourceMap.getString("btnNuevoCliente.toolTipText")); // NOI18N
        btnNuevoCliente.setName("btnNuevoCliente"); // NOI18N
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 30, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtFechaVenta.setDateFormatString(resourceMap.getString("txtFechaVenta.dateFormatString")); // NOI18N
        txtFechaVenta.setName("txtFechaVenta"); // NOI18N
        txtFechaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaVentaKeyTyped(evt);
            }
        });
        jPanel3.add(txtFechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 130, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 90));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtCodigoProducto.setName("txtCodigoProducto"); // NOI18N
        jPanel4.add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 90, -1));

        txtCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("txtCantidad.border.lineColor"))); // NOI18N
        txtCantidad.setName("txtCantidad"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel4.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 90, 20));

        txtNombreProducto.setName("txtNombreProducto"); // NOI18N
        jPanel4.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, -1));

        txtStock.setName("txtStock"); // NOI18N
        jPanel4.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 90, -1));

        txtPrecio.setName("txtPrecio"); // NOI18N
        jPanel4.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 90, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        btnAgregarArticulo.setIcon(resourceMap.getIcon("btnAgregarArticulo.icon")); // NOI18N
        btnAgregarArticulo.setToolTipText(resourceMap.getString("btnAgregarArticulo.toolTipText")); // NOI18N
        btnAgregarArticulo.setName("btnAgregarArticulo"); // NOI18N
        btnAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarArticuloActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 40, 40));

        btnCancelarArticulo.setIcon(resourceMap.getIcon("btnCancelarArticulo.icon")); // NOI18N
        btnCancelarArticulo.setToolTipText(resourceMap.getString("btnCancelarArticulo.toolTipText")); // NOI18N
        btnCancelarArticulo.setName("btnCancelarArticulo"); // NOI18N
        btnCancelarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarArticuloActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 160, 220));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        JtVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Precio", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtVenta.setName("JtVenta"); // NOI18N
        jScrollPane1.setViewportView(JtVenta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 420, 140));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, -1, -1));

        txtTotalPagar.setBorder(javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("txtTotalPagar.border.lineColor"))); // NOI18N
        txtTotalPagar.setName("txtTotalPagar"); // NOI18N
        jPanel1.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 100, 20));

        btnEliminarArticulo.setIcon(resourceMap.getIcon("btnEliminarArticulo.icon")); // NOI18N
        btnEliminarArticulo.setToolTipText(resourceMap.getString("btnEliminarArticulo.toolTipText")); // NOI18N
        btnEliminarArticulo.setName("btnEliminarArticulo"); // NOI18N
        btnEliminarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarArticuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 40, 30));

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 50, 45));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 50, 45));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        Cliente_Buscar clientes=new Cliente_Buscar();
        buscarCliente=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), clientes, "Buscar clientes");
        clientes.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        clientes.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, buscarCliente);
        clientes.valor=1;
}//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtFechaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaVentaKeyTyped

}//GEN-LAST:event_txtFechaVentaKeyTyped

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        Cliente_Nuevo clienteNuevo=new Cliente_Nuevo();
        nuevoCliente=Utiles_Panel.creaVentanaInternaModal((FrameView)this.getRefDep(), clienteNuevo, "Regístrar cliente");
        clienteNuevo.setRefDep(this);//dependencia entre quien lista usuarios y quien hace mantenimientos de usuarios
        clienteNuevo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, nuevoCliente);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
         this.Actualizar_busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void JtProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtProductoMouseClicked
        String ccodigo=(String)this.JtProducto.getValueAt(this.JtProducto.getSelectedRow(), 0);
        this.ObtenerProducto(ccodigo);
    }//GEN-LAST:event_JtProductoMouseClicked

    private void btnEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarArticuloActionPerformed
        DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
        if(this.JtVenta.getRowCount()>0){
            int fila = this.JtVenta.getSelectedRow();
            if (fila >= 0) {
                int respuesta=JOptionPane.showConfirmDialog(null,"¿Eliminar el producto de la lista detalle?","MENSAJE",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    modelo.removeRow(fila);
                    this.Limpiar_campos_producto();
                    this.txtCantidad.setEnabled(false);
                    this.btnAgregarArticulo.setEnabled(false);
                    this.btnCancelarArticulo.setEnabled(false);
                    this.Calcular_total_pagar();
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se encuentra ningun producto seleccionado en la lista de detalle para eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay producto en la lista detalle");
        }
    }//GEN-LAST:event_btnEliminarArticuloActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.Limpiar_campos_producto();
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true);
        this.Habilitar_botones(false, true, true, false, true, true, true);

        String Codigo=boleta.boleta_generar_codigo();
        txtCodigo.setText(Codigo);

        this.JrbNombre.setSelected(true);
        
        this.Actualizar_busqueda();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Limpiar_campos_producto();
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(false);
        this.Habilitar_botones(true, false, false, true, false, false, false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCancelarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarArticuloActionPerformed
        this.Limpiar_campos_producto();
        this.txtCantidad.setEnabled(false);
        this.btnAgregarArticulo.setEnabled(false);
        this.btnCancelarArticulo.setEnabled(false);
    }//GEN-LAST:event_btnCancelarArticuloActionPerformed

    private void btnAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarArticuloActionPerformed
        String vCodigoArt=this.txtCodigoProducto.getText().trim();
        String vNombreArt=this.txtNombreProducto.getText().trim();
        String vCantidadArt=this.txtCantidad.getText().trim();

        if(vCodigoArt.isEmpty() || vNombreArt.isEmpty() || vCantidadArt.isEmpty()){
                JOptionPane.showMessageDialog(null, "No a seleccionado producto ó falta ingresar cantidad");
        }else{
            if(Verificar_articulo_registrado()==false){
               String fila[];
               DefaultTableModel modelo=(DefaultTableModel)this.JtVenta.getModel();
               fila=new String[5];
               fila[0]=this.txtCodigoProducto.getText().trim();
               fila[1]=this.txtNombreProducto.getText().trim();
               fila[2]=this.txtPrecio.getText().trim();
               fila[3]=this.txtCantidad.getText().trim();
               double importe=Double.parseDouble(this.txtPrecio.getText().trim()) * Integer.parseInt(this.txtCantidad.getText().trim());
               fila[4]=String.valueOf(importe);
               modelo.addRow(fila);
               this.Limpiar_campos_producto();
               this.txtCantidad.setEnabled(false);
               this.btnAgregarArticulo.setEnabled(false);
               this.btnCancelarArticulo.setEnabled(false);
               this.Calcular_total_pagar();
           }else{
                int respuesta=JOptionPane.showConfirmDialog(null,"¿Producto ya existe en la lista detalle, si acepta se aumentara la cantidad?","MENSAJE",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Actualizar_cantidad_registrada(this.txtCodigoProducto.getText().trim(),this.txtCantidad.getText().trim());
                    this.Limpiar_campos_producto();
                    this.txtCantidad.setEnabled(false);
                    this.btnAgregarArticulo.setEnabled(false);
                    this.btnCancelarArticulo.setEnabled(false);
                    this.Calcular_total_pagar();
                    JOptionPane.showMessageDialog(null, "Se aumento la cantidad");
                }
           }
        }
    }//GEN-LAST:event_btnAgregarArticuloActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.Gestionar_venta();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        try{
            this.Validar_cantidad_vender();
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(this, "Error de escritura :"+ne.toString());
       }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtCantidad, evt);
    }//GEN-LAST:event_txtCantidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbCodigo;
    private javax.swing.JRadioButton JrbNombre;
    private javax.swing.JTable JtProducto;
    private javax.swing.JTable JtVenta;
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarArticulo;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminarArticulo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoProducto;
    private com.toedter.calendar.JDateChooser txtFechaVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables

}
