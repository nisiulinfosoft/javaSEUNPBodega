package Utiles;

import javax.swing.JPanel;

public abstract class JP_Modelo extends JPanel {    
    /** Creates new form Panel_Modelo */
    public JP_Modelo() {
        initComponents();       
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
            
    public void setIDUser( String id ){
        this.idUser = id;
    }    
        
    public void setPadre( int t, Object p ){
        padre = p;
        tipo = t;
    }      
    
    public void cerrarPadre(){
        switch( tipo ){
            case 1:     //Caso de que el padre sea un JFrame
                ( ( javax.swing.JFrame ) padre ).dispose();
                break;                
            case 2:     //Caso de que el padre sea un JDialog
                ( ( javax.swing.JDialog ) padre ).dispose();
                break;                
            case 3:     //Caso de que el padre sea un JInternalFrame
                ( ( javax.swing.JInternalFrame ) padre ).dispose();
                break;
        }
    }
    
    public void ajustarTamanio(){
        switch( tipo ){
            case 1:     //Caso de que el padre sea un JFrame
                ( ( javax.swing.JFrame ) padre ).pack();
                break;
               
            case 2:     //Caso de que el padre sea un JDialog
                ( ( javax.swing.JDialog ) padre ).pack();
                break;
                
            case 3:     //Caso de que el padre sea un JInternalFrame
                ( ( javax.swing.JInternalFrame ) padre ).pack();
                break;
        }
    }


    public void setCliente( Object cli ){
        cliente = cli;
    }
    
    public void setRef( Object r ){
        this.ref = r;
    }
    
    public void setRefDep( Object r ){
        this.refDep = r;
    }

    public Object getCliente(){
        return this.cliente;
    }
    
    public Object getRef(){
        return this.ref;
    }
        
    public Object getRefDep(){
        return this.refDep;
    }
    
    public Object getPadre(){
        return padre;
    }
        
    public int getTipo(){
        return tipo;
    }   
    
    public String getIDUser(){
        return this.idUser;
    }


    //Este metodo lo puedo sobreescribir para el caso que tengamos paneles con busquedas predeterminadas en el formulario
    public void consulta(){

    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    //Referencia al tipo de Padre puede ser un JFrame, JInternalFrame o JDialog
    private Object padre;
    private int tipo;    
    
    //Referencia del contenedor Principal, en caso sea hijo de un MDI y necesito para llamar a otra ventana interna
    private Object ref;
    
    
    //Referencia del objeto donde fue llamado este objeto
    private Object refDep;
        
    //Necesarios para la busqueda sensitiva

    //Usuario o inicio de sesión en la BD
    private String idUser;

    //Cliente Remoto puede ser EJB o RMI
    Object cliente;

}
