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

public class Timer extends AppCompatActivity {

    String CHANNEL_ID = "my_channel_01";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Button button = (Button)findViewById(R.id.buttonStartTimer);
        button.setOnClickListener(start_timer);

        notificationManager = NotificationManagerCompat.from(this);
    }


    public void notification(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "my_channel_01";
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
                .setContentText("Done!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    private View.OnClickListener start_timer = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            new CountDownTimer(3000, 1000) {

                TextView fieldTime = (TextView)findViewById(R.id.textViewTime) ;

                public void onTick(long millisUntilFinished) {
                    fieldTime.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    notification();
                    fieldTime.setText("done!");
                    // notificationId is a unique int for each notification that you must define
                }
            }.start();

        }


    };

}
