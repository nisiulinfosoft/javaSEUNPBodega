package Utiles;

import javax.swing.ImageIcon;
import Utiles.JInternalFrame_Modelo;

public class Utiles_Panel {

    public Utiles_Panel() {
    }
        
    public static javax.swing.JFrame creaVentana( javax.swing.JPanel panel, String t ){
        javax.swing.JFrame jf = new javax.swing.JFrame( t );
        jf.setContentPane( panel );        
        /*ImageIcon icono = new ImageIcon("pck_imagenes/logout.png");
        jf.setIconImage(icono.getImage());*/
        jf.pack();
        jf.setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );
        jf.setLocationRelativeTo( null );
        jf.setVisible( true );
        return jf;
    }
    
    
    public static javax.swing.JDialog creaVentanaDialogo( javax.swing.JFrame propietario, javax.swing.JPanel panel, String t ){
        javax.swing.JDialog jd = new javax.swing.JDialog( propietario, t );
        jd.setContentPane( panel );
        jd.setModal( true );
        jd.setResizable( false );
        jd.pack();
        jd.setDefaultCloseOperation( javax.swing.JDialog.DO_NOTHING_ON_CLOSE );
        jd.setLocationRelativeTo( propietario );
        return jd;
    }    
    
    public static javax.swing.JInternalFrame creaVentanaInterna( javax.swing.JFrame padre, javax.swing.JPanel panel, String t ){        
        javax.swing.JInternalFrame vi = new javax.swing.JInternalFrame( t, false, true, false, true );
        vi.setContentPane( panel );
        vi.pack();
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) padre.getContentPane();
        jdp.add( vi );
        vi.setDefaultCloseOperation( javax.swing.JInternalFrame.DISPOSE_ON_CLOSE );
        vi.show();
        return vi;
    }
    //(vale)
    public static Utiles.JInternalFrame_Modelo creaVentanaInterna_Panel( org.jdesktop.application.FrameView padre, javax.swing.JPanel panel, String t ){
        JInternalFrame_Modelo vi = new Utiles.JInternalFrame_Modelo(panel, t, false, true, false, true );
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) padre.getComponent();
        jdp.add( vi );
        vi.setLocation(20,20);
        vi.show();
        return vi;
    }
    //Método creado para la aplicación FrameView
    public static javax.swing.JInternalFrame creaVentanaInterna( org.jdesktop.application.FrameView padre, javax.swing.JPanel panel, String t ){
        javax.swing.JInternalFrame vi = new javax.swing.JInternalFrame( t, false, true, false, true );
        vi.setContentPane( panel );
        vi.pack();
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) padre.getComponent();
        jdp.add( vi );
        vi.setDefaultCloseOperation( javax.swing.JInternalFrame.DISPOSE_ON_CLOSE );
        vi.show();
        return vi;
    }

    public static javax.swing.JInternalFrame creaVentanaInterna( org.jdesktop.application.FrameView padre,
            javax.swing.JPanel panel, String title, boolean resizable, boolean closable,
            boolean maximizable, boolean iconifiable ){

        javax.swing.JInternalFrame vi = new javax.swing.JInternalFrame( title, resizable, closable, maximizable, iconifiable );
        vi.setContentPane( panel );
        vi.pack();
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) padre.getComponent();
        jdp.add( vi );
        vi.setDefaultCloseOperation( javax.swing.JInternalFrame.DISPOSE_ON_CLOSE );
        vi.show();
        return vi;
    }

    public static javax.swing.JInternalFrame creaVentanaInternaModal( javax.swing.JFrame vpadre, javax.swing.JPanel panel, String t ){
        //Creo la Ventana Iterna
        javax.swing.JInternalFrame vimodal = new javax.swing.JInternalFrame( t, false, true, false, true );
        vimodal.setContentPane( panel );
        //Agrego la ventana interna al contenedor principal de la ventana padre
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) vpadre.getContentPane();
        jdp.add( vimodal );
               
        //Establezco mi glass pane que voy a mostrar con la ventana interna para este caso un JDesktopPane ya que hereda de un Component y q administra mejor los JInternalFrame, tambien obtengo el layered pane del frame que lo voy a agregar
        javax.swing.JDesktopPane glass = new javax.swing.JDesktopPane();
        javax.swing.JLayeredPane layer = vpadre.getLayeredPane();
        //Hago mi glass transparente para que al mostrarlo no opaque lo que se encuentra en el contentpane
        glass.setOpaque( false );
        //Agrego la ventana interna al glass y este a su vez lo coloco en la ventana padre
        glass.add( vimodal );
        vpadre.setGlassPane( glass );
        //Damos el comportamiento de modal
        vimodal.addInternalFrameListener( new ModalAdapter(glass) );
   
        //Redimensionando el glass al tama�o del LayaredPane y a este le colocamos la propiedad de modal
        //Hago esto puesto que si yo coloco un combobox, este tiene problemas al visualizarse y lo soluciono colocando el layared pane como modal 
        glass.setSize (layer.getSize()); 
        layer.add(glass, javax.swing.JLayeredPane.MODAL_LAYER, 0);
        vimodal.pack();
        vimodal.setDefaultCloseOperation( javax.swing.JInternalFrame.DISPOSE_ON_CLOSE );        
        //Muestro el glass pane 
        glass.setVisible( true );
        vimodal.show();
        return vimodal;
    }

    //Método creado para la aplicación FrameView (vale para ventana mayormente buscar)
    public static Utiles.JInternalFrame_Modelo creaVentanaInternaModal( org.jdesktop.application.FrameView vpadre, javax.swing.JPanel panel, String t ){
        //Creo la Ventana Iterna
        JInternalFrame_Modelo vimodal = new Utiles.JInternalFrame_Modelo(panel, t, false, true, false, false );
        vimodal.setContentPane( panel );
        //Agrego la ventana interna al contenedor principal de la ventana padre
        javax.swing.JDesktopPane jdp = ( javax.swing.JDesktopPane ) vpadre.getComponent();
        jdp.add( vimodal );
        //Establezco mi glass pane que voy a mostrar con la ventana interna para este caso un JDesktopPane ya que hereda de un Component y q administra mejor los JInternalFrame, tambien obtengo el layered pane del frame que lo voy a agregar
        javax.swing.JDesktopPane glass = new javax.swing.JDesktopPane();
        javax.swing.JLayeredPane layer = vpadre.getFrame().getLayeredPane();
        //Hago mi glass transparente para que al mostrarlo no opaque lo que se encuentra en el contentpane
        glass.setBorder(null);
        glass.setOpaque( false );
        //Agrego la ventana interna al glass y este a su vez lo coloco en la ventana padre
        glass.add( vimodal );
        vpadre.getFrame().setGlassPane( glass );
        //Damos el comportamiento de modal
        vimodal.addInternalFrameListener( new ModalAdapter(glass) );
        //Redimensionando el glass al tama�o del LayaredPane y a este le colocamos la propiedad de modal
        //Hago esto puesto que si yo coloco un combobox, este tiene problemas al visualizarse y lo soluciono colocando el layared pane como modal
        glass.setSize (layer.getSize());
        layer.add(glass, javax.swing.JLayeredPane.MODAL_LAYER, 0);
        vimodal.pack();
        vimodal.setDefaultCloseOperation( javax.swing.JInternalFrame.DISPOSE_ON_CLOSE );
        //Muestro el glass pane
        glass.setVisible( true );
        vimodal.setLocation(50,150);
        vimodal.show();
        return vimodal;
    }
    
    //Clase interna que establece comportamiento modal
    static class ModalAdapter extends javax.swing.event.InternalFrameAdapter {	
        public ModalAdapter(java.awt.Component glass) {
            this.glass = glass;
                // Asociando los eventos del mouse
                javax.swing.event.MouseInputAdapter adapter = new javax.swing.event.MouseInputAdapter(){};
                glass.addMouseListener(adapter);
                glass.addMouseMotionListener(adapter);
        }

        @Override
        public void internalFrameClosed( javax.swing.event.InternalFrameEvent e ) {
                glass.setVisible(false);
            }

        java.awt.Component glass;
    }
    
    public static int TIPO_JFRAME = 1;
    public static int TIPO_JDIALOG = 2;
    public static int TIPO_JINTERNALFRAME = 3;
    
}
