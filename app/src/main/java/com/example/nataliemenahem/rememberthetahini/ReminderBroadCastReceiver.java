package com.example.nataliemenahem.rememberthetahini;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Created by NatalieMenahem on 01/01/2016.
 */
public class ReminderBroadCastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        //do something QUICK
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Get taskId & message
        long taskId = intent.getLongExtra("taskId", 0);
        String notificationText = intent.getStringExtra("taskMessage");

        Intent myIntent = new Intent(context, NewTaskActivity.class);
        myIntent.putExtra(AppConst.ExtrasTaskName, notificationText);
        myIntent.putExtra(AppConst.ExtrasTaskId, taskId);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

        Notification builder = new Notification.Builder(context)
                .setContentTitle("Remember the Tahini reminder")
                .setContentText(notificationText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();

        builder.defaults |= Notification.DEFAULT_SOUND;
        builder.defaults |= Notification.DEFAULT_VIBRATE;
        builder.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(null, (int) taskId, builder); //0 is id

    }
}
