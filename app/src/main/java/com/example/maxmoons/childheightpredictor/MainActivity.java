package com.example.maxmoons.childheightpredictor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public int mHeight=60;
    public int dHeight=60;
    public double prediction=62.5;
    public boolean metric=false;
    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar MHeightInches = (SeekBar) findViewById(R.id.MHeightInches);
        final SeekBar DHeightInches = (SeekBar) findViewById(R.id.DHeightInches);
        final Switch BoyGirl = (Switch) findViewById(R.id.BoyGirl);
        final TextView MHeightDisplay = (TextView) findViewById(R.id.MHeightDisplay);
        final TextView DHeightDisplay = (TextView) findViewById(R.id.DHeightDisplay);
        final TextView HeightPrediction = (TextView) findViewById(R.id.HeightPrediction);
        final CheckBox Metric = (CheckBox) findViewById(R.id.Metric);

        MHeightInches.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar MHeightInches, int progress, boolean fromUser) {
                mHeight = progress;
                CalculateHeight();
                ChangeMDisplay();
            }

            public void onStartTrackingTouch(SeekBar MHeightInches) {
            }

            public void onStopTrackingTouch(SeekBar MHeightInches) {
            }
        });

        DHeightInches.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar DHeightInches, int progress, boolean fromUser) {
                dHeight = progress;
                CalculateHeight();
                ChangeDDisplay();
            }

            public void onStartTrackingTouch(SeekBar DHeightInches) {
            }

            public void onStopTrackingTouch(SeekBar DHeightInches) {
            }
        });

        BoyGirl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CalculateHeight();
            }
        });

        Metric.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                metric = b;
                if(metric)
                {
                    MHeightInches.setMax(244);
                    DHeightInches.setMax(244);
                }
                else
                {
                    MHeightInches.setMax(96);
                    DHeightInches.setMax(96);
                }
                ChangeMDisplay();
                ChangeDDisplay();
                CalculateHeight();
            }
        });
    }



    public void ChangeMDisplay(){
        final TextView MHeightDisplay = (TextView) findViewById(R.id.MHeightDisplay);
        /*if(!metric)
            MHeightDisplay.setText(""+mHeight);
        else {
            int holder = (int) (mHeight * 2.54);
            MHeightDisplay.setText(""+holder);
        }*/
        MHeightDisplay.setText(""+mHeight);
    }

    public void ChangeDDisplay(){
        final TextView DHeightDisplay = (TextView) findViewById(R.id.DHeightDisplay);
        /*if(!metric)
            DHeightDisplay.setText(""+dHeight);
        else {
            int holder = (int) (dHeight * 2.54);
            DHeightDisplay.setText(""+holder);
        }*/
        DHeightDisplay.setText(""+dHeight);
    }

    public void CalculateHeight(){
        final Switch BoyGirl = (Switch) findViewById(R.id.BoyGirl);
        final TextView HeightPrediction = (TextView) findViewById(R.id.HeightPrediction);

        if(!metric) {
            prediction = (mHeight + dHeight) / 2.0;
            if (!BoyGirl.isChecked())
                prediction += 2.5;
            else
                prediction -= 2.5;
            if(prediction<0) prediction=0;
            HeightPrediction.setText(prediction+"");
        }else {
            prediction = (mHeight + dHeight)/ 2.0;
            if (!BoyGirl.isChecked())
                prediction += 6.35;
            else
                prediction -= 6.35;
            if(prediction<0) prediction=0;
            HeightPrediction.setText(df.format(prediction));

        }
    }

    public void Terms(View v)
    {
        Uri uri = Uri.parse("https://microhealthllc.com/about/terms-of-use/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
