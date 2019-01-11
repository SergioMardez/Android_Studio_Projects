package com.sergiom.daggerlogin.root;

import android.app.Application;

import com.sergiom.daggerlogin.login.LoginModule;

public class App extends Application {
    //Aqui se declaran los modulos que hagan falta
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        //Se inicializan los componentes. Todos los que sean necesarios. Hay que añadir en el
        //Manifest -> android:name=".App". Para indicar que nosotros iniciamos la app a traves de este fichero
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    //Al ser privada la component, se crea este metodo para poder recuperarla
    public ApplicationComponent getComponent() {
        return component;
    }
}
