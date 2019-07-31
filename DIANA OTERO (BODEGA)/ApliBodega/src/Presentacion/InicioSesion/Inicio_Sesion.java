package Presentacion.InicioSesion;

import DatosAcceso.DaoEmpleado;
import DatosAcceso.DaoUsuario;
import LogicaGetSet.Empleado;
import LogicaGetSet.Usuario;
import aplibodega.ApliBodegaApp;
import aplibodega.ApliBodegaView;
import javax.swing.JOptionPane;

public class Inicio_Sesion extends javax.swing.JFrame {

    DaoUsuario usuario=new DaoUsuario();
    DaoEmpleado empleado=new DaoEmpleado();

    public Inicio_Sesion(javax.swing.JFrame padre) {
        super("Inicio sesión");
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Inicio_Sesion.class);
        txtPass.setText(resourceMap.getString("txtPass.text")); // NOI18N
        txtPass.setName("txtPass"); // NOI18N
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 200, -1));

        txtLogin.setText(resourceMap.getString("txtLogin.text")); // NOI18N
        txtLogin.setName("txtLogin"); // NOI18N
        jPanel1.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 200, -1));

        btnAceptar.setIcon(resourceMap.getIcon("btnAceptar.icon")); // NOI18N
        btnAceptar.setText(resourceMap.getString("btnAceptar.text")); // NOI18N
        btnAceptar.setName("btnAceptar"); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 100, -1));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Usuario objUsuario=null;
        Empleado objEmpleado=null;
        try{
            String codigo=this.txtLogin.getText().trim();
            String contrasena=this.txtPass.getText().trim();
            if(codigo.isEmpty() || contrasena.isEmpty()){
                JOptionPane.showMessageDialog(this, "Falta llenar los campos de texto");
            }else{
                objUsuario=usuario.usuario_inicio_sesion(codigo,contrasena);
                if(objUsuario!=null){

                    this.setVisible(false);
                    ApliBodegaView acceso=new ApliBodegaView(ApliBodegaApp.getApplication(),this);
                    acceso.getApplication().show(acceso);

                    objEmpleado=empleado.empleado_obtener_por_codigo(objUsuario.getCodigo_per());
                    acceso.lblNombreUsuario.setText(objEmpleado.getApellidos_emp()+" "+objEmpleado.getNombre_emp());

                    acceso.jmAdministracion.setVisible(Boolean.parseBoolean(objUsuario.getAdministracion_usu()));
                    acceso.jmVenta.setVisible(Boolean.parseBoolean(objUsuario.getVenta_per()));
                    acceso.jmConsultas.setVisible(Boolean.parseBoolean(objUsuario.getConsultas_usu()));
                    acceso.jmReportes.setVisible(Boolean.parseBoolean(objUsuario.getReportes_usu()));

                    acceso.JtbCliente.setVisible(Boolean.parseBoolean(objUsuario.getAdministracion_usu()));
                    acceso.JtbProveedor.setVisible(Boolean.parseBoolean(objUsuario.getAdministracion_usu()));
                    acceso.JtbProducto.setVisible(Boolean.parseBoolean(objUsuario.getAdministracion_usu()));
                    acceso.JtbVender.setVisible(Boolean.parseBoolean(objUsuario.getVenta_per()));

                }
                else{
                    this.txtPass.setText("");
                    this.txtPass.requestFocus();
                    JOptionPane.showMessageDialog(this, "Datos de usuario incorrectos");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea cancelar el ingreso al sistema?","MENSAJE",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables

}
