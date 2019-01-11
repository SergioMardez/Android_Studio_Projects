package com.sergiom.daggerlogin.login;

public class MemoryRepository implements LoginRepository {

    /*
    * Esta es la clase para guardar y recuperar el usuario. Implementa la interfaz
    * LoginRepository que tiene los metodos para realizar las acciones necesarias.
    * */

    private User user;

    @Override
    public void saveUser(User user) {
        if(user == null) {
            //Para que el usuario nunca sea nulo y se sepa si es nulo al ser por defecto
            user = getUser();
        }
        this.user = user;
    }

    @Override
    public User getUser() {
        //Si es nulo se puede devolver un usuario creado para este caso
        if(user == null) {
            user = new User("Antonio", "Banderas");
            user.setId(0);
            return user;
        } else {
            return user;
        }
    }
}
