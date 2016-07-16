package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.prefs.Prefs;

/**
 * Created by kjhoo on 2016-07-16.
 */
public class selectmusic extends Activity {


    TextView text1,text2,text3,text4,text5;
    Button btn1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmusic);
        btn1 = (Button)findViewById(R.id.goback2);
        text1 = (TextView)findViewById(R.id.vibration);
        text2 = (TextView)findViewById(R.id.music1);
        text3 = (TextView)findViewById(R.id.music2);
        text4 = (TextView)findViewById(R.id.music3);
        text5 = (TextView)findViewById(R.id.base_music);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(selectmusic.this, "music1 선택", Toast.LENGTH_SHORT).show();
                Prefs.with(selectmusic.this).write("music_uri", "music1.mp3");
                Prefs.with(selectmusic.this).write("true","Music1");
                setResult(RESULT_OK);
                finish();
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(selectmusic.this, "Music2 선택", Toast.LENGTH_SHORT).show();
                Prefs.with(selectmusic.this).write("music_uri", "music2.mp3");
                Prefs.with(selectmusic.this).write("true","Music2");
                setResult(RESULT_OK);
                finish();
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(selectmusic.this, "Music3 선택", Toast.LENGTH_SHORT).show();
                Prefs.with(selectmusic.this).write("music_uri", "test1.ogg");
                Prefs.with(selectmusic.this).write("true","Music3");
                setResult(RESULT_OK);
                finish();
            }
        });
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(selectmusic.this, "Base Busic 선택", Toast.LENGTH_SHORT).show();
                Prefs.with(selectmusic.this).write("music_uri", RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString());
                Prefs.with(selectmusic.this).write("true","Base_Music");
                setResult(RESULT_OK);
                finish();
            }
        });
    }


}

