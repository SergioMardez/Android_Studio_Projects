package com.example.sergio.httprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{\n" +
                            "cod: 1,\n" +
                            "message: 'London'" +
                      "}";

        City city = null;

        //Parsear el json
        try {
            //Recuperar los datos del json
            JSONObject mJson = new JSONObject(json);
            int id = mJson.getInt("id");
            String name = mJson.getString("name");

            //Crear la ciudad
            city = new City(id, name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, city.getId() + " -- " + city.getName(), Toast.LENGTH_LONG).show();
    }
}
