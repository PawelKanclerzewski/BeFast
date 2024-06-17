package com.example.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChangeSet extends AppCompatActivity {
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7, seekBar8;
    Button btn1;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    public String[] temp = new String[8];
    public String[] pres = new String[8];
    public Double toefr, toefl, toerl, toerr, camfr, camfl, camrl, camrr, presfr, presfl, presrl, presrr;
    public String[] lapTimes = new String[20]; // Tablica czasów okrążenia przekonwertowanych na format mm:ss:ds
    int t1, t2, t3, t4, p1, p2, p3, p4;
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
    public double najszybszeOkrazenie;
    int ktoreNajszybsze;
    public int[] timeArray = new int[20];
    public double[] delta = new double[20]; // Delta przekazywana do nowej aktywności (do zrobienia)
    int lap_nr = 0; // aktualny numer okrążenia
    public double[] s = new double[20]; // tablica drogi pokonanej na okrążeniach
    double[] timelap = new double[10]; // czas poszczególnego okrążenia zerowany po nowym okrążeniu z timera
    Double tt1 = (double) t1;
    Double tt2 = (double) t2;
    Double tt3 = (double) t3;
    Double tt4 = (double) t4;

    Double pp1 = (double) p1;
    Double pp2 = (double) p2;
    Double pp3 = (double) p3;
    Double pp4 = (double) p4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_set);

        najszybszeOkrazenie = getIntent().getDoubleExtra("najszybszeOkrazenie", 0.0);
        ktoreNajszybsze = getIntent().getIntExtra("ktoreNajszybsze", 1);

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

        lapTimes = getIntent().getStringArrayExtra("lapTimes");
        timeArray = getIntent().getIntArrayExtra("timeArray");

        delta = getIntent().getDoubleArrayExtra("delta");

        lap_nr = getIntent().getIntExtra("lap_nr", 1);
        s = getIntent().getDoubleArrayExtra("s");
        timelap = getIntent().getDoubleArrayExtra("timelap");

        v1 = getIntent().getDoubleArrayExtra("v1");
        v2 = getIntent().getDoubleArrayExtra("v2");
        v3 = getIntent().getDoubleArrayExtra("v3");
        v4 = getIntent().getDoubleArrayExtra("v4");
        v5 = getIntent().getDoubleArrayExtra("v5");
        v6 = getIntent().getDoubleArrayExtra("v6");
        v7 = getIntent().getDoubleArrayExtra("v7");
        v8 = getIntent().getDoubleArrayExtra("v8");
        v9 = getIntent().getDoubleArrayExtra("v9");
        v10 = getIntent().getDoubleArrayExtra("v10");

        delta1 = getIntent().getDoubleArrayExtra("delta1");
        delta2 = getIntent().getDoubleArrayExtra("delta2");
        delta3 = getIntent().getDoubleArrayExtra("delta3");
        delta4 = getIntent().getDoubleArrayExtra("delta4");
        delta5 = getIntent().getDoubleArrayExtra("delta5");
        delta6 = getIntent().getDoubleArrayExtra("delta6");
        delta7 = getIntent().getDoubleArrayExtra("delta7");
        delta8 = getIntent().getDoubleArrayExtra("delta8");
        delta9 = getIntent().getDoubleArrayExtra("delta9");
        delta10 = getIntent().getDoubleArrayExtra("delta10");


        btn1 = (Button) findViewById(R.id.button);
        // Inflate the layout for this fragment
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        textView1 = (TextView) findViewById(R.id.textView42);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        textView2 = (TextView) findViewById(R.id.textView45);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        textView3 = (TextView) findViewById(R.id.textView48);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        textView4 = (TextView) findViewById(R.id.textView51);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        textView5 = (TextView) findViewById(R.id.textView55);
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        textView6 = (TextView) findViewById(R.id.textView58);
        seekBar7 = (SeekBar) findViewById(R.id.seekBar7);
        textView7 = (TextView) findViewById(R.id.textView61);
        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
        textView8 = (TextView) findViewById(R.id.textView64);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView1.setText("Selected value: " + String.valueOf(i) + " [℃]");
                t1 = i;
                wpiszDoTemp (i, 1);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView2.setText("Selected value: " + String.valueOf(i) + " [℃]");
                t2 = i;
                wpiszDoTemp (i, 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView3.setText("Selected value: " + String.valueOf(i) + " [℃]");
                t3 = i;
                wpiszDoTemp (i, 3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView4.setText("Selected value: " + String.valueOf(i) + " [℃]");
                t4 = i;
                wpiszDoTemp (i, 4);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView5.setText("Selected value: " + String.valueOf(i) + " [psi]");
                p1 = i;
                wpiszDoPres (i, 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView6.setText("Selected value: " + String.valueOf(i) + " [psi]");
                p2 = i;
                wpiszDoPres (i, 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView7.setText("Selected value: " + String.valueOf(i) + " [psi]");
                p3 = i;
                wpiszDoPres (i, 3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView8.setText("Selected value: " + String.valueOf(i) + " [psi]");
                p4 = i;
                wpiszDoPres (i, 4);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void wpiszDoTemp (int t, int x) {
        temp[x-1] = String.valueOf(t);
    }

    public void wpiszDoPres (int p, int x) {
        pres[x-1] = String.valueOf(p);
    }
    public void konwerujnadouble(){
        tt1 = (double) t1;
        tt2 = (double) t2;
        tt3 = (double) t3;
        tt4 = (double) t4;

        pp1 = (double) p1;
        pp2 = (double) p2;
        pp3 = (double) p3;
        pp4 = (double) p4;
    }

    public void Load(View v) {
        konwerujnadouble();
        Intent Intent = new Intent(this, Load.class);
        Intent.putExtra("toefr", toefr);
        Intent.putExtra("toerl", toerl);
        Intent.putExtra("toerr", toerr);

        Intent.putExtra("camfl", camfl);
        Intent.putExtra("camfr", camfr);
        Intent.putExtra("camrl", camrl);
        Intent.putExtra("camrr", camrr);

        Intent.putExtra("presfl", presfl);
        Intent.putExtra("presfr", presfr);
        Intent.putExtra("presrl", presrl);
        Intent.putExtra("presrr", presrr);

        Intent.putExtra("lapTimes", lapTimes);
        Intent.putExtra("lap_nr", lap_nr);

        Intent.putExtra("s", s);
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

        Intent.putExtra("p1", pp1);
        Intent.putExtra("p2", pp2);
        Intent.putExtra("p3", pp3);
        Intent.putExtra("p4", pp4);
        Intent.putExtra("t1", tt1);
        Intent.putExtra("t2", tt2);
        Intent.putExtra("t3", tt3);
        Intent.putExtra("t4", tt4);

        startActivity(Intent);
    }
}

