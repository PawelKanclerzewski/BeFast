package com.example.befast;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PDF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PDF extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Double p1, p2, p3, p4, t1, t2, t3, t4, presfr, presrl, presrr, presfl;

    public PDF() {
        // Required empty public constructor
    }

    public interface StringArrayListener {
        void onStringArrayReceived(String[] strings);
    }
    private StringArrayListener stringArrayListener;

    public void setStringArrayListener(StringArrayListener listener) {
        stringArrayListener = listener;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PDF.
     */
    // TODO: Rename and change types and number of parameters
    public static PDF newInstance(String param1, String param2) {
        PDF fragment = new PDF();
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
        View v = inflater.inflate(R.layout.fragment_pdf, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            p1 = bundle.getDouble("p1");
            p2 = bundle.getDouble("p2");
            p3 = bundle.getDouble("p3");
            p4 = bundle.getDouble("p4");
            t1 = bundle.getDouble("t1");
            t2 = bundle.getDouble("t2");
            t3 = bundle.getDouble("t3");
            t4 = bundle.getDouble("t4");
            presfl = bundle.getDouble("presfl");
            presfr = bundle.getDouble("presfr");
            presrl = bundle.getDouble("presrl");
            presrr = bundle.getDouble("presrr");

        }

        GraphView graph = (GraphView) v.findViewById(R.id.Graf1);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series16 = new LineGraphSeries<>();


        graph.setTitle("Tyre pressure compared to the ideal value");
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setTextSize(24);
        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer.setVerticalAxisTitleColor(Color.WHITE);
        gridLabelRenderer.setHorizontalAxisTitleColor(Color.WHITE);
        gridLabelRenderer.setHorizontalAxisTitle("Race stage [%]");
        gridLabelRenderer.setVerticalAxisTitle("Pressure [psi]");
        gridLabelRenderer.setGridColor(Color.WHITE);
        gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);
        gridLabelRenderer.setNumHorizontalLabels(4); // Ustawienie ilości pionowych linii na osi Y

        series16.appendData(new DataPoint(0, 30), true, 2);
        series16.appendData(new DataPoint(100, 30), true, 2);
        graph.addSeries(series16);

        series1.appendData(new DataPoint(0, presfr), true, 2);
        series1.appendData(new DataPoint(100, p1), true, 2);
        series2.appendData(new DataPoint(0, presfl), true, 2);
        series2.appendData(new DataPoint(100, p2), true, 2);
        series3.appendData(new DataPoint(0, presrr), true, 2);
        series3.appendData(new DataPoint(100, p3), true, 2);
        series4.appendData(new DataPoint(0, presrl), true, 2);
        series4.appendData(new DataPoint(100, p4), true, 2);
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);
        graph.addSeries(series4);
        series1.setColor(Color.rgb(50, 17, 0) );
        series2.setColor(Color.rgb(255,255,0));
        series3.setColor(Color.rgb(255,82,0));
        series4.setColor(Color.rgb(150,0,75));
        series16.setColor(Color.rgb(0,255,75));


        GraphView graph2 = (GraphView) v.findViewById(R.id.Graf2);
        LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series6 = new LineGraphSeries<>();

        graph2.setTitle("Tyre temperature compared to the ideal value");
        graph2.setTitleColor(Color.WHITE);
        graph2.getGridLabelRenderer().setTextSize(24);
        GridLabelRenderer gridLabelRenderer2 = graph2.getGridLabelRenderer();
        gridLabelRenderer2.setNumHorizontalLabels(4); // Ustawienie ilości pionowych linii na osi Y
        gridLabelRenderer2.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setVerticalAxisTitleColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalAxisTitleColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalAxisTitle("Car side");
        gridLabelRenderer2.setVerticalAxisTitle("Temperature [℃]");
        gridLabelRenderer2.setGridColor(Color.WHITE);
        gridLabelRenderer2.setVerticalLabelsColor(Color.WHITE);
        gridLabelRenderer2.setHorizontalLabelsColor(Color.WHITE);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(3);
        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setMinY(0);
        graph2.getViewport().setMaxY(80);

// Create a custom LabelFormatter for the X-axis
        gridLabelRenderer2.setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    switch ((int) value) {
                        case 0:
                            return "FR";
                        case 1:
                            return "FL";
                        case 2:
                            return "RR";
                        case 3:
                            return "RL";
                        default:
                            return super.formatLabel(value, isValueX);
                    }
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        series5.appendData(new DataPoint(0, t1), true, 4);
        series5.appendData(new DataPoint(1, t2), true, 4);
        series5.appendData(new DataPoint(2, t3), true, 4);
        series5.appendData(new DataPoint(3, t4), true, 4);

        series6.appendData(new DataPoint(0, 60), true, 2);
        series6.appendData(new DataPoint(3, 60), true, 2);
        graph2.addSeries(series5);
        graph2.addSeries(series6);

        series5.setColor(Color.rgb(200, 100, 50));
        series6.setColor(Color.rgb(50, 255, 50));


        return v;
    }

}