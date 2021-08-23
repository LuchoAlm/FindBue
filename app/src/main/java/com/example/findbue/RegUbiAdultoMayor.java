package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegUbiAdultoMayor extends AppCompatActivity {
    public Button registrar, cancelar;
    EditText ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_reg_ubi_adulto_mayor);

        cancelar = (Button) findViewById(R.id.buttonCancelarAM);
        registrar = (Button) findViewById(R.id.buttonRegistrarAM);
        ubicacionDomAM = (EditText) findViewById(R.id.editTextTextPersonName12);
        latitudAM = (EditText) findViewById(R.id.editTextTextPersonName14);
        longitudAM = (EditText) findViewById(R.id.editTextTextPersonName15);
        metrosPermitidosAM = (EditText) findViewById(R.id.editTextTextPersonName13);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("datosDeUbicacionDelAdultoMayor");
                AdultoMayor_DatosUbicacion adultoMayorDatosUbicacion = new AdultoMayor_DatosUbicacion(ubicacionDomAM.getText().toString(),
                            latitudAM.getText().toString(),
                            longitudAM.getText().toString(),
                            longitudAM.getText().toString());
                myref.push().setValue(adultoMayorDatosUbicacion);
                Intent intent = new Intent(RegUbiAdultoMayor.this, PanelPrincipalUsuario.class);
                Toast.makeText(RegUbiAdultoMayor.this, "Registrando adulto mayor!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegUbiAdultoMayor.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });
    }
}