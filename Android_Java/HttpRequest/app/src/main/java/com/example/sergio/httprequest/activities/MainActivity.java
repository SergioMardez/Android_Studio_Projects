package com.example.sergio.httprequest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sergio.httprequest.API.API;
import com.example.sergio.httprequest.R;
import com.example.sergio.httprequest.API.APIServices.WeatherService;
import com.example.sergio.httprequest.models.City;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Primera forma, parseando a mano para ver como funciona
        /*String json =
                "{" +
                    "id: 0," +
                    "ciudades: [" +
                        "{" +
                            "id: 1," +
                            "name: 'London'" +
                        "}," +
                        "{" +
                            "id: 2," +
                            "name: 'Salamanca'" +
                        "}]" +
                "}";

        City city = null;*/

        /*/Parsear el json de forma manual. Cuando solo tenia dos parametros*
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

        Toast.makeText(this, city.getId() + " -- " + city.getName(), Toast.LENGTH_LONG).show();*/

        //Con la libreria Gson. Se le pasa el json y la clase a la que se quiere añadir. Se puede ver con debug.
        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //Town town = gson.fromJson(json, Town.class);


        //Utilizando retrofit desde la clase API
        WeatherService service = API.getApi().create(WeatherService.class);

        Call<City> cityCall = service.getCityCelsius("Seville,ES", API.APPKEY, "metric");



        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
