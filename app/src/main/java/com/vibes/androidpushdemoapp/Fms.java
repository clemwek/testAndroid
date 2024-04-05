package com.vibes.androidpushdemoapp;



import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vibes.vibes.Vibes;

public class Fms extends FirebaseMessagingService {
    private static final String TAG = "c.v.aex.FMS";

    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.d(TAG, "Inside onmessage received");
        Vibes.getInstance().handleNotification(getApplicationContext(), message.getData());
    }

    @Override
    public void onNewToken(String pushToken) {
        if(BuildConfig.DEBUG){
            Log.i(TAG,"Firebase token refreshed");
        }
        super.onNewToken(pushToken);
        Log.d(TAG, "VibesPush: " + pushToken);

    }

}