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
public class Boleta {

    private String codigo_bol;
    private String codigo_emp;
    private String codigo_cli;
    private Date fecha_bol;

    public Boleta() {
    }

    public Boleta(String codigo_bol, String codigo_emp, String codigo_cli, Date fecha_bol) {
        this.codigo_bol = codigo_bol;
        this.codigo_emp = codigo_emp;
        this.codigo_cli = codigo_cli;
        this.fecha_bol = fecha_bol;
    }

    public String getCodigo_bol() {
        return codigo_bol;
    }

    public void setCodigo_bol(String codigo_bol) {
        this.codigo_bol = codigo_bol;
    }

    public String getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(String codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getCodigo_emp() {
        return codigo_emp;
    }

    public void setCodigo_emp(String codigo_emp) {
        this.codigo_emp = codigo_emp;
    }

    public Date getFecha_bol() {
        return fecha_bol;
    }

    public void setFecha_bol(Date fecha_bol) {
        this.fecha_bol = fecha_bol;
    }
}
