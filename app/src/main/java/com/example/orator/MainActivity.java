package com.example.orator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   EditText text ;
    Button click;
    Button cambutton;
    AdView mAdView;
       public static boolean state=false;
    public static  String s;
   public  static Boolean hola=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        if (!state && (!Say.saystate) && (!cam.camstate)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setContentView(R.layout.activity_splash_screen);
                    state = true;
                    hola=true;
                }
            });



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setContentView(R.layout.activity_main);
                    text = (EditText) findViewById(R.id.content);
                    click = (Button) findViewById(R.id.dictate);
                    cambutton = (Button) findViewById(R.id.Camera);

                        hola=true;
                    text.setText(cam.yess);

                    click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            s = text.getText().toString();
                            changeclass();

                        }
                    });
                    cambutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            camClass();


                        }
                    });


                }
            }, 2000);


        }
        else
        {
            setContentView(R.layout.activity_main);
            hola=true;

//###########################################################################################################################
            //  THIS IS FOR ADS!!!!!!


            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


//################################################################################################################3



            text = (EditText) findViewById(R.id.content);
            click = (Button) findViewById(R.id.dictate);
            cambutton = (Button) findViewById(R.id.Camera);

            text.setText(cam.yess);

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s = text.getText().toString();
                    changeclass();

                }
            });
            cambutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    camClass();


                }
            });

        }
    }

    public void changeclass()
    {
        Intent intent = new Intent(this, Say.class);
        startActivity(intent);
      }
      public void camClass()
      {
          Intent intent2 = new Intent(this,cam.class);
          startActivity(intent2);
      }



}

