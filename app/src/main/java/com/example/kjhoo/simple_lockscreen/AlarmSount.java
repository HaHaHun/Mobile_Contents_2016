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
        stop.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                player.stop();
                finish();
                //Intent i = new Intent(context,Bye.class);
                //startActivity(i);
                return false;
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
            }
        } catch (IOException e) {
            Log.e("Error....",e.getMessage());
        }
    }
    private String getAlarmSound() {
        return Prefs.with(this).read("music_uri");
    }
}

