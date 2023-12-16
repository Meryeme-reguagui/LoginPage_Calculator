package com.ensaf.macalculatrice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    FrameLayout map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map=findViewById(R.id.map);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap=googleMap;
        LatLng ensaFes=new LatLng(33.996474 ,-4.991522 );
        this.gMap.addMarker(new MarkerOptions().position(ensaFes).title("Ensa Fès"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaFes));

        LatLng ensaTanger=new LatLng(35.737330 ,-5.894399 );
        this.gMap.addMarker(new MarkerOptions().position(ensaTanger).title("Ensa Tanger"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaTanger));

        LatLng ensaKech=new LatLng(31.646908 ,-8.020362 );
        this.gMap.addMarker(new MarkerOptions().position(ensaKech).title("Ensa Marrakech"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaKech));

        LatLng ensaKenitra=new LatLng(34.248610 ,-6.583230  );
        this.gMap.addMarker(new MarkerOptions().position(ensaKenitra).title("Ensa Kénitra"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaKenitra));

        LatLng ensaTetouan=new LatLng(35.562353,-5.364488 );
        this.gMap.addMarker(new MarkerOptions().position(ensaTetouan).title("Ensa Tétouan"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaTetouan));

        LatLng ensaSafi=new LatLng(32.326895,-9.263625);
        this.gMap.addMarker(new MarkerOptions().position(ensaSafi).title("Ensa Safi"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaSafi));

        LatLng ensaElJadida=new LatLng(33.251062,-8.434113);
        this.gMap.addMarker(new MarkerOptions().position(ensaElJadida).title("Ensa El Jadida"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaElJadida));

        LatLng ensaAgadir=new LatLng(30.406156,-9.529800);
        this.gMap.addMarker(new MarkerOptions().position(ensaAgadir).title("Ensa Agadir"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaAgadir));

        LatLng ensaAlHoceima=new LatLng(35.172773,-3.861954);
        this.gMap.addMarker(new MarkerOptions().position(ensaAlHoceima).title("Ensa Al Hoceima"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaAlHoceima));

        LatLng ensaOujda=new LatLng(34.650547,-1.96343);
        this.gMap.addMarker(new MarkerOptions().position(ensaOujda).title("Ensa Oujda"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaOujda));

        LatLng ensaBerrechid=new LatLng(33.258812,-7.584004);
        this.gMap.addMarker(new MarkerOptions().position(ensaBerrechid).title("Ensa Berrechid"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(ensaBerrechid));


    }
}
