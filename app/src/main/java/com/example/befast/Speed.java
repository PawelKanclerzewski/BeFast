package com.example.befast;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.WHITE;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Speed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Speed extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LineGraphSeries<DataPoint> series;
    public double x1, x2, y1, y2, x3, x4, y3, y4, x5, y5, x6, y6, x7, y7, x8, y8, x21, y21, x22, y22, x23, y23, x24, y24, x25, y25, x26, y26, x27, y27, x28, y28;
    public double[] p1 = new double[200];
    public double[] p2 = new double[200];
    public double[] p3 = new double[200];
    public double[] p4 = new double[200];
    public double[] p5 = new double[200];
    public double[] p6 = new double[200];
    public double[] p7 = new double[200];
    public double[] p8 = new double[200];
    public double[] p9 = new double[200];
    public double[] p10 = new double[200];
    public double[] pnew1 = new double[200];
    public double[] pnew2 = new double[200];
    public double[] d1 = new double[200];
    public double[] d2 = new double[200];
    public double[] d3 = new double[200];
    public double[] d4 = new double[200];
    public double[] d5 = new double[200];
    public double[] d6 = new double[200];
    public double[] d7 = new double[200];
    public double[] d8 = new double[200];
    public double[] d9 = new double[200];
    public double[] d10 = new double[200];
    public double[] dnew1 = new double[200];
    public double[] dnew2 = new double[200];
    public int[] czasy = new int[20];
    public int czasyPoj;
    public String[] lapTimes = new String[20];
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    public boolean b1, b2, b3, b4 , b5, b6, b7, b8;
    public Speed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Speed.
     */
    // TODO: Rename and change types and number of parameters
    public static Speed newInstance(String param1, String param2) {
        Speed fragment = new Speed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_speed, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            p1 = bundle.getDoubleArray("v1");
            p2 = bundle.getDoubleArray("v2");
            p3 = bundle.getDoubleArray("v3");
            p4 = bundle.getDoubleArray("v4");
            p5 = bundle.getDoubleArray("v5");
            p6 = bundle.getDoubleArray("v6");
            p7 = bundle.getDoubleArray("v7");
            p8 = bundle.getDoubleArray("v8");
            p9 = bundle.getDoubleArray("v9");
            p10 = bundle.getDoubleArray("v10");
            d1 = bundle.getDoubleArray("d1");
            d2 = bundle.getDoubleArray("d2");
            d3 = bundle.getDoubleArray("d3");
            d4 = bundle.getDoubleArray("d4");
            d5 = bundle.getDoubleArray("d5");
            d6 = bundle.getDoubleArray("d6");
            d7 = bundle.getDoubleArray("d7");
            d8 = bundle.getDoubleArray("d8");
            d9 = bundle.getDoubleArray("d9");
            d10 = bundle.getDoubleArray("d10");
            czasy = bundle.getIntArray("timeArray");
            lapTimes = bundle.getStringArray("lapTimes");
        }
        b1 = false;
        b2 = false;
        b3 = false;
        b4 = false;
        b5 = false;
        b6 = false;
        b7 = false;
        b8 = false;


        /////////////////////////////////WYKRESY///////////////////////////
        x1 = 0.0;
        x2 = 0.0;
        pnew1 = p1;
        pnew2 = p2;
        dnew1 = d1;
        dnew2 = d2;

        GraphView graph = (GraphView) v.findViewById(R.id.Graf1);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series6 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series7 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series8 = new LineGraphSeries<>();

        graph.setTitle("Speed over time");
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setTextSize(24);
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer.setVerticalAxisTitleColor(Color.WHITE);
        gridLabelRenderer.setHorizontalAxisTitleColor(Color.WHITE);
        gridLabelRenderer.setHorizontalAxisTitle("Time [s]");
        gridLabelRenderer.setVerticalAxisTitle("Speed [km/h]");
        gridLabelRenderer.setGridColor(Color.WHITE);
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(Math.max(czasy[1], czasy[2]));

        for(int i = 0; i <= czasy[1]; i++){
            x1 = i;
            y1 = p1[i];
            series1.appendData(new DataPoint(x1, y1), true, 500);
        }
        for(int i = 0; i <= czasy[2]; i++){
            x2 = i;
            y2 = p2[i];
            series2.appendData(new DataPoint(x2, y2), true, 500);
        }
        for(int i = 0; i <= czasy[3]; i++){
            x3 = i;
            y3 = p3[i];
            series3.appendData(new DataPoint(x3, y3), true, 500);
        }
        for(int i = 0; i <= czasy[4]; i++){
            x4 = i;
            y4 = p4[i];
            series4.appendData(new DataPoint(x4, y4), true, 500);
        }
        for(int i = 0; i <= czasy[5]; i++){
            x5 = i;
            y5 = p5[i];
            series5.appendData(new DataPoint(x5, y5), true, 500);
        }
        for(int i = 0; i <= czasy[6]; i++){
            x6 = i;
            y6 = p6[i];
            series6.appendData(new DataPoint(x6, y6), true, 500);
        }
        for(int i = 0; i <= czasy[7]; i++){
            x7 = i;
            y7 = p7[i];
            series7.appendData(new DataPoint(x7, y7), true, 500);
        }
        for(int i = 0; i <= czasy[8]; i++){
            x8 = i;
            y8 = p8[i];
            series8.appendData(new DataPoint(x8, y8), true, 500);
        }

        series1.setColor(Color.rgb(255, 0, 0));     // Czerwony
        series2.setColor(Color.rgb(0, 255, 0));     // Zielony
        series3.setColor(Color.rgb(0, 0, 255));     // Niebieski
        series4.setColor(Color.rgb(255, 255, 0));   // Żółty
        series5.setColor(Color.rgb(255, 0, 255));   // Magenta
        series6.setColor(Color.rgb(0, 255, 255));   // Cyjan
        series7.setColor(Color.rgb(128, 0, 0));    // Ciemnoczerwony
        series8.setColor(Color.rgb(0, 128, 0));    // Ciemnozielony
////////////////////////WYKRES 2///////////////////////////////
        GraphView graph2 = (GraphView) v.findViewById(R.id.Graf2);
        LineGraphSeries<DataPoint> series21 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series22 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series23 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series24 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series25 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series26 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series27 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series28 = new LineGraphSeries<>();

        graph2.setTitle("Time delta over time");
        graph2.setTitleColor(Color.WHITE);
        GridLabelRenderer gridLabelRenderer2 = graph2.getGridLabelRenderer();
        gridLabelRenderer2.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setVerticalAxisTitleColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalAxisTitleColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalAxisTitle("Time [s]");
        gridLabelRenderer2.setVerticalAxisTitle("Delta [km/h]");
        gridLabelRenderer2.setGridColor(Color.WHITE);
        gridLabelRenderer2.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalLabelsColor(Color.WHITE);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(1);
        graph2.getViewport().setMaxX(Math.max(czasy[1], czasy[2]));


        for(int i = 0; i <= czasy[1]; i++){
            x21 = i;
            y21 = d1[i];
            series21.appendData(new DataPoint(x21, y21), true, 500);
        }
        for(int i = 0; i <= czasy[2]; i++){
            x22 = i;
            y22 = d2[i];
            series22.appendData(new DataPoint(x22, y22), true, 500);
        }
        for(int i = 0; i <= czasy[3]; i++){
            x23 = i;
            y23 = d3[i];
            series23.appendData(new DataPoint(x23, y23), true, 500);
        }
        for(int i = 0; i <= czasy[4]; i++){
            x24 = i;
            y24 = d4[i];
            series24.appendData(new DataPoint(x24, y24), true, 500);
        }
        for(int i = 0; i <= czasy[5]; i++){
            x25 = i;
            y25 = d5[i];
            series25.appendData(new DataPoint(x25, y25), true, 500);
        }
        for(int i = 0; i <= czasy[6]; i++){
            x26 = i;
            y26 = d6[i];
            series26.appendData(new DataPoint(x26, y26), true, 500);
        }
        for(int i = 0; i <= czasy[7]; i++){
            x27 = i;
            y27 = d7[i];
            series27.appendData(new DataPoint(x27, y27), true, 500);
        }
        for(int i = 0; i <= czasy[8]; i++){
            x28 = i;
            y28 = d8[i];
            series28.appendData(new DataPoint(x28, y28), true, 500);
        }
        series21.setColor(Color.rgb(255, 0, 0));     // Czerwony
        series22.setColor(Color.rgb(0, 255, 0));     // Zielony
        series23.setColor(Color.rgb(0, 0, 255));     // Niebieski
        series24.setColor(Color.rgb(255, 255, 0));   // Żółty
        series25.setColor(Color.rgb(255, 0, 255));   // Magenta
        series26.setColor(Color.rgb(0, 255, 255));   // Cyjan
        series27.setColor(Color.rgb(128, 0, 0));    // Ciemnoczerwony
        series28.setColor(Color.rgb(0, 128, 0));    // Ciemnozielony

        // Inflate the layout for this fragment
        btn1 = v.findViewById(R.id.button2);
        if(lapTimes[1] == null){ btn1.setVisibility(View.GONE);}
        btn1.setOnClickListener(view -> {
            if(b1){
                b1 = false;
                graph.removeSeries(series1);
                graph2.removeSeries(series21);

            } else {
                b1 = true;
                graph.addSeries(series1);
                graph2.addSeries(series21);

            }
            changeUI(btn1, b1);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn2 = v.findViewById(R.id.button3);
        if(lapTimes[2] == null){ btn2.setVisibility(View.GONE);}
        btn2.setOnClickListener(view -> {
            if(b2){
                b2 = false;
                graph.removeSeries(series2);
                graph2.removeSeries(series22);

            } else {
                b2 = true;
                graph.addSeries(series2);
                graph2.addSeries(series22);
            }
            changeUI(btn2, b2);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn3 = v.findViewById(R.id.button4);
        if(lapTimes[3] == null){ btn3.setVisibility(View.GONE);}
        btn3.setOnClickListener(view -> {
            if(b3){
                b3 = false;
                graph.removeSeries(series3);
                graph2.removeSeries(series23);

            } else {
                b3 = true;
                graph.addSeries(series3);
                graph2.addSeries(series23);
            }
            changeUI(btn3, b3);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn4 = v.findViewById(R.id.button5);
        if(lapTimes[4] == null){ btn4.setVisibility(View.GONE);}
        btn4.setOnClickListener(view -> {
            if(b4){
                b4 = false;
                graph.removeSeries(series4);
                graph2.removeSeries(series24);
            } else {
                b4 = true;
                graph.addSeries(series4);
                graph2.addSeries(series24);
            }
            changeUI(btn4, b4);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn5 = v.findViewById(R.id.button6);
        if(lapTimes[5] == null){ btn5.setVisibility(View.GONE);}
        btn5.setOnClickListener(view -> {
            if(b5){
                b5 = false;
                graph.removeSeries(series5);
                graph2.removeSeries(series25);
            } else {
                b5 = true;
                graph.addSeries(series5);
                graph2.addSeries(series25);
            }
            changeUI(btn5, b5);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn6 = v.findViewById(R.id.button7);
        if(lapTimes[6] == null){ btn6.setVisibility(View.GONE);}
        btn6.setOnClickListener(view -> {
            if(b6){
                b6 = false;
                graph.removeSeries(series6);
                graph2.removeSeries(series26);
            } else {
                b6 = true;
                graph.addSeries(series6);
                graph2.addSeries(series26);
            }
            changeUI(btn6, b6);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn7 = v.findViewById(R.id.button8);
        if(lapTimes[7] == null){ btn7.setVisibility(View.GONE);}
        btn7.setOnClickListener(view -> {
            if(b7){
                b7 = false;
                graph.removeSeries(series7);
                graph2.removeSeries(series27);
            } else {
                b7 = true;
                graph.addSeries(series7);
                graph2.addSeries(series27);
            }
            changeUI(btn7, b7);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        btn8 = v.findViewById(R.id.button9);
        if(lapTimes[8] == null){ btn8.setVisibility(View.GONE);}
        btn8.setOnClickListener(view -> {
            if(b8){
                b8 = false;
                graph.removeSeries(series8);
                graph2.removeSeries(series28);
            } else {
                b8 = true;
                graph.addSeries(series8);
                graph2.addSeries(series28);
            }
            changeUI(btn8, b8);
            graph.onDataChanged(false, false);
            graph2.onDataChanged(false, false);
        });

        return v;
    }

    public void changeUI(Button button, boolean onoff){
        if(onoff){
            button.setBackgroundResource(R.drawable.custom_button);
        }else{
            button.setBackgroundResource(R.drawable.buttonbeforespeed);
        }

    }
}