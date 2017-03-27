package com.daili.tsapp.tsService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
//暂时没有要在后台接单的需求  暂时没有使用到
public class FindFormService extends Service {
    public FindFormService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
