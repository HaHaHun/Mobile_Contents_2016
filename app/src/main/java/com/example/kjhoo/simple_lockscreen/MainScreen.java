package com.example.kjhoo.simple_lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by kjhoo on 2016-07-12.
 */
public class MainScreen extends Activity {

    Button start_app, graph;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Button start_app = (Button) findViewById(R.id.start_app);


        start_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,settingalarm.class);
                startActivityForResult(intent , 1234);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}





