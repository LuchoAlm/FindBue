package com.example.findbue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class AdministrarComoFamiliar extends AppCompatActivity {
    Button editarMiInfoPersonal, editarAM, eliminarAM;
    TextView nombreAM, correoAM;
    PanelPrincipalUsuario panelPrincipalUsuario = new PanelPrincipalUsuario();
    AdministrarComoEncargado encargado = new AdministrarComoEncargado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_administrar_familiar);

        editarMiInfoPersonal = (Button) findViewById(R.id.buttonEditarInfoPerson);
        editarAM = (Button) findViewById(R.id.buttonEditarAM);
        eliminarAM = (Button) findViewById(R.id.buttonEliminarAM);
        nombreAM = (TextView) findViewById(R.id.textView31);
        correoAM = (TextView) findViewById(R.id.textView33);

        editarMiInfoPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //encargado.mostrarDatosUsuario();
                Intent intent = new Intent(AdministrarComoFamiliar.this, AdministrarComoEncargado.class);
                startActivity(intent);
                encargado.mostrarDatosUsuario();
            }
        });

        editarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(AdministrarComoFamiliar.this, EditarAdultoMayor.class);
                startActivity(intent);
            }
        });

        eliminarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(nombreAM.getContext());
                builder.setTitle("¿Está seguro de borrar al adulto mayor?");

                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(nombreAM.getContext(), "Usuario borrado exitosamente", Toast.LENGTH_SHORT).show();
                        /*FirebaseDatabase.getInstance().getReference().child("datosPersonalesAdultoMayor")
                                .child(FirebaseDatabase.getInstance().getReference().child("datosPersonalesAdultoMayor").getKey())
                                .removeValue();*/
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(nombreAM.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

   /* private void mostrarDatosUsuario() {
        AdministrarComoEncargado encargado = new AdministrarComoEncargado();
        Intent intent = getIntent();
        String nombre_user = intent.getStringExtra("nombreCompleto");
        String direccionDom_user = intent.getStringExtra("direccionDom");
        String telefonoMov_user = intent.getStringExtra("telefonoMov");
        String correo_user = intent.getStringExtra("correo");

        encargado.nombreUsuario.setText(nombre_user);
        encargado.direccionUsuario.setText(direccionDom_user);
        encargado.telefonoMovUsuario.setText(telefonoMov_user);
        encargado.correoUsuario.setText(correo_user);

    }*/
}