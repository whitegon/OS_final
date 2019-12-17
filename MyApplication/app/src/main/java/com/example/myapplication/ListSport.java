package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList ;
import java.util.List ;

import android.content.Intent;



public class ListSport extends AppCompatActivity {


    ArrayList<String> sport_list = new ArrayList<String>() ;
    ArrayAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sport);

        Button button = (Button)findViewById(R.id.button_add);
        button.setOnClickListener(add_sport);

        sport_list.add("胸") ;
        sport_list.add("背") ;
        sport_list.add("腿") ;

//        String[] str = {"胸","背", "腿"} ;

        ListView listView_sport = (ListView) findViewById(R.id.listview_sport) ;
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sport_list ) ;

        listView_sport.setAdapter(adapter);
        listView_sport.setOnItemClickListener(onClickListView);
        listView_sport.setOnItemLongClickListener(onLongClickListView);


    }


    private View.OnClickListener add_sport = new View.OnClickListener() {





        @Override
        public void onClick (View v){

            EditText fieldsport = (EditText)findViewById(R.id.editText_sport) ;

            String sport = (fieldsport.getText().toString()) ;

            sport_list.add(sport) ;
            adapter.notifyDataSetChanged();


        }



    };



    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(ListSport.this, Timer.class));
        }
    };

    private AdapterView.OnItemLongClickListener onLongClickListView = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            new AlertDialog.Builder(ListSport.this)
                    .setTitle("want to delele?")
                    .setMessage("Want to delete " + sport_list.get(position) + " item?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sport_list.remove(sport_list.get(position));
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();

            return true;
        }



    };



}


