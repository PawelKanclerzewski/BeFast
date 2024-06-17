package com.example.befast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Laps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Laps extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private double[] delta;
    private String[] lapTimes;
    double najszybszeOkrazenie;
    double x, y;
    double[] times = new double[10];
    int miejsca = 0;
    public Laps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Laps.
     */
    // TODO: Rename and change types and number of parameters
    public static Laps newInstance(String param1, String param2) {
        Laps fragment = new Laps();
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

        Bundle bundle = getArguments();
        if (bundle != null) {
            lapTimes = bundle.getStringArray("Przejazdy");
            delta = bundle.getDoubleArray("delta");
            najszybszeOkrazenie = bundle.getDouble("najszybszeOkrazenie");
            times = bundle.getDoubleArray("timelap");

        }
        View v = inflater.inflate(R.layout.fragment_laps, container, false);

        TextView Number1 = (TextView) v.findViewById(R.id.textView1);
        TextView Number2 = (TextView) v.findViewById(R.id.textView4);
        TextView Number3 = (TextView) v.findViewById(R.id.textView7);
        TextView Number4 = (TextView) v.findViewById(R.id.textView10);
        TextView Number5 = (TextView) v.findViewById(R.id.textView13);
        TextView Number6 = (TextView) v.findViewById(R.id.textView16);
        TextView Number7 = (TextView) v.findViewById(R.id.textView19);
        TextView Number8 = (TextView) v.findViewById(R.id.textView22);
        TextView Number9 = (TextView) v.findViewById(R.id.textView25);
        TextView Number10 = (TextView) v.findViewById(R.id.textView28);

        TextView Laptime1 = (TextView) v.findViewById(R.id.textView2);
        TextView Delta1 = (TextView) v.findViewById(R.id.textView3);
        TextView Laptime2 = (TextView) v.findViewById(R.id.textView5);
        TextView Delta2 = (TextView) v.findViewById(R.id.textView6);
        TextView Laptime3 = (TextView) v.findViewById(R.id.textView8);
        TextView Delta3 = (TextView) v.findViewById(R.id.textView9);
        TextView Laptime4 = (TextView) v.findViewById(R.id.textView11);
        TextView Delta4 = (TextView) v.findViewById(R.id.textView12);
        TextView Laptime5 = (TextView) v.findViewById(R.id.textView14);
        TextView Delta5 = (TextView) v.findViewById(R.id.textView15);
        TextView Laptime6 = (TextView) v.findViewById(R.id.textView17);
        TextView Delta6 = (TextView) v.findViewById(R.id.textView18);
        TextView Laptime7 = (TextView) v.findViewById(R.id.textView20);
        TextView Delta7 = (TextView) v.findViewById(R.id.textView21);
        TextView Laptime8 = (TextView) v.findViewById(R.id.textView23);
        TextView Delta8 = (TextView) v.findViewById(R.id.textView24);
        TextView Laptime9 = (TextView) v.findViewById(R.id.textView26);
        TextView Delta9 = (TextView) v.findViewById(R.id.textView27);

        if(lapTimes[1] != null){
            miejsca++;
            Laptime1.setText(lapTimes[1]);
            Number1.setText("1.");
            if(delta[1] > 0){
                Delta1.setTextColor(Color.parseColor("#FF0000"));
                Delta1.setText("+" + String.valueOf(delta[1]));
            }
            else if (delta[1] == 0){
                Delta1.setText(String.valueOf(delta[1]));
                Delta1.setTextColor(Color.parseColor("#0B6623"));
            }
        }

        if(lapTimes[2] != null){
            miejsca++;
            Laptime2.setText(lapTimes[2]);
            Number2.setText("2.");
            if(delta[2] > 0){
                Delta2.setTextColor(Color.parseColor("#FF0000"));
                Delta2.setText("+" + String.valueOf(delta[2]));
            }
            else if (delta[2] == 0) {
                Delta2.setTextColor(Color.parseColor("#0B6623"));
                Delta2.setText(String.valueOf(delta[2]));
            }
        }

        if(lapTimes[3] != null){
            miejsca++;
            Laptime3.setText(lapTimes[3]);
            Number3.setText("3.");
            if(delta[3]>0){
                Delta3.setTextColor(Color.parseColor("#FF0000"));
                Delta3.setText("+" + String.valueOf(delta[3]));
            }
            else if (delta[3] == 0){
                Delta3.setTextColor(Color.parseColor("#0B6623"));
                Delta3.setText(String.valueOf(delta[3]));
            }
        }

        if(lapTimes[4] != null){
            miejsca++;
            Laptime4.setText(lapTimes[4]);
            Number4.setText("4.");
            if(delta[4] > 0){
                Delta4.setTextColor(Color.parseColor("#FF0000"));
                Delta4.setText("+" + String.valueOf(delta[4]));
            }
            else if (delta[4] == 0){
                Delta4.setTextColor(Color.parseColor("#0B6623"));
                Delta4.setText(String.valueOf(delta[4]));
            }
        }

        if(lapTimes[5] != null){
            miejsca++;
            Laptime5.setText(lapTimes[5]);
            Number5.setText("5.");

            if(delta[5] > 0){
                Delta5.setTextColor(Color.parseColor("#FF0000"));
                Delta5.setText("+" + String.valueOf(delta[5]));
            }
            else if (delta[5] == 0){
                Delta5.setTextColor(Color.parseColor("#0B6623"));
                Delta5.setText(String.valueOf(delta[5]));
            }
        }

        if(lapTimes[6] != null){
            miejsca++;
            Laptime6.setText(lapTimes[6]);
            Number6.setText("6.");
            if(delta[6] > 0){
                Delta6.setTextColor(Color.parseColor("#FF0000"));
                Delta6.setText("+" + String.valueOf(delta[6]));
            }
            else if (delta[6] == 0){
                Delta6.setTextColor(Color.parseColor("#0B6623"));
                Delta6.setText(String.valueOf(delta[6]));
            }
        }

        if(lapTimes[7] != null){
            miejsca++;
            Laptime7.setText(lapTimes[7]);
            Number7.setText("7.");
            if(delta[7] > 0){
                Delta7.setTextColor(Color.parseColor("#FF0000"));
                Delta7.setText("+" + String.valueOf(delta[7]));
            }
            else if (delta[7] == 0){
                Delta7.setTextColor(Color.parseColor("#0B6623"));
                Delta7.setText(String.valueOf(delta[7]));
            }
        }

        if(lapTimes[8] != null){
            miejsca++;
            Laptime8.setText(lapTimes[8]);
            Number8.setText("8.");
            if(delta[8] > 0){
                Delta8.setTextColor(Color.parseColor("#FF0000"));
                Delta8.setText("+" + String.valueOf(delta[8]));

            }
            else if (delta[8] == 0){
                miejsca++;
                Delta8.setTextColor(Color.parseColor("#0B6623"));
                Delta8.setText(String.valueOf(delta[8]));
            }
        }

        if(lapTimes[9] != null){
            miejsca++;
            Laptime9.setText(lapTimes[9]);
            Number9.setText("9.");
            if(delta[9] > 0){
                Delta9.setTextColor(Color.parseColor("#FF0000"));
                Delta9.setText(String.valueOf(delta[9]));
            }
            else if (delta[9] == 0){
                miejsca++;
                Delta9.setTextColor(Color.parseColor("#0B6623"));
                Delta9.setText(String.valueOf(delta[9]));
            }
        }

        /*Laptime10.setText("-");
        Delta10.setText("-");
        Number10.setText("10.");

        TextView Laptime10 = (TextView) v.findViewById(R.id.textView29);
        TextView Delta10 = (TextView) v.findViewById(R.id.textView30);
        if(delta[9]>0) Delta10.setTextColor(Color.parseColor("#FF0000"));
        else if (delta[9]<0) Delta10.setTextColor(Color.parseColor("#0B6623"));*/

        int ileOkrazen = 0;
        while(lapTimes[ileOkrazen] != null){
            ileOkrazen++;
        }
        ileOkrazen = ileOkrazen - 1;
        Log.d("Ile:", "" + ileOkrazen);

        GraphView graph = (GraphView) v.findViewById(R.id.Graf3);
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer.setVerticalAxisTitleColor(Color.WHITE);  // Dodano tę linijkę
        gridLabelRenderer.setHorizontalAxisTitleColor(Color.WHITE);  // Dodano tę linijkę
        gridLabelRenderer.setHorizontalAxisTitle("Lap number");
        gridLabelRenderer.setVerticalAxisTitle("Time [s]");
        gridLabelRenderer.setLabelHorizontalHeight(20);
        gridLabelRenderer.setLabelVerticalWidth(5); // wartość w pikselach
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer.setNumHorizontalLabels(ileOkrazen); // Ustawienie ilości pionowych linii na osi Y

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph.setTitle("Lap times over time");
        graph.getGridLabelRenderer().setPadding(50);  // Dostosuj wartość odsunięcia wertykalnego
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setTextSize(24);
        gridLabelRenderer.setGridColor(Color.WHITE);
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(ileOkrazen);
        graph.getViewport().setMaxY(1000);

        PointsGraphSeries<DataPoint> pointSeries = new PointsGraphSeries<>();

        for(int i = 1; i < 10; i++){
            x = i;
            y = (times[i] / 100) % 60;
            Log.d("X" + i, "" + x);
            Log.d("Y" + i, "Czas: " + y);
            series.appendData(new DataPoint(x, y), true, 10);
            pointSeries.appendData(new DataPoint(x, y), true, 10);

        }
        graph.addSeries(series);
        series.setColor(Color.rgb(0, 255, 0) );

        pointSeries.setColor(Color.RED); // Ustaw kolor punktów
        pointSeries.setSize(8); // Ustaw rozmiar punktów w pikselach

        double minTime = series.getLowestValueY();
        graph.getViewport().setMinY(minTime);

// Dodaj serię danych punktowych na wykres
        graph.addSeries(pointSeries);
        return v;
    }
}