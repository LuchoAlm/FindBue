package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditarAdultoMayor extends AppCompatActivity {
    EditText correoNuevo, direccionNueva, telefonoNuevo;
    Button actualizarAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_editar_adulto_mayor);

        correoNuevo = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        direccionNueva = (EditText) findViewById(R.id.editTextTextPersonName4);
        telefonoNuevo = (EditText) findViewById(R.id.editTextPhone);
        actualizarAM = (Button) findViewById(R.id.button8);

        actualizarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });
    }

    private void actualizarDatos() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> map = new HashMap<>();
        map.put("direccionDom", direccionNueva.getText().toString());
        map.put("telefonoMov", telefonoNuevo.getText().toString());
        map.put("correo", actualizarAM.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("usuarios")
                .child(uid).updateChildren(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Actualización exitosa!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No se pudo realizar la actualización", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*public void mostrarDatosUsuario() {
        Intent intent = getIntent();
        String nombre_user = intent.getStringExtra("nombreCompleto");
        String direccionDom_user = intent.getStringExtra("direccionDom");
        String telefonoMov_user = intent.getStringExtra("telefonoMov");
        String correo_user = intent.getStringExtra("correo");

        nombreUsuario.setText(nombre_user);
        direccionUsuario.setText(direccionDom_user);
        telefonoMovUsuario.setText(telefonoMov_user);
        correoUsuario.setText(correo_user);
    }*/
}