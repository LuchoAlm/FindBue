package com.example.findbue;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                Intent intent = new Intent(PanelPrincipalUsuario.this, RetrieveActivityAdultoM.class);
                startActivity(intent);
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
                                Intent intent = new Intent(PanelPrincipalUsuario.this, RetrieveActivityEditarMiPerfil.class);
                                startActivity(intent);
                            }
                        });
                        consultarUbicacion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, MapsActivity.class);
                                startActivity(intent);
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
                                Intent intent = new Intent(PanelPrincipalUsuario.this, RetrieveActivityEditarMiPerfil.class);
                                startActivity(intent);
                            }
                        });
                        editarEncargado.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, RetrieveActivityUsuario.class);
                                startActivity(intent);
                            }
                        });
                        administrarAM.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, EdicionYEliminacionAdultoM.class);
                                startActivity(intent);
                            }
                        });
                        consultarUbicacion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(PanelPrincipalUsuario.this, MapsActivity.class);
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

        consultarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PanelPrincipalUsuario.this, EditBusqueda.class);
                startActivity(i);
            }
        });

        double lati = getIntent().getExtras().getDouble("latitud");
        double logi = getIntent().getExtras().getDouble("longitud");


        if (lati != 0.0 && logi != 0.0){
                System.out.println("Valores: "+lati+"  "+logi);
        }
    }
}