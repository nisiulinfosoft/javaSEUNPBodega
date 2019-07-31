package Utiles;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class Cls_Validar_CampoTexto {

    //METODO PARA VALIDAR CAJA DE TEXTOS SOLO NUMEROS ENTEROS
    public static void ValidarSoloNumerosEnteros(JTextField CajaText,KeyEvent evt){
        int e=(int)evt.getKeyChar();
        if ((e >= 32 && e <= 47) || (e>=58 && e<=126) || (e>=128 && e<=255)){ //ACEPTA SOLO NUMEROS ENTEROS Y si pongo "e >= 32 && e <= 45" acepta el punto que sirve para numeros decimales
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);//BORRA EL CARACTER ESCRITO EN AL CAJA DE TEXTO
            JOptionPane.showMessageDialog(null,"Solo números enteros porfavor","ERROR DE INGRESO",JOptionPane.ERROR_MESSAGE);
        }
        if(e==10){
            CajaText.transferFocus();
        }
        //ESTO SE PUEDE REEMPLAZAR POR evt.setKeyChar((char)KeyEvent.VK_CLEAR):
        /*Toolkit.getDefaultToolkit().beep();
        evt.consume();*/
        //e==47 es el : "/ (eslai)"
        //e==46 es el : ". (punto)"
    }

    //METODO PARA VALIDAR CAJA DE TEXTOS SOLO NUMEROS ENTEROS Y DECIMALES
    public static void ValidarSoloNumerosEnterosDecimales(JTextField CajaText,KeyEvent evt){
        int e=(int)evt.getKeyChar();
        if ((e >= 32 && e <= 45) || e==47 || (e>=58 && e<=126) || (e>=128 && e<=255)){ //ACEPTA SOLO NUMEROS ENTEROS Y si pongo "e >= 32 && e <= 45" acepta el punto que sirve para numeros decimales
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);//BORRA EL CARACTER ESCRITO EN AL CAJA DE TEXTO
            JOptionPane.showMessageDialog(null,"Solo números enteros o decimales porfavor","ERROR DE INGRESO",JOptionPane.ERROR_MESSAGE);
        }
        if(e==10){
            CajaText.transferFocus();
        }
    }

    //METODO PARA VALIDAR CAJA DE TEXTO SOLO LETRAS
    public static void ValidarSoloLetras(JTextField CajaText,KeyEvent evt){
        int k = (int) evt.getKeyChar();
        if (k > 47 && k < 58) { //ACEPTA SOLO LETRAS Y EL PUNTO y otros caracteres
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//BORRA EL CARACTER ESCRITO EN LA CAJA DE TEXTO
            JOptionPane.showMessageDialog(null, "Solo letras porfavor", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            CajaText.transferFocus();
        }
        //del 48 al 57 es del "0 al 9"
    }

    //METODO PARA LIMITAR CARACTERES EN UNA CAJA DE TEXTO
    public static void LimitarCaracteres(JTextField CajaText,int limite,KeyEvent evt){
        if (CajaText.getText().length()== limite){
            evt.consume();
        }
    }
    
    public static void remplazarMinusculasMayusculas()
   {
        //el dispatcher se registra en forma global, por lo que es recomendable hacerlo dentro del frame principal
        //primero obtenemos le FocusManager (que a su vez es el KeyEventDispatcher)
        // responsable de la gestión de las ventanas activas y focalizadas
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        //y enseguida registramos nuestro dispatcher
        manager.addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e) {
                //solo las notificaciones del tipo "typed" son las que actualizan los componentes
                if(e.getID() == KeyEvent.KEY_TYPED){
                        //como vamos a convertir todo a mayúsculas, entonces solo checamos si los caracteres son
                        //minusculas
                        if(e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z'){
                                //si lo son, entonces lo reemplazamos por su respectivo en mayúscula en el mismo evento
                                // (esto se logra por que en java todas las variables son pasadas por referencia)
                                e.setKeyChar((char)(((int)e.getKeyChar()) - 32));
                        }
                }
                //y listo, regresamos siempre falso para que las demas notificaciones continuen, si regresamos true
                // significa que el dispatcher consumio el evento
                return false;
                }
            });
   }
    public static String formatoFecha(int año,int mes,int dia)
    {
        String fecha="";
        String añ=String.valueOf(año);
        String me="";
        String di="";
        if(mes<10)
             me="0"+String.valueOf(mes);
        else me=String.valueOf(mes);
        if(dia<10)
            di="0"+String.valueOf(dia);
        else di=String.valueOf(dia);
        fecha=añ+"-"+me+"-"+di;
        return fecha;
    }
    public static int month(String m) { // Retorna un numero entre 0 y 11 que corresponde al mes

		int mo = 0;

		if(m.equals("Ene") || m.equals("Jan"))
		mo = 1;
		else if(m.equals("Feb"))
		mo = 2;
		else if(m.equals("Mar"))
		mo = 3;
		else if(m.equals("Abr") || m.equals("Apr"))
		mo = 4;
		else if(m.equals("May"))
		mo = 5;
		else if(m.equals("Jun"))
		mo = 6;
		else if(m.equals("Jul"))
		mo = 7;
		else if(m.equals("Ago") || m.equals("Aug"))
		mo = 8;
		else if(m.equals("Sep") || m.equals("Set"))
		mo = 9;
		else if(m.equals("Oct"))
		mo = 10;
		else if(m.equals("Nov"))
		mo = 11;
		else
		mo = 12;
		return(mo);
	}// month
}
