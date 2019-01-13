package com.sergiom.daggerlogin.http;

import com.sergiom.daggerlogin.http.twitch.Twitch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TwitchAPI {

    //Interfaz grafica basica para interactuar con twitch
    @GET("games/top")
    Call <Twitch> getTopGames(@Header("Client-Id") String clientId);

}
