package com.example.findbue;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    String correo, password, nombreCompleto, direccionDom, telefonoMov;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Usuario() {
    }

    public Usuario(String correo, String password, String nombreCompleto, String direccionDom, String telefonoMov) {
        this.correo = correo;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.direccionDom = direccionDom;
        this.telefonoMov = telefonoMov;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("correo", correo);
        result.put("password", password);
        result.put("nombreCompleto", nombreCompleto);
        result.put("direccionDom", direccionDom);
        result.put("telefonoMov", telefonoMov);
        return result;
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
