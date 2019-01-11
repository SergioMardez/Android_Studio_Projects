package com.sergiom.daggerlogin.login;

public interface LoginActivityMVP {

    //Se declaran las interfaces para el modelo, vista y presentador. Se crean sus clases despues

    interface Model {
        void createUser(String firstName, String lastName);

        User getUser();
    }

    interface View {
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();

        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Presenter {
        void setView(LoginActivityMVP.View view);

        void loginButtonClicked(); //Para poder guardar el usuario cuando se pulsa el boton

        void getCurrentUser(); //Para mostrar el usuario creado
    }

}
