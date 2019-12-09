package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

public class Timer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Button button = (Button)findViewById(R.id.buttonStartTimer);
        button.setOnClickListener(start_timer);
    }

    private View.OnClickListener start_timer = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            new CountDownTimer(30000, 1000) {

                TextView fieldTime = (TextView)findViewById(R.id.textViewTime) ;

                public void onTick(long millisUntilFinished) {
                    fieldTime.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    fieldTime.setText("done!");
                }
            }.start();

        }


    };

}
