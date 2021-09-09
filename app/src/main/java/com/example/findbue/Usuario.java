package com.example.findbue;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    //String imagen = "https://w7.pngwing.com/pngs/831/88/png-transparent-user-profile-computer-icons-user-interface-mystique-miscellaneous-user-interface-design-smile.png";
    String correo, password, nombreCompleto, direccionDom, telefonoMov, img;

    public Usuario() {
    }

    public Usuario(String correo, String password, String nombreCompleto, String direccionDom, String telefonoMov, String img) {
        this.nombreCompleto = nombreCompleto;
        this.direccionDom = direccionDom;
        this.telefonoMov = telefonoMov;
        this.correo = correo;
        this.password = password;
        this.img = img;
    }

    public  String getImg(){
        return "https://w7.pngwing.com/pngs/831/88/png-transparent-user-profile-computer-icons-user-interface-mystique-miscellaneous-user-interface-design-smile.png";
    }

    public void setImg(String img) {
        this.img = img;
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
