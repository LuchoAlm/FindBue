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
                if(!validateCorreo() || !validatePassword() || !validateName() || !validateDireccionDom() || !validateTelefonoMov()) {
                    validate();
                    return;
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("usuarios");
                    Usuario user = new Usuario(
                            correo.getText().toString(),
                            password.getText().toString(),
                            nombreCompleto.getText().toString(),
                            direccionDom.getText().toString(),
                            telefonoMov.getText().toString());
                    myref.push().setValue(user);
                    Intent intent = new Intent(RegistrarUsuario.this, PanelPrincipalUsuario.class);
                    Toast.makeText(RegistrarUsuario.this, "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
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

    public void validate() {
        validateCorreo();
        validatePassword();
        validateName();
        validateDireccionDom();
        validateTelefonoMov();
    }

    public Boolean validateCorreo() {

        String val = correo.getText().toString();
        String patronCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            correo.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronCorreo)) {
            correo.setError("Correo no válido");
            return false;
        } else {
            correo.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {

        String val = password.getText().toString();
        String patronPassword = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@$%^&+=])" +
                "(?=.\\s+$)" +
                ".{4,}" +
                "$";

        if(val.isEmpty()) {
            password.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronPassword)) {
            password.setError("Contraseña no válida");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    public Boolean validateName() {

        String val = nombreCompleto.getText().toString();

        if(val.isEmpty()) {
            nombreCompleto.setError("Campo obligatorio");
            return false;
        } else {
            nombreCompleto.setError(null);
            return true;
        }

    }

    public Boolean validateDireccionDom() {

        String val = direccionDom.getText().toString();

        if(val.isEmpty()) {
            direccionDom.setError("Campo obligatorio");
            return false;
        } else {
            direccionDom.setError(null);
            return true;
        }

    }

    public Boolean validateTelefonoMov() {

        String val = telefonoMov.getText().toString();
        String patronTelefono = "09[0-9]{8}";

        if(val.isEmpty()) {
            telefonoMov.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronTelefono)) {
            telefonoMov.setError("Teléfono no válido");
            return false;
        } else {
            telefonoMov.setError(null);
            return true;
        }

    }

}