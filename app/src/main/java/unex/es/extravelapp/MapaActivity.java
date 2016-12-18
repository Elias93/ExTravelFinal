package unex.es.extravelapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Variables de origen
    Float viaje_o_Lat;
    Float viaje_o_Lon;
    String viaje_o_LatString;
    String viaje_o_LonString;

    //Variables de destino
    Float viaje_d_Lat;
    Float viaje_d_Lon;
    String viaje_d_LatString;
    String viaje_d_LonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Obtenemos las coordenadas del viaje
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        viaje_o_LatString = (String) extras.get("viaje_origen_Lat");
        viaje_o_LonString = (String) extras.get("viaje_origen_Lon");
        viaje_d_LatString = (String) extras.get("viaje_destino_Lat");
        viaje_d_LonString = (String) extras.get("viaje_destino_Lon");

        viaje_o_Lat = Float.parseFloat(viaje_o_LatString);
        viaje_o_Lon = Float.parseFloat(viaje_o_LonString);
        viaje_d_Lat = Float.parseFloat(viaje_d_LatString);
        viaje_d_Lon = Float.parseFloat(viaje_d_LonString);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Origen = new LatLng(viaje_o_Lat, viaje_o_Lon);
        LatLng Destino = new LatLng(viaje_d_Lat, viaje_d_Lon);
        mMap.addMarker(new MarkerOptions()
                .position(Origen)
                .title("Marker en Origen")
        );

        mMap.addPolyline(new PolylineOptions().add(
                Origen, Destino
        )
        .width(10)
        .color(Color.BLUE)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Origen, 6));
    }
}
