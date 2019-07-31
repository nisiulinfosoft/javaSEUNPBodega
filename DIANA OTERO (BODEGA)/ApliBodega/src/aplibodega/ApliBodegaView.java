/*
 * ApliBodegaView.java
 */

package aplibodega;

import DatosAcceso.DaoReporte;
import Presentacion.Administracion.Cargo_Administrar;
import Presentacion.Administracion.Categoria_Administrar;
import Presentacion.Administracion.Cliente_Administrar;
import Presentacion.Administracion.Cliente_Buscar;
import Presentacion.Administracion.Empleado_Administrar;
import Presentacion.Administracion.Empleado_Buscar;
import Presentacion.Administracion.Marca_Administrar;
import Presentacion.Administracion.Producto_Administrar;
import Presentacion.Administracion.Producto_Buscar;
import Presentacion.Administracion.Proveedor_Administrar;
import Presentacion.Administracion.Proveedor_Buscar;
import Presentacion.Administracion.UnidadMedida_Administrar;
import Presentacion.Administracion.Usuario_Administrar;
import Presentacion.Administracion.Usuario_Buscar;
import Presentacion.InicioSesion.Inicio_Sesion;
import Presentacion.Ventas.GestionarVenta;
import Utiles.Cls_DesktopPaneConFondo;
import Utiles.Utiles_Panel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class ApliBodegaView extends FrameView {

    JInternalFrame jif_usuario,jif_empleado,jif_cargo,jif_proveedor,jif_cliente,jif_producto,jif_categoria,jif_marca,jif_unidadMedida,jif_gestionarVenta;
    JInternalFrame jif_consultarUsuario,jif_consultarEmpleado,jif_consultarProveedor,jif_consultarCliente,jif_consultarProducto;

    DaoReporte REPORTE=new DaoReporte();

    public ApliBodegaView(SingleFrameApplication app,Inicio_Sesion padre) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ApliBodegaApp.getApplication().getMainFrame();
            aboutBox = new ApliBodegaAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ApliBodegaApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jmAdministracion = new javax.swing.JMenu();
        JmiUsuario = new javax.swing.JMenuItem();
        JmiEmpleado = new javax.swing.JMenuItem();
        JmiCargo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        JmiProveedor = new javax.swing.JMenuItem();
        JmiCliente = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        JmiProducto = new javax.swing.JMenuItem();
        JmiCategoria = new javax.swing.JMenuItem();
        JmiMarca = new javax.swing.JMenuItem();
        JmiUnidadMedida = new javax.swing.JMenuItem();
        jmVenta = new javax.swing.JMenu();
        JmiGestionarVenta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        JmiReporteProducto = new javax.swing.JMenuItem();
        jmConsultas = new javax.swing.JMenu();
        JmiConsultaUsuario = new javax.swing.JMenuItem();
        JmiConsultaEmpleado = new javax.swing.JMenuItem();
        JmiConsultaProveedor = new javax.swing.JMenuItem();
        JmiConsultaCliente = new javax.swing.JMenuItem();
        JmiConsultaArticulo = new javax.swing.JMenuItem();
        jmReportes = new javax.swing.JMenu();
        JmiReporteEmpleado = new javax.swing.JMenuItem();
        JmiReporteProveedor = new javax.swing.JMenuItem();
        JmiReporteCliente = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        JmiAcercaDe = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jDesktopPane1 = new Cls_DesktopPaneConFondo();
        jToolBar1 = new javax.swing.JToolBar();
        JtbSalir = new javax.swing.JButton();
        JtbCliente = new javax.swing.JButton();
        JtbProveedor = new javax.swing.JButton();
        JtbProducto = new javax.swing.JButton();
        JtbVender = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(ApliBodegaView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getActionMap(ApliBodegaView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jmAdministracion.setText(resourceMap.getString("jmAdministracion.text")); // NOI18N
        jmAdministracion.setName("jmAdministracion"); // NOI18N

        JmiUsuario.setIcon(resourceMap.getIcon("JmiUsuario.icon")); // NOI18N
        JmiUsuario.setText(resourceMap.getString("JmiUsuario.text")); // NOI18N
        JmiUsuario.setName("JmiUsuario"); // NOI18N
        JmiUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiUsuarioActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiUsuario);

        JmiEmpleado.setIcon(resourceMap.getIcon("JmiEmpleado.icon")); // NOI18N
        JmiEmpleado.setText(resourceMap.getString("JmiEmpleado.text")); // NOI18N
        JmiEmpleado.setName("JmiEmpleado"); // NOI18N
        JmiEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiEmpleadoActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiEmpleado);

        JmiCargo.setIcon(resourceMap.getIcon("JmiCargo.icon")); // NOI18N
        JmiCargo.setText(resourceMap.getString("JmiCargo.text")); // NOI18N
        JmiCargo.setName("JmiCargo"); // NOI18N
        JmiCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiCargoActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiCargo);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jmAdministracion.add(jSeparator2);

        JmiProveedor.setIcon(resourceMap.getIcon("JmiProveedor.icon")); // NOI18N
        JmiProveedor.setText(resourceMap.getString("JmiProveedor.text")); // NOI18N
        JmiProveedor.setName("JmiProveedor"); // NOI18N
        JmiProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiProveedorActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiProveedor);

        JmiCliente.setIcon(resourceMap.getIcon("JmiCliente.icon")); // NOI18N
        JmiCliente.setText(resourceMap.getString("JmiCliente.text")); // NOI18N
        JmiCliente.setName("JmiCliente"); // NOI18N
        JmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiClienteActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiCliente);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jmAdministracion.add(jSeparator1);

        JmiProducto.setIcon(resourceMap.getIcon("JmiProducto.icon")); // NOI18N
        JmiProducto.setText(resourceMap.getString("JmiProducto.text")); // NOI18N
        JmiProducto.setName("JmiProducto"); // NOI18N
        JmiProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiProductoActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiProducto);

        JmiCategoria.setIcon(resourceMap.getIcon("JmiCategoria.icon")); // NOI18N
        JmiCategoria.setText(resourceMap.getString("JmiCategoria.text")); // NOI18N
        JmiCategoria.setName("JmiCategoria"); // NOI18N
        JmiCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiCategoriaActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiCategoria);

        JmiMarca.setIcon(resourceMap.getIcon("JmiMarca.icon")); // NOI18N
        JmiMarca.setText(resourceMap.getString("JmiMarca.text")); // NOI18N
        JmiMarca.setName("JmiMarca"); // NOI18N
        JmiMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiMarcaActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiMarca);

        JmiUnidadMedida.setIcon(resourceMap.getIcon("JmiUnidadMedida.icon")); // NOI18N
        JmiUnidadMedida.setText(resourceMap.getString("JmiUnidadMedida.text")); // NOI18N
        JmiUnidadMedida.setName("JmiUnidadMedida"); // NOI18N
        JmiUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiUnidadMedidaActionPerformed(evt);
            }
        });
        jmAdministracion.add(JmiUnidadMedida);

        menuBar.add(jmAdministracion);

        jmVenta.setText(resourceMap.getString("jmVenta.text")); // NOI18N
        jmVenta.setName("jmVenta"); // NOI18N

        JmiGestionarVenta.setIcon(resourceMap.getIcon("JmiGestionarVenta.icon")); // NOI18N
        JmiGestionarVenta.setText(resourceMap.getString("JmiGestionarVenta.text")); // NOI18N
        JmiGestionarVenta.setName("JmiGestionarVenta"); // NOI18N
        JmiGestionarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiGestionarVentaActionPerformed(evt);
            }
        });
        jmVenta.add(JmiGestionarVenta);

        jMenu6.setIcon(resourceMap.getIcon("jMenu6.icon")); // NOI18N
        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N

        JmiReporteProducto.setIcon(resourceMap.getIcon("JmiReporteProducto.icon")); // NOI18N
        JmiReporteProducto.setText(resourceMap.getString("JmiReporteProducto.text")); // NOI18N
        JmiReporteProducto.setName("JmiReporteProducto"); // NOI18N
        JmiReporteProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiReporteProductoActionPerformed(evt);
            }
        });
        jMenu6.add(JmiReporteProducto);

        jmVenta.add(jMenu6);

        menuBar.add(jmVenta);

        jmConsultas.setText(resourceMap.getString("jmConsultas.text")); // NOI18N
        jmConsultas.setName("jmConsultas"); // NOI18N

        JmiConsultaUsuario.setIcon(resourceMap.getIcon("JmiConsultaUsuario.icon")); // NOI18N
        JmiConsultaUsuario.setText(resourceMap.getString("JmiConsultaUsuario.text")); // NOI18N
        JmiConsultaUsuario.setName("JmiConsultaUsuario"); // NOI18N
        JmiConsultaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultaUsuarioActionPerformed(evt);
            }
        });
        jmConsultas.add(JmiConsultaUsuario);

        JmiConsultaEmpleado.setIcon(resourceMap.getIcon("JmiConsultaEmpleado.icon")); // NOI18N
        JmiConsultaEmpleado.setText(resourceMap.getString("JmiConsultaEmpleado.text")); // NOI18N
        JmiConsultaEmpleado.setName("JmiConsultaEmpleado"); // NOI18N
        JmiConsultaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultaEmpleadoActionPerformed(evt);
            }
        });
        jmConsultas.add(JmiConsultaEmpleado);

        JmiConsultaProveedor.setIcon(resourceMap.getIcon("JmiConsultaProveedor.icon")); // NOI18N
        JmiConsultaProveedor.setText(resourceMap.getString("JmiConsultaProveedor.text")); // NOI18N
        JmiConsultaProveedor.setName("JmiConsultaProveedor"); // NOI18N
        JmiConsultaProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultaProveedorActionPerformed(evt);
            }
        });
        jmConsultas.add(JmiConsultaProveedor);

        JmiConsultaCliente.setIcon(resourceMap.getIcon("JmiConsultaCliente.icon")); // NOI18N
        JmiConsultaCliente.setText(resourceMap.getString("JmiConsultaCliente.text")); // NOI18N
        JmiConsultaCliente.setName("JmiConsultaCliente"); // NOI18N
        JmiConsultaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultaClienteActionPerformed(evt);
            }
        });
        jmConsultas.add(JmiConsultaCliente);

        JmiConsultaArticulo.setIcon(resourceMap.getIcon("JmiConsultaArticulo.icon")); // NOI18N
        JmiConsultaArticulo.setText(resourceMap.getString("JmiConsultaArticulo.text")); // NOI18N
        JmiConsultaArticulo.setName("JmiConsultaArticulo"); // NOI18N
        JmiConsultaArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiConsultaArticuloActionPerformed(evt);
            }
        });
        jmConsultas.add(JmiConsultaArticulo);

        menuBar.add(jmConsultas);

        jmReportes.setText(resourceMap.getString("jmReportes.text")); // NOI18N
        jmReportes.setName("jmReportes"); // NOI18N

        JmiReporteEmpleado.setIcon(resourceMap.getIcon("JmiReporteEmpleado.icon")); // NOI18N
        JmiReporteEmpleado.setText(resourceMap.getString("JmiReporteEmpleado.text")); // NOI18N
        JmiReporteEmpleado.setName("JmiReporteEmpleado"); // NOI18N
        JmiReporteEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiReporteEmpleadoActionPerformed(evt);
            }
        });
        jmReportes.add(JmiReporteEmpleado);

        JmiReporteProveedor.setIcon(resourceMap.getIcon("JmiReporteProveedor.icon")); // NOI18N
        JmiReporteProveedor.setText(resourceMap.getString("JmiReporteProveedor.text")); // NOI18N
        JmiReporteProveedor.setName("JmiReporteProveedor"); // NOI18N
        JmiReporteProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiReporteProveedorActionPerformed(evt);
            }
        });
        jmReportes.add(JmiReporteProveedor);

        JmiReporteCliente.setIcon(resourceMap.getIcon("JmiReporteCliente.icon")); // NOI18N
        JmiReporteCliente.setText(resourceMap.getString("JmiReporteCliente.text")); // NOI18N
        JmiReporteCliente.setName("JmiReporteCliente"); // NOI18N
        JmiReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiReporteClienteActionPerformed(evt);
            }
        });
        jmReportes.add(JmiReporteCliente);

        menuBar.add(jmReportes);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        JmiAcercaDe.setIcon(resourceMap.getIcon("JmiAcercaDe.icon")); // NOI18N
        JmiAcercaDe.setText(resourceMap.getString("JmiAcercaDe.text")); // NOI18N
        JmiAcercaDe.setName("JmiAcercaDe"); // NOI18N
        JmiAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmiAcercaDeActionPerformed(evt);
            }
        });
        jMenu5.add(JmiAcercaDe);

        menuBar.add(jMenu5);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(400, 35));
        statusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N
        statusPanel.add(statusPanelSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusPanel.add(statusMessageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, -1, -1));

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N
        statusPanel.add(statusAnimationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 8, -1, -1));

        progressBar.setName("progressBar"); // NOI18N
        statusPanel.add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 8, -1, -1));

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        statusPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblNombreUsuario.setFont(resourceMap.getFont("lblNombreUsuario.font")); // NOI18N
        lblNombreUsuario.setText(resourceMap.getString("lblNombreUsuario.text")); // NOI18N
        lblNombreUsuario.setName("lblNombreUsuario"); // NOI18N
        statusPanel.add(lblNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 150, -1));

        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        ((Cls_DesktopPaneConFondo)jDesktopPane1).setImagen("/Images/fondo_menu.jpg");

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        JtbSalir.setIcon(resourceMap.getIcon("JtbSalir.icon")); // NOI18N
        JtbSalir.setText(resourceMap.getString("JtbSalir.text")); // NOI18N
        JtbSalir.setToolTipText(resourceMap.getString("JtbSalir.toolTipText")); // NOI18N
        JtbSalir.setFocusable(false);
        JtbSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JtbSalir.setName("JtbSalir"); // NOI18N
        JtbSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JtbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtbSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(JtbSalir);

        JtbCliente.setIcon(resourceMap.getIcon("JtbCliente.icon")); // NOI18N
        JtbCliente.setText(resourceMap.getString("JtbCliente.text")); // NOI18N
        JtbCliente.setToolTipText(resourceMap.getString("JtbCliente.toolTipText")); // NOI18N
        JtbCliente.setFocusable(false);
        JtbCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JtbCliente.setName("JtbCliente"); // NOI18N
        JtbCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JtbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtbClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(JtbCliente);

        JtbProveedor.setIcon(resourceMap.getIcon("JtbProveedor.icon")); // NOI18N
        JtbProveedor.setText(resourceMap.getString("JtbProveedor.text")); // NOI18N
        JtbProveedor.setToolTipText(resourceMap.getString("JtbProveedor.toolTipText")); // NOI18N
        JtbProveedor.setFocusable(false);
        JtbProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JtbProveedor.setName("JtbProveedor"); // NOI18N
        JtbProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JtbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtbProveedorActionPerformed(evt);
            }
        });
        jToolBar1.add(JtbProveedor);

        JtbProducto.setIcon(resourceMap.getIcon("JtbProducto.icon")); // NOI18N
        JtbProducto.setText(resourceMap.getString("JtbProducto.text")); // NOI18N
        JtbProducto.setFocusable(false);
        JtbProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JtbProducto.setName("JtbProducto"); // NOI18N
        JtbProducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JtbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtbProductoActionPerformed(evt);
            }
        });
        jToolBar1.add(JtbProducto);

        JtbVender.setIcon(resourceMap.getIcon("JtbVender.icon")); // NOI18N
        JtbVender.setText(resourceMap.getString("JtbVender.text")); // NOI18N
        JtbVender.setToolTipText(resourceMap.getString("JtbVender.toolTipText")); // NOI18N
        JtbVender.setFocusable(false);
        JtbVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JtbVender.setName("JtbVender"); // NOI18N
        JtbVender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JtbVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtbVenderActionPerformed(evt);
            }
        });
        jToolBar1.add(JtbVender);

        setComponent(jDesktopPane1);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(jToolBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void JmiUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiUsuarioActionPerformed
        if(this.jif_usuario==null||jif_usuario.isClosed()){//si esta cerrado
           Usuario_Administrar usuario=new Usuario_Administrar();
           jif_usuario=Utiles_Panel.creaVentanaInterna_Panel(this, usuario, "Administrar usuario");
           usuario.setRefDep(this);//la referencia de quien depende
           usuario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_usuario);
       }
    }//GEN-LAST:event_JmiUsuarioActionPerformed

    private void JmiEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiEmpleadoActionPerformed
        if(this.jif_empleado==null||jif_empleado.isClosed()){//si esta cerrado
           Empleado_Administrar empleado=new Empleado_Administrar();
           jif_empleado=Utiles_Panel.creaVentanaInterna_Panel(this, empleado, "Administrar empleado");
           empleado.setRefDep(this);//la referencia de quien depende
           empleado.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_empleado);
       }
    }//GEN-LAST:event_JmiEmpleadoActionPerformed

    private void JmiCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiCargoActionPerformed
        if(this.jif_cargo==null||jif_cargo.isClosed()){//si esta cerrado
           Cargo_Administrar cargo=new Cargo_Administrar();
           jif_cargo=Utiles_Panel.creaVentanaInterna_Panel(this, cargo, "Administrar cargo");
           cargo.setRefDep(this);//la referencia de quien depende
           cargo.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cargo);
       }
    }//GEN-LAST:event_JmiCargoActionPerformed

    private void JmiProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiProveedorActionPerformed
        if(this.jif_proveedor==null||jif_proveedor.isClosed()){//si esta cerrado
           Proveedor_Administrar proveedor=new Proveedor_Administrar();
           jif_proveedor=Utiles_Panel.creaVentanaInterna_Panel(this, proveedor, "Administrar proveedor");
           proveedor.setRefDep(this);//la referencia de quien depende
           proveedor.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_proveedor);
       }
    }//GEN-LAST:event_JmiProveedorActionPerformed

    private void JmiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiClienteActionPerformed
        if(this.jif_cliente==null||jif_cliente.isClosed()){//si esta cerrado
           Cliente_Administrar cliente=new Cliente_Administrar();
           jif_cliente=Utiles_Panel.creaVentanaInterna_Panel(this, cliente, "Administrar cliente");
           cliente.setRefDep(this);//la referencia de quien depende
           cliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cliente);
       }
    }//GEN-LAST:event_JmiClienteActionPerformed

    private void JmiProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiProductoActionPerformed
        if(this.jif_producto==null||jif_producto.isClosed()){//si esta cerrado
           Producto_Administrar producto=new Producto_Administrar();
           jif_producto=Utiles_Panel.creaVentanaInterna_Panel(this, producto, "Administrar producto");
           producto.setRefDep(this);//la referencia de quien depende
           producto.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_producto);
       }
    }//GEN-LAST:event_JmiProductoActionPerformed

    private void JmiCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiCategoriaActionPerformed
        if(this.jif_categoria==null||jif_categoria.isClosed()){//si esta cerrado
           Categoria_Administrar categoria=new Categoria_Administrar();
           jif_categoria=Utiles_Panel.creaVentanaInterna_Panel(this, categoria, "Administrar categoria");
           categoria.setRefDep(this);//la referencia de quien depende
           categoria.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_categoria);
       }
    }//GEN-LAST:event_JmiCategoriaActionPerformed

    private void JmiMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiMarcaActionPerformed
        if(this.jif_marca==null||jif_marca.isClosed()){//si esta cerrado
           Marca_Administrar marca=new Marca_Administrar();
           jif_marca=Utiles_Panel.creaVentanaInterna_Panel(this, marca, "Administrar marca");
           marca.setRefDep(this);//la referencia de quien depende
           marca.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_marca);
       }
    }//GEN-LAST:event_JmiMarcaActionPerformed

    private void JmiUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiUnidadMedidaActionPerformed
        if(this.jif_unidadMedida==null||jif_unidadMedida.isClosed()){//si esta cerrado
           UnidadMedida_Administrar unidadMedida=new UnidadMedida_Administrar();
           jif_unidadMedida=Utiles_Panel.creaVentanaInterna_Panel(this, unidadMedida, "Administrar unidad medida");
           unidadMedida.setRefDep(this);//la referencia de quien depende
           unidadMedida.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_unidadMedida);
       }
    }//GEN-LAST:event_JmiUnidadMedidaActionPerformed

    private void JmiConsultaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultaUsuarioActionPerformed
        if(this.jif_consultarUsuario==null||jif_consultarUsuario.isClosed()){//si esta cerrado
           Usuario_Buscar listarUsuario=new Usuario_Buscar();
           jif_consultarUsuario=Utiles_Panel.creaVentanaInterna_Panel(this, listarUsuario, "Consultar usuarios");
           listarUsuario.setRefDep(this);//la referencia de quien depende
           listarUsuario.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_consultarUsuario);
           listarUsuario.valor=0;
       }
    }//GEN-LAST:event_JmiConsultaUsuarioActionPerformed

    private void JmiConsultaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultaEmpleadoActionPerformed
        if(this.jif_consultarEmpleado==null||jif_consultarEmpleado.isClosed()){//si esta cerrado
           Empleado_Buscar listarEmpleado=new Empleado_Buscar();
           jif_consultarEmpleado=Utiles_Panel.creaVentanaInterna_Panel(this, listarEmpleado, "Consultar empleados");
           listarEmpleado.setRefDep(this);//la referencia de quien depende
           listarEmpleado.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_consultarEmpleado);
           listarEmpleado.valor=0;
       }
    }//GEN-LAST:event_JmiConsultaEmpleadoActionPerformed

    private void JmiConsultaProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultaProveedorActionPerformed
        if(this.jif_consultarProveedor==null||jif_consultarProveedor.isClosed()){//si esta cerrado
           Proveedor_Buscar listarProveedor=new Proveedor_Buscar();
           jif_consultarProveedor=Utiles_Panel.creaVentanaInterna_Panel(this, listarProveedor, "Consultar proveedores");
           listarProveedor.setRefDep(this);//la referencia de quien depende
           listarProveedor.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_consultarProveedor);
           listarProveedor.valor=0;
       }
    }//GEN-LAST:event_JmiConsultaProveedorActionPerformed

    private void JmiConsultaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultaClienteActionPerformed
        if(this.jif_consultarCliente==null||jif_consultarCliente.isClosed()){//si esta cerrado
           Cliente_Buscar listarCliente=new Cliente_Buscar();
           jif_consultarCliente=Utiles_Panel.creaVentanaInterna_Panel(this, listarCliente, "Consultar clientes");
           listarCliente.setRefDep(this);//la referencia de quien depende
           listarCliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_consultarCliente);
           listarCliente.valor=0;
       }
    }//GEN-LAST:event_JmiConsultaClienteActionPerformed

    private void JmiConsultaArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiConsultaArticuloActionPerformed
        if(this.jif_consultarProducto==null||jif_consultarProducto.isClosed()){//si esta cerrado
           Producto_Buscar listaProductos=new Producto_Buscar();
           jif_consultarProducto=Utiles_Panel.creaVentanaInterna_Panel(this, listaProductos, "Consultar productos");
           listaProductos.setRefDep(this);//la referencia de quien depende
           listaProductos.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_consultarProducto);
           listaProductos.valor=0;
       }
    }//GEN-LAST:event_JmiConsultaArticuloActionPerformed

    private void JmiReporteEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiReporteEmpleadoActionPerformed
        REPORTE.Reporte_empleado();
    }//GEN-LAST:event_JmiReporteEmpleadoActionPerformed

    private void JmiReporteProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiReporteProveedorActionPerformed
        REPORTE.Reporte_proveedor();
    }//GEN-LAST:event_JmiReporteProveedorActionPerformed

    private void JmiReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiReporteClienteActionPerformed
        REPORTE.Reporte_cliente();
    }//GEN-LAST:event_JmiReporteClienteActionPerformed

    private void JmiReporteProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiReporteProductoActionPerformed
        REPORTE.Reporte_producto();
    }//GEN-LAST:event_JmiReporteProductoActionPerformed

    private void JmiAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiAcercaDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmiAcercaDeActionPerformed

    private void JmiGestionarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmiGestionarVentaActionPerformed
        if(this.jif_gestionarVenta==null||jif_gestionarVenta.isClosed()){//si esta cerrado
           GestionarVenta venta=new GestionarVenta();
           jif_gestionarVenta=Utiles_Panel.creaVentanaInterna_Panel(this, venta, "Gestionar venta");
           venta.setRefDep(this);//la referencia de quien depende
           venta.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_gestionarVenta);
       }
    }//GEN-LAST:event_JmiGestionarVentaActionPerformed

    private void JtbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtbSalirActionPerformed
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea salir del sistema?","MENSAJE",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_JtbSalirActionPerformed

    private void JtbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtbClienteActionPerformed
        if(this.jif_cliente==null||jif_cliente.isClosed()){//si esta cerrado
           Cliente_Administrar cliente=new Cliente_Administrar();
           jif_cliente=Utiles_Panel.creaVentanaInterna_Panel(this, cliente, "Administrar cliente");
           cliente.setRefDep(this);//la referencia de quien depende
           cliente.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_cliente);
       }
    }//GEN-LAST:event_JtbClienteActionPerformed

    private void JtbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtbProveedorActionPerformed
        if(this.jif_proveedor==null||jif_proveedor.isClosed()){//si esta cerrado
           Proveedor_Administrar proveedor=new Proveedor_Administrar();
           jif_proveedor=Utiles_Panel.creaVentanaInterna_Panel(this, proveedor, "Administrar proveedor");
           proveedor.setRefDep(this);//la referencia de quien depende
           proveedor.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_proveedor);
       }
    }//GEN-LAST:event_JtbProveedorActionPerformed

    private void JtbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtbProductoActionPerformed
        if(this.jif_producto==null||jif_producto.isClosed()){//si esta cerrado
           Producto_Administrar producto=new Producto_Administrar();
           jif_producto=Utiles_Panel.creaVentanaInterna_Panel(this, producto, "Administrar producto");
           producto.setRefDep(this);//la referencia de quien depende
           producto.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_producto);
       }
    }//GEN-LAST:event_JtbProductoActionPerformed

    private void JtbVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtbVenderActionPerformed
        if(this.jif_gestionarVenta==null||jif_gestionarVenta.isClosed()){//si esta cerrado
           GestionarVenta venta=new GestionarVenta();
           jif_gestionarVenta=Utiles_Panel.creaVentanaInterna_Panel(this, venta, "Gestionar venta");
           venta.setRefDep(this);//la referencia de quien depende
           venta.setPadre(Utiles_Panel.TIPO_JINTERNALFRAME, jif_gestionarVenta);
       }
    }//GEN-LAST:event_JtbVenderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JmiAcercaDe;
    private javax.swing.JMenuItem JmiCargo;
    private javax.swing.JMenuItem JmiCategoria;
    private javax.swing.JMenuItem JmiCliente;
    private javax.swing.JMenuItem JmiConsultaArticulo;
    private javax.swing.JMenuItem JmiConsultaCliente;
    private javax.swing.JMenuItem JmiConsultaEmpleado;
    private javax.swing.JMenuItem JmiConsultaProveedor;
    private javax.swing.JMenuItem JmiConsultaUsuario;
    private javax.swing.JMenuItem JmiEmpleado;
    private javax.swing.JMenuItem JmiGestionarVenta;
    private javax.swing.JMenuItem JmiMarca;
    private javax.swing.JMenuItem JmiProducto;
    private javax.swing.JMenuItem JmiProveedor;
    private javax.swing.JMenuItem JmiReporteCliente;
    private javax.swing.JMenuItem JmiReporteEmpleado;
    private javax.swing.JMenuItem JmiReporteProducto;
    private javax.swing.JMenuItem JmiReporteProveedor;
    private javax.swing.JMenuItem JmiUnidadMedida;
    private javax.swing.JMenuItem JmiUsuario;
    public javax.swing.JButton JtbCliente;
    public javax.swing.JButton JtbProducto;
    public javax.swing.JButton JtbProveedor;
    private javax.swing.JButton JtbSalir;
    public javax.swing.JButton JtbVender;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JMenu jmAdministracion;
    public javax.swing.JMenu jmConsultas;
    public javax.swing.JMenu jmReportes;
    public javax.swing.JMenu jmVenta;
    public javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
