package edu.sfsu.cs.orange.ocr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,R.string.app_name,Toast.LENGTH_SHORT).show();
        NotificationManager nm = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder mBuilder= new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("PAPA알리미")
                .setContentText("알람ㄱㄱ")
                .setAutoCancel(true)
                .setTicker("PAPA알리미-소식왔어요!")
                .setDefaults(Notification.DEFAULT_SOUND)
                ;
        nm.notify(0,mBuilder.build());
    }
}
