/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaGetSet;

/**
 *
 * @author Luisin Enrique
 */
public class Usuario {

    private String codigo_usu;
    private String codigo_per;
    private String login_usu;
    private String pass_usu;
    private String administracion_usu;
    private String venta_per;
    private String consultas_usu;
    private String reportes_usu;

    public Usuario() {
    }

    public Usuario(String codigo_usu, String codigo_per, String login_usu, String pass_usu, String administracion_usu, String venta_per, String consultas_usu, String reportes_usu) {
        this.codigo_usu = codigo_usu;
        this.codigo_per = codigo_per;
        this.login_usu = login_usu;
        this.pass_usu = pass_usu;
        this.administracion_usu = administracion_usu;
        this.venta_per = venta_per;
        this.consultas_usu = consultas_usu;
        this.reportes_usu = reportes_usu;
    }

    public String getAdministracion_usu() {
        return administracion_usu;
    }

    public void setAdministracion_usu(String administracion_usu) {
        this.administracion_usu = administracion_usu;
    }

    public String getCodigo_per() {
        return codigo_per;
    }

    public void setCodigo_per(String codigo_per) {
        this.codigo_per = codigo_per;
    }

    public String getCodigo_usu() {
        return codigo_usu;
    }

    public void setCodigo_usu(String codigo_usu) {
        this.codigo_usu = codigo_usu;
    }

    public String getConsultas_usu() {
        return consultas_usu;
    }

    public void setConsultas_usu(String consultas_usu) {
        this.consultas_usu = consultas_usu;
    }

    public String getLogin_usu() {
        return login_usu;
    }

    public void setLogin_usu(String login_usu) {
        this.login_usu = login_usu;
    }

    public String getPass_usu() {
        return pass_usu;
    }

    public void setPass_usu(String pass_usu) {
        this.pass_usu = pass_usu;
    }

    public String getReportes_usu() {
        return reportes_usu;
    }

    public void setReportes_usu(String reportes_usu) {
        this.reportes_usu = reportes_usu;
    }

    public String getVenta_per() {
        return venta_per;
    }

    public void setVenta_per(String venta_per) {
        this.venta_per = venta_per;
    }
}
