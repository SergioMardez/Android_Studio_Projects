package com.sergiom.daggerlogin.login;

import android.support.annotation.Nullable;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    //Hace falta una referencia a la vista y al modelo para comunicarse con ell
    //@Nullable para ver si una variable es nula o no (si se ha iniciado correctamente)
    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    //Dagger crea el modelo necesario para que el presentador pueda ser creado
    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if(view != null){
            if(view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName().trim(), view.getLastName().trim());
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();

        if(user == null) {
            if (view != null) {
                view.showUserNotAvailable();
            }
        } else {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
