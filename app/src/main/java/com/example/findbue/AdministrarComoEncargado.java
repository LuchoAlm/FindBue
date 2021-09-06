package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdministrarComoEncargado extends AppCompatActivity {
    EditText correoUsuario, direccionUsuario, telefonoMovUsuario;
    TextView nombreUsuario;
    Button actualizarUsuario, eliminarEncargado;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_administrar_encargado);

        correoUsuario = findViewById(R.id.textView28);
        direccionUsuario = findViewById(R.id.textView29);
        telefonoMovUsuario = findViewById(R.id.textView30);
        nombreUsuario = findViewById(R.id.textView23);
        actualizarUsuario = (Button) findViewById(R.id.button7);
        eliminarEncargado = (Button) findViewById(R.id.button4);
        mostrarDatosUsuario();

        eliminarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEncargadoDB();
            }
        });

        actualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });
    }

    public void eliminarEncargadoDB() {
        FirebaseDatabase.getInstance().getReference().child("usuarios")
                .child("tCcumxQYSIQ1eTpZ8YOASMbyyHy2").removeValue();
        Toast.makeText(AdministrarComoEncargado.this, "Usuario borrado exitosamente", Toast.LENGTH_SHORT).show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(AdministrarComoEncargado.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Eliminar usuario del sistema")
                .setMessage("¿Está seguro?")
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("Estoy aquí ") ;
                /*FirebaseDatabase.getInstance().getReference().child("usuarios")
                        .child("tCcumxQYSIQ1eTpZ8YOASMbyyHy2").removeValue();*/

      /*  })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AdministrarComoEncargado.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });*/

    }


    private void limpiarCasillas() {
        direccionUsuario.setText("");
        telefonoMovUsuario.setText("");
        correoUsuario.setText("");
    }

    private void actualizarDatos() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> map = new HashMap<>();
        map.put("direccionDom", direccionUsuario.getText().toString());
        map.put("telefonoMov", telefonoMovUsuario.getText().toString());
        map.put("correo", correoUsuario.getText().toString());

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


    public void mostrarDatosUsuario() {
        Intent intent = getIntent();
        String nombre_user = intent.getStringExtra("nombreCompleto");
        String direccionDom_user = intent.getStringExtra("direccionDom");
        String telefonoMov_user = intent.getStringExtra("telefonoMov");
        String correo_user = intent.getStringExtra("correo");

        nombreUsuario.setText(nombre_user);
        direccionUsuario.setText(direccionDom_user);
        telefonoMovUsuario.setText(telefonoMov_user);
        correoUsuario.setText(correo_user);

    }
}