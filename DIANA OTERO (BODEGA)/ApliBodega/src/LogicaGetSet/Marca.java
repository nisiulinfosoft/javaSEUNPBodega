/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Marca {

    private String codigo_mar;
    private String nombre_mar;
    private String descripcion_mar;

    public Marca() {
    }

    public Marca(String codigo_mar, String nombre_mar, String descripcion_mar) {
        this.codigo_mar = codigo_mar;
        this.nombre_mar = nombre_mar;
        this.descripcion_mar = descripcion_mar;
    }

    public String getCodigo_mar() {
        return codigo_mar;
    }

    public void setCodigo_mar(String codigo_mar) {
        this.codigo_mar = codigo_mar;
    }

    public String getDescripcion_mar() {
        return descripcion_mar;
    }

    public void setDescripcion_mar(String descripcion_mar) {
        this.descripcion_mar = descripcion_mar;
    }

    public String getNombre_mar() {
        return nombre_mar;
    }

    public void setNombre_mar(String nombre_mar) {
        this.nombre_mar = nombre_mar;
    }
}
