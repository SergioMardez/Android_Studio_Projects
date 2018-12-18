package com.example.sergio.notificaciones;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Utilizando butter knife
    @BindView(R.id.editTextTitle)
    EditText editTextTitle;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.switchImportance)
    Switch switchImportance;

    @BindString(R.string.switch_notification_on) String switchTextOn;
    @BindString(R.string.switch_notification_off) String switchTextOff;

    private NotificationHandler notificationHandler;
    private boolean isHighImportance = false;

    //Esto tendria que estar en el servidor para hacer un identificador de notificaciones y que se puedan hacer multiples
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //Despues de setContentView
        notificationHandler = new NotificationHandler(this);
    }

    @OnClick(R.id.buttonSend)
    public void click(){
        sendNotfication();
    }

    @OnCheckedChanged(R.id.switchImportance)
    public void change(CompoundButton buttonView, boolean isChecked) {
        isHighImportance = isChecked;
        switchImportance.setText((isChecked) ? switchTextOn : switchTextOff); //Operador ternario (condicion) ? true : false
    }

    private void sendNotfication() {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)){
            Notification.Builder nb = notificationHandler.createNotification(title, message, isHighImportance);
            notificationHandler.getManager().notify(++counter, nb.build()); //Aqui se lanza la notificacion

            notificationHandler.publishNotificationSummaryGroup(isHighImportance); //Para poder acumularlas en el mismo grupo
        }
    }
}
