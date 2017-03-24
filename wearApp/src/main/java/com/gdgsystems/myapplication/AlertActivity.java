package com.gdgsystems.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Julien on 2017-03-22.
 */

public class AlertActivity extends Activity {

    private ProgressBar alertProg;
    private TextView countDown;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);

        alertProg = (ProgressBar)findViewById(R.id.progressBar2);
        alertProg.setMax(30000);
        countDown = (TextView)findViewById(R.id.countDown);

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
