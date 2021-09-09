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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PanelPrincipalUsuario extends AppCompatActivity {
    Button consultarUbicacion, administrarAM, editarEncargado, editarMiPerfil;
    ImageButton addadultoMayor, addEncargado;
    Switch seleccionRol;
    TextView textEncargado;
    LinearLayout linearEncargados, linearLayoutAddAdulto;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    private DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("adultosMayores");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_panel_principal_usuario);

        seleccionRol = (Switch) findViewById(R.id.switch2);
        linearLayoutAddAdulto = (LinearLayout) findViewById(R.id.linearLayout);
        //Encargados
        textEncargado = (TextView) findViewById(R.id.textView11);
        linearEncargados = (LinearLayout) findViewById(R.id.linearLayout2);
        editarEncargado = (Button) findViewById(R.id.button14);
        editarMiPerfil = (Button) findViewById(R.id.button16);

        //Adultos Mayores
        addadultoMayor = (ImageButton) findViewById(R.id.imageButton);
        administrarAM = (Button) findViewById(R.id.button6);
        consultarUbicacion = (Button) findViewById(R.id.button15);

        administrarAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatosBD_AM(mDatabase2);
            }
        });

        seleccionRol.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                if (view.getId() == R.id.switch2) {
                    if (seleccionRol.isChecked()) { //ROL ENCARGADOS
                        textEncargado.setVisibility(View.GONE);
                        linearEncargados.setVisibility(View.GONE);
                        administrarAM.setVisibility(View.GONE);
                        editarEncargado.setVisibility(View.GONE);
                        addadultoMayor.setVisibility(View.GONE);
                        editarMiPerfil.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                obtenerDatosBD(mDatabase);
                            }
                        });
                    } if(!seleccionRol.isChecked()) { //ROL FAMILIAR
                        textEncargado.setVisibility(View.VISIBLE);
                        linearEncargados.setVisibility(View.VISIBLE);
                        administrarAM.setVisibility(View.VISIBLE);
                        editarEncargado.setVisibility(View.VISIBLE);
                        addadultoMayor.setVisibility(View.VISIBLE);
                        editarMiPerfil.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //obtenerDatosBD(mDatabase);
                            }
                        });
                        editarEncargado.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //obtenerDatosBD(mDatabase);
                            }
                        });
                        administrarAM.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, EditarAdultoMayor.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });

        addadultoMayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelPrincipalUsuario.this, RegistrarDatosAdultoMayor.class);
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

                   /* Intent intent = new Intent(getApplicationContext(), AdministrarComoEncargado.class);
                    intent.putExtra("nombreCompleto", nombreFromDB);
                    intent.putExtra("direccionDom", direccionFromDB);
                    intent.putExtra("telefonoMov", telefonoFromDB);
                    intent.putExtra("correo", correoFromDB);
                    startActivity(intent);
*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mPostReference.addValueEventListener(valueEventListener);
    }

    private void obtenerDatosBD_AM(DatabaseReference mPostReference) {

        String uid = mDatabase2.getKey();
        System.out.println("MI CLAVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE: " + uid);

       ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombreAMFromDB = snapshot.child(uid).child("nombreCompletoAM").getValue(String.class);
                String correoAMFromDB = snapshot.child(uid).child("correoAM").getValue(String.class);
                String direccionAMFromDB = snapshot.child(uid).child("direccionDomAM").getValue(String.class);
                String telefonoAMFromDB = snapshot.child(uid).child("telefonoMovAM").getValue(String.class);

                Intent intent = new Intent(getApplicationContext(), EditarAdultoMayor.class);
                intent.putExtra("nombreCompleto", nombreAMFromDB);
                intent.putExtra("direccionDom", correoAMFromDB);
                intent.putExtra("telefonoMov", direccionAMFromDB);
                intent.putExtra("correo", telefonoAMFromDB);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mPostReference.addValueEventListener(valueEventListener);
    }

}