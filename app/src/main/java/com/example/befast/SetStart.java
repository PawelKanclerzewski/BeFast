package com.example.befast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class SetStart extends AppCompatActivity {

    private static final int PERMISSIONS_FINE_LOCATION = 99;
    Boolean gps_done = false;
    double x1, x2, y1, y2;

    public static final int DEFAULT_UPDATE_INTERVAL = 1;
    public static final int MAX_UPDATE_INTERVAL = 10;

    TextView longtidual, lateral, accuracy;

    LocationRequest locationRequest;
    LocationCallback locationCallBack;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_start);

        longtidual = (TextView)findViewById(R.id.text_n2);
        lateral = (TextView)findViewById(R.id.text_e2);
        accuracy = (TextView)findViewById(R.id.text_e3);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(MAX_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(MAX_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateUIValues(locationResult.getLastLocation());
            }
        };
        updateGPS();
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else {
                    Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SetStart.this);
        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    updateUIValues(location);
                }
            });
        }
        else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    private void updateUIValues(Location location) {
        if (location != null) {
            lateral.setText(String.valueOf(location.getLatitude()));
            longtidual.setText(String.valueOf(location.getLongitude()));
            accuracy.setText(String.valueOf(location.getAccuracy()));

            if (gps_done) {
                x2 = location.getLatitude();
                y2 = location.getLongitude();
            } else{
                x1 = location.getLatitude();
                y1 = location.getLongitude();
            }
        }
        else {
            lateral.setText("Please wait");
            longtidual.setText("Please wait");
            accuracy.setText("Please wait");
        }

    }
    public void next(View v)
    {
        TextView tv = (TextView)findViewById(R.id.text_info);
        TextView tv2 = (TextView)findViewById(R.id.text_n3);
        Button btn = (Button) findViewById(R.id. button_next_set);
        if (gps_done)
        {
            Go(v);
        } else
        {
            tv.setText("Go to the second start point");
            tv2.setText("Coordinates of the second point");
            btn.setText("Go to car settings");
            startLocationUpdates();
            gps_done = true;
        }
    }

    public void Go(View v){
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
        Intent Intent = new Intent(this, Car_settings.class);

        Intent.putExtra("x1", x1);
        Intent.putExtra("y1", y1);
        Intent.putExtra("x2", x2);
        Intent.putExtra("y2", y2);

        startActivity(Intent);
    }

}