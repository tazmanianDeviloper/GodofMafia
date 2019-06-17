package com.godofmafia.godofmafia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView settingsButton;
    TextView sunOrMoon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        settingsButton = findViewById(R.id.settings_button);
        sunOrMoon = findViewById(R.id.sun_or_moon);

        settingsButton.setOnClickListener(this);
        sunOrMoon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings_button:
                // TODO: 6/14/2019 set settings
                break;
            case R.id.sun_or_moon:
                Toast.makeText(this, "DAY", Toast.LENGTH_SHORT).show();
                // TODO: 6/18/2019 add cockatoo sound
                break;
        }
    }
}
