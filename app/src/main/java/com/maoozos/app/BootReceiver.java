package com.maoozos.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        NotificationHelper.ensureChannel(context);
        // v4 keeps reminder scheduling owned by the native app. Future scheduled reminders can be reloaded from AppStore here.
    }
}
