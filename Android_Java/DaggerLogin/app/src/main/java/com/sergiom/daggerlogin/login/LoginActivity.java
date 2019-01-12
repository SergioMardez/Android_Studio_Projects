package com.sergiom.daggerlogin.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sergiom.daggerlogin.R;
import com.sergiom.daggerlogin.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    //La activity será la vista. Es la que conoce los elementos de la UI. Solo muestra los elementos,
    //no accede a la informacion.
    //Hace falta crear una serie de clases para que funcione correctamente Dagger II
    //Se añade -> implements LoginActivityMVP.View, despues de crear la LoginActivityMVP interface.


    //Se inyecta el presenter en la vista, para obtener una refencia al mismo
    @Inject
    LoginActivityMVP.Presenter presenter;

    EditText firstName, lastName;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para indicar que esta actividad se debe inyectar en dagger. Casteado a App:
        ((App) getApplication()).getComponent().inject(this);

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Se hace en el onResume por si la vista se tiene que destruir y volver a crear.
        //Asi siempre estara asociado.
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return this.firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return this.lastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error, el usuario no está disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error, nombre o apellidos está vacío", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }
}
