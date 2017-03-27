package com.gdgsystems.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Julien on 2017-03-22.
 */

public class AlertActivity extends Activity {

    private ProgressBar alertProg;
    private TextView countDown;
    Button helpBut;
    Button fineBut;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_layout);

        alertProg = (ProgressBar)findViewById(R.id.progressBar2);
        alertProg.setMax(30000);
        countDown = (TextView)findViewById(R.id.countDown);
        final SharedPreferences.Editor editor = MainActivity.prefs.edit();

        helpBut = (Button) findViewById(R.id.nurseAlertBut);
        helpBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(MainActivity.prefs.getBoolean("vibrate", false)){
                    MainActivity.vib.vibrate(300);

                }

                Intent clicky = new Intent(AlertActivity.this,ResolveActivity.class);
                startActivity(clicky);
                editor.putBoolean("nurseCall", true);
                editor.commit();
            }
        });

        fineBut = (Button) findViewById(R.id.amFineBut);
        fineBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(MainActivity.prefs.getBoolean("vibrate", false)){
                    MainActivity.vib.vibrate(300);
                }

                Intent clicky = new Intent(AlertActivity.this,ResolveActivity.class);
                startActivity(clicky);
                editor.putBoolean("nurseCall", false);
                editor.commit();
            }
        });


        new CountDownTimer(30000,1000){


            public void onTick(long millisUntilFinished){
                countDown.setText(""+ millisUntilFinished/1000);
                alertProg.setProgress((int)millisUntilFinished);


            }

            public void onFinish(){
                //SEND ALERT RIGHT HERE (WHEN THE TIMER ENDS)
                countDown.setText("AN ALERT WAS SENT TO YOUR NURSE");
            }
        }.start();

    }
}
