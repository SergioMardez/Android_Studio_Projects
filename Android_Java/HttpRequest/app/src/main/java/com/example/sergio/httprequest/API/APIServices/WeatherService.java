package com.example.sergio.httprequest.API.APIServices;

import com.example.sergio.httprequest.models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    //http://api.openweathermap.org/data/2.5/weather?q=seville,es&APPID=3c8d4d95afdc812f921ac33f8bb13878
    //despues de '?' siempre viene algun parametro, query ----> q=seville
    //Para meter la clave, que viene al final 'appid' ----> 3c8d4d95afdc812f921ac33f8bb13878

    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("APPID") String key);

    @GET("weather")
    Call<City> getCityCelsius(@Query("q") String city, @Query("APPID") String key, @Query("units") String value);
}
