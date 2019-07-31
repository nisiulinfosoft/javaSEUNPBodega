package Presentacion.Administracion;

import DatosAcceso.DaoCliente;
import LogicaGetSet.Cliente;
import Presentacion.Ventas.GestionarVenta;
import Utiles.Cls_Validar_CampoTexto;
import Utiles.JP_Modelo;
import javax.swing.JOptionPane;

public class Cliente_Nuevo extends JP_Modelo {

    DaoCliente cliente=new DaoCliente();

    String vCodigo,vRUC,vRazonSocial,vDireccion;

    public Cliente_Nuevo() {
        initComponents();

        this.Generar_codigo();
        this.Habilitar_campos_textos(true);
    }

    public void Asignar_campos_texto(){
        vCodigo=this.txtCodigo.getText().trim();
        vRUC=this.txtRUC.getText().trim();
        vRazonSocial=this.txtRazonSocial.getText().trim();
        vDireccion=this.txtDireccion.getText().trim();
    }

    public void Limpiar_campos_texto()
    {
        this.txtRUC.setText("");
        this.txtRazonSocial.setText("");
        this.txtDireccion.setText("");
        this.txtRUC.requestFocus();
    }

   public void Habilitar_campos_textos(boolean a)
   {
        this.txtCodigo.setEnabled(false);
        this.txtRUC.setEnabled(a);
        this.txtRazonSocial.setEnabled(a);
        this.txtDireccion.setEnabled(a);
   }

   public void Generar_codigo()
    {
        String Codigo=cliente.cliente_generar_codigo();
        this.txtCodigo.setText(Codigo);
    }

   public void RegistrarCliente()
  {
        this.Asignar_campos_texto();
        if(vCodigo.isEmpty() || vRUC.isEmpty() || vRazonSocial.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completar los campos obligatorios");
        }else{

            if(this.txtRUC.getText().length() < 11){
                JOptionPane.showMessageDialog(this,"El campo RUC debe tener 11  digitos");
                this.txtRUC.requestFocus();
            }else {

                int respuesta=JOptionPane.showConfirmDialog(null,"¿Seguro de regístrar datos de cliente?","Pregunta",JOptionPane.YES_NO_OPTION);
                if(respuesta==JOptionPane.OK_OPTION)
                {
                    Cliente objCliente=null;
                    try {
                        objCliente=new Cliente();
                        objCliente.setCodigo_cli(this.txtCodigo.getText().trim());
                        objCliente.setRuc_cli(this.txtRUC.getText().trim());
                        objCliente.setRazon_social_cli(this.txtRazonSocial.getText().trim());
                        objCliente.setDireccion_cli(this.txtDireccion.getText().trim());

                        if(cliente.cliente_registrar(objCliente)==true){
                            JOptionPane.showMessageDialog(this,"Regístro guardado con exito");

                            String ccodigo=this.txtCodigo.getText().trim();

                            if(this.getRefDep() instanceof GestionarVenta)//para ver quien fue quien lo llamo
                            {
                                ((GestionarVenta)this.getRefDep()).ObtenerCliente(ccodigo);
                            }
                            this.cerrarPadre();

                        }else{
                            JOptionPane.showMessageDialog(this,"Error datos no registrados");
                            this.Generar_codigo();
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                    this.Limpiar_campos_texto();
                    this.Habilitar_campos_textos(true);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        txtRUC = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(aplibodega.ApliBodegaApp.class).getContext().getResourceMap(Cliente_Nuevo.class);
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

        txtDireccion.setName("txtDireccion"); // NOI18N
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 360, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        btnRegistrar.setIcon(resourceMap.getIcon("btnRegistrar.icon")); // NOI18N
        btnRegistrar.setToolTipText(resourceMap.getString("btnRegistrar.toolTipText")); // NOI18N
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 50, 45));

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setToolTipText(resourceMap.getString("btnCancelar.toolTipText")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 50, 45));

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setToolTipText(resourceMap.getString("btnCerrar.toolTipText")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 50, 45));

        txtRUC.setName("txtRUC"); // NOI18N
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUCKeyTyped(evt);
            }
        });
        jPanel1.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 140, -1));

        txtRazonSocial.setName("txtRazonSocial"); // NOI18N
        jPanel1.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 360, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 210));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        this.RegistrarCliente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.Generar_codigo();
        this.Limpiar_campos_texto();
        this.Habilitar_campos_textos(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarPadre();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtRUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyTyped
        Cls_Validar_CampoTexto.LimitarCaracteres(txtRUC, 11, evt);
        Cls_Validar_CampoTexto.ValidarSoloNumerosEnteros(txtRUC, evt);
    }//GEN-LAST:event_txtRUCKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables

}
