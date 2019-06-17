package com.godofmafia.godofmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    // XML layout
    RelativeLayout secondActLayout;
    // layout attributes
    ImageView settingsButton;
    TextView confirmButton;
    TextView sunOrMoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        secondActLayout = findViewById(R.id.second_act_layout);

        settingsButton = findViewById(R.id.settings_button);
        confirmButton = findViewById(R.id.confirm_button);
        sunOrMoon = findViewById(R.id.sun_or_moon);

        settingsButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        sunOrMoon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_button:
                openThirdActivity();
                break;
            case  R.id.settings_button:
                // TODO: 6/14/2019 set settings.
                break;
            case R.id.sun_or_moon:
                Toast.makeText(this, "NIGHT", Toast.LENGTH_SHORT).show();
                // TODO: 6/18/2019 add owl sound
                break;
            default:
                /*
                TODO: 6/18/2019 add owl sound
                TODO: 6/18/2019  recyclerView pulled characters from firestore and randomly assign to players.
                */
                break;
        }
    }

    public void openThirdActivity(){
        Intent intent2 = new Intent(this, ThirdActivity.class);
        startActivity(intent2);
    }
}
