package com.godofmafia.godofmafia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    TextView settingsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        settingsButton = findViewById(R.id.settings_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings_button:
                // TODO: 6/14/2019 set settings
                break;
        }
    }
}
