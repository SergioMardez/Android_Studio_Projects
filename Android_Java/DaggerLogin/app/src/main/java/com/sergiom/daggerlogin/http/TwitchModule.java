package com.sergiom.daggerlogin.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TwitchModule {

    public final String BASE_URL = "https://api.twitch.tv/helix/";


    //Para opciones de registro
    @Provides
    public OkHttpClient provideHttpClient() {
        //Se obtiene el cuerpo de la llamada. Registra las lineas de solicitud y respuesta con sus encabezados y respuestas.
        //Se define el cliente de conexion cuando se llame a provideHttpClient.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create()) //Conversor de JSon recibidos a clases de java. (libreria de Gson)
                .build();
    }

    @Provides
    public TwitchAPI provideTwitchService() {
        //Se crean las llamadas establecidas en la interfaz TwitchAPI
        return provideRetrofit(BASE_URL, provideHttpClient()).create(TwitchAPI.class);
    }

}
