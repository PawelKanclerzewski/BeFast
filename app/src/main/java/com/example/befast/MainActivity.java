package com.example.befast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void new_session(View view){
    Intent Intent = new Intent(this, Settings.class);
    startActivity(Intent);

    }
    public void load_data(View view){
        Intent Intent = new Intent(this, Load.class);
        startActivity(Intent);

    }


}