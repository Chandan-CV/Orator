package com.example.orator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;

import android.speech.tts.TextToSpeech;
//import android.util.//;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Say extends AppCompatActivity {


    public static boolean saystate =false;
    TextToSpeech tts;
            Button click, done;
           TextView tv;

            public static int i=(Settings.place);
           String s = MainActivity.s;
           String a[] = s.split(" ");
           Handler handler = new Handler();
           String say="",state ="play";
           ImageView set;
           ImageButton prev,nxt;
    public static   SharedPreferences speedPref;
    public static SharedPreferences wordPref;
    public static SharedPreferences gapPref;
    public static SharedPreferences lengthPref;
    public static SharedPreferences setPref;

    Float speed = 1.0f;
        int n;
        int t;
        int lengthy;
      AdView mAdView;
    private InterstitialAd mInterstitialAd;
    ArrayList<String> arlist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_say);
        if(i-1>0)
        {
            i=i-1;
        }
        prev =(ImageButton)findViewById(R.id.Previous);
        nxt =(ImageButton)findViewById(R.id.Next);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9550074938550545/9652978872");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        saystate=true;
//###########################################################333333    ADS ADS ADS #####################################################
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


//################################################################ ADS ADS ADS ADS ADS #####################################################

        tv = (TextView)findViewById(R.id.Show);
        click = (Button)findViewById(R.id.play);
        set = findViewById(R.id.settings);
        done = findViewById(R.id.button123);

        final MainActivity ob = new MainActivity();
        speedPref = getSharedPreferences("speed",MODE_PRIVATE);
        wordPref = getSharedPreferences("words",MODE_PRIVATE);
        gapPref = getSharedPreferences("gap",MODE_PRIVATE);
        lengthPref = getSharedPreferences("length",MODE_PRIVATE);
        setPref = getSharedPreferences("set",MODE_PRIVATE);



        boolean what = setPref.getBoolean("set",false);
            if (!what)
            {
                Intent intent = new Intent(Say.this,Settings.class);
                startActivity(intent);
            }
            int lol = speedPref.getInt("speed",50);
            lol/=50;
           speed = ((float) lol * 1.0f);
            lengthy= Integer.parseInt(Objects.requireNonNull(lengthPref.getString("length", "7")));
           if(speed <0.1f)
           {
               speed =0.1f;
           }
            n= Integer.parseInt(Objects.requireNonNull(wordPref.getString("words", "3")));
            t= Integer.parseInt(Objects.requireNonNull(gapPref.getString("gap", "6")));
            t*=1000;
            click.setText(state);
            tv.setText("click on the play button!! to start");

             init();


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.setText(state);

                if(state.equals("play"))
                {
                    if((a.length>0)&&(a.length<n)) {
                       tv.setText(s);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
                        } else {
                            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }

                    if(a.length>0)
                    {
                        state="pause";
                        click.setText(state);
                        show.run();


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No text is entered",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    state="play";
                    click.setText(state);
                    handler.removeCallbacksAndMessages(null);
                }


            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Say.this,Settings.class);
                startActivity(intent);
                state="play";
                click.setText(state);
                handler.removeCallbacksAndMessages(null);

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=0;
                Settings.place=0;
                handler.removeCallbacksAndMessages(null);

                state="play";
                click.setText(state);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                  //  //.d("TAG", "The interstitial wasn't loaded yet.");
                   // Toast.makeText(getApplicationContext(),"ad was not loaded!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Say.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                mInterstitialAd.setAdListener(new AdListener() {

                    public void onAdClosed() {
                        Intent intent = new Intent(Say.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });



            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              handler.removeCallbacksAndMessages(null);

               if((i-2)>=0)
               {
                    i-=2;
               }
                   handler.postDelayed(show,0);
            state="pause";
               click.setText(state);

            }
        });
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(show,0);

                state="pause";
                click.setText(state);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(),"Press DONE button to go back to the main screen.",Toast.LENGTH_SHORT).show();
    }
//initialisation of the Text to speech engine
    public void init()
    {

        breakdown();
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                if(status ==TextToSpeech.SUCCESS)
                {
                    int result = tts.setLanguage(Locale.UK);
                    if(result== TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                     // //  Log.e("TAG", "Language not supported");
                        Toast.makeText(getApplicationContext(),"Language not supported or missing data",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(),"Initialization successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //.e("TAG", "Initialisation unsuccessful");
                    Toast.makeText(getApplicationContext(),"initialisation unsuccessful",Toast.LENGTH_SHORT).show();

                }
            }


        });


        tts.setSpeechRate(speed);
    }


//The main TTS runnable
Runnable show = new Runnable() {
    @Override
    public void run() {
        finalsay();
    }
};




    public void finalsay()
    {//1 2 3 4
        if ((i<arlist.size())&&i>=0)
        {
            tv.setText(arlist.get(i));//1 2 3 4

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts.speak(arlist.get(i),TextToSpeech.QUEUE_FLUSH,null,null);
            } else
            {
                tts.speak(arlist.get(i), TextToSpeech.QUEUE_FLUSH, null);
            }


        }
        i++;//1 2 3 4
        if(arlist.size()-i>=1)//3 2 1
        {
            handler.postDelayed(show,t);
        }
    }















    public void breakdown()
    {
        String s="";
        int i=0;
        int c=0;

        while (i<a.length)
        {

            s+=a[i]+" ";
            c++;//1

            if((c==n)|| a[i].length()>=lengthy)
            {

                arlist.add(s);
                c=0;
                s="";

            }

            i++;

        }
        arlist.add(s);


    }










    //on closing the app
    @Override
    protected void onDestroy() {

        if (tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }



    }

