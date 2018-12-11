package com.example.sergio.seccion_09_maps.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sergio.seccion_09_maps.R;
import com.example.sergio.seccion_09_maps.fragments.MapFragment;
import com.example.sergio.seccion_09_maps.fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para controlar la rotacion y que no se reinicie el fragment. Se usa el bundle,
        //si viene vacio es que es la primera vez que se llama.
        if(savedInstanceState == null) {
            //Llamar al fragment por defecto
            currentFragment = new WelcomeFragment();
            changeFragment(currentFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Menu de opciones
        switch (item.getItemId()) {
            case R.id.menu_welcome:
                currentFragment = new WelcomeFragment();
                break;
            case R.id.menu_map:
                currentFragment = new MapFragment();
                break;
        }

        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }

    //Cambiar el fragment
    private void changeFragment (Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}
