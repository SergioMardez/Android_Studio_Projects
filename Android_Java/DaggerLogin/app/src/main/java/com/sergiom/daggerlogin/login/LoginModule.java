package com.sergiom.daggerlogin.login;

import dagger.Module;
import dagger.Provides;


//Es el punto de conexion con dagger
@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository) {
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository providesLoginRepository() {
        return new MemoryRepository(); //Cambiar aqui si se quiere una base de datos, cloud o lo que se necesita para guardar
    }
}
