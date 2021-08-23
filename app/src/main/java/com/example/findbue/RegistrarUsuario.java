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

public class RegistrarUsuario extends AppCompatActivity {
    public Button registrarse, cancelar;
    public EditText correo, password, nombreCompleto, direccionDom, telefonoMov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_registrar_usuarios);

        registrarse = (Button) findViewById(R.id.buttonRegistrarme);
        cancelar = (Button) findViewById(R.id.buttonCancelarRegistro);
        correo = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        nombreCompleto = (EditText) findViewById(R.id.editTextTextPersonName);
        direccionDom = (EditText) findViewById(R.id.editTextDireccion);
        telefonoMov = (EditText) findViewById(R.id.editTextPhone2);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("usuarios");
                Usuario user = new Usuario(correo.getText().toString(),
                        password.getText().toString(),
                        nombreCompleto.getText().toString(),
                        direccionDom.getText().toString(),
                        telefonoMov.getText().toString());
                myref.push().setValue(user);
                Intent intent =  new Intent( RegistrarUsuario.this, PanelPrincipalUsuario.class);
                Toast.makeText(RegistrarUsuario.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarUsuario.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}