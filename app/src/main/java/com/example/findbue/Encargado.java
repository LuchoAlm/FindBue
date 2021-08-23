package com.example.findbue;

public class Encargado {
    String correoEnc, correoAdultoM;

    public Encargado() {
    }

    public Encargado(String correoEnc, String correoAdultoM) {
        this.correoEnc = correoEnc;
        this.correoAdultoM = correoAdultoM;
    }

    public String getCorreoEnc() {
        return correoEnc;
    }

    public void setCorreoEnc(String correoEnc) {
        this.correoEnc = correoEnc;
    }

    public String getCorreoAdultoM() {
        return correoAdultoM;
    }

    public void setCorreoAdultoM(String correoAdultoM) {
        this.correoAdultoM = correoAdultoM;
    }
}
