package com.godofmafia.godofmafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.File;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    // main layout
    RelativeLayout secondActLayout;
    // attributes
    ImageView settingsButton;
    TextView confirmButton;
    TextView sunOrMoon;

    // avatar and camera button layout
    RelativeLayout cameraPopUp;
    // attributes
    TextView picB4Change;
    TextView cameraIcon;
    Button cancel;

    // pull players' info from db by fallowing references
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference playerRef = db.collection("Players");

    // object of PlayerAdapter class which us an extension of fireStoreRecycler adapter
    private PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // main layout
        secondActLayout = findViewById(R.id.second_act_layout);

        settingsButton = findViewById(R.id.settings_button);
        confirmButton = findViewById(R.id.confirm_button);
        sunOrMoon = findViewById(R.id.sun_or_moon);

        settingsButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        sunOrMoon.setOnClickListener(this);

        setUpRecyclerView();
    }

    // fireStoreRecyclerView and corresponding buttons
    private void setUpRecyclerView(){
       Query query = playerRef.orderBy("name", Query.Direction.ASCENDING);

        // class with items in the view holder, the adapter mapping those items, and the query
        FirestoreRecyclerOptions<PlayerList> options = new FirestoreRecyclerOptions.Builder<PlayerList>()
                .setQuery(query, PlayerList.class)
                .build();

        // object of PlayerAdapter class contains attributes and interfaces
        adapter = new PlayerAdapter(options);

        // the recyclerView in the XML and its attributes
        RecyclerView playersListRecyclerView = findViewById(R.id.players_recycler_view);
        playersListRecyclerView.setHasFixedSize(true);
        playersListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playersListRecyclerView.setAdapter(adapter);

        // buttons within each view of the recyclerView (interface)
        adapter.setOnItemClickListener(new PlayerAdapter.onItemClickListener() {

            @Override
            public void onAvatarClick(DocumentSnapshot documentSnapshot, int position) {
                //PlayerList playerList = documentSnapshot.toObject(PlayerList.class);
                // String avatar = documentSnapshot.getId();

                // hidden layout for avatar button
                cameraPopUp = findViewById(R.id.cameraPopUp);

                picB4Change = findViewById(R.id.picB4Change);
                cameraIcon = findViewById(R.id.cameraIcon);
                cancel = findViewById(R.id.cancel);

                cameraPopUp.setVisibility(View.VISIBLE);

                cameraIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 7/10/2019 Open Camera
                        Intent lunchCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (lunchCamera.resolveActivity(getPackageManager()) != null) {
                            File picturefile = null;

                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraPopUp.setVisibility(View.INVISIBLE);
                    }
                });

            }

            @Override
            public void onNameClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(SecondActivity.this, "name clicked", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onIconClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(SecondActivity.this, "icon clicked", Toast.LENGTH_LONG).show();

            }
        });

    }

    // start listening when activity starts
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // stop listening when activity goes into the background
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    // buttons in the layout, outside of the recyclerView
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

    // third activity to start the game
    public void openThirdActivity(){
        Intent intent2 = new Intent(this, ThirdActivity.class);
        startActivity(intent2);
    }
}
