package com.sergiom.daggerlogin.login;

public interface LoginRepository {

    //Asi no hay problema si se utiliza BBDD, ficheros o la nube para guardar los datos

    void saveUser(User user);
    User getUser();
}
