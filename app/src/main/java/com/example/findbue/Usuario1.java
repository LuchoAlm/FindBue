package com.example.findbue;

public class Usuario1 {
    String nombre, correo, telefono, img;

    public Usuario1() {
    }

    public Usuario1(String nombre, String correo, String telefono, String img) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
