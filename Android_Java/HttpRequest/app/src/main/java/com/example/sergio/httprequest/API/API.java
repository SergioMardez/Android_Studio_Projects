package com.example.sergio.httprequest.API;

import com.example.sergio.httprequest.API.Deserializers.MyDeserializer;
import com.example.sergio.httprequest.models.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;
    public static final String APPKEY = "3c8d4d95afdc812f921ac33f8bb13878";

    //Para crear el retrofit si no se ha creado o devolverlo en caso de que exista una instancia
    public static Retrofit getApi(){
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class, new MyDeserializer()); //Registrar el deserializador creado en la clase MyDeserializer

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }

}
