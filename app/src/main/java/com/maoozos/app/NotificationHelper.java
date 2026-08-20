package com.maoozos.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public final class NotificationHelper {
    public static final String CHANNEL_ID = "maoozos_reminders_v4";
    private NotificationHelper() {}
    public static void ensureChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            if (nm == null) return;
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID, context.getString(com.maoozos.app.R.string.notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            ch.setDescription(context.getString(com.maoozos.app.R.string.notification_channel_description));
            ch.enableVibration(true);
            nm.createNotificationChannel(ch);
        }
    }
}
