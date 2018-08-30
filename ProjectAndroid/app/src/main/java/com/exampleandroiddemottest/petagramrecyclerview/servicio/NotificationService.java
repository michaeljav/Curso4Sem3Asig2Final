package com.exampleandroiddemottest.petagramrecyclerview.servicio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.exampleandroiddemottest.petagramrecyclerview.MainActivity;
import com.exampleandroiddemottest.petagramrecyclerview.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class NotificationService extends FirebaseMessagingService {
    public static final String TAGG ="FIREBASE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        if (remoteMessage.getNotification() != null) {
            Log.d(TAGG, "Notification Title: " +
                    remoteMessage.getNotification().getTitle());

            Log.d(TAGG , "Notification Message: " +
                    remoteMessage.getNotification().getBody());
        }


        /*Notificacion*/
        //PRIMERA PRUEBA

        //se dirigira algun lado la notifiacion
        Intent i = new Intent(this,MainActivity.class);
        //tipo de datos que recibira la notificacion para dirigirse a nuestro intent.
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
        //SONIDO POR DEFECTO DE NOTIFIACION
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //trabajando la notificacion
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this,"IDP")
                // .setSmallIcon(R.drawable.bell30)
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentTitle("Vino")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                ;
        //notifiacion manager sera encargado de mandar la notificacion
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification =notificationCompat.build();
        notificationManager.notify(0,notification);
        /*Notificacion END */

    }


    @Override
    public void onNewToken(String s) {
        // super.onNewToken(s);
        //Log.d("", "Refreshed token: " + token);
        Log.d("tokenFirebase",s);
    }
}
