package com.anilugale.wholesale;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchActivity extends FragmentActivity implements LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 35000, 10, this);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));
        mMap.setMyLocationEnabled(true);
       LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       Criteria criteria = new Criteria();
       String provider = locationManager.getBestProvider(criteria, true);
       Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

      if(myLocation!=null) {
          double latitude = myLocation.getLatitude();
          double longitude = myLocation.getLongitude();
          LatLng latLng = new LatLng(latitude, longitude);
          mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
          mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
          LatLng latLng1 = new LatLng(latitude, longitude);
          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 16.0f));
          mMap.addMarker(new MarkerOptions().position(latLng1).title("You are here!").snippet("Consider yourself located").icon(BitmapDescriptorFactory.fromResource(R.drawable.user_marker)));
      }
        else
      {

          LatLng latLng = new LatLng( 21.1289956,82.7792201);
          mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
          mMap.animateCamera(CameraUpdateFactory.zoomTo(3));

          mMap.clear();
          Toast.makeText(this,"your location setting is off turn on to get better experience.",Toast.LENGTH_SHORT).show();
      }
    }


    @Override
    public void onLocationChanged(Location location) {
        setUpMapIfNeeded();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(this,"Location provider on.",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(this,"Location provider off.",Toast.LENGTH_SHORT).show();
    }


}

