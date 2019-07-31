/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Cliente {

    private String codigo_cli;
    private String ruc_cli;
    private String razon_social_cli;
    private String direccion_cli;

    public Cliente() {
    }

    public Cliente(String codigo_cli, String ruc_cli, String razon_social_cli, String direccion_cli) {
        this.codigo_cli = codigo_cli;
        this.ruc_cli = ruc_cli;
        this.razon_social_cli = razon_social_cli;
        this.direccion_cli = direccion_cli;
    }

    public String getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(String codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getDireccion_cli() {
        return direccion_cli;
    }

    public void setDireccion_cli(String direccion_cli) {
        this.direccion_cli = direccion_cli;
    }

    public String getRazon_social_cli() {
        return razon_social_cli;
    }

    public void setRazon_social_cli(String razon_social_cli) {
        this.razon_social_cli = razon_social_cli;
    }

    public String getRuc_cli() {
        return ruc_cli;
    }

    public void setRuc_cli(String ruc_cli) {
        this.ruc_cli = ruc_cli;
    }
}
