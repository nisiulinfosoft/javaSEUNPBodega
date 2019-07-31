/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Proveedor {

    private String codigo_prov;
    private String ruc_prov;
    private String razon_social_prov;
    private String direccion_prov;
    private String telefono_prov;
    private String email_prov;
    private String observacion_prov;

    public Proveedor() {
    }

    public Proveedor(String codigo_prov, String ruc_prov, String razon_social_prov, String direccion_prov, String telefono_prov, String email_prov, String observacion_prov) {
        this.codigo_prov = codigo_prov;
        this.ruc_prov = ruc_prov;
        this.razon_social_prov = razon_social_prov;
        this.direccion_prov = direccion_prov;
        this.telefono_prov = telefono_prov;
        this.email_prov = email_prov;
        this.observacion_prov = observacion_prov;
    }

    public String getCodigo_prov() {
        return codigo_prov;
    }

    public void setCodigo_prov(String codigo_prov) {
        this.codigo_prov = codigo_prov;
    }

    public String getDireccion_prov() {
        return direccion_prov;
    }

    public void setDireccion_prov(String direccion_prov) {
        this.direccion_prov = direccion_prov;
    }

    public String getEmail_prov() {
        return email_prov;
    }

    public void setEmail_prov(String email_prov) {
        this.email_prov = email_prov;
    }

    public String getObservacion_prov() {
        return observacion_prov;
    }

    public void setObservacion_prov(String observacion_prov) {
        this.observacion_prov = observacion_prov;
    }

    public String getRazon_social_prov() {
        return razon_social_prov;
    }

    public void setRazon_social_prov(String razon_social_prov) {
        this.razon_social_prov = razon_social_prov;
    }

    public String getRuc_prov() {
        return ruc_prov;
    }

    public void setRuc_prov(String ruc_prov) {
        this.ruc_prov = ruc_prov;
    }

    public String getTelefono_prov() {
        return telefono_prov;
    }

    public void setTelefono_prov(String telefono_prov) {
        this.telefono_prov = telefono_prov;
    }
}
