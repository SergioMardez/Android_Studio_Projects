package com.example.sergio.seccion_10_translations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Desde un activity se puede coger el texto asi
        Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
    }
}
