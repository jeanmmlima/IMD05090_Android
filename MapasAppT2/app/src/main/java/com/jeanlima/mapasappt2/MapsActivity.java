 package com.jeanlima.mapasappt2;

 import android.Manifest;
 import android.content.Context;
 import android.content.pm.PackageManager;
 import android.location.Location;
 import android.location.LocationListener;
 import android.location.LocationManager;
 import android.os.Bundle;
 import android.util.Log;

 import androidx.annotation.NonNull;
 import androidx.core.app.ActivityCompat;
 import androidx.fragment.app.FragmentActivity;

 import com.google.android.gms.maps.CameraUpdateFactory;
 import com.google.android.gms.maps.GoogleMap;
 import com.google.android.gms.maps.OnMapReadyCallback;
 import com.google.android.gms.maps.SupportMapFragment;
 import com.google.android.gms.maps.model.LatLng;
 import com.google.android.gms.maps.model.MarkerOptions;
 import com.jeanlima.mapasappt2.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    //permissao de localização
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Pedir permissão ao usuário para usar a localização
        ActivityCompat.requestPermissions(this,permissoes,1);

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

        //PARTE 2 - Localização

        //1. instanciar location manager e location listener
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                Log.d("Localização","Local: "+location.toString());

                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();

                mMap.clear();

                LatLng localUsuario = new LatLng(latitude,longitude);

                mMap.addMarker(
                        new MarkerOptions()
                                .position(localUsuario)
                                .title("Marcador Usuario")

                );

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localUsuario,15));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };

        //verificar se a permissão foi concedida e definir parametros de atualização da localização

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, //tempo mínimo de atualização em milis
                    0, //distancia mínima de atualização em (m)
                    locationListener


            );
        }


        /* PARTE 1 - MAPAS
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        LatLng imd = new LatLng(-5.832067823468595, -35.20500462035491);
        mMap.addMarker(
                new MarkerOptions()
                .position(imd)
                .title("Marcado IMD")
                .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.escola)
                )
        );
        //zoom: 2 - 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(imd,15));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {


                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,"Lat: "+latitude+" Long: "+longitude, Toast.LENGTH_LONG).show();


                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Marcador do Lugar Clicado")
                                .icon(
                                        BitmapDescriptorFactory.fromResource(R.drawable.escola)
                                )

                );



            }
        });

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(imd);
        circleOptions.radius(500); //metros

        circleOptions.fillColor(Color.argb(128,255,153,0)); //alpha - opacidade


        mMap.addCircle(circleOptions);

         */

    }
}