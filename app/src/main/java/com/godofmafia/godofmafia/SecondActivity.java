package com.godofmafia.godofmafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    // XML layout
    RelativeLayout secondActLayout;
    // layout attributes
    ImageView settingsButton;
    TextView confirmButton;
    TextView sunOrMoon;

    RecyclerView playersListRecyclerView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference playerRef = db.collection("Players");

    private PlayerAdapter adapter;

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

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
       Query query = playerRef.orderBy("name", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<PlayerList> options = new FirestoreRecyclerOptions.Builder<PlayerList>()
                .setQuery(query, PlayerList.class)
                .build();

        adapter = new PlayerAdapter(options);

        RecyclerView playersListRecyclerView = findViewById(R.id.players_recycler_view);
        playersListRecyclerView.setHasFixedSize(true);
        playersListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playersListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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
