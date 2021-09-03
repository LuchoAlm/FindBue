package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarAdultoMayor extends AppCompatActivity {
    public Button continuar, cancelar;
    EditText nombresCompletosAM, correoAM, direccionDomAM, telefonoMovAM,fechaNacAM, sexoAM
            ,enfermedadesAM, medicamentosAM, personaEncargadaAM, descripcionFisicaAM;
            //, ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_registrar_adulto_mayor);

        continuar = (Button) findViewById(R.id.buttonRegistrarFam);
        cancelar = (Button) findViewById(R.id.buttonCancelar);

        nombresCompletosAM = (EditText) findViewById(R.id.editTextTextPersonName2);
        correoAM = (EditText) findViewById(R.id.editTextTextPersonName3);
        direccionDomAM = (EditText) findViewById(R.id.editTextTextPersonName);
        telefonoMovAM = (EditText) findViewById(R.id.editTextTextPersonName5);
        fechaNacAM = (EditText) findViewById(R.id.editTextTextPersonName6);
        sexoAM = (EditText) findViewById(R.id.editTextTextPersonName7);
        enfermedadesAM = (EditText) findViewById(R.id.editTextTextPersonName8);
        medicamentosAM = (EditText) findViewById(R.id.editTextTextPersonName9);
        personaEncargadaAM = (EditText) findViewById(R.id.editTextTextPersonName10);
        descripcionFisicaAM = (EditText) findViewById(R.id.editTextTextPersonName11);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validarNombresAM() || !validateCorreo() || !validarDireccionDomAM() ||
                !validateTelefonoMov() || !validarEnfermedades() || !validarSexo() || !validarDescrFisicaAM() ||
                !validarDescrFisicaAM() || !validarMedicamentos() || !validarPersonaEncargada()){
                    validar();
                    return;
                }else{
                    System.out.println("Datos ingresados correctamente");
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("datosPersonalesDelAdultoMayor");
                AdultoMayor_DatosPersonales adultoMayorDatosPersonales;
                adultoMayorDatosPersonales = new AdultoMayor_DatosPersonales(nombresCompletosAM.getText().toString(),
                        correoAM.getText().toString(),
                        direccionDomAM.getText().toString(),
                        telefonoMovAM.getText().toString(),
                        fechaNacAM.getText().toString(),
                        sexoAM.getText().toString(),
                        enfermedadesAM.getText().toString(),
                        medicamentosAM.getText().toString(),
                        personaEncargadaAM.getText().toString(),
                        descripcionFisicaAM.getText().toString());
                myref.push().setValue(adultoMayorDatosPersonales);
                Intent intent =  new Intent( RegistrarAdultoMayor.this, RegUbiAdultoMayor.class);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarAdultoMayor.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validarNombresAM() {

        String val = nombresCompletosAM.getText().toString();

        if(val.isEmpty()) {
            nombresCompletosAM.setError("Campo obligatorio");
            return false;
        } else {
            nombresCompletosAM.setError(null);
            return true;
        }

    }

    public Boolean validarDireccionDomAM() {

        String val = direccionDomAM.getText().toString();

        if(val.isEmpty()) {
            direccionDomAM.setError("Campo obligatorio");
            return false;
        } else {
            direccionDomAM.setError(null);
            return true;
        }

    }

    public Boolean validateTelefonoMov() {

        String val = telefonoMovAM.getText().toString();
        String patronTelefono = "09[0-9]{8}";

        if(val.isEmpty()) {
            telefonoMovAM.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronTelefono)) {
            telefonoMovAM.setError("Teléfono no válido");
            return false;
        } else {
            telefonoMovAM.setError(null);
            return true;
        }

    }

    public Boolean validateCorreo() {

        String val = correoAM.getText().toString();
        String patronCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            correoAM.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronCorreo)) {
            correoAM.setError("Correo no válido");
            return false;
        } else {
            correoAM.setError(null);
            return true;
        }
    }

    public Boolean validarEnfermedades() {

        String val = enfermedadesAM.getText().toString();

        if(val.isEmpty()) {
            enfermedadesAM.setError("Campo requerido");
            return false;
        } else {
            enfermedadesAM.setError(null);
            return true;
        }

    }

    public Boolean validarFechaNacAM(){
        String val = fechaNacAM.getText().toString();

        if(val.isEmpty()){
            fechaNacAM.setError("Campo requerido");
            return false;
        }else{
            try{
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
                formatoFecha.setLenient(false);
                formatoFecha.parse(val);
            }catch(Exception e){
                fechaNacAM.setError("Fecha incorrecta");
                return false;
            }
            fechaNacAM.setError(null);
            return true;
        }
    }

    public Boolean validarSexo(){
        String val = sexoAM.getText().toString();

        if(val.isEmpty()){
            sexoAM.setError("Campo requerido");
            return false;
        }else{
            sexoAM.setError(null);
            return true;
        }
    }

    public Boolean validarMedicamentos() {

        String val = medicamentosAM.getText().toString();

        if(val.isEmpty()) {
            medicamentosAM.setError("Campo requerido");
            return false;
        } else {
            medicamentosAM.setError(null);
            return true;
        }

    }

    public Boolean validarPersonaEncargada() {

        String val = medicamentosAM.getText().toString();

        if(val.isEmpty()) {
            medicamentosAM.setError("Campo requerido");
            return false;
        } else {
            medicamentosAM.setError(null);
            return true;
        }

    }

    public Boolean validarDescrFisicaAM(){
        String val = descripcionFisicaAM.getText().toString();

        if(val.isEmpty()){
            descripcionFisicaAM.setError("Campo requerido");
            return false;
        }else{
            descripcionFisicaAM.setError(null);
            return true;
        }
    }

    public void validar(){
        validarNombresAM();
        validateCorreo();
        validateTelefonoMov();
        validarMedicamentos();
        validarDireccionDomAM();
        validarSexo();
        validarEnfermedades();
        validarFechaNacAM();
        validarPersonaEncargada();
        validarDescrFisicaAM();
    }
}