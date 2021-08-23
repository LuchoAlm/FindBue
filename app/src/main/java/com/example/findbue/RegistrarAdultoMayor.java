package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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
}