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
        correoEncargado = (EditText) findViewById(R.id.editTextTextPersonName4);
        correoAdultoM = (EditText) findViewById(R.id.editTextTextPersonName16);

        agregarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateCorreoEncargado() || !validateCorreoAdultoM()) {
                    validate();
                    return;
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("encargados");
                    Encargado encargado = new Encargado(
                            correoEncargado.getText().toString(),
                            correoAdultoM.getText().toString());
                    myref.push().setValue(encargado);
                    Intent intent = new Intent(AgregarEncargado.this, PanelPrincipalUsuario.class);
                    Toast.makeText(AgregarEncargado.this, "Encargado asociado exitosamente!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
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

    public void validate() {
        validateCorreoEncargado();
        validateCorreoAdultoM();
    }

    public Boolean validateCorreoEncargado() {

        String val = correoEncargado.getText().toString();
        String patronCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            correoEncargado.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronCorreo)) {
            correoEncargado.setError("Correo no válido");
            return false;
        } else {
            correoEncargado.setError(null);
            return true;
        }
    }

    public Boolean validateCorreoAdultoM() {

        String val = correoAdultoM.getText().toString();
        String patronCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            correoAdultoM.setError("Campo obligatorio");
            return false;
        } else if(!val.matches(patronCorreo)) {
            correoAdultoM.setError("Correo no válido");
            return false;
        } else {
            correoAdultoM.setError(null);
            return true;
        }
    }
}