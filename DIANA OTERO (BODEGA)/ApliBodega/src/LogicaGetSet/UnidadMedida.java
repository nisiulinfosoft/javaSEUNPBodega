/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class UnidadMedida {

    private String codigo_uni;
    private String nombre_uni;
    private String descripcion_uni;

    public UnidadMedida() {
    }

    public UnidadMedida(String codigo_uni, String nombre_uni, String descripcion_uni) {
        this.codigo_uni = codigo_uni;
        this.nombre_uni = nombre_uni;
        this.descripcion_uni = descripcion_uni;
    }

    public String getCodigo_uni() {
        return codigo_uni;
    }

    public void setCodigo_uni(String codigo_uni) {
        this.codigo_uni = codigo_uni;
    }

    public String getDescripcion_uni() {
        return descripcion_uni;
    }

    public void setDescripcion_uni(String descripcion_uni) {
        this.descripcion_uni = descripcion_uni;
    }

    public String getNombre_uni() {
        return nombre_uni;
    }

    public void setNombre_uni(String nombre_uni) {
        this.nombre_uni = nombre_uni;
    }
}
