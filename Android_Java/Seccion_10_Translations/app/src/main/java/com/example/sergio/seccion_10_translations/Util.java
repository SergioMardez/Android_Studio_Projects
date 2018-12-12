package com.example.sergio.seccion_10_translations;

import android.content.Context;

public class Util {

    //Para pasar string con una clase se necesita el context
    public static String getWelcome(Context context){
        return context.getString(R.string.welcome);
    }
}
