package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarUsuario extends AppCompatActivity {
    public Button buttonRegistrarse;
    public EditText correo, password, nombreCompleto, direccionDom, telefonoMov;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
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

        //mAuth = FirebaseAuth.getInstance();

        buttonRegistrarse = (Button) findViewById(R.id.buttonRegistrarme);
        correo = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        nombreCompleto = (EditText) findViewById(R.id.editTextTextPersonName);
        direccionDom = (EditText) findViewById(R.id.editTextDireccion);
        telefonoMov = (EditText) findViewById(R.id.editTextPhone2);

     /*   buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoIngresado = correo.getText().toString();
                String passwordIngresado = password.getText().toString();
                String nombreCompletoIngresado = nombreCompleto.getText().toString();
                String direccionIngresada = direccionDom.getText().toString();
                String telefonoIngresado = telefonoMov.getText().toString();
            }});
               if(correoIngresado.isEmpty()){
                    correo.setError("Campo obligatorio");
                    correo.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(correoIngresado).matches()){
                    correo.setError("Correo no válido");
                    correo.requestFocus();
                    return;
                }

                if(correoIngresado.isEmpty()){
                    password.setError("Campo obligatorio");
                    password.requestFocus();
                    return;
                }

                if(correoIngresado.isEmpty()){
                    nombreCompleto.setError("Campo obligatorio");
                    nombreCompleto.requestFocus();
                    return;
                }

                if(correoIngresado.isEmpty()){
                    direccionDom.setError("Campo obligatorio");
                    direccionDom.requestFocus();
                    return;
                }

                if(correoIngresado.isEmpty()){
                    telefonoMov.setError("Campo obligatorio");
                    telefonoMov.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(correoIngresado,passwordIngresado)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    database = FirebaseDatabase.getInstance();
                                    myref = database.getReference("usuarios");
                                    Usuario usuario = new Usuario(nombreCompletoIngresado, direccionIngresada, telefonoIngresado);
                                    myref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(usuario);
                                }else {
                                    Toast.makeText(RegistrarUsuario.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });*/

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myref = database.getReference("usuarios");
                mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString());
                Usuario user = new Usuario(correo.getText().toString(),
                        password.getText().toString(),
                        nombreCompleto.getText().toString(),
                        direccionDom.getText().toString(),
                        telefonoMov.getText().toString());

                //Validaciones
                if (correo.getText().toString().isEmpty()) {
                    correo.setError("El campo no puede estar vacío");
                } else if (!correo.getText().toString().matches(validacionCorreo)) {
                    correo.setError("Dirección de correo inválida");
                } else
                    correo.setError(null);
                if (password.getText().toString().isEmpty()) {
                    password.setError("El campo no puede estar vacío");
                } else {
                    password.setError(null);
                    if (nombreCompleto.getText().toString().isEmpty()) {
                        nombreCompleto.setError("El campo no puede estar vacío");
                    } else {
                        nombreCompleto.setError(null);
                        if (direccionDom.getText().toString().isEmpty()) {
                            direccionDom.setError("El campo no puede estar vacío");
                        } else {
                            direccionDom.setError(null);
                            if (telefonoMov.getText().toString().isEmpty()) {
                                telefonoMov.setError("El campo no puede estar vacío");
                            } else {
                                telefonoMov.setError(null);
                                myref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                                //myref.push().setValue(user);
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

