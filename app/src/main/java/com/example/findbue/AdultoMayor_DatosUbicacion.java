package com.example.findbue;

public class AdultoMayor_DatosUbicacion {
    String ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM;

    public AdultoMayor_DatosUbicacion() {
    }

    public AdultoMayor_DatosUbicacion(String ubicacionDomAM, String latitudAM, String longitudAM, String metrosPermitidosAM) {
        this.ubicacionDomAM = ubicacionDomAM;
        this.latitudAM = latitudAM;
        this.longitudAM = longitudAM;
        this.metrosPermitidosAM = metrosPermitidosAM;
    }

    public String getUbicacionDomAM() {
        return ubicacionDomAM;
    }

    public void setUbicacionDomAM(String ubicacionDomAM) {
        this.ubicacionDomAM = ubicacionDomAM;
    }

    public String getLatitudAM() {
        return latitudAM;
    }

    public void setLatitudAM(String latitudAM) {
        this.latitudAM = latitudAM;
    }

    public String getLongitudAM() {
        return longitudAM;
    }

    public void setLongitudAM(String longitudAM) {
        this.longitudAM = longitudAM;
    }

    public String getMetrosPermitidosAM() {
        return metrosPermitidosAM;
    }

    public void setMetrosPermitidosAM(String metrosPermitidosAM) {
        this.metrosPermitidosAM = metrosPermitidosAM;
    }
}
