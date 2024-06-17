package com.example.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageView imageview;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.tracks, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.my_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        imageview = findViewById(R. id. image_trackdesc);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();

        if (text.equals("Tor Poznań")) {
            imageview.setImageResource(R. drawable.tor_poznan);
            i=1;
        }
        if (text.equals("Autodrom Jastrząb")) {
            imageview.setImageResource(R. drawable.autodrom_jastrzab);
            i=1;
        }
        if (text.equals("Tor Koszalin")) {
            imageview.setImageResource(R. drawable.tor_koszalin);
            i=1;
        }
        if (text.equals("Autodrom Słomczyn")) {
            imageview.setImageResource(R. drawable.tor_slomczyn);
            i=1;
        }

        if (text.equals("Tor Pszczółki")) {
            imageview.setImageResource(R. drawable.tor_pszczolki);
            i=1;
        }

        if (text.equals("Tor Bednary")) {
            imageview.setImageResource(R. drawable.tor_bednary);
            i=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void car_settings(View view){
        if(i==0);
        Intent Intent = new Intent(this, Car_settings.class);
        startActivity(Intent);

    }

    public void setstart(View view){

        Intent Intent = new Intent(this, SetStart.class);
        startActivity(Intent);

    }
}