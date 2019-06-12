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
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        firstActLayout = findViewById(R.id.first_act_layout);
        numberOfPlayers = findViewById(R.id.number_of_players);
        addPlayer = findViewById(R.id.add_player);
        doneButton = findViewById(R.id.done_button);
        settingsButton = findViewById(R.id.settings_button);

    }

    public void onStart () {
        super.onStart();
        addPlayers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
/*            case R.id.add_player:
                addPlayers();
                break;*/
            case R.id.done_button:
                openSecondActivity();
                break;
            case R.id.settings_button:

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
                    // display the total number of players next the hash tag.
                    numberOfPlayers.append(Integer.toString(playerSum));
                    // keep the soft keyboard available for the next entry.
                    //imm.showSoftInput(addPlayer,InputMethodManager.SHOW_IMPLICIT);
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
