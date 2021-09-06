package com.example.findbue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistrarDatosAdultoMayor extends AppCompatActivity {
    Button registarAM, fechaPicker;
    EditText nombreCompletoAM, correoAM, direccionDomAM, telefonoMovAM,
             enfermedadesAM, medicamentosAM, personaEncargadaAM,
             descripcionFisicaAM,ubicacionDomAM, latitudAM, longitudAM, metrosPermitidosAM;
    Spinner seleccionarSexo;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultamos la barra de acción
        getSupportActionBar().hide();
        //Ocultamos la barra de estado del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrar_datos_adulto_mayor);
        SeleccionarFecha();

        registarAM = (Button) findViewById(R.id.button10);
        fechaPicker = (Button) findViewById(R.id.button9);
        nombreCompletoAM = (EditText) findViewById(R.id.editTextTextPersonName16);
        correoAM = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        direccionDomAM = (EditText) findViewById(R.id.editTextTextPersonName17);
        telefonoMovAM = (EditText) findViewById(R.id.editTextPhone3);
        seleccionarSexo = (Spinner) findViewById(R.id.spinner);
        enfermedadesAM = (EditText) findViewById(R.id.editTextTextMultiLine);
        medicamentosAM = (EditText) findViewById(R.id.editTextTextMultiLine2);
        personaEncargadaAM = (EditText) findViewById(R.id.editTextTextPersonName18);
        descripcionFisicaAM = (EditText) findViewById(R.id.editTextTextMultiLine3);
        ubicacionDomAM = (EditText) findViewById(R.id.editTextTextPersonName19);
        latitudAM = (EditText) findViewById(R.id.editTextTextPersonName20);
        longitudAM = (EditText) findViewById(R.id.editTextTextPersonName21);
        metrosPermitidosAM = (EditText) findViewById(R.id.editTextNumber);
        
       registarAM.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Validaciones
               String nombreCompletoBD = nombreCompletoAM.getText().toString();
               String correoBD = correoAM.getText().toString();
               String direccionDomBD = direccionDomAM.getText().toString();
               String telefonoMovBD = telefonoMovAM.getText().toString();
               String fechaNacBD = fechaPicker.getText().toString();
               String sexoBD = seleccionarSexo.getSelectedItem().toString();
               String enfermedadesBD = enfermedadesAM.getText().toString();
               String medicamentosBD = medicamentosAM.getText().toString();
               String personaEncargadaBD = personaEncargadaAM.getText().toString();
               String descripcionFisicaBD = descripcionFisicaAM.getText().toString();
               String ubicacionDomBD = ubicacionDomAM.getText().toString();
               String latitudBD = latitudAM.getText().toString();
               String longitudBD = longitudAM.getText().toString();
               String metrosPermitidosBD = metrosPermitidosAM.getText().toString();

               insertarDatos(nombreCompletoBD, correoBD, direccionDomBD, telefonoMovBD,
                       fechaNacBD, sexoBD, enfermedadesBD, medicamentosBD, personaEncargadaBD,
                       descripcionFisicaBD, ubicacionDomBD, latitudBD, longitudBD, metrosPermitidosBD);
               limpiarCasillas();
               //goToPrincipalPanel();
           }
       });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleccionarSexo.setAdapter(adapter);

        seleccionarSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void insertarDatos(String nombreCompletoBD, String correoBD, String direccionDomBD,
                               String telefonoMovBD, String fechaNacBD, String sexoBD, String enfermedadesBD,
                               String medicamentosBD, String personaEncargadaBD, String descripcionFisicaBD,
                               String ubicacionDomBD, String latitudBD, String longitudBD, String metrosPermitidosBD) {
        firebaseDatabase= FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("adultosMayores");
        AdultoMayor adultoMayor = new AdultoMayor(nombreCompletoBD, correoBD, direccionDomBD,
                                           telefonoMovBD, fechaNacBD, sexoBD, enfermedadesBD, medicamentosBD, personaEncargadaBD,
                                            descripcionFisicaBD, ubicacionDomBD, latitudBD, longitudBD, metrosPermitidosBD);
        myref.child(telefonoMovAM.getText().toString()).setValue(adultoMayor);
        Toast.makeText(this, "Adulto mayor registrado exitosamente!", Toast.LENGTH_SHORT).show();

    }

    public void limpiarCasillas(){
        nombreCompletoAM.setText("");
        correoAM.setText("");
        direccionDomAM.setText("");
        telefonoMovAM.setText("");
        fechaPicker.setText("");
        enfermedadesAM.setText("");
        medicamentosAM.setText("");
        personaEncargadaAM.setText("");
        descripcionFisicaAM.setText("");
        ubicacionDomAM.setText("");
        latitudAM.setText("");
        longitudAM.setText("");
        medicamentosAM.setText("");

    };

    private void goToPrincipalPanel() {

    }

    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return  makeDateString(dia, mes, anio);
    }

    public void SeleccionarFecha(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;
                String fecha =  makeDateString(dia, mes, anio);
                fechaPicker.setText(fecha);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, anio, mes, dia);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dia, int mes, int anio) {
        return dia + " " + getFormatoMes(mes) + " " + anio;
    }

    private String getFormatoMes(int mes) {
        if(mes == 1)
            return "enero";
        if(mes == 2)
            return "febrero";
        if(mes == 3)
            return "marzo";
        if(mes == 4)
            return "abril";
        if(mes == 5)
            return "mayo";
        if(mes == 6)
            return "junio";
        if(mes == 7)
            return "julio";
        if(mes == 8)
            return "agosto";
        if(mes == 9)
            return "septiembre";
        if(mes == 10)
            return "octubre";
        if(mes == 11)
            return "noviembre";
        if(mes == 12)
            return "diciembre";

        return null;
    }

    public void abrirDatePicker(View view){
        datePickerDialog.show();
    }
}