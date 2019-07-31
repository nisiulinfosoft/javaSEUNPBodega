/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class DetalleBoleta {

    private String codigo_bol;
    private String codigo_pro;
    private double precio_bol;
    private int cantidad_bol;

    public DetalleBoleta() {
    }

    public DetalleBoleta(String codigo_bol, String codigo_pro, double precio_bol, int cantidad_bol) {
        this.codigo_bol = codigo_bol;
        this.codigo_pro = codigo_pro;
        this.precio_bol = precio_bol;
        this.cantidad_bol = cantidad_bol;
    }

    public int getCantidad_bol() {
        return cantidad_bol;
    }

    public void setCantidad_bol(int cantidad_bol) {
        this.cantidad_bol = cantidad_bol;
    }

    public String getCodigo_bol() {
        return codigo_bol;
    }

    public void setCodigo_bol(String codigo_bol) {
        this.codigo_bol = codigo_bol;
    }

    public String getCodigo_pro() {
        return codigo_pro;
    }

    public void setCodigo_pro(String codigo_pro) {
        this.codigo_pro = codigo_pro;
    }

    public double getPrecio_bol() {
        return precio_bol;
    }

    public void setPrecio_bol(double precio_bol) {
        this.precio_bol = precio_bol;
    }
}
