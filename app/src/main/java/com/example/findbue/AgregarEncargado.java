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

public class AgregarEncargado extends AppCompatActivity {
    Button agregarEncargado, cancelar;
    EditText correoEncargado, correoAdultoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_agregar_encargado);

        agregarEncargado = (Button) findViewById(R.id.buttonAgregarEncargado);
        cancelar = (Button) findViewById(R.id.buttonCancelarEncargado);
        correoEncargado = (EditText) findViewById(R.id.correoEncarRegistro);
        correoAdultoM = (EditText) findViewById(R.id.correoAdultoRegistro);

        agregarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("encargados");
                Encargado encargado = new Encargado(correoEncargado.getText().toString(), correoAdultoM.getText().toString());
                myref.push().setValue(encargado);
                Intent intent =  new Intent( AgregarEncargado.this, PanelPrincipalUsuario.class);
                Toast.makeText(AgregarEncargado.this, "Encargado asociado exitosamente!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AgregarEncargado.this, PanelPrincipalUsuario.class);
                startActivity(intent);
            }
        });

    }
}