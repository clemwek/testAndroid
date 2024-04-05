package com.vibes.androidpushdemoapp;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.iid.FirebaseInstanceId;
import com.vibes.vibes.Credential;
import com.vibes.vibes.Vibes;
import com.vibes.vibes.VibesConfig;
import com.vibes.vibes.VibesListener;

public class DemoApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initVibesSDK();

    }
     public void initVibesSDK() {
         VibesConfig config = new VibesConfig.Builder()
                 .setAppId("UATINTEGRATIONTEST")
                 .setApiUrl("https://public-api-uatus0.vibescm.com/mobile_apps")
                 .build();
         Log.d(TAG, "Initializing Vibes with appId=[" + "UATINTEGRATIONTEST" + "] and apiUrl=[" + "https://public-api-uatus0.vibescm.com/mobile_apps" + "]");
         Vibes.initialize(this, config);
         Vibes.getInstance().registerDevice(new VibesListener<Credential>() {
             @Override
             public void onSuccess(Credential value) {
                 Log.d(TAG, "Register device Successfull");
                 Vibes.getInstance().registerPush(initToken(), new VibesListener<Void>() {

                     @Override
                     public void onSuccess(Void value) {
                         Log.d(TAG, "Register push Success");
                     } @Override
                     public void onFailure(String errorText) {
                     }
                 });
             }

             @Override
             public void onFailure(String errorText) {
             }
         });
         Log.d(TAG, "=======TOKEN==========" + initToken());

     }

     public void registerDevice() {
//        Vibes.getInstance().
     }

     private String initToken() {
         return FirebaseInstanceId.getInstance().getToken();
     }
}
