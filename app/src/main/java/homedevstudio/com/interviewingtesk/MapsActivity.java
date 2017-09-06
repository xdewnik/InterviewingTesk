package homedevstudio.com.interviewingtesk;

import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.randomLocationButton)FloatingActionButton randomLocationButton;
    private GoogleMap mMap;
    private double randomLatValue;
    private double randomLngValue;
    private double latOdessa;
    private double lngOdessa;


    private double latKmValue;
    private double lngKmValue;
    private double latMin;
    private double latMax;
    private double lngMin;
    private double lngMax;

    private final static  int RADIUS_ODESSA = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            goToGeo("Одесса");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToLocationZoom(LatLngBounds marker, int zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getCenter(),zoom));
        mMap.setLatLngBoundsForCameraTarget(marker);
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(20.0f);
    }

    private void goToRandomPlace(LatLng marker, int zoom){
        mMap.addMarker(new MarkerOptions().position(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,zoom));
    }


    private void goToGeo(String placeName) throws IOException{
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(placeName,1);
        Address address = list.get(0);

        latOdessa = address.getLatitude();
        lngOdessa = address.getLongitude();

        generateValues(latOdessa,lngOdessa);

        LatLngBounds ODESSA = new LatLngBounds(
                new LatLng(latMin, lngMin), new LatLng(latMax, lngMax));
        LatLng odessaarker = new LatLng(latOdessa,lngOdessa);
        goToLocationZoom(ODESSA, 15);


    }

    private LatLng generateRandomCoords(){

        Random r = new Random();
         randomLatValue = latMin + (latMax - latMin) * r.nextDouble();
         randomLngValue = lngMin + (lngMax - lngMin) * r.nextDouble();

            LatLng randomMarker = new LatLng(randomLatValue, randomLngValue);
            return randomMarker;

    }

    @OnClick(R.id.randomLocationButton)
    public void onRandomButtonClick(){
        goToRandomPlace(generateRandomCoords(), 15);

    }

    private void generateValues(double lat, double lng){
         latKmValue = 0.00898 * RADIUS_ODESSA;
         lngKmValue = 0.01440 * RADIUS_ODESSA;
         latMin = lat - latKmValue;
         latMax = lat + latKmValue;
         lngMin = lng - lngKmValue;
         lngMax = lng + lngKmValue;
    }

}

