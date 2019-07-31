/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Cargo {

    private String codigo_car;
    private String nombre_car;
    private String descripcion_car;

    public Cargo() {
    }

    public Cargo(String codigo_car, String nombre_car, String descripcion_car) {
        this.codigo_car = codigo_car;
        this.nombre_car = nombre_car;
        this.descripcion_car = descripcion_car;
    }

    public String getCodigo_car() {
        return codigo_car;
    }

    public void setCodigo_car(String codigo_car) {
        this.codigo_car = codigo_car;
    }

    public String getDescripcion_car() {
        return descripcion_car;
    }

    public void setDescripcion_car(String descripcion_car) {
        this.descripcion_car = descripcion_car;
    }

    public String getNombre_car() {
        return nombre_car;
    }

    public void setNombre_car(String nombre_car) {
        this.nombre_car = nombre_car;
    }
}
