package com.example.kjhoo.simple_lockscreen;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import es.dmoral.prefs.Prefs;

public class ScreenReceiver extends BroadcastReceiver {

    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keyLock = null;
    private TelephonyManager telephonyManager = null;
    private boolean isPhoneIdle = true;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences = context.getSharedPreferences("screen", 0);
        editor = sharedPreferences.edit();

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            if (km == null)
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (keyLock == null)
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
            if(telephonyManager == null){
                telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
            }
            if(isPhoneIdle){
                disableKeyguard();

                if(!(sharedPreferences.getBoolean("lockIsRunning", false))) {
                   //if( Prefs.with().read("file")){
                        Intent i = new Intent(context, MainActivity.class);//알람이울릴시간일때 락스크린을 알람중지 화면으로 바꿔주면된다.
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                   // }
                    //if(Prefs.with(this).read("file2")){
                   //     Intent intent3 = new Intent(context,AlarmSount.class);//알람이울릴시간일때 락스크린을 알람중지 화면으로 바꿔주면된다.
                    //    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   //     context.startActivity(intent3);
                   // }
                    editor.putBoolean("lockIsRunning", true);
                    editor.apply();
                }
            }
        }
    }
    public void reenableKeyguard() {
        keyLock.reenableKeyguard();
    }
    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }
    private PhoneStateListener phoneListener = new PhoneStateListener(){

        @Override

        public void onCallStateChanged(int state, String incomingNumber){
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE :
                    isPhoneIdle = true;
                    break;
                case TelephonyManager.CALL_STATE_RINGING :
                    isPhoneIdle = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    isPhoneIdle = false;
                    break;
            }
        }

    };
}
