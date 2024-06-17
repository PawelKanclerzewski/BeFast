package com.example.befast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.example.befast.SetStart.MAX_UPDATE_INTERVAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Timer extends AppCompatActivity {

    java.util.Timer timer;
    TimerTask timerTaskovr;
    Double timeovrl = 0.0; // ogólny czas wyścigu z timera
    double[] timelap = new double[10]; // czas poszczególnego okrążenia zerowany po nowym okrążeniu z timera
    public String[] lapTimes = new String[20]; // Tablica czasów okrążenia przekonwertowanych na format mm:ss:ds
    public double[][] x = new double[20][200]; // Tablica współrzędnej x w konkretnym czasie okrążenia
    public double[][] y = new double[20][200]; // Tablica współrzędnej y w konkretnym czasie okrążenia
    public double[][] speed = new double[20][200]; // Tablica prędkości w konkretnych punktach okrążenia
    double x1, x2, y1, y2;
    public double[] s = new double[20]; // tablica drogi pokonanej na okrążeniach

    public double[] v1 = new double[200];
    public double[] v2 = new double[200];
    public double[] v3 = new double[200];
    public double[] v4 = new double[200];
    public double[] v5 = new double[200];
    public double[] v6 = new double[200];
    public double[] v7 = new double[200];
    public double[] v8 = new double[200];
    public double[] v9 = new double[200];
    public double[] v10 = new double[200];
    public double[] delta1 = new double[200];
    public double[] delta2 = new double[200];
    public double[] delta3 = new double[200];
    public double[] delta4 = new double[200];
    public double[] delta5 = new double[200];
    public double[] delta6 = new double[200];
    public double[] delta7 = new double[200];
    public double[] delta8 = new double[200];
    public double[] delta9 = new double[200];
    public double[] delta10 = new double[200];
    public int ileokr = 0;

    int lap_nr = 0; // aktualny numer okrążenia
    int records = 1; // rekordy służące do wypisania na końcu wszystkich czasów
    public TextView textview_delta, textview_lap, textview_ovrl, textview_lapnr, textview_speed;

    Double toefl, toefr, toerl, toerr, camfl, camfr, camrl, camrr, presfl, presfr, presrl, presrr; // Wartości ustawień do aktywności Load

    LocationRequest locationRequest;
    LocationCallback locationCallBack;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    public int[] timeArray = new int[20];
    int time = 0; // Czas inkrementowany razem z gpsem
    public int[] czasy = new int[20]; // Tablica czasów inkrementowanych
    public double[] delta = new double[20]; // Delta przekazywana do nowej aktywności (do zrobienia)
    public double deltalap = 0.0; //Aktualna delta wyświetlana na ekranie
    double najszybszeOkrazenie;
    double potencjalnieNajszybsze = 999999999999999999999999999999.0;
    int ktoreNajszybsze = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Ustaw flagę FLAG_KEEP_SCREEN_ON na aktywność
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        textview_delta = (TextView) findViewById(R.id.text_delta);
        textview_lap = (TextView) findViewById(R.id.text_laptime);
        textview_ovrl = (TextView) findViewById(R.id.text_ovrtime);
        textview_lapnr = (TextView) findViewById(R.id.text_lapnr);
        textview_speed = (TextView) findViewById(R.id.text_speed);

        timer = new java.util.Timer();

        toefl = getIntent().getDoubleExtra("toefl", 0.0);
        toefr = getIntent().getDoubleExtra("toefr", 0.0);
        toerl = getIntent().getDoubleExtra("toerl", 0.0);
        toerr = getIntent().getDoubleExtra("toerr", 0.0);

        camfl = getIntent().getDoubleExtra("camfl", 0.0);
        camfr = getIntent().getDoubleExtra("camfr", 0.0);
        camrl = getIntent().getDoubleExtra("camrl", 0.0);
        camrr = getIntent().getDoubleExtra("camrr", 0.0);

        presfl = getIntent().getDoubleExtra("presfl", 0.0);
        presfr = getIntent().getDoubleExtra("presfr", 0.0);
        presrl = getIntent().getDoubleExtra("presrl", 0.0);
        presrr = getIntent().getDoubleExtra("presrr", 0.0);

        Log.d("pressfr", String.valueOf(presfr));
        Log.d("pressfl", String.valueOf(presfl));
        Log.d("pressrr", String.valueOf(presrr));
        Log.d("pressrl", String.valueOf(presrl));
        startGPS();
    }
    private void startGPS() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(MAX_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(MAX_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Showcords(locationResult.getLastLocation());
                ogarnijTablice(locationResult.getLastLocation());
            }
        };
        updateGPS();
        startLocationUpdates();
        startTimer();
    }

    private void startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    public void resetButton(View view){
    new_lap();
    }

    public void ogarnijTablice(Location location){
        if(lap_nr == 1){
            v1[time] = location.getSpeed();
        }else if(lap_nr == 2){
            v2[time] = location.getSpeed();
        } else if(lap_nr == 3){
            v3[time] = location.getSpeed();
        } else if(lap_nr == 4){
            v4[time] = location.getSpeed();
        } else if(lap_nr == 5){
            v5[time] = location.getSpeed();
        } else if(lap_nr == 6){
            v6[time] = location.getSpeed();
        } else if(lap_nr == 7){
            v7[time] = location.getSpeed();
        } else if(lap_nr == 8){
            v8[time] = location.getSpeed();
        } else if(lap_nr == 9){
            v9[time] = location.getSpeed();
        } else if(lap_nr == 10){
            v10[time] = location.getSpeed();
        }
    }

    public void ogarnijDelte(Double deltalap) {
        if (lap_nr == 1) {
            delta1[time] = deltalap;
        } else if (lap_nr == 2) {
            delta2[time] = deltalap;
        } else if (lap_nr == 3) {
            delta3[time] = deltalap;
        } else if (lap_nr == 4) {
            delta4[time] = deltalap;
        } else if (lap_nr == 5) {
            delta5[time] = deltalap;
        } else if (lap_nr == 6) {
            delta6[time] = deltalap;
        } else if (lap_nr == 7) {
            delta7[time] = deltalap;
        } else if (lap_nr == 8) {
            delta8[time] = deltalap;
        } else if (lap_nr == 9) {
            delta9[time] = deltalap;
        } else if (lap_nr == 10) {
            delta10[time] = deltalap;
        }
    }

    private void Showcords(Location location){
        x[lap_nr][time] = location.getLatitude();
        y[lap_nr][time] = location.getLongitude();
        speed[lap_nr][time] = location.getSpeed();
        s[lap_nr] += speed[lap_nr][time];

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        textview_speed.setText(String.valueOf(decimalFormat.format(speed[lap_nr][time]*3600/1000) + " km/h"));

        Log.d("Time", String.valueOf(time));
        Log.d("X", String.valueOf(x[lap_nr][time]));
        Log.d("Y", String.valueOf(y[lap_nr][time]));
        Log.d("Current Speed", String.valueOf(speed[lap_nr][time]));
        if(lap_nr>=2) {
            Log.d("Previous Speed", String.valueOf(speed[lap_nr-1][time]));
            Log.d("Delta", (String.valueOf(speed[lap_nr-1][time]- speed[lap_nr][time])));
        }

        Log.d("Distance", String.valueOf(s[lap_nr]));

        getDelta();
        time++;

    }
    private void getDelta() {
        if (lap_nr >= 2) {
            BigDecimal prevSpeed = BigDecimal.valueOf(speed[lap_nr - 1][time]);
            BigDecimal currSpeed = BigDecimal.valueOf(speed[lap_nr][time]);

            // Oblicz różnicę prędkości między poprzednim a obecnym punktem

            BigDecimal diff;
            if(prevSpeed.doubleValue() == 0.0){
                diff = currSpeed;
            } else diff = prevSpeed.subtract(currSpeed);
            Log.d("diff", String.valueOf(speed[lap_nr-1][time]- speed[lap_nr][time]));
            // Jeśli różnica jest dodatnia (zwiększająca się prędkość), dodaj ją do deltalap
            // Jeśli różnica jest ujemna (zmniejszająca się prędkość), odejmij ją od deltalap
            Log.d("deltalap-1", String.valueOf(deltalap));

            deltalap = deltalap + diff.doubleValue(); // Dodawanie w BigDecimal
            Log.d("deltalap", String.valueOf(deltalap));

            Log.d("DELTA", String.valueOf(deltalap));
            ogarnijDelte(deltalap);

            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String formattedNumber = decimalFormat.format(deltalap);
            //String formattedNumber = decimalFormat.format(diff);
            textview_delta.setText(formattedNumber);

            if (deltalap <= 0) {
                textview_delta.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                textview_delta.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }
    }

    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Timer.this);
        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    tracking(location);
                }
            });
        }
        else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }
    private void tracking(Location location) {
        if (location != null) {

            Log.d("PIES", "Nie wiem co tu wpisać, ale boję się usunąć xD");
        }
    }
    public void startTimer()
    {
        timerTaskovr = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        timeovrl++;
                        timelap[lap_nr]++;
                        textview_ovrl.setText(getTimerText(timeovrl));
                        textview_lap.setText(getTimerText(timelap[lap_nr]));

                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTaskovr, 0 ,10);
    }


    private String getTimerText(Double time){

        int rounded = (int) Math.round(time);

        int milis = rounded % 100;
        int seconds = (rounded / 100) % 60;
        int minutes = rounded / 6000;

        return formatTime(milis, seconds, minutes);
    }

    private String formatTime(int milis, int seconds, int minutes){
        return String.format("%02d", minutes) + " : " + String.format("%02d", seconds) + " : " + String.format("%02d", milis);
    }

    public void new_lap(){
        ileokr++;
        czyNajszybszy();
        updateTime();
        timeArray[lap_nr] = time;
        czasy[lap_nr] = time;
        Log.d(lapTimes[lap_nr], String.valueOf(lap_nr));
        lap_nr++;
        textview_lapnr.setText(String.format("%d",lap_nr));
        time = 0;
        deltalap = 0;
    }

    private void czyNajszybszy() {
        if(lap_nr > 0 && timelap[lap_nr] < potencjalnieNajszybsze){
                potencjalnieNajszybsze = timelap[lap_nr];
                ktoreNajszybsze = lap_nr;
            }
        }

    private void updateTime() {
        lapTimes[lap_nr] = getTimerText(timelap[lap_nr]);
        formatTime(0, 0, 0);
    }

    public void wypisz(){
        while(records < lap_nr){
            delta[records] = (timelap[records] - najszybszeOkrazenie) / 100;
            Log.d("Lap: " + String.format("%d", records) + "    ", lapTimes[records]);
            Log.d("Lap " + String.format("%d", records) + "    Delta:", String.valueOf(delta[records]));
            records++;
        }

        Log.d("Najszybsze okrążenie: ", String.valueOf(ktoreNajszybsze));
        Log.d("Czas najszybszego okrążenia: ", String.valueOf(najszybszeOkrazenie));

        int czas=0;
        while(czas<=time){
            Log.d("X", String.valueOf(x[czas]));
            Log.d("Y", String.valueOf(y[czas]));
            Log.d("Y", String.valueOf(speed[lap_nr][czas]));
            czas++;
        }
        Log.d("Czy działaTimer", "Wartość zmiennej liczba: " + timeArray[1]);
        Log.d("Czy działaTimer", "Wartość zmiennej liczba: " + timeArray[2]);
    }
    public void Load(View v) {
        ileokr++;
        najszybszy();
        wypisz();
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);

        Intent Intent = new Intent(this, ChangeSet.class);
        Intent.putExtra("toefr", toefr);
        Intent.putExtra("toerl", toerl);
        Intent.putExtra("toerr", toerr);
        Intent.putExtra("ileokr", ileokr);

        Intent.putExtra("camfl", camfl);
        Intent.putExtra("camfr", camfr);
        Intent.putExtra("camrl", camrl);
        Intent.putExtra("camrr", camrr);

        Intent.putExtra("presfl", presfl);
        Intent.putExtra("presfr", presfr);
        Intent.putExtra("presrl", presrl);
        Intent.putExtra("tpresrr", presrr);

        Intent.putExtra("lapTimes", lapTimes);
        Intent.putExtra("lap_nr", lap_nr);

        Intent.putExtra("s", s);
        Intent.putExtra("speed", speed);
        Intent.putExtra("czasy", czasy);
        Intent.putExtra("delta", delta);
        Intent.putExtra("najszybszeOkrazenie", najszybszeOkrazenie);
        Intent.putExtra("ktoreNajszybsze", ktoreNajszybsze);

        Intent.putExtra("v1", v1);
        Intent.putExtra("v2", v2);
        Intent.putExtra("v3", v3);
        Intent.putExtra("v4", v4);
        Intent.putExtra("v5", v5);
        Intent.putExtra("v6", v6);
        Intent.putExtra("v7", v7);
        Intent.putExtra("v8", v8);
        Intent.putExtra("v9", v9);
        Intent.putExtra("v10", v10);
        Intent.putExtra("delta1", delta1);
        Intent.putExtra("delta2", delta2);
        Intent.putExtra("delta3", delta3);
        Intent.putExtra("delta4", delta4);
        Intent.putExtra("delta5", delta5);
        Intent.putExtra("delta6", delta6);
        Intent.putExtra("delta7", delta7);
        Intent.putExtra("delta8", delta8);
        Intent.putExtra("delta9", delta9);
        Intent.putExtra("delta10", delta10);
        Intent.putExtra("timelap", timelap);
        Intent.putExtra("timeArray", timeArray);

        startActivity(Intent);

    }
    private void najszybszy() {
        najszybszeOkrazenie = timelap[ktoreNajszybsze];
    }
}