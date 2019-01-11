package com.sergiom.daggerlogin.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/*
 * Para el control de dependencias de Dagger II. Se tiene que poner @Module.
 * Hay un modulo para cada funcionalidad de la App.
 *
 * @Provides, para los elementos que tienen un valor return.
 *
 * @Singleton, para instancias que tienen que ser creadas solo una vez y compartidas
 * por toda la App. Como el caso de application.
 *
 * */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule (Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

}
