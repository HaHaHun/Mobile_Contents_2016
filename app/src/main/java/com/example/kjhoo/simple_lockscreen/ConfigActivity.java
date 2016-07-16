package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by kjhoo on 2016-06-26.
 */

public class ConfigActivity extends Activity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private Button onBtn, offBtn,apllyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionactivity);
        pref = getSharedPreferences("screen", MODE_PRIVATE);
        editor = pref.edit();
        onBtn = (Button) findViewById(R.id.btn1);
        offBtn = (Button) findViewById(R.id.btn2);
        apllyBtn = (Button)findViewById(R.id.apply);


        onBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TimePickerDialog dialog = new TimePickerDialog(ConfigActivity.this, listener, 15, 24, false);
                //dialog.show();


                Intent intent = new Intent(ConfigActivity.this, ScreenService.class);
                startService(intent);
            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TimePickerDialog dialog = new TimePickerDialog(ConfigActivity.this, listener, 15, 24, false);
                //dialog.show();


                Intent intent = new Intent(ConfigActivity.this, ScreenService.class);
                stopService(intent);
            }
        });

    }

    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {


        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();
            //editor.putInt("hour",hourOfDay);
            //editor.putInt("minute",minute);
            //editor.apply();
//

        }

    };
}


