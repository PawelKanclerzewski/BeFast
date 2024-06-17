package com.example.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Car_settings extends AppCompatActivity {

    public String toefl = "0.0";
    public String toefr = "0.0";
    public String toerl = "0.0";
    public String toerr = "0.0";

    public String camfl = "0.0";
    public String camfr = "0.0";
    public String camrl = "0.0";
    public String camrr = "0.0";

    public String presfl = "0.0";
    public String presfr = "0.0";
    public String presrl = "0.0";
    public String presrr = "0.0";

    private EditText valuefl;
    private EditText valuefr;
    private EditText valuerl;
    private EditText valuerr;

    public String previousSet = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_settings);

        valuefl = (EditText) findViewById(R.id.value_FL);
        valuefr = (EditText) findViewById(R.id.value_FR);
        valuerl = (EditText) findViewById(R.id.value_RL);
        valuerr = (EditText) findViewById(R.id.value_RR);
    }

    public void Ready(View view){
        Intent Intent = new Intent(this, ready.class);



        Intent.putExtra("toefl", toefl);
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

        Log.d("pressfr", String.valueOf(presfr));
        Log.d("pressfl", String.valueOf(presfl));
        Log.d("pressrr", String.valueOf(presrr));
        Log.d("pressrl", String.valueOf(presrl));

        startActivity(Intent);

    }

    public void Toe(View v){
        saveSet();
        editSet("toe");
        TextView tv = (TextView)findViewById(R.id.text_setting);
        tv.setText("Enter the car's Toe Angle [ deg ]");
        previousSet = "toe";
    }

    public void Camber(View v){
        saveSet();
        editSet("camber");
        TextView tv = (TextView)findViewById(R.id.text_setting);
        tv.setText("Enter the car's Camber Angle [ deg ]");
        previousSet = "camber";
    }

    public void Pressure(View v){
        saveSet();
        editSet("pressure");
        TextView tv = (TextView)findViewById(R.id.text_setting);
        tv.setText("Enter the car's Tyre pressure [ psi / bar ]");
        previousSet = "pressure";
    }

    public void saveSet(){
        switch(previousSet){
            case "none":
                valuefl.setVisibility(View.VISIBLE);
                valuefr.setVisibility(View.VISIBLE);
                valuerl.setVisibility(View.VISIBLE);
                valuerr.setVisibility(View.VISIBLE);
                break;

            case "toe":
                toefl = String.valueOf(valuefl.getText());
                toefr = String.valueOf(valuefr.getText());
                toerl = String.valueOf(valuerl.getText());
                toerr = String.valueOf(valuerr.getText());
                break;

            case "camber":
                camfl = String.valueOf(valuefl.getText());
                camfr = String.valueOf(valuefr.getText());
                camrl = String.valueOf(valuerl.getText());
                camrr = String.valueOf(valuerr.getText());
                break;

            case "pressure":
                presfl = String.valueOf(valuefl.getText());
                presfr = String.valueOf(valuefr.getText());
                presrl = String.valueOf(valuerl.getText());
                presrr = String.valueOf(valuerr.getText());
                break;
        }
    }

    public void editSet(String setting){
        switch(setting){
            case "toe":
                valuefl.setText(toefl);
                valuefr.setText(toefr);
                valuerl.setText(toerl);
                valuerr.setText(toerr);
                break;

            case "camber":
                valuefl.setText(camfl);
                valuefr.setText(camfr);
                valuerl.setText(camrl);
                valuerr.setText(camrr);
                break;

            case "pressure":
                valuefl.setText(presfl);
                valuefr.setText(presfr);
                valuerl.setText(presrl);
                valuerr.setText(presrr);
                break;
        }
    }


}