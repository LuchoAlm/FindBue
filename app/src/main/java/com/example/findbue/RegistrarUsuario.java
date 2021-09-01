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
    public Button registrarse;
    public EditText correo, password, nombreCompleto, direccionDom, telefonoMov;
    FirebaseDatabase database;
    DatabaseReference myref;
    String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrar_usuarios);

        registrarse = (Button) findViewById(R.id.buttonRegistrarme);
        correo = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        nombreCompleto = (EditText) findViewById(R.id.editTextTextPersonName);
        direccionDom = (EditText) findViewById(R.id.editTextDireccion);
        telefonoMov = (EditText) findViewById(R.id.editTextPhone2);


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myref = database.getReference("usuarios");
                Usuario user = new Usuario(correo.getText().toString(),
                        password.getText().toString(),
                        nombreCompleto.getText().toString(),
                        direccionDom.getText().toString(),
                        telefonoMov.getText().toString());

                //Validaciones
                if(correo.getText().toString().isEmpty()){
                    correo.setError("El campo no puede estar vacío");
                }else if(!correo.getText().toString().matches(validacionCorreo)){
                    correo.setError("Dirección de correo inválida");
                }else
                    correo.setError(null);
                    if(password.getText().toString().isEmpty()){
                        password.setError("El campo no puede estar vacío");
                    }else{
                        password.setError(null);
                        if(nombreCompleto.getText().toString().isEmpty()){
                            nombreCompleto.setError("El campo no puede estar vacío");
                        }else{
                            nombreCompleto.setError(null);
                            if(direccionDom.getText().toString().isEmpty()){
                                direccionDom.setError("El campo no puede estar vacío");
                            }else{
                                direccionDom.setError(null);
                                if(telefonoMov.getText().toString().isEmpty()){
                                    telefonoMov.setError("El campo no puede estar vacío");
                                }else{
                                    telefonoMov.setError(null);
                                    myref.child(user.password.toString()).setValue(user);
                                    Intent intent = new Intent(RegistrarUsuario.this, PanelPrincipalUsuario.class);
                                    Toast.makeText(RegistrarUsuario.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }



        });
    }
}