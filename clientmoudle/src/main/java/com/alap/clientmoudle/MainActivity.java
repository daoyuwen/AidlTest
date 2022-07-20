package com.alap.clientmoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alap.aidl706.IMyAidlInterface;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText editText_a;
    private EditText editText_b;
    private Button button;
    private TextView textView;
    private IMyAidlInterface iMyAidlInterface;//创建的aidl文件名称
//=new IMyAidlInterface() {
//        @Override
//        public int add(int a, int b) throws RemoteException {
//            return a+b;
//        }
//
//        @Override
//        public IBinder asBinder() {
//            return null;
//        }
//    };



    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /**
             * 如果iMyAidlInterface != null 连接成功
             */

            Log.e(TAG, "onServiceConnected: 1111111" );
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_a = findViewById(R.id.edit_a);
        editText_b = findViewById(R.id.edit_b);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(editText_a.getText().toString());
                int b = Integer.parseInt(editText_b.getText().toString());
                Log.e(TAG, "onClick: "+a );
                Log.e(TAG, "onClick: "+b );
             int add = 0;
                try {
                     add = iMyAidlInterface.add(a, b);
                    textView.setText(a + " + " + b + " = " + add);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent();

        intent.setComponent(new ComponentName("com.alap.aidl706","com.alap.aidl706.ServiceService"));


//        intent.setAction("com.alap.aidl706.ServiceService.action");//服务器manifests中注册所填写的action
//        intent.setPackage("com.alap.aidl706");//服务器包名

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


}