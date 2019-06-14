package com.godofmafia.godofmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    static List<String> names = new ArrayList<>();
    static int playerSum = 0;

    RelativeLayout firstActLayout;
    TextView numberOfPlayers;
    EditText addPlayer;
    TextView doneButton;
    TextView settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        firstActLayout = findViewById(R.id.first_act_layout);
        numberOfPlayers = findViewById(R.id.number_of_players);
        addPlayer = findViewById(R.id.add_player);
        doneButton = findViewById(R.id.done_button);
        settingsButton = findViewById(R.id.settings_button);

        addPlayer.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_player:
                addPlayers();
                break;
            case R.id.done_button:
                if (playerSum < 5){
                    Toast.makeText(this, "Minimum of 5 players are needed", Toast.LENGTH_LONG)
                            .show();
                }
                if (playerSum >= 5){
                    openSecondActivity();
                }
                break;
            case R.id.settings_button:
// TODO: 6/14/2019 Set Settings
                break;
        }
    }

    public void addPlayers (){
        addPlayer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    // add player's name to the name array.
                    names.add(addPlayer.getText().toString());
                    // clear the TextView for the next name.
                    addPlayer.getText().clear();
                    // increment the total number of players.
                    playerSum++;
                    // clear the last sum before addition.
                    numberOfPlayers.setText("");
                    // display the total number of players next the hash tag.
                    numberOfPlayers.setText(String.valueOf(playerSum));
                }
                return false;
            }
        });
    }

    public void openSecondActivity(){
        Intent intent1 = new Intent(this, SecondActivity.class);
        startActivity(intent1);
    }


}
