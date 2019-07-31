/*
 * ApliBodegaApp.java
 */

package aplibodega;

import Presentacion.InicioSesion.Inicio_Sesion;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 * The main class of the application.
 */
public class ApliBodegaApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        //show(new ApliBodegaView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ApliBodegaApp
     */
    public static ApliBodegaApp getApplication() {
        return Application.getInstance(ApliBodegaApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
        Inicio_Sesion inicio=new Inicio_Sesion(new javax.swing.JFrame());
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
                try{
                    //para cambiar aparienza a las interfaces graficas
                    SubstanceLookAndFeel.setSkin(new org.pushingpixels.substance.api.skin.CremeCoffeeSkin());
                }
                catch (Exception e) {
                    System.out.println("Fallo la inicializacion del substance");
                    System.out.println(e.getMessage().toString());
                }
            }
        });
        inicio.setVisible(true);
        //launch(ApliBodegaApp.class, args);
    }
}
