package com.example.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ready extends AppCompatActivity {
    Double toefl;
    Double toefr;
    Double toerl;
    Double toerr;

    Double camfl;
    Double camfr;
    Double camrl;
    Double camrr;

    Double presfl;
    Double presfr;
    Double presrl;
    Double presrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        toefl = Double.parseDouble(getIntent().getStringExtra("toefl"));
        toefr = Double.parseDouble(getIntent().getStringExtra("toefr"));
        toerl = Double.parseDouble(getIntent().getStringExtra("toerl"));
        toerr = Double.parseDouble(getIntent().getStringExtra("toerr"));

        camfl = Double.parseDouble(getIntent().getStringExtra("camfl"));
        camfr = Double.parseDouble(getIntent().getStringExtra("camfr"));
        camrl = Double.parseDouble(getIntent().getStringExtra("camrl"));
        camrr = Double.parseDouble(getIntent().getStringExtra("camrr"));

        presfl = Double.parseDouble(getIntent().getStringExtra("presfl"));
        presfr = Double.parseDouble(getIntent().getStringExtra("presfr"));
        presrl = Double.parseDouble(getIntent().getStringExtra("presrl"));
        presrr = Double.parseDouble(getIntent().getStringExtra("presrr"));
    }

    public void Timer(View view){
        Intent Intent = new Intent(this, Timer.class);

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
}