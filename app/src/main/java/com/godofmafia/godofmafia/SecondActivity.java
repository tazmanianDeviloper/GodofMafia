package com.godofmafia.godofmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView settingsButton;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        settingsButton = findViewById(R.id.settings_button);
        confirmButton = findViewById(R.id.confirm_button);

        settingsButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
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
        }
    }

    public void openThirdActivity(){
        Intent intent2 = new Intent(this, ThirdActivity.class);
        startActivity(intent2);
    }
}
