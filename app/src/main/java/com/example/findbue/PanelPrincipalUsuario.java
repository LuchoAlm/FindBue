package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PanelPrincipalUsuario extends AppCompatActivity {
    Button consultarUbicacion, consultarRuta, btnAdminUsuario;
    ImageButton agregarAM, agregarEncargado, eliminarAM, eliminarEncargado, editar;
    Switch seleccionRol;
    TextView textEncargado;
    LinearLayout encargados;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    private FirebaseAuth mAuth;
    //AdministrarComoFamiliar usuarioSistema = new AdministrarComoFamiliar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_panel_principal_usuario);

        seleccionRol = (Switch) findViewById(R.id.switch2);
        textEncargado = (TextView) findViewById(R.id.textViewEncargados);
        encargados = (LinearLayout) findViewById(R.id.linearEncargados);
        agregarAM = (ImageButton) findViewById(R.id.btnAddAdulto);
        agregarEncargado = (ImageButton) findViewById(R.id.btnAddEncargado);
        btnAdminUsuario = (Button) findViewById(R.id.button6);

        seleccionRol.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                if (view.getId() == R.id.switch2) {
                    if (seleccionRol.isChecked()) { //ROL ENCARGADOS
                        textEncargado.setVisibility(View.GONE);
                        encargados.setVisibility(View.GONE);
                        btnAdminUsuario.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                obtenerDatosBD(mDatabase);
                            }
                        });
                    } if(!seleccionRol.isChecked()) { //ROL FAMILIAR
                        textEncargado.setVisibility(View.VISIBLE);
                        encargados.setVisibility(View.VISIBLE);
                        btnAdminUsuario.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, AdministrarComoFamiliar.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });

        agregarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, RegistrarDatosAdultoMayor.class);
                startActivity(intent);
            }
        });

        agregarEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, AgregarEncargado.class);
                startActivity(intent);
            }
        });

    }

    private void obtenerDatosBD(DatabaseReference mPostReference) {
        String uid = getIntent().getExtras().getString("uid");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nombreFromDB = snapshot.child(uid).child("nombreCompleto").getValue(String.class);
                    String correoFromDB = snapshot.child(uid).child("correo").getValue(String.class);
                    String direccionFromDB = snapshot.child(uid).child("direccionDom").getValue(String.class);
                    String telefonoFromDB = snapshot.child(uid).child("telefonoMov").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), AdministrarComoEncargado.class); //PerfilUsuario
                    intent.putExtra("nombreCompleto", nombreFromDB);
                    intent.putExtra("direccionDom", direccionFromDB);
                    intent.putExtra("telefonoMov", telefonoFromDB);
                    intent.putExtra("correo", correoFromDB);

                    startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mPostReference.addValueEventListener(valueEventListener);
    }
}