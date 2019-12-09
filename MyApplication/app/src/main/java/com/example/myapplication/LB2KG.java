package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LB2KG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lb2_kg);

        Button button = (Button)findViewById(R.id.buttonCallbkg);
        button.setOnClickListener(caculate_LBKG);

    }

    private View.OnClickListener caculate_LBKG = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            EditText fieldlb = (EditText)findViewById(R.id.editTextLb) ;
            EditText fieldkg = (EditText)findViewById(R.id.editTextKg) ;

            if(fieldlb.getText().toString().isEmpty()){
                double kg = Double.parseDouble(fieldkg.getText().toString()) ;

                fieldlb.setText(Double.toString((kg * 2.20462262)));

            }else{
                double lb = Double.parseDouble(fieldlb.getText().toString()) ;

                fieldkg.setText(Double.toString((lb * 0.45359237)));
            }


        }


    };


}
