package com.jeanlima.mapasapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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
import com.jeanlima.mapasapp.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    //permissao
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

        //Pedir permissao ao usuário para usar localização
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

        /* ### PARTE 2 - LOCALIZACAO


         */
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Localização","Local: "+location.toString());

                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();

                mMap.clear();

                LatLng localUsuario = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(localUsuario).title("posucao usuario"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localUsuario,15));

                /* PARTE 3 GEOCODING */
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try{

                    //List<Address> listaEndereco = geocoder.getFromLocation(latitude,longitude,1);
                    String stringEndereco = "Campus Universitário - Lagoa Nova, Natal - RN, 598078-970";
                    List<Address> listaEndereco = geocoder.getFromLocationName(stringEndereco,1);
                    if(listaEndereco != null && listaEndereco.size() > 0){
                        Address endereco = listaEndereco.get(0);
                        //0:"Av. Brigadeiro Luís Antônio - Bela Vista, São Paulo - SP, 01325-050, Brazil
                        //0:"R. do Antimônio, 59109 - Lagoa Nova, Natal - RN, 59076-550, Brazil
                        Log.d("Endereco: ",endereco.toString());
                    }


                }catch(IOException e){
                    e.printStackTrace();

                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, //tempo mínimo entre atualizações (milisegundos)
                    0, //distancia mínima entre atualizções (metros)
                    locationListener

            );

        }

        /* #### PARTE 1 - MAPAS

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        //1. definindo local via latitude e longitude
        LatLng imd = new LatLng(-5.8318990803881094, -35.205129967097015);

        //2. adicionando marcador
        mMap.addMarker(
                new MarkerOptions()
                .position(imd)
                .title("Marcador do IMD")
                /*
                .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )
                 */
               // .icon(BitmapDescriptorFactory.fromResource(R.drawable.escola))
        //);
        //zoom pode variar 2 a 21
        /*
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(imd,15));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,"OnClick - lat: "+latitude+" long: "+longitude, Toast.LENGTH_LONG).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Lugar MArcado")
                                .snippet("Descricao do lugar")
                                /*
                                .icon(
                                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                                )
                                 */
                         //       .icon(BitmapDescriptorFactory.fromResource(R.drawable.escola))
              //  );

          //  }
        //});

        /*
        //2. desenhando formas
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(imd);
        circleOptions.radius(500); //metros

        //circleOptions.fillColor(Color.BLUE);
        circleOptions.fillColor(Color.argb(128,255,153,0)); //alpha - opacidade
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.YELLOW);

        mMap.addCircle(circleOptions);
        #### FIM PARTE 1 - MAPAS
        */

    }
}