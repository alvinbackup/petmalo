package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pet_malo.databinding.ActivityMapstoreBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class mapstore extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ActivityMapstoreBinding binding;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    MarkerOptions marker;
    Vector<MarkerOptions> markerOptions;

//for image icon marker
public Bitmap mapicons(String drawableName,int width, int height){
    Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(drawableName,"drawable", getPackageName()));
    Bitmap reszisedBitmap = Bitmap.createScaledBitmap(imageBitmap,width,height,false);
    return reszisedBitmap;
}
//

//passing data from click marker String
HashMap<String, String> mMarker=new HashMap<String, String>();
//


//marker link for the markers in database
    private String URL = "https://pet-shop-management.000webhostapp.com/android_map_markers/all.php";
    RequestQueue requestQueue;
    Gson gson;
    Storesmarker[] storesmarkers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//Bottom Nav java End
        binding = ActivityMapstoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gson = new GsonBuilder().create();



        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);


        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(mapstore.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            //when permission denied
            ActivityCompat.requestPermissions(mapstore.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        markerOptions = new Vector<>();



        //Bottom nav start
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);
        //Set Home Nav As Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_findstore);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), My_cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_findstore:
                        startActivity(new Intent(getApplicationContext(), mapstore.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }
        });

    }


    private void getCurrentLocation() {
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
                if (location != null){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                            //Initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());
                            //Create marker Option
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("Me");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                            // Add Marker
                            enableMyLocation();

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        map=googleMap;
        for (MarkerOptions mark : markerOptions){
            map.addMarker(mark);
        }
        sendRequest();

    }
    private void enableMyLocation() {

        String perms[] = {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_NETWORK_STATE"};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
                Log.d("hafizxx","permission granted");
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission

            Log.d("hafizxx","permission denied");
            ActivityCompat.requestPermissions(this,perms ,200);

        }
    }
    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestQueue.add(stringRequest);

    }
    public Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            storesmarkers = gson.fromJson(response, Storesmarker[].class);
            Log.d("Storesmarker", "Number of Store Markers Data Point: " + storesmarkers.length);

if (storesmarkers.length<1){
    Toast.makeText(getApplicationContext(), "Problem Retrieving JSON Data", Toast.LENGTH_LONG).show();
    return;
}

            for (Storesmarker info: storesmarkers){
                Double lat = Double.parseDouble(info.lat);
                Double lng = Double.parseDouble(info.lng);
                String title = info.Store_name;
                String snippet= info.id;

              MarkerOptions marker =  new MarkerOptions().position(new LatLng(lat, lng))
                        .title(title)
                        .snippet(snippet)

                      .icon(BitmapDescriptorFactory.fromBitmap(mapicons(
                        "marker",100,100)));
                map.addMarker(marker).showInfoWindow();
                      String id = marker.getTitle();
                      mMarker.put(id, title);
                      String store_id =marker.getSnippet();
                      mMarker.put(store_id,snippet);


              map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                  @Override
                  public boolean onMarkerClick(Marker marker) {
                      String maptitle=mMarker.get(marker.getTitle());
                      String s_id=mMarker.get(marker.getSnippet());

                          Intent i = new Intent(mapstore.this, storepage.class);
                          i.putExtra("title",maptitle);
                          i.putExtra("id",s_id);
                          startActivity(i);
                          return false;

                  }

              });
            }
        }
    };
    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

}
