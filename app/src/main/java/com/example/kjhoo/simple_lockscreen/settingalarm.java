package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import es.dmoral.prefs.Prefs;

/**
 * Created by kjhoo on 2016-07-13.
 */
public class settingalarm extends Activity {

    Button btn1, btn2, btn3;
    TextView text1, text2, text3, text4;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(1234, RESULT_OK, data);
        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    text3.setText(Prefs.with(this).read("true"));


                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.makingalarm);

        sharedPreferences = getSharedPreferences("screen", 0);
        editor = sharedPreferences.edit();
        btn1 = (Button) findViewById(R.id.goback);
        btn2 = (Button) findViewById(R.id.wakeuptime);
        btn3 = (Button) findViewById(R.id.sleepingtime);
        text1 = (TextView) findViewById(R.id.complete);
        text2 = (TextView) findViewById(R.id.knowlock);
        text3 = (TextView) findViewById(R.id.ringbell);
        text4 = (TextView) findViewById(R.id.howmanytime);


        final CharSequence[] items = {"반복 없음", "2회", "3회", "4회", "5회", "10회"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("반복 횟수");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                text4.setText(items[item]);
                dialog.dismiss();
            }
        });
        final AlertDialog Alert = builder.create();



        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TimeZone utc = TimeZone.getTimeZone("UTC");
                //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Long ringtime = Prefs.with(settingalarm.this).readLong("ringtime", 0);
                Long ringtime2 = Prefs.with(settingalarm.this).readLong("ringtime2",0);
                ScreenService.setAlarm(ringtime, settingalarm.this);
                ScreenService2.setAlarm2(ringtime2, settingalarm.this);
                Log.e("asdf",ringtime + ":" + ringtime2);
                //Prefs.with(settingalarm.this).write("file", "false");
                //Prefs.with(settingalarm.this).write("file2", "true");
                Log.e("asdf", String.valueOf(ringtime));
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingalarm.this, ConfigActivity.class);
                startActivity(intent);
            }
        });


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingalarm.this, selectmusic.class);
                startActivityForResult(intent, 1234);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(settingalarm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btn3.setText(hourOfDay + " : " + minute);
                        Long Happy = Long.valueOf(hourOfDay * 60 + minute)*1000*60;
                        Log.e("asdf",String.valueOf(Happy));
                        Long Happy2 = Prefs.with(settingalarm.this).readLong("ringtime", 0);
                        Long Happy3 = Happy2 - Happy;
                        Prefs.with(settingalarm.this).writeLong("ringtime2",Happy3);

                    }
                }, 0, 0, false);
                dialog.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(settingalarm.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                btn2.setText(hourOfDay + " : " + minute);

                                Calendar calendar = Calendar.getInstance();
                                int ringtime_hour = 0, ringtime_minute = 0;
                                int Total_Minute_Plus = 0;
                                int CurrentHour, CurrentMinute;
                                int SetHour, SetMinute;
                                CurrentHour = Integer.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                                CurrentMinute = Integer.valueOf(calendar.get(Calendar.MINUTE));
                                SetHour = Integer.valueOf(hourOfDay);
                                SetMinute = Integer.valueOf(minute);

                                int Total_Minute_Current = (CurrentHour * 60) + CurrentMinute;
                                int Total_Minute_Set = (SetHour * 60) + SetMinute;
                                Log.e("asdf", String.valueOf(CurrentHour));
                                Log.e("asdf", String.valueOf(CurrentMinute));
                                Log.e("asdf", String.valueOf(SetHour));
                                Log.e("asdf", String.valueOf(SetMinute));


                                if (Total_Minute_Set > Total_Minute_Current) {
                                    Total_Minute_Plus = Total_Minute_Set - Total_Minute_Current;
                                } else if (Total_Minute_Set < Total_Minute_Current) {
                                    Total_Minute_Plus = 1440 - Total_Minute_Current + Total_Minute_Set;
                                }
                                Log.e("asdf", String.valueOf(Total_Minute_Plus));
                                Prefs.with(settingalarm.this).writeLong("ringtime", Total_Minute_Plus * 60 * 1000);
                            }
                        }, 0, 0, false);
                dialog.show();



            }
        });
    }
}








