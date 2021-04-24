package com.example.orator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.security.PublicKey;

public class Settings extends AppCompatActivity {

    //SharedPreferences.Editor editor = sharedPreferences.edit();
    SeekBar speed;
    EditText wordgap,length;
    EditText wordlim;
    Button apply,restore;
    public static  boolean lol=false;
        SharedPreferences.Editor speedEdit, wordEdit,gapEdit,lengthedit,setedit;
    SharedPreferences speedPref = Say.speedPref;
    SharedPreferences gapPref = Say.gapPref;
    SharedPreferences wordPref =  Say.wordPref;
    SharedPreferences lengthPref =  Say.lengthPref;
    SharedPreferences setPref =  Say.setPref;
    public static int place =0;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        lol=true;
        speed = (SeekBar) findViewById(R.id.speedBar);
        wordgap = (EditText) findViewById(R.id.Timegap);
        wordlim =(EditText)findViewById(R.id.wordlimit);
        apply = (Button) findViewById(R.id.ok);
        speedEdit = speedPref.edit();
        wordEdit = wordPref.edit();
        gapEdit = gapPref.edit();
        lengthedit = lengthPref.edit();
        setedit = setPref.edit();
        setedit.putBoolean("set",true);
        restore =(Button) findViewById(R.id.res);
        length =(EditText) findViewById(R.id.longWord);
        length.setText(lengthPref.getString("length","7"));
        speed.setProgress( speedPref.getInt("speed",50));
        wordgap.setText(gapPref.getString("gap","6"));
        wordlim.setText(wordPref.getString("words","3"));
      place=Say.i;

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = (wordgap.getText().toString());
                String  b =(wordlim.getText().toString());
                String l = length.getText().toString();
                if((Integer.parseInt(a)>50)||(Integer.parseInt(a)>50)||(Integer.parseInt(a)>50))
                {
                    Toast.makeText(getApplicationContext(),"the number input is out of range!",Toast.LENGTH_SHORT).show();

                }
                init();
                finish();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedEdit.putInt("speed",50);
                gapEdit.putString("gap","6");
                wordEdit.putString("words","3");
                length.setText("7");
                speed.setProgress(50);
                wordgap.setText("6");
                wordlim.setText("3");
            }
        });


    }
       public void init()
       {

           setedit.putBoolean("set",true);

           speedEdit.putInt("speed",speed.getProgress());
           String a = (wordgap.getText().toString());
           String  b =(wordlim.getText().toString());
           String l = length.getText().toString();


           gapEdit.putString("gap",a);
           wordEdit.putString("words",b);
            lengthedit.putString("length",l);

            speedEdit.apply();
            setedit.apply();
            wordEdit.apply();
            gapEdit.apply();

            lengthedit.apply();




            length.setText(lengthPref.getString("length","7"));
           wordgap.setText(gapPref.getString("gap","6"));
           wordlim.setText(wordPref.getString("words","3"));
           Intent intent = new Intent(this,Say.class);
           startActivity(intent);
       }

}