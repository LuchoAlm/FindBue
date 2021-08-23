package com.example.findbue;

public class Usuario {
    String correo, password, nombreCompleto, direccionDom, telefonoMov;

    public Usuario() {
    }

    public Usuario(String correo, String password, String nombreCompleto, String direccionDom, String telefonoMov) {
        this.correo = correo;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.direccionDom = direccionDom;
        this.telefonoMov = telefonoMov;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccionDom() {
        return direccionDom;
    }

    public void setDireccionDom(String direccionDom) {
        this.direccionDom = direccionDom;
    }

    public String getTelefonoMov() {
        return telefonoMov;
    }

    public void setTelefonoMov(String telefonoMov) {
        this.telefonoMov = telefonoMov;
    }
}
