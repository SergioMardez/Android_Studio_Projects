package com.example.sergio.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANNEL_HIGH_ID = "1";
    private final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";

    public static final String CHANNEL_LOW_ID = "2";
    private final String CHANNEL_LOW_NAME = "LOW CHANNEL";
    private final int SUMMARY_GROUP_ID = 1001; //Puede ser el identificador que se quiere. Cualquier numero
    private final String SUMMARY_GROUP_NAME = "GROUPING_NOTIFICATION";

    public NotificationHandler(Context base) {
        super(base);
        createChannels();
    }

    public NotificationManager getManager(){
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    private void createChannels(){
        if(Build.VERSION.SDK_INT >= 26){
            //Creating High channel
            NotificationChannel highChannel = new NotificationChannel(CHANNEL_HIGH_ID,
                    CHANNEL_HIGH_NAME, NotificationManager.IMPORTANCE_HIGH);

            //Extra configuration en el HIGHCHANNEL
            highChannel.enableLights(true);//Para la luz led
            highChannel.setLightColor(Color.YELLOW);
            highChannel.setShowBadge(true);//Punto en el icono de la aplicacion
            highChannel.enableVibration(true);
            //highChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});//patron de vibracion
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            //Cambiar el sonido
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            highChannel.setSound(defaultSoundUri, null);

            NotificationChannel lowChannel = new NotificationChannel(CHANNEL_LOW_ID,
                    CHANNEL_LOW_NAME, NotificationManager.IMPORTANCE_LOW);

            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManager().createNotificationChannel(highChannel);
            getManager().createNotificationChannel(lowChannel);
        }
    }

    public Notification.Builder createNotification (String title, String message, boolean isHighImportance){
        if (Build.VERSION.SDK_INT >= 26) {
            if (isHighImportance) {
                return this.createNotificationWithChannel(title, message, CHANNEL_HIGH_ID);
            }
            return this.createNotificationWithChannel(title, message, CHANNEL_LOW_ID);
        }

        return this.createNotificationWithoutChannel(title, message);
    }

    private Notification.Builder createNotificationWithChannel (String title, String message, String channelId){
        //Comparando con los codigos de version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Intent para ir al activity al dar en la notificacion
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Para que al dar a retroceso se cierre la app
            //Es un intent preparado pero solo cuando el usuario le da a la notificacion. Puede que se lance o no
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            //Para añadir acciones a la notificiacion. Botones del whatsapp para responder o cancelar.
            Notification.Action action = new Notification.Action.Builder(Icon.createWithResource(this, android.R.drawable.ic_menu_send),
                    "See Details", pIntent).build();

            return new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(title).setContentText(message)
                    .setActions(action) //Se le añade a la notificacion la accion. Puede dar problema a la hora de agrupar (Se tendria que quitar)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setAutoCancel(true);
        }

        return null;
    }

    //Sin canal, para versiones anteriorios a Oreo. api 26 o menor
    private Notification.Builder createNotificationWithoutChannel (String title, String message){
        //Aqui solo el pending intent con la notificacion
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        return new Notification.Builder(getApplicationContext())
                    .setContentTitle(title).setContentText(message)
                    .setContentIntent(pIntent)
                    .setColor(getColor(R.color.colorPrimary))
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
    }

    public void publishNotificationSummaryGroup(boolean isHighImportant) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = (isHighImportant) ? CHANNEL_HIGH_ID : CHANNEL_LOW_ID;
            Notification summaryNotification = new Notification.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(android.R.drawable.stat_notify_call_mute)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setGroupSummary(true)
                    .build();

            //Este summary_group_id es que agrupa las notificaciones, al petenecer todas al mismo grupo
            getManager().notify(SUMMARY_GROUP_ID, summaryNotification);
        }
    }
}
