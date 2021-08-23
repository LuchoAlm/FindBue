package com.example.findbue;

public class AdultoMayor {
    public String getNombresCompletosAM() {
        return nombresCompletosAM;
    }

    public void setNombresCompletosAM(String nombresCompletosAM) {
        this.nombresCompletosAM = nombresCompletosAM;
    }

    public String getCorreoAM() {
        return correoAM;
    }

    public void setCorreoAM(String correoAM) {
        this.correoAM = correoAM;
    }

    public String getDireccionDomAM() {
        return direccionDomAM;
    }

    public void setDireccionDomAM(String direccionDomAM) {
        this.direccionDomAM = direccionDomAM;
    }

    public String getTelefonoMovAM() {
        return telefonoMovAM;
    }

    public void setTelefonoMovAM(String telefonoMovAM) {
        this.telefonoMovAM = telefonoMovAM;
    }

    public String getFechaNacAM() {
        return fechaNacAM;
    }

    public void setFechaNacAM(String fechaNacAM) {
        this.fechaNacAM = fechaNacAM;
    }

    public String getSexoAM() {
        return sexoAM;
    }

    public void setSexoAM(String sexoAM) {
        this.sexoAM = sexoAM;
    }

    public String getEnfermedadesAM() {
        return enfermedadesAM;
    }

    public void setEnfermedadesAM(String enfermedadesAM) {
        this.enfermedadesAM = enfermedadesAM;
    }

    public String getMedicamentosAM() {
        return medicamentosAM;
    }

    public void setMedicamentosAM(String medicamentosAM) {
        this.medicamentosAM = medicamentosAM;
    }

    public String getPersonaEncargadaAM() {
        return personaEncargadaAM;
    }

    public void setPersonaEncargadaAM(String personaEncargadaAM) {
        this.personaEncargadaAM = personaEncargadaAM;
    }

    public String getDescripcionFisicaAM() {
        return descripcionFisicaAM;
    }

    public void setDescripcionFisicaAM(String descripcionFisicaAM) {
        this.descripcionFisicaAM = descripcionFisicaAM;
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

    private String  nombresCompletosAM;
    private String correoAM;
    private String direccionDomAM;
    private String telefonoMovAM;
    private String fechaNacAM;
    private String sexoAM;
    private String enfermedadesAM;
    private String medicamentosAM;
    private String personaEncargadaAM;
    private String descripcionFisicaAM;
    private String ubicacionDomAM;
    private String latitudAM;
    private String longitudAM;

    public String getMetrosPermitidosAM() {
        return metrosPermitidosAM;
    }

    public void setMetrosPermitidosAM(String metrosPermitidosAM) {
        this.metrosPermitidosAM = metrosPermitidosAM;
    }

    private String metrosPermitidosAM;

    public AdultoMayor(String nombresCompletosAM,
                       String correoAM,
                       String direccionDomAM,
                       String telefonoMovAM,
                       String fechaNacAM,
                       String sexoAM,
                       String enfermedadesAM,
                       String medicamentosAM,
                       String personaEncargadaAM,
                       String descripcionFisicaAM,
                       String ubicacionDomAM,
                       String latitudAM,
                       String longitudAM,
                       String metrosPermitidosAM) {
        this.nombresCompletosAM=nombresCompletosAM;
        this.correoAM=correoAM;
        this.direccionDomAM=direccionDomAM;
        this.telefonoMovAM=telefonoMovAM;
        this.fechaNacAM=fechaNacAM;
        this.sexoAM=sexoAM;
        this.enfermedadesAM=enfermedadesAM;
        this.medicamentosAM=medicamentosAM;
        this.personaEncargadaAM=personaEncargadaAM;
        this.descripcionFisicaAM=descripcionFisicaAM;
        this.ubicacionDomAM=ubicacionDomAM;
        this.latitudAM=latitudAM;
        this.longitudAM=longitudAM;
        this.metrosPermitidosAM=metrosPermitidosAM;
    }

    public AdultoMayor() {
    }

    public void conectarDB(){
        //Logica de la base de datos
    }
}
