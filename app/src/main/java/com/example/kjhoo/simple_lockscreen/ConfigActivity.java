package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import es.dmoral.prefs.Prefs;

/**
 * Created by kjhoo on 2016-06-26.
 */

public class ConfigActivity extends Activity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ImageView locklevel1, locklevel2 , locklevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionactivity);

        pref = getSharedPreferences("screen", MODE_PRIVATE);
        editor = pref.edit();

        locklevel1 = (ImageView) findViewById(R.id.locklevel1);
        locklevel2 = (ImageView)findViewById(R.id.locklevel2);
        locklevel3 = (ImageView)findViewById(R.id.locklevel3) ;

        locklevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"잠금화면 적용", Toast.LENGTH_SHORT).show();
                Prefs.with(ConfigActivity.this).write("truelock","1단계");
                setResult(RESULT_OK);
                finish();
            }
        });
        locklevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"알림 + 잠금화면 적용", Toast.LENGTH_SHORT).show();
                Prefs.with(ConfigActivity.this).writeBoolean("LockLevel1",true);
                Prefs.with(ConfigActivity.this).write("truelock","2단계");
                setResult(RESULT_OK);
                finish();
            }
        });
        locklevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"진동 + 알림 + 잠금화면 적용", Toast.LENGTH_SHORT).show();
                Prefs.with(ConfigActivity.this).writeBoolean("LockLevel2",true);
                Prefs.with(ConfigActivity.this).write("truelock","3단계");
                setResult(RESULT_OK);
                finish();

            }
        });
        //onBtn = (Button) findViewById(R.id.btn1);
        //offBtn = (Button) findViewById(R.id.btn2);
        //apllyBtn = (Button)findViewById(R.id.apply);


        //onBtn.setOnClickListener(new View.OnClickListener() {

        //    @Override
        //    public void onClick(View v) {
        //        //TimePickerDialog dialog = new TimePickerDialog(ConfigActivity.this, listener, 15, 24, false);
        //        //dialog.show();


        //        Intent intent = new Intent(ConfigActivity.this, ScreenService.class);
        //        startService(intent);
        //    }
        //});

        //offBtn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        //TimePickerDialog dialog = new TimePickerDialog(ConfigActivity.this, listener, 15, 24, false);
        //        //dialog.show();


        //        Intent intent = new Intent(ConfigActivity.this, ScreenService.class);
        //        stopService(intent);
        //    }
        //});

    }

}


