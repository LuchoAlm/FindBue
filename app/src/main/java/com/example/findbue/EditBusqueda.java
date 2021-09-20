package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.findbue.databinding.ActivityEditBusquedaBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

public class EditBusqueda extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private SearchView searchView;
    private SupportMapFragment supportMapFragment;
    private FusedLocationProviderClient client;
    private ActivityEditBusquedaBinding binding;
    public LatLng actual= new LatLng(0, 0);
    public LatLng nuevaLocation;
    public String layout;

    private Marker markePrueba, markerDrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditBusquedaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Asiganmos la variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        //Inicializamos el fused

        client = LocationServices.getFusedLocationProviderClient(this);

        //Checamos los permisos
        if (ActivityCompat.checkSelfPermission(EditBusqueda.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Si tenemos permisos llamamos al metodo
            getCurrentLocation();
        } else{
            //Cuando no tenemos permisos
            //Requerimos permisos
            ActivityCompat.requestPermissions(EditBusqueda.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Bundle parametros = this.getIntent().getExtras();
        if (parametros!=null){
            layout = getIntent().getStringExtra("layout");
            }


    }

    private void getCurrentLocation() {
        //Inicializamos la tarea de localización
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //cuando es correcto
                if (location !=null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            //Inicializamos lat Ing
                            //UBICACIÓN ACTUAL
                            //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                            LatLng latLng = new LatLng(-0.210634, -78.488464);
                            ubicacionActual(latLng);

                            //Creamos las opciones del marcador
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Ubicación Adulto Mayor");

                            //Zoom al mapa
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

                            //Añadimos el marcador en el mapa
                            googleMap.addMarker(options.position(latLng).title("Morelos").draggable(true));
                            drawCircle(latLng);
                        }
                    });
                }
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMarkerDragListener(this);

        //Marcado en distancia permitida

        LatLng permitido = new LatLng(-0.123803, -78.513266);
        LatLng noPermitido = new LatLng(-0.123439, -78.510565);

        //mMap.addMarker(new MarkerOptions().position(permitido).title("Permitido"));
        //mMap.addMarker(new MarkerOptions().position(noPermitido).title("No Permitido"));

        //double val1 = Math.sqrt( Math.pow(permitido.latitude-actual.latitude,2)+Math.pow(permitido.longitude-actual.longitude,2));

        //double val2 = Math.sqrt( Math.pow(noPermitido.latitude-actual.latitude,2)+Math.pow(noPermitido.longitude-actual.longitude,2));

        //Toast.makeText(this, "El resultado es:"+val1, Toast.LENGTH_LONG).show();

        //Toast.makeText(this, "El resultado es:"+val2, Toast.LENGTH_LONG).show();

        System.out.println("\n\n\n----> LUIS El valor es: "+this.actual+"\n\n\n");

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        //Agregar marcador
        LatLng morelos = new LatLng(-0.210634, -78.488464);
        markerDrag = googleMap.addMarker(new MarkerOptions()
        .position(morelos).title("Morelos").draggable(true));



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Cuando el permiso es aceptado
                //Llamamos al método
                getCurrentLocation();
            }
        }
    }


    private void drawCircle(LatLng point){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(200); //jalar la cantidad de metros //Registro de AM
        // Border color of the circle
        circleOptions.strokeColor(Color.argb(5,0,173,181));
        // Fill color of the circle
        circleOptions.fillColor(0x3080d4ff);
        // Border width of the circle
        circleOptions.strokeWidth(2);
        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }

    public void ubicacionActual (LatLng latLng){
        this.actual = latLng;
        System.out.println("\n\n\n----> El valor es: "+this.actual+"\n\n\n");
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

        if(marker.equals(markerDrag)){
            nuevaLocation = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            System.out.println(nuevaLocation);
        }

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        if(marker.equals(markerDrag)){
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(nuevaLocation).title("Funciona").draggable(true));
            drawCircle(nuevaLocation);

            if(layout.equals("RegistrarAdultoMayor")){

                Intent i = new Intent(EditBusqueda.this ,RegistrarDatosAdultoMayor.class);
                double  latitud = nuevaLocation.latitude;
                double  longitud = nuevaLocation.longitude;

                i.putExtra("latitud",latitud);
                i.putExtra("longitud",longitud);

                startActivity(i);

            }else if (layout.equals("RegistrarUsuario")){
                Intent i = new Intent(EditBusqueda.this ,RegistrarUsuario.class);
                double  latitud = nuevaLocation.latitude;
                double  longitud = nuevaLocation.longitude;

                i.putExtra("latitud",latitud);
                i.putExtra("longitud",longitud);
                startActivity(i);
            }else if (layout.equals("PanelPrincipal")){
                Intent i = new Intent(EditBusqueda.this, PanelPrincipalUsuario.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }

        }
    }
}