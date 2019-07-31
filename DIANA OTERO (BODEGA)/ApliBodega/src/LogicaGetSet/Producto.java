/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Producto {

    private String codigo_pro;
    private String codigo_prov;
    private String codigo_cat;
    private String codigo_mar;
    private String codigo_uni;
    private String nombre_pro;
    private int stock_max_pro;
    private int stock_min_pro;
    private double precio_compra_pro;
    private double precio_venta_pro;
    private Imagen foto_pro;
    private String observacion_pro;

    public Producto() {
    }

    public Producto(String codigo_pro, String codigo_prov, String codigo_cat, String codigo_mar, String codigo_uni, String nombre_pro, int stock_max_pro, int stock_min_pro, double precio_compra_pro, double precio_venta_pro, Imagen foto_pro, String observacion_pro) {
        this.codigo_pro = codigo_pro;
        this.codigo_prov = codigo_prov;
        this.codigo_cat = codigo_cat;
        this.codigo_mar = codigo_mar;
        this.codigo_uni = codigo_uni;
        this.nombre_pro = nombre_pro;
        this.stock_max_pro = stock_max_pro;
        this.stock_min_pro = stock_min_pro;
        this.precio_compra_pro = precio_compra_pro;
        this.precio_venta_pro = precio_venta_pro;
        this.foto_pro = foto_pro;
        this.observacion_pro = observacion_pro;
    }

    public String getCodigo_cat() {
        return codigo_cat;
    }

    public void setCodigo_cat(String codigo_cat) {
        this.codigo_cat = codigo_cat;
    }

    public String getCodigo_mar() {
        return codigo_mar;
    }

    public void setCodigo_mar(String codigo_mar) {
        this.codigo_mar = codigo_mar;
    }

    public String getCodigo_pro() {
        return codigo_pro;
    }

    public void setCodigo_pro(String codigo_pro) {
        this.codigo_pro = codigo_pro;
    }

    public String getCodigo_prov() {
        return codigo_prov;
    }

    public void setCodigo_prov(String codigo_prov) {
        this.codigo_prov = codigo_prov;
    }

    public String getCodigo_uni() {
        return codigo_uni;
    }

    public void setCodigo_uni(String codigo_uni) {
        this.codigo_uni = codigo_uni;
    }

    public Imagen getFoto_pro() {
        return foto_pro;
    }

    public void setFoto_pro(Imagen foto_pro) {
        this.foto_pro = foto_pro;
    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public String getObservacion_pro() {
        return observacion_pro;
    }

    public void setObservacion_pro(String observacion_pro) {
        this.observacion_pro = observacion_pro;
    }

    public double getPrecio_compra_pro() {
        return precio_compra_pro;
    }

    public void setPrecio_compra_pro(double precio_compra_pro) {
        this.precio_compra_pro = precio_compra_pro;
    }

    public double getPrecio_venta_pro() {
        return precio_venta_pro;
    }

    public void setPrecio_venta_pro(double precio_venta_pro) {
        this.precio_venta_pro = precio_venta_pro;
    }

    public int getStock_max_pro() {
        return stock_max_pro;
    }

    public void setStock_max_pro(int stock_max_pro) {
        this.stock_max_pro = stock_max_pro;
    }

    public int getStock_min_pro() {
        return stock_min_pro;
    }

    public void setStock_min_pro(int stock_min_pro) {
        this.stock_min_pro = stock_min_pro;
    }
}
