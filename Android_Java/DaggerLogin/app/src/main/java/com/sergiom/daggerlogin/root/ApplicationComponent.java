package com.sergiom.daggerlogin.root;


import com.sergiom.daggerlogin.http.TwitchModule;
import com.sergiom.daggerlogin.login.LoginActivity;
import com.sergiom.daggerlogin.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/*
* Sirve para referencias entre activites, fragments o services gracias a dagger.
* Cada una de las componentes dependen de un modulo, applicationModule, se tiene
* que marcar con @Component. Y se añade los modulos que lo componen.
*
* Para tener solo un modelo, una vista y un controlador, se le puede poner
* @Singlenton por encima de @Component.
*
* */

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class, TwitchModule.class})
public interface ApplicationComponent {

    //Para inyectar la activity. Al ser una interfaz, solo hay que tener el metodo, no la implementacion
    void inject(LoginActivity target);
}
