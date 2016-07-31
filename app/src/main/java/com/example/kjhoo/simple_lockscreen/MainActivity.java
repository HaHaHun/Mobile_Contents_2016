package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

import es.dmoral.prefs.Prefs;



public class MainActivity extends Activity {
    private MediaPlayer player2;
    Button finish;
    ImageView playmusic;
    boolean popo = true;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("screen", 0);
        editor = sharedPreferences.edit();
         finish = (Button)findViewById(R.id.finish);
         playmusic = (ImageView)findViewById(R.id.musicplayer);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(!popo)
                {
                    player2.stop();
                }
                editor.putBoolean("lockIsRunning", false);
                editor.apply();
            }
        });
        playmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popo) {
                    popo = false;
                    play(MainActivity.this, "thunder.mp3");
                   playmusic.setBackgroundResource(R.drawable.stopmusic);
                }
                else{
                    popo = true;
                    player2.stop();
                    playmusic.setBackgroundResource(R.drawable.playmusic);
                }
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        |WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
    private void play(Context context, String fileName) {
        if(Prefs.with(context).readBoolean("alarmisringing",false)){
            if(!popo) {
                player2.stop();
            }
        }
        player2 = new MediaPlayer();
        player2.setLooping(true);
        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd(fileName);
            player2.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            final AudioManager audio = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audio.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                player2.setAudioStreamType(AudioManager.STREAM_ALARM);
                player2.prepare();
                player2.start();
            }
        } catch (IOException e) {
            Log.e("Error....",e.getMessage());
        }
    }

}

