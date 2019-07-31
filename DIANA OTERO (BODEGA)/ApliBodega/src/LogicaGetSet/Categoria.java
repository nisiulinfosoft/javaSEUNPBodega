/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Categoria {

    private String codigo_cat;
    private String nombre_cat;
    private String descripcion_cat;

    public Categoria() {
    }

    public Categoria(String codigo_cat, String nombre_cat, String descripcion_cat) {
        this.codigo_cat = codigo_cat;
        this.nombre_cat = nombre_cat;
        this.descripcion_cat = descripcion_cat;
    }

    public String getCodigo_cat() {
        return codigo_cat;
    }

    public void setCodigo_cat(String codigo_cat) {
        this.codigo_cat = codigo_cat;
    }

    public String getDescripcion_cat() {
        return descripcion_cat;
    }

    public void setDescripcion_cat(String descripcion_cat) {
        this.descripcion_cat = descripcion_cat;
    }

    public String getNombre_cat() {
        return nombre_cat;
    }

    public void setNombre_cat(String nombre_cat) {
        this.nombre_cat = nombre_cat;
    }
}
