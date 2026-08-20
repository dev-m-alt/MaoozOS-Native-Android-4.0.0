package com.maoozos.app;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class ReminderReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        NotificationHelper.ensureChannel(context);
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        int id = Math.abs((intent.getStringExtra("id") == null ? String.valueOf(System.currentTimeMillis()) : intent.getStringExtra("id")).hashCode());
        Intent open = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(context, id, open, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        android.app.Notification.Builder b = Build.VERSION.SDK_INT >= 26 ? new android.app.Notification.Builder(context, NotificationHelper.CHANNEL_ID) : new android.app.Notification.Builder(context);
        b.setSmallIcon(R.drawable.ic_maoozos).setContentTitle(title == null ? "MaoozOS Reminder" : title).setContentText(message == null ? "You have an upcoming item." : message).setAutoCancel(true).setContentIntent(pi).setPriority(android.app.Notification.PRIORITY_HIGH);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null && (Build.VERSION.SDK_INT < 33 || nm.areNotificationsEnabled())) nm.notify(id, b.build());
    }
    public static void schedule(Context c, String id, long when, String title, String message) {
        Intent in = new Intent(c, ReminderReceiver.class).putExtra("id", id).putExtra("title", title).putExtra("message", message);
        int code = Math.abs(id.hashCode());
        PendingIntent pi = PendingIntent.getBroadcast(c, code, in, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        if (am == null) return;
        if (Build.VERSION.SDK_INT >= 31 && am.canScheduleExactAlarms()) am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, when, pi);
        else if (Build.VERSION.SDK_INT >= 23) am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, when, pi);
        else am.set(AlarmManager.RTC_WAKEUP, when, pi);
    }
}
