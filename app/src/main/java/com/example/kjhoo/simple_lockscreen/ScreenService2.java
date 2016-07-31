package com.example.kjhoo.simple_lockscreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import es.dmoral.prefs.Prefs;

/**
 * Created by kjhoo on 2016-07-17.
 */

public class ScreenService2 extends Service {
    private ScreenReceiver mReceiver = null;
    static Vibrator v;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent != null) {
            if (intent.getAction() == null) {
                if (mReceiver == null) {
                    mReceiver = new ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(mReceiver, filter);
                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    public static void setAlarm2(Long Ms, final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("start", "screenlockstart");
                Prefs.with(context).writeBoolean("asd",true);
                Intent intent = new Intent(context, ScreenService.class);
                context.startService(intent);

            }
        }, Ms);
    }

    public static void setAlarm3(Long Ms, final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, ScreenService.class);
                context.stopService(intent);

            }
        }, Ms);
    }

    public static void setAlarm4(Long Ms, final Context context) {
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (Prefs.with(context).readBoolean("LockLevel1", false)) {
                    Toast.makeText(context, "주무세요!", Toast.LENGTH_SHORT).show();
                    Log.e("toast", "토스트 실행");
                }
                if (Prefs.with(context).readBoolean("LockLevel2", false)) {
                    v.vibrate(500);
                    Log.e("vivrate", "진동 실행");
                }
                Prefs.with(context).writeBoolean("lockleveliswhat", true);

            }
        }, Ms);
    }
}
