package com.wcy.hybirdinteg;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import io.dcloud.application.DCloudApplication;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class MainApplication extends DCloudApplication implements Application.ActivityLifecycleCallbacks {

    static Activity currActivity;


    public static Activity getCurrActivity() {
        return currActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            FlutterEngine flutterEngine = new FlutterEngine(this);
            flutterEngine.getDartExecutor().executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            );
            FlutterEngineCache.getInstance().put("flutter_native",flutterEngine);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        currActivity = activity;

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
