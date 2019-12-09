package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.buttonCalBMI);
        button.setOnClickListener(caculate_BMI);
        Button button2 = (Button)findViewById(R.id.buttonGotolbkg);
        button2.setOnClickListener(goto_lbkg);

    }


    private View.OnClickListener caculate_BMI = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            EditText fieldheight = (EditText)findViewById(R.id.editTextheight) ;
            EditText fieldweight = (EditText)findViewById(R.id.editTextweight) ;

            double height = Double.parseDouble(fieldheight.getText().toString())/100 ;
            double weight = Double.parseDouble(fieldweight.getText().toString()) ;

            double bmi = weight / ( height * height ) ;

            TextView result_bmi = (TextView)findViewById(R.id.result_bmi) ;
            result_bmi.setText(Integer.toString((int)bmi)) ;

            TextView bmi_note = (TextView)findViewById(R.id.bmi_note) ;

            if ( bmi < 18.5 ) {
                bmi_note.setTextColor(Color.RED);
                bmi_note.setText("過輕");
            }


            else if ( bmi >= 18.5 && bmi <= 24 ) {
                bmi_note.setTextColor(Color.GREEN);
                bmi_note.setText("正常");
            }

            else{
                bmi_note.setTextColor(Color.RED);
                bmi_note.setText("超重");
            }



        }


    };


    private View.OnClickListener goto_lbkg = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, LB2KG.class));


        }


    };


}
