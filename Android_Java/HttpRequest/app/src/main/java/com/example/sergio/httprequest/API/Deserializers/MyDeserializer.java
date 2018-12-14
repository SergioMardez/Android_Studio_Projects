package com.example.sergio.httprequest.API.Deserializers;

import com.example.sergio.httprequest.models.City;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        /*
            Para hacer esta parte hay que cambiar la clase city, quitando la temperatura y añadiendo y string de country
         */
        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();

        //Para recoger un obejeto de un objeto, se va entrando en el objeto y cogiendo lo que se quiere
        String country = json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();

        City city = new City(id, name, country);

        return city;
    }
}
