package com.example.findbue;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.findbue.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.protobuf.DescriptorProtos;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private  static final int REQUEST_CHECK_SETTINGS = 102;
    private LocationCallback mLocationCallback;
    private LocationSettingsRequest.Builder builder;
    public LocationRequest mLocationRequest;
    Marker marker;
    private FusedLocationProviderClient fusedLocationClient;
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        obtenerUltimaUbicacion();

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if(locationResult == null){
                    return;
                }

                for (Location location : locationResult.getLocations()){
                    agregarMarcador (location.getLatitude(), location.getLongitude());
                }
            };
        };

        mLocationRequest = createLocationRequest();

        builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        checkLocationSetting(builder);
    }

    private void checkLocationSetting(LocationSettingsRequest.Builder builder) {
        builder.setAlwaysShow(true);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                iniciarActualizacionesUbicacion();
            }
        });
        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                }catch (ApiException exception){
                    switch (exception.getStatusCode()){
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvable = (ResolvableApiException)  exception;

                                resolvable.startResolutionForResult(MapsActivity.this,
                                        REQUEST_CHECK_SETTINGS);

                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }catch (ClassCastException e){

                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });
    }

    private void agregarMarcador(double lat, double lng) {

        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 19);
        if (marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Tu posición")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_location)));
        mMap.animateCamera(miUbicacion);

    }

    private void obtenerUltimaUbicacion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions

                // muestra una ventana o Dialog en donde el usuario debe
                // dar permisos para el uso del GPS de su dispositivo.
                // El método dialogoSolicitarPermisoGPS() lo crearemos más adelante.
                dialogoSolicitarPermisoGPS();

            }
        }

    }

    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(30000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setSmallestDisplacement(30);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    public void iniciarActualizacionesUbicacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                return;
            }
        }

        // Obtenemos la ubicación más reciente
        fusedLocationClient.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null /* Looper */);
    }



    private void dialogoSolicitarPermisoGPS(){
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
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