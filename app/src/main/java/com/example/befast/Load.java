package com.example.befast;

import android.Manifest;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.befast.ui.main.SectionsPagerAdapter;
import com.example.befast.databinding.ActivityLoadBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Load extends AppCompatActivity implements PDF.StringArrayListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ActivityLoadBinding binding;
    public Double toefl, toefr, toerl, toerr, camfl, camfr, camrl, camrr, presfl, presfr, presrl, presrr;
    public int ileokr;
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

    public String[] lapTimes = new String[10];
    public double[] delta = new double[10];
    public double[] timelap = new double[10];

    public double[] s = new double[10];
    public double speed[][];
    public double najszybszeOkrazenie;
    public int lap_nr;
    public int best = 999999999;
    int ktoreNajszybsze;
    public String[] temppres = new String[4];
    public int[] timeArray = new int[20];
    public Double t1, t2, t3, t4, p1, p2, p3, p4;
    final static int REQUEST_CODE = 1232;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        getValues();

        // Inicjalizacja ViewPagera
        viewPager = findViewById(R.id.view_pager);
        // Inicjalizacja TabLayout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putStringArray("Przejazdy", lapTimes);
        bundle.putDoubleArray("delta", delta);
        bundle.putInt("ktoreNajszybsze", ktoreNajszybsze);

        bundle.putDoubleArray("v1", v1);
        bundle.putDoubleArray("v2", v2);
        bundle.putDoubleArray("v3", v3);
        bundle.putDoubleArray("v4", v4);
        bundle.putDoubleArray("v5", v5);
        bundle.putDoubleArray("v6", v6);
        bundle.putDoubleArray("v7", v7);
        bundle.putDoubleArray("v8", v8);
        bundle.putDoubleArray("v9", v9);
        bundle.putDoubleArray("v10", v10);

        bundle.putDoubleArray("d1", delta1);
        bundle.putDoubleArray("d2", delta2);
        bundle.putDoubleArray("d3", delta3);
        bundle.putDoubleArray("d4", delta4);
        bundle.putDoubleArray("d5", delta5);
        bundle.putDoubleArray("d6", delta6);
        bundle.putDoubleArray("d7", delta7);
        bundle.putDoubleArray("d8", delta8);
        bundle.putDoubleArray("d9", delta9);
        bundle.putDoubleArray("d10", delta10);

        bundle.putIntArray("timeArray", timeArray);
        bundle.putDoubleArray("timelap", timelap);
        bundle.putStringArray("lapTimes", lapTimes);
        bundle.putDouble("t1", t1);
        bundle.putDouble("t2", t2);
        bundle.putDouble("t3", t3);
        bundle.putDouble("t4", t4);
        bundle.putDouble("p1", p1);
        bundle.putDouble("p2", p2);
        bundle.putDouble("p3", p3);
        bundle.putDouble("p4", p4);
        bundle.putDouble("presfl", presfl);
        bundle.putDouble("presfr", presfr);
        bundle.putDouble("presrl", presrl);
        bundle.putDouble("presrr", presrr);
        Laps lapsFragment = new Laps();
        lapsFragment.setArguments(bundle);

        Speed speedFragment = new Speed();
        speedFragment.setArguments(bundle);

        PDF pdfFragment = new PDF();
        pdfFragment.setArguments(bundle);

        adapter.addFragment(lapsFragment, "Laps");
        adapter.addFragment(speedFragment, "Data analysys");
        adapter.addFragment(pdfFragment, "Car settings");

        viewPager.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPermissions();
                createPDF();
                Snackbar.make(view, "Written Sucessfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void createPDF() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1080, 1920, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo); // Poprawka tej linii

        Canvas canvas = page.getCanvas();
////////////////////////////////////////////////////////////
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(48);

        String text = "Race logs from day " + setDate();

        float x = 240;
        float y = 100;

        canvas.drawText(text, x, y, paint);
////////////////////////////////////////////////////////////

        int tableWidth = 800;
        int tableHeight = 400;
        int startX = 140;
        int startY = 150;

        Paint tablePaint = new Paint();
        tablePaint.setColor(Color.WHITE);

        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);

        String[][] pies = {
                {"Driver:", ""},
                {"Race track:", ""}
        };

        String[][] camToe = {
                {"Car Side", "Camber", "Toe"},
                {"Front Right", "6.0", "10.0"},
                {"Front Left", "6.0", "10.0"},
                {"Rear Right", "8.0", "5.0"},
                {"Rear Left", "8.0", "5.0"}
        };
        /*
        camToe[1][1] = String.valueOf(camfr);
        camToe[2][1] = String.valueOf(camfl);
        camToe[3][1] = String.valueOf(camrr);
        camToe[4][1] = String.valueOf(camfl);
        camToe[1][2] = String.valueOf(toefr);
        camToe[2][2] = String.valueOf(toefl);
        camToe[3][2] = String.valueOf(toerr);
        camToe[4][2] = String.valueOf(toefl);

         */
        /*
        String[][] data = {
                {"Lap nr", "Lap time", "Delta" , "Comment"},
                {"1", lapTimes[1], String.valueOf(delta[1]), ""},
                {"2", lapTimes[2], String.valueOf(delta[2]), ""},
                {"3", lapTimes[3], String.valueOf(delta[3]), ""},
                {"4", lapTimes[4], String.valueOf(delta[4]), ""},
                {"5", lapTimes[5], String.valueOf(delta[5]), ""},
        };

 */

        String[][] data = {
                {"Lap nr", "Lap time", "Delta" , "Comment"},
                {"1", "01:40:91", "11.8", ""},
                {"2", "01:36:58", "7.47", ""},
                {"3", "01:29:11", "0.0", ""},
                {"4", "01:34:19", "5.08", ""},
                {"5", "01:35:64", "6.53", ""},
        };

        int rowHeight = 60;
        int columnWidth = tableWidth / (pies[0].length);

        for (int i = 0; i < pies.length; i++) {
            for (int j = 0; j < pies[i].length; j++) {
                x = startX + columnWidth * j;
                y = startY + rowHeight * i;

                canvas.drawText(pies[i][j], x + 10, y + 40, textPaint);
            }
            canvas.drawLine(x - 200,  y + 40,  x + 150, y + 40, linePaint);
        }

        columnWidth = tableWidth / (data[0].length);

        startY += 150;
        float X = startX;
        float Y = startY;

        int z = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                X = startX + columnWidth * j;
                Y = startY + rowHeight * i;
                z++;

                // Rysowanie linii oddzielających kolumny
                canvas.drawLine(X,Y + rowHeight, X, Y, linePaint);

                // Rysowanie linii oddzielających wiersze
                canvas.drawLine(X, Y,  X + columnWidth, Y, linePaint);

                canvas.drawText(data[i][j], X + 10, Y + 40, textPaint);

            }
        }
        canvas.drawLine(startX,  Y + rowHeight,  X + columnWidth, Y + rowHeight, linePaint);
        canvas.drawLine(X + columnWidth, startY,  X + columnWidth, Y + rowHeight, linePaint);

        columnWidth = tableWidth / (camToe[0].length);

        startY = Math.round(Y) + rowHeight + 50;


        z = 0;
        for (int i = 0; i < camToe.length; i++) {
            for (int j = 0; j < camToe[i].length; j++) {
                X = startX + columnWidth * j;
                Y = startY + rowHeight * i;
                z++;

                // Rysowanie linii oddzielających kolumny
                canvas.drawLine(X,Y + rowHeight, X, Y, linePaint);

                // Rysowanie linii oddzielających wiersze
                    canvas.drawLine(X, Y,  X + columnWidth, Y, linePaint);

                canvas.drawText(camToe[i][j], X + 10, Y + 40, textPaint);

            }
        }
        canvas.drawLine(startX,  Y + rowHeight,  X + columnWidth, Y + rowHeight, linePaint);
        canvas.drawLine(X + columnWidth, startY,  X + columnWidth, Y + rowHeight, linePaint);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        float startnewY = Y + 140;
        float startnewX = 280;
        paint.setTextSize(36);

        String temp = "Tyre pressure during the race";
        canvas.drawText(temp, startnewX, startnewY, paint);

        startnewY += 25;
        String[][] pressure = {
                {"Car Side", "Before race", "After race" , "Comment"},
                {"Front Right", "18.0", "35.0" , ""},
                {"Front Left", "18.0", "35.0" , ""},
                {"Rear Right", "25.0", "58.0" , ""},
                {"Rear Left", "25.0", "45.0" , ""}
        };
/*
        pressure[1][1] = String.valueOf(presfr);
        pressure[2][1] = String.valueOf(presfl);
        pressure[3][1] = String.valueOf(presrl);
        pressure[4][1] = String.valueOf(presrr);

        pressure[1][2] = String.valueOf(p1);
        pressure[2][2] = String.valueOf(p2);
        pressure[3][2] = String.valueOf(p3);
        pressure[4][2] = String.valueOf(p4);

 */

        columnWidth = tableWidth / (pressure[0].length);

        for (int i = 0; i < pressure.length; i++) {
            for (int j = 0; j < pressure[i].length; j++) {
                X = startX + columnWidth * j;
                Y = startnewY + rowHeight * i;

                // Rysowanie linii oddzielających kolumny
                canvas.drawLine(X,Y + rowHeight, X, Y, linePaint);

                // Rysowanie linii oddzielających wiersze
                canvas.drawLine(X, Y,  X + columnWidth, Y, linePaint);

                canvas.drawText(pressure[i][j], X + 10, Y + 40, textPaint);

            }
        }
        canvas.drawLine(startX,  Y + rowHeight,  X + columnWidth, Y + rowHeight, linePaint);
        canvas.drawLine(X + columnWidth, startnewY,  X + columnWidth, Y + rowHeight, linePaint);

///////////////////////////////////////////////////////////////////////////////////////////////////

        startnewY = Y + 140;
        startnewX = 310;

        String press = "Tyre temperature during the race";
        canvas.drawText(press, startnewX, startnewY, paint);

        startnewY += 25;

        String[][] temperature = {
                {"Car Side", "Before race", "After race" , "Comment"},
                {"Front Right", "25 °C", "48" , ""},
                {"Front Left", "25 °C", "35" , ""},
                {"Rear Right", "25 °C", "53" , ""},
                {"Rear Left", "25 °C", "42" , ""}
        };
        /*
        temperature[1][2] = String.valueOf(t1 + "°C");
        temperature[2][2] = String.valueOf(t2 + "°C");
        temperature[3][2] = String.valueOf(t3 + "°C");
        temperature[4][2] = String.valueOf(t4 + "°C");


         */
        for (int i = 0; i < temperature.length; i++) {
            for (int j = 0; j < temperature[i].length; j++) {


                X = startX + columnWidth * j;
                Y = startnewY + rowHeight * i;

                // Rysowanie linii oddzielających kolumny
                canvas.drawLine(X,Y + rowHeight, X, Y, linePaint);

                // Rysowanie linii oddzielających wiersze
                canvas.drawLine(X, Y,  X + columnWidth, Y, linePaint);

                canvas.drawText(temperature[i][j], X + 10, Y + 40, textPaint);

            }
        }
        canvas.drawLine(startX,  Y + rowHeight,  X + columnWidth, Y + rowHeight, linePaint);
        canvas.drawLine(X + columnWidth, startnewY,  X + columnWidth, Y + rowHeight, linePaint);
        document.finishPage(page);

///////////////////////////////////////////////////////////////////////////////////////////////////

        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String filename = "BEFAST logs.pdf";
        File file = new File(downloadDir, filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            document.close();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("mylog", "Error while writing" + e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getValues(){
        najszybszeOkrazenie = getIntent().getDoubleExtra("najszybszeOkrazenie", 0.0);
        ktoreNajszybsze = getIntent().getIntExtra("ktoreNajszybsze", 1);
        toefl = getIntent().getDoubleExtra("toefl", 10);
        toefr = getIntent().getDoubleExtra("toefr", 0.0);
        toerl = getIntent().getDoubleExtra("toerl", 5);
        toerr = getIntent().getDoubleExtra("toerr", 0.0);

        camfl = getIntent().getDoubleExtra("camfl", 0.0);
        camfr = getIntent().getDoubleExtra("camfr", 0.0);
        camrl = getIntent().getDoubleExtra("camrl", 0.0);
        camrr = getIntent().getDoubleExtra("camrr", 0.0);

        presfl = getIntent().getDoubleExtra("presfl", 0.0);
        presfr = getIntent().getDoubleExtra("presfr", 0.0);
        presrl = getIntent().getDoubleExtra("presrl", 25);
        presrr = getIntent().getDoubleExtra("presrr", 0.0);

        ileokr = getIntent().getIntExtra("ileokr", 0);

        lapTimes = getIntent().getStringArrayExtra("lapTimes");
        timeArray = getIntent().getIntArrayExtra("timeArray");
        //Bundle mBundle = new Bundle();
        //mBundle.putSerializable("speed",  speed);

        delta = getIntent().getDoubleArrayExtra("delta");

        lap_nr = getIntent().getIntExtra("lap_nr", 1);
        s = getIntent().getDoubleArrayExtra("s");
        timelap = getIntent().getDoubleArrayExtra("timelap");

        for(int i =0; i< timelap.length; i++){
            Log.d("Czasy z Loada", "" + timelap[i]);
        }

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

        p1 = getIntent().getDoubleExtra("p1", 0);
        p2 = getIntent().getDoubleExtra("p2", 0);
        p3 = getIntent().getDoubleExtra("p3", 0);
        p4 = getIntent().getDoubleExtra("p4", 0);

        t1 = getIntent().getDoubleExtra("t1", 0);
        t2 = getIntent().getDoubleExtra("t2", 0);
        t3 = getIntent().getDoubleExtra("t3", 0);
        t4 = getIntent().getDoubleExtra("t4", 0);
    }

    private void askPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    public String setDate(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = dateFormat.format(today);
        return formattedDate;
    }

    @Override
    public void onStringArrayReceived(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            Log.d("Wartość temp[" + i + "]: ", strings[i]);
        }
    }

    // Klasa adaptera dla ViewPagera
    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}