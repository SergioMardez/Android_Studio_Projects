package com.example.sergio.seccion_09_maps.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sergio.seccion_09_maps.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, View.OnClickListener {

    View rootView;
    private GoogleMap gMap;
    private MapView mapView;

    private List<Address> addresses;
    private Geocoder geoCoder;

    private MarkerOptions marker;

    private FloatingActionButton fab;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        fab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.map);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    private void checkGPSEnabled () {
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);

            if(gpsSignal == 0) {
                //El gps no esta activado. Se pide activarlo
                showInfoAlert();
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showInfoAlert() {
        new AlertDialog.Builder(getContext()).setTitle("GPS Signal")
                .setMessage("You don't have GPS signal. Would you like to enable the GPS signal now?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", null).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng fuentesO = new LatLng(40.605, -6.822);

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        //Opciones del marcador
        marker = new MarkerOptions();
        marker.position(fuentesO);
        marker.title("Mi marcador");
        marker.draggable(true);
        marker.snippet("Esto es una caja de texto donde modificar los datos");
        marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));

        gMap.addMarker(marker);
        gMap.addMarker(new MarkerOptions().position(fuentesO).title("Hola desde el pueblo").draggable(true));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(fuentesO));
        gMap.animateCamera(zoom);

        //Para recoger la informacion de las calles
        gMap.setOnMarkerDragListener(this);

        geoCoder = new Geocoder(getContext(), Locale.getDefault());

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        //Para cerrar la ventana del marcador
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitud = marker.getPosition().longitude;

        //Para recoger la informacion
        try {
            addresses = geoCoder.getFromLocation(latitude, longitud, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();

        marker.setSnippet("address: " + address + "\n" +
                ", " + city +
                ", " + country +
                ", " + postalCode);
        marker.showInfoWindow();

        /*
        Toast.makeText(getContext(), "address: " + address + "\n" +
                "Ciudad: " + city + "\n" +
                "Estado: " + state + "\n" +
                "Pais: " + country + "\n" +
                "Codigo Postal: " + postalCode + "\n"
                , Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onClick(View v) {
        this.checkGPSEnabled();
    }
}
