package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
        Button button3 = (Button)findViewById(R.id.buttonGotoTimer);
        button3.setOnClickListener(goto_sport);

    }


    private View.OnClickListener caculate_BMI = new View.OnClickListener(){

        @Override
        public void onClick(View v) {


            //caculate bmi
            EditText fieldheight = (EditText)findViewById(R.id.editTextheight) ;
            EditText fieldweight = (EditText)findViewById(R.id.editTextweight) ;
            EditText fieldyear = (EditText)findViewById(R.id.editTextyear) ;
            RadioGroup boy_girl = (RadioGroup)findViewById(R.id.RadioGroup_gender) ;
            RadioGroup sport_type = (RadioGroup)findViewById(R.id.RadioGroup_sport_type) ;

            if(fieldheight.getText().toString().isEmpty() ||
                    fieldweight.getText().toString().isEmpty() ||
                    fieldyear.getText().toString().isEmpty() ||
                    (boy_girl.getCheckedRadioButtonId()  == -1) ||
                    (sport_type.getCheckedRadioButtonId() == -1)
            )
            {
                TextView alert = (TextView)findViewById(R.id.Alert);
                alert.setText("欄位都必填");
                alert.setTextColor(Color.RED);
                return;
            }



            double height = Double.parseDouble(fieldheight.getText().toString())/100 ;
            double weight = Double.parseDouble(fieldweight.getText().toString()) ;

            double bmi = weight / ( height * height ) ;

            TextView result_bmi = (TextView)findViewById(R.id.result_bmi) ;
            result_bmi.setText(Integer.toString((int)bmi)) ;

            TextView bmi_note = (TextView)findViewById(R.id.bmi_note) ;
            TextView alert = (TextView)findViewById(R.id.Alert);

            if ( bmi < 18.5 ) {
                bmi_note.setTextColor(Color.RED);
                bmi_note.setText("過輕");
                alert.setText("");
            }


            else if ( bmi >= 18.5 && bmi <= 24 ) {
                bmi_note.setTextColor(Color.GREEN);
                bmi_note.setText("正常");
                alert.setText("");
            }

            else{
                bmi_note.setTextColor(Color.RED);
                bmi_note.setText("超重");
                alert.setText("");
            }

            double bmr = 0 ;

            //caculate bmr and tdee
            int year = Integer.parseInt(fieldyear.getText().toString()) ;

            //choose boy or girl
            switch (boy_girl.getCheckedRadioButtonId()){
                case R.id.radioButton_boy:
                    bmr = ( 13.7 * weight ) + ( 5.0 * height * 100 ) - ( 6.8 * year ) + 66 ;
                    break;

                case R.id.radioButton_girl:
                    bmr = ( 9.6 * weight ) + ( 1.8 * height * 100 ) - ( 4.7 * year ) + 655 ;
                    break;

            }



            double tdee = 0 ;


            //choose sport type
            switch (sport_type.getCheckedRadioButtonId()){
                case R.id.radioButton_nosport:
                    tdee = bmr * 1.2 ;
                    break;

                case R.id.radioButton_lightsport:
                    tdee = bmr * 1.375 ;
                    break;

                case R.id.radioButton_medsport:
                    tdee = bmr * 1.55 ;
                    break;

                case R.id.radioButton_hardsport:
                    tdee = bmr * 1.725 ;
                    break;

                case R.id.radioButton_everyday:
                    tdee = bmr * 1.9 ;
                    break;

            }

            TextView result_tdee = (TextView)findViewById(R.id.result_tdee) ;
            result_tdee.setText(Integer.toString((int)tdee));

            TextView tdee_note = (TextView)findViewById(R.id.tdee_note) ;
            tdee_note.setText("維持體重：TDEE\n" +
                    "增加肌肉：TDEE+300\n" +
                    "減少脂肪：TDEE-300");


        }


    };


    private View.OnClickListener goto_lbkg = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, LB2KG.class));
        }


    };

    private View.OnClickListener goto_timer = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, Timer.class));
        }


    };

    private View.OnClickListener goto_sport = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, ListSport.class));
        }


    };

}