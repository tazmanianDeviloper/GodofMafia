package com.godofmafia.godofmafia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    // debug
    private static final String TAG = "FirstActivity";

    // internal collection
    private static final List<String> namesArray = new ArrayList<>();

    // key value constant for db map (line 136)
    private static final String KEY_NAME = "name";
    // database reference
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // XML layout
    RelativeLayout firstActLayout;
    // layout attributes
    TextView numberOfPlayers;
    EditText addPlayer;
    TextView doneButton;
    ImageView settingsButton;
    TextView sunOrMoon;

    // counter
    private static int playerSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        firstActLayout = findViewById(R.id.first_act_layout);
        numberOfPlayers = findViewById(R.id.number_of_players);
        addPlayer = findViewById(R.id.add_player);
        doneButton = findViewById(R.id.done_button);
        settingsButton = findViewById(R.id.settings_button);
        sunOrMoon = findViewById(R.id.sun_or_moon);

        addPlayer.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        numberOfPlayers.setOnClickListener(this);
        sunOrMoon.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_player:
                // add players' names to array and db
                addPlayers();
                break;
            case R.id.done_button:
                // SecondActivity()
                onClickConditions();
                break;
            case R.id.settings_button:
                // TODO: 6/14/2019 Set Settings
                break;
            case R.id.number_of_players:
                Toast.makeText(this, "Total number of players", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sun_or_moon:
                Toast.makeText(this, "DAY", Toast.LENGTH_SHORT).show();
                // TODO: 6/18/2019 add rooster sound
                break;

            // TODO: 6/25/2019 may want to delete default, bc you want to start the sound when the game starts
            default:
                Toast.makeText(this, "DAY", Toast.LENGTH_SHORT).show();
                // TODO: 6/18/2019 add rooster sound
                break;
        }
    }

    public void addPlayers (){
        addPlayer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT) {

                    // TODO: 6/18/2019 ask Clinton how objects can be created on-demand? is it done through instance variable?
                    // addPlayer input must be set to players' namesArray but each Player object has to auto generate since numbers of players are unknown.
                    /*
                    Player p1 = new Player();
                    p1.setNames(addPlayer.getText().toString());
                    */

                    // add players' names to namesArray[] (internal collection)
                    namesArray.add(addPlayer.getText().toString());
                    // add players' names to a String variable for db map
                    String nameInput = addPlayer.getText().toString();
                    // clear the TextView for the next name.
                    addPlayer.getText().clear();
                    // increment the total number of players.
                    playerSum++;
                    // clear the last sum before addition.
                    numberOfPlayers.setText("");
                    // display the total number of players next the hash tag.
                    numberOfPlayers.setText(String.valueOf(playerSum));

                    // push: adding a new document (external collection)
                    Map <String, Object> name = new HashMap<>();
                    name.put(KEY_NAME, nameInput);
                    db.collection("Players")
                            // incrementing the document with every iteration, thus creating a new
                            .document("Player_".concat(String.valueOf(playerSum)))
                            // adding a new name to each new document (player)
                            .set(name)
                            // debug
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(FirstActivity.this,"Name Saved", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(FirstActivity.this,"Error!", Toast.LENGTH_SHORT)
                                            .show();
                                    Log.d(TAG, e.toString());
                                }
                            });
                }
                return false;
            }
        });
    }

    public void onClickConditions(){
        if (playerSum >= 5){
            // nested Intent() that should activate if the above conditions are met.
            Intent intent1 = new Intent(this, SecondActivity.class);
            startActivity(intent1);
            // return statement is needed, bc if above conditions are met we don't need below statement
            return;
        }
        Toast.makeText(this, "Minimum of 5 players are needed", Toast.LENGTH_LONG)
                .show();
    }

}
