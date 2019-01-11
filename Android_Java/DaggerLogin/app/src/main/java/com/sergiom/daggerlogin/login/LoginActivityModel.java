package com.sergiom.daggerlogin.login;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        //Comprobar si existe, si está bien escrito, etc. Tiene que ser correcto para ser guardado.
        repository.saveUser(new User(firstName, lastName));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }

}
