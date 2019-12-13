package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    public int set, worktime, idletime, cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Button button = (Button)findViewById(R.id.buttonStartTimer);
        button.setOnClickListener(start_timer);

        notificationManager = NotificationManagerCompat.from(this);
    }


    public void notification(String text){
        String CHANNEL_ID = "my_channel_01";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Timer")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    public void timer(){
        if(cnt == set * 2){
            return;
        }else if(cnt % 2 == 0){
            new CountDownTimer(worktime, 1000) {

                TextView fieldTime = (TextView)findViewById(R.id.textViewRemainTime) ;

                public void onTick(long millisUntilFinished) {
                    fieldTime.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    notification("Set " + Integer.toString(cnt / 2 + 1) + " Done!");
                    fieldTime.setText("set done!");
                    cnt += 1;
                    timer();
                }
            }.start();
        }else{
            new CountDownTimer(idletime, 1000) {

                TextView fieldTime = (TextView)findViewById(R.id.textViewRemainTime) ;

                public void onTick(long millisUntilFinished) {
                    fieldTime.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    notification("Idle " + Integer.toString(cnt / 2 + 1) + " Done!");
                    fieldTime.setText("idle done!");
                    cnt += 1;
                    timer();
                }
            }.start();
        }

    }

    private View.OnClickListener start_timer = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            EditText fieldset = (EditText)findViewById(R.id.editTextSet) ;
            set = Integer.parseInt(fieldset.getText().toString());

            EditText fieldworktime = (EditText)findViewById(R.id.editTextWorkTime) ;
            String[] timeStr = fieldworktime.getText().toString().split(":");
            int min = Integer.parseInt(timeStr[0]);
            int sec = Integer.parseInt(timeStr[1]);
            worktime = (min * 60 + sec) * 1000;

            EditText fieldidletime = (EditText)findViewById(R.id.editTextIdleTime) ;
            timeStr = fieldidletime.getText().toString().split(":");
            min = Integer.parseInt(timeStr[0]);
            sec = Integer.parseInt(timeStr[1]);
            idletime = (min * 60 + sec) * 1000;

            cnt = 0;
            timer();

        }


    };

}
