package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

import es.dmoral.prefs.Prefs;

/**
 * Created by kjhoo on 2016-07-16.
 */
public class AlarmSount extends Activity {


    private MediaPlayer player;

    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alarm_layout);
        Button stop = (Button) findViewById(R.id.alarm);
        //stop.setOnTouchListener(new View.OnTouchListener() {
        //    public boolean onTouch(View arg0, MotionEvent arg1) {
        //        Prefs.with(AlarmSount.this).writeBoolean("alarmisringing",false);
        //        player.stop();
        //        finish();
        //        return false;
        //    }
        //});
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.with(AlarmSount.this).writeBoolean("alarmisringing",false);
                Prefs.with(AlarmSount.this).writeBoolean("happybirthday",false);
                Prefs.with(context).writeBoolean("asd",false);
                player.stop();
                finish();
            }
        });
        play(this, getAlarmSound());


    }
    private void play(Context context, String fileName) {
        player = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd(fileName);
            player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            final AudioManager audio = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audio.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                player.setAudioStreamType(AudioManager.STREAM_ALARM);
                player.prepare();
                player.start();
                Prefs.with(context).writeBoolean("asd",false);
                ScreenService2.setAlarm3(0l,AlarmSount.this);
                String For = Prefs.with(AlarmSount.this).read("for");
                int banbok = 0;
                switch (For) {
                    case "반복 없음" : banbok = 1;break;
                    case "2회"      : banbok = 2;break;
                    case "3회"      : banbok = 3;break;
                    case "4회"      : banbok = 4;break;
                    case "5회"      : banbok = 5;break;
                    case "10회"     : banbok = 10;break;

                }
                Prefs.with(AlarmSount.this).write("banbok",String.valueOf(banbok));
                int j;
                for(j=1;j<banbok;j++) {
                    ScreenService.setAlarm(300000l,AlarmSount.this);
                }
            }
        } catch (IOException e) {
            Log.e("Error....",e.getMessage());
        }
    }
    private String getAlarmSound() {
        return Prefs.with(this).read("music_uri");
    }

}

