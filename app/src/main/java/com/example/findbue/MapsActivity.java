package com.example.findbue;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.findbue.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        double distancia;

        // Add a marker in Sydney and move the camera
        //Registro de AM ... traigo Lat y Long
        LatLng sydney = new LatLng(-0.16446, -78.48419); //jalar la latitud y la longitud del firebase //FIJA

        //GetCurrentLocation()
        LatLng sydney2 = new LatLng(-0.165079, -78.479162); //jalar la latitud y la longitud del firebase //MOVIL

        if(!sydney.equals(sydney2)){
            distancia = Math.sqrt(Math.pow((sydney2.latitude - sydney.latitude),2) + Math.pow((sydney2.longitude - sydney.longitude),2))*10000;
            System.out.println("Distancia: " + distancia);
            if(distancia > 60){
                Toast.makeText(MapsActivity.this, "Adulto mayor fuera del rango permitido", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MapsActivity.this, "Adulto a salvo", Toast.LENGTH_SHORT).show();
            }
        }
        drawCircle(sydney);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void drawCircle(LatLng point){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(80); //jalar la cantidad de metros //Registro de AM
        // Border color of the circle
        circleOptions.strokeColor(Color.GRAY);
        // Fill color of the circle
        circleOptions.fillColor(0x3080d4ff);
        // Border width of the circle
        circleOptions.strokeWidth(2);
        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }
}