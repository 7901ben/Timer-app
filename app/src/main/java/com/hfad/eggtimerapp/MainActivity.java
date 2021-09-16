package com.hfad.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   ListView listViewMin;
   ListView listViewSec;
   TextView textView;
   CountDownTimer countDownTimer ;
   Button start;
   Button reset;
   long forCountDownTimer=0;
   int enteredSec = 0;
   int enteredMin = 0;
    int min,sec;
    MediaPlayer mp;

   public void onReset(View view){
      Log.i("info","reset clicked");
       countDownTimer.cancel();
    }
    public void onPause(View view){
        Log.i("info","reset clicked");
        //countDownTimer.;
    }

    public void onStart(View view){
        start = (Button)findViewById(R.id.start);
        Log.i("info","start clicked");
        forCountDownTimer = (enteredMin*1000)*60+enteredSec*1000 ;
        new CountDownTimer (forCountDownTimer,1000){

            public void onTick(long millisUntilFinished) {
                // textView.setText(""+millisUntilFinished/1000);
                //here you can have your logic to set text to edittext

                //String nsec;

              min = (int)millisUntilFinished/60000;
              sec = (int)millisUntilFinished%60000;


              textView.setText(min +":"+sec/1000);
            }

            public void onFinish() {

                mp.start();
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewMin = (ListView)findViewById(R.id.listViewMin);
        listViewSec = (ListView)findViewById(R.id.listViewSec);
        textView = (TextView)findViewById(R.id.textView);
         mp = MediaPlayer.create(this, R.raw.sound);
        ArrayList<Integer> min = new ArrayList<Integer>();
        for(int i = 0 ; i <= 10 ; i++){
            min.add(i);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,min);
        listViewMin.setAdapter(arrayAdapter);
        listViewMin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("info",Integer.toString(position)+"is clicked");
                enteredMin = position;
                Toast.makeText(MainActivity.this,"U have chosen :"+Integer.toString(position), Toast.LENGTH_SHORT).show();
                textView.setText(enteredMin +":"+ enteredSec);
            }
        });

        ArrayList<Integer> sec = new ArrayList<Integer>();
        for(int i = 0 ; i <= 60 ; i++){
            sec.add(i);
        }
        ArrayAdapter<Integer> arrayAdapter1 = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,sec);
        listViewSec.setAdapter(arrayAdapter1);
        listViewSec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("info",Integer.toString(position)+"is clicked");
                enteredSec = position;
                Toast.makeText(MainActivity.this,"U have chosen :"+Integer.toString(position), Toast.LENGTH_SHORT).show();
                textView.setText(enteredMin +":"+ enteredSec);
            }
        });

    }
}