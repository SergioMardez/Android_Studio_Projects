package com.example.sergio.httprequest.models;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("temp")
    private float temperature;
    private float pressure;
    private float humidity;
    @SerializedName("temp_min")
    private float tempMin;
    @SerializedName("temp_max")
    private float tempMax;

    public Temperature() {}

    public Temperature(float temperature, float pressure, float humidity, float tempMin, float tempMax) {
        this.setTemperature(temperature);
        this.setPressure(pressure);
        this.setHumidity(humidity);
        this.setTempMin(tempMin);
        this.setTempMax(tempMax);
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

}
