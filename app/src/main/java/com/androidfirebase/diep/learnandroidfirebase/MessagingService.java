package com.androidfirebase.diep.learnandroidfirebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Administrator on 5/10/2017.
 */

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("KiemTra", remoteMessage.getFrom() + " - " + remoteMessage.getData() + remoteMessage.getNotification().getBody());

    }
}
