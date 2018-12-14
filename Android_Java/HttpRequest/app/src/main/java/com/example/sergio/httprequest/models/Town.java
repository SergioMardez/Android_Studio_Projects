package com.example.sergio.httprequest.models;

import com.example.sergio.httprequest.models.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Town {

    private int id;
    //serializedName es para decir el nombre con el que viene en el json, si quieres poner otro distinto en la clase
    @SerializedName("ciudades")
    private List<City> cities;

    public Town() {}

    public Town(int id, List<City> cities) {
        this.id = id;
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCity() {
        return cities;
    }

    //Para parsear el contenido del json automaticamente con gson. Deserializacion.
    public static City parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        City city = gson.fromJson(response, City.class);
        return city;
    }
}
