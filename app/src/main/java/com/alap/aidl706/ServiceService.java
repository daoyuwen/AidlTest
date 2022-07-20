package com.alap.aidl706;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class ServiceService extends Service {

    private static final String TAG = "ServiceService";

    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {

            return a+b;
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "add: "+stub);
        return stub;
    }
    }

