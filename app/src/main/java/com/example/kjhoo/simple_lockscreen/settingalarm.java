
package com.example.kjhoo.simple_lockscreen;

        import android.view.View;
        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.DatePickerDialog;
        import android.app.PendingIntent;
        import android.content.Context;

        import android.app.TimePickerDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.media.RingtoneManager;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.os.Vibrator;
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
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

import com.example.kjhoo.simple_lockscreen.ConfigActivity;
import com.example.kjhoo.simple_lockscreen.R;
import com.example.kjhoo.simple_lockscreen.ScreenService;
import com.example.kjhoo.simple_lockscreen.ScreenService2;
import com.example.kjhoo.simple_lockscreen.selectmusic;

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
    ImageView image1, image2, image3, image4, image5, image6, image7;
    boolean a = true;
    boolean imageiboolean1 = true;
    boolean imageiboolean2 = true;
    boolean imageiboolean3 = true;
    boolean imageiboolean4 = true;
    boolean imageiboolean5 = true;
    boolean imageiboolean6 = true;
    boolean imageiboolean7 = true;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1234:

                if (resultCode == RESULT_OK) {

                    text3.setText(Prefs.with(settingalarm.this).read("true"));
                }
                break;
            case 1235:
                if (resultCode == RESULT_OK) {
                    text2.setText(Prefs.with(settingalarm.this).read("truelock"));
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
        image1 = (ImageView) findViewById(R.id.wall);
        image2 = (ImageView) findViewById(R.id.ha);
        image3 = (ImageView) findViewById(R.id.soo);
        image4 = (ImageView) findViewById(R.id.mok);
        image5 = (ImageView) findViewById(R.id.km);
        image6 = (ImageView) findViewById(R.id.to);
        image7 = (ImageView) findViewById(R.id.ill);

        // Vibrate for 500 milliseconds


        final CharSequence[] items = {"반복 없음", "2회", "3회", "4회", "5회", "10회"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("반복 횟수");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                Log.e("asdf", items[item].toString());
                Prefs.with(settingalarm.this).write("for", items[item].toString());
                text4.setText(items[item]);
                dialog.dismiss();
            }
        });
        final AlertDialog Alert = builder.create();

        image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //asd.vibrate(400);
                if (imageiboolean1) {
                    imageiboolean1 = false;
                    image1.setBackgroundResource(R.drawable.wallon);
                } else {
                    imageiboolean1 = true;
                    image1.setBackgroundResource(R.drawable.walloff);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean2) {
                    imageiboolean2 = false;
                    image2.setBackgroundResource(R.drawable.haon);
                } else {
                    imageiboolean2 = true;
                    image2.setBackgroundResource(R.drawable.haoff);
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean3) {
                    imageiboolean3 = false;
                    image3.setBackgroundResource(R.drawable.sooon);
                } else {
                    imageiboolean3 = true;
                    image3.setBackgroundResource(R.drawable.soooff);
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean4) {
                    imageiboolean4 = false;
                    image4.setBackgroundResource(R.drawable.mokon);
                } else {
                    imageiboolean4 = true;
                    image4.setBackgroundResource(R.drawable.mokoff);
                }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean5) {
                    imageiboolean5 = false;
                    image5.setBackgroundResource(R.drawable.kmon);
                } else {
                    imageiboolean5 = true;
                    image5.setBackgroundResource(R.drawable.kmoff);
                }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean6) {
                    imageiboolean6 = false;
                    image6.setBackgroundResource(R.drawable.toon);
                } else {
                    imageiboolean6 = true;
                    image6.setBackgroundResource(R.drawable.tooff);

                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageiboolean7) {
                    imageiboolean7 = false;
                    image7.setBackgroundResource(R.drawable.iffon);
                } else {
                    imageiboolean7 = true;
                    image7.setBackgroundResource(R.drawable.iffoff);
                }
            }
        });





        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    Toast.makeText(settingalarm.this, "주무세요!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        new Thread() {



            @Override
            public void run() {


                while (a) {
                    boolean fuck = Prefs.with(settingalarm.this).readBoolean("asd", false);

                   // boolean mother = Prefs.with(settingalarm.this).readBoolean("happybirthday",false);
                   // if(!mother)
                   // {
                   //     a = false;
                   //     break;
                   // }
                    while (fuck) {
                        try {
                            fuck = Prefs.with(settingalarm.this).readBoolean("asd", false);

                            h.sendEmptyMessage(2);
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }.start();

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TimeZone utc = TimeZone.getTimeZone("UTC");
                //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                Long ringtime = Prefs.with(settingalarm.this).readLong("ringtime", 0);
                Long ringtime2 = Prefs.with(settingalarm.this).readLong("ringtime2", 0);
                Toast.makeText(getApplicationContext(), "알람 적용 완료", Toast.LENGTH_SHORT).show();
                ScreenService.setAlarm(ringtime, settingalarm.this);


                ScreenService2.setAlarm2(ringtime2, settingalarm.this);
                Log.e("asdf", Prefs.with(settingalarm.this).read("banbok"));


                //if(fuck){
                //    ScreenService2.setAlarm4(3000l,settingalarm.this);
                //    Log.e("asdf","fuck반환되서 실행됨");
                //    while(true) {
                //        while (Prefs.with(settingalarm.this).readBoolean("lockleveliswhat", false)) {
                //            ScreenService2.setAlarm4(3000l, settingalarm.this);
//
                //            Prefs.with(settingalarm.this).writeBoolean("ockleveliswhat",false);
//
                //        }
                //    }
                //}
                //do{
                //    ScreenService2.setAlarm4(300000l, settingalarm.this);
                //}while( Prefs.with(settingalarm.this).readBoolean("lockleveliswhat",false));
                Log.e("asdf", ringtime + ":" + ringtime2);
                //Prefs.with(settingalarm.this).write("file", "false");
                //Prefs.with(settingalarm.this).write("file2", "true");
                Log.e("asdf", String.valueOf(ringtime));

            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingalarm.this, ConfigActivity.class);
                startActivityForResult(intent, 1235);
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
                        btn3.setText(hourOfDay + ":" + minute);
                        Calendar calendar = Calendar.getInstance();

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
                        Prefs.with(settingalarm.this).writeLong("ringtime2", Total_Minute_Plus * 60 * 1000);

                    }
                }, 0, 0, false); //변경사항 */적용 안함 - 알람이 울리는 시간에서부터 자고싶은 시간 설정;/*
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
                                btn2.setText(hourOfDay + ":" + minute);

                                Calendar calendar = Calendar.getInstance();

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








