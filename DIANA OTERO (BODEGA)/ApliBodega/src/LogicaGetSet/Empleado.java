/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

import java.util.Date;

/**
 *
 * @author Luisin Enrique
 */
public class Empleado {

    private String codigo_emp;
    private String codigo_car;
    private String dni_emp;
    private String nombre_emp;
    private String apellidos_emp;
    private String sexo_emp;
    private Date fecha_nacimiento_emp;
    private String direccion_emp;
    private String telefono_emp;
    private String celular_emp;
    private String email_emp;
    private Imagen foto_emp;
    private String observacion_emp;

    public Empleado() {
    }

    public Empleado(String codigo_emp, String codigo_car, String dni_emp, String nombre_emp, String apellidos_emp, String sexo_emp, Date fecha_nacimiento_emp, String direccion_emp, String telefono_emp, String celular_emp, String email_emp, Imagen foto_emp, String observacion_emp) {
        this.codigo_emp = codigo_emp;
        this.codigo_car = codigo_car;
        this.dni_emp = dni_emp;
        this.nombre_emp = nombre_emp;
        this.apellidos_emp = apellidos_emp;
        this.sexo_emp = sexo_emp;
        this.fecha_nacimiento_emp = fecha_nacimiento_emp;
        this.direccion_emp = direccion_emp;
        this.telefono_emp = telefono_emp;
        this.celular_emp = celular_emp;
        this.email_emp = email_emp;
        this.foto_emp = foto_emp;
        this.observacion_emp = observacion_emp;
    }

    public String getApellidos_emp() {
        return apellidos_emp;
    }

    public void setApellidos_emp(String apellidos_emp) {
        this.apellidos_emp = apellidos_emp;
    }

    public String getCelular_emp() {
        return celular_emp;
    }

    public void setCelular_emp(String celular_emp) {
        this.celular_emp = celular_emp;
    }

    public String getCodigo_car() {
        return codigo_car;
    }

    public void setCodigo_car(String codigo_car) {
        this.codigo_car = codigo_car;
    }

    public String getCodigo_emp() {
        return codigo_emp;
    }

    public void setCodigo_emp(String codigo_emp) {
        this.codigo_emp = codigo_emp;
    }

    public String getDireccion_emp() {
        return direccion_emp;
    }

    public void setDireccion_emp(String direccion_emp) {
        this.direccion_emp = direccion_emp;
    }

    public String getDni_emp() {
        return dni_emp;
    }

    public void setDni_emp(String dni_emp) {
        this.dni_emp = dni_emp;
    }

    public String getEmail_emp() {
        return email_emp;
    }

    public void setEmail_emp(String email_emp) {
        this.email_emp = email_emp;
    }

    public Date getFecha_nacimiento_emp() {
        return fecha_nacimiento_emp;
    }

    public void setFecha_nacimiento_emp(Date fecha_nacimiento_emp) {
        this.fecha_nacimiento_emp = fecha_nacimiento_emp;
    }

    public Imagen getFoto_emp() {
        return foto_emp;
    }

    public void setFoto_emp(Imagen foto_emp) {
        this.foto_emp = foto_emp;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getObservacion_emp() {
        return observacion_emp;
    }

    public void setObservacion_emp(String observacion_emp) {
        this.observacion_emp = observacion_emp;
    }

    public String getSexo_emp() {
        return sexo_emp;
    }

    public void setSexo_emp(String sexo_emp) {
        this.sexo_emp = sexo_emp;
    }

    public String getTelefono_emp() {
        return telefono_emp;
    }

    public void setTelefono_emp(String telefono_emp) {
        this.telefono_emp = telefono_emp;
    }
}
