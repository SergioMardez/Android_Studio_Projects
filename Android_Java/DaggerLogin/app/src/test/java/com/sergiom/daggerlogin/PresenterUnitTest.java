package com.sergiom.daggerlogin;

import com.sergiom.daggerlogin.login.LoginActivityMVP;
import com.sergiom.daggerlogin.login.LoginActivityPresenter;
import com.sergiom.daggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    //Esto sera simulado por Mockito para poder testear el Presenter (necesita ambos)
    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    //Se tiene que configuara el metodo con el marcador @Before
    //Sirve para decirle a JUnit que cada vez que se ejecute JUnit se tiene que ejercutar eso.
    //Proceso de inicializacion o limpieza de variables
    @Before
    public void initialization() {
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        user = new User("Manolo", "Escobas");

        //Indicar a mockito como hay que comportarse al acceder a getUser() u otros metodos que no son del Presenter
        //when(mockedModel.getUser()).thenReturn(user);

        //when(mockedView.getFirstName()).thenReturn("Antonio");
        //when(mockedView.getLastName()).thenReturn("Banderas");

        //El presenter necesita el modelo y la vista
        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistsInteractionWithView() {
        //Metodo a comprobar
        presenter.getCurrentUser();

        //Despues de pedir un usuario, seria un test positivo si no se hiciera nada en la vista.
        //Ni mostrar datos nuevos, ni Toast.
        //verifyZeroInteractions(mockedView);

        //Cuando no hay usuario, la vista se llama solo una vez. (Mostrar el Toast)
        verify(mockedView,times(1)).showUserNotAvailable();
        //Si el usuario es creado antes, no se llama nunca a showUserNotAvailable()
        //verify(mockedView,never()).showUserNotAvailable();
    }

    @Test
    public void loadUserFromRepoWhenValidUserIsPresent() {
        when(mockedModel.getUser()).thenReturn(user);

        //Metodo a comprobar
        presenter.getCurrentUser();

        //Comprobamos la interactuacion con el modelo
        verify(mockedModel, times(1)).getUser();

        //Comprobamos la interactuacion con la vista
        verify(mockedView,times(1)).setFirstName("Manolo");
        verify(mockedView,times(1)).setLastName("Escobas");
        verify(mockedView, never()).showUserNotAvailable();
        //Fallaria si los datos no fueran correctos:
        //verify(mockedView,times(1)).setFirstName("Antonio");
    }

    @Test
    public void showErrorMessageWhenUserIsNull() {
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockedModel, times(1)).getUser();

        verify(mockedView,never()).setFirstName("Manolo");
        verify(mockedView,never()).setLastName("Escobas");
        verify(mockedView,times(1)).showUserNotAvailable();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty() {
        //Primera prueba poniendo el primer campo firstName vacio
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView, times(1)).getFirstName();
        verify(mockedView, never()).getLastName();
        verify(mockedView, times(1)).showInputError();

        //Segunda prueba, poniendo un valor en firstName y dejando lastName vacio
        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        //El metodo se llama dos veces, una en la prueba anterior y una en la actual. Al no haber limpiado la prueba (pasar por el Before)
        verify(mockedView, times(2)).getFirstName();
        verify(mockedView, times(1)).getLastName(); //Este metodo es la primera vez que se llama, al no pasar el test anteriormente
        verify(mockedView, times(2)).showInputError(); //Se llamo antes y ahora otra ves
    }

    @Test
    public void saveValidUser() {
        when(mockedView.getFirstName()).thenReturn("Sergio");
        when(mockedView.getLastName()).thenReturn("Martin");

        presenter.loginButtonClicked();

        //Llamadas dobles, una en el if y otra cuando se crea el usuario
        verify(mockedView, times(2)).getFirstName();
        verify(mockedView,times(2)).getLastName();

        //Miramos que el modelo persista en el repositorio
        verify(mockedModel,times(1)).createUser("Sergio", "Martin");

        //Comprobar si se muestra el mensaje de usuario guardado
        verify(mockedView, times(1)).showUserSaved();
    }

}
