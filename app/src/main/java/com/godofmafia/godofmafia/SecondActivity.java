package com.godofmafia.godofmafia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.godofmafia.godofmafia.FirstActivity.KEY_AVATAR;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SecondActivity";

    // main layout
    RelativeLayout secondActLayout;
    // attributes
    ImageView settingsButton;
    TextView confirmButton;
    TextView sunOrMoon;

    // avatar and camera button layout
    RelativeLayout cameraPopUp;
    // attributes
    ImageView picB4Change;
    TextView cameraIcon;
    Button cancel;
    // global identifier for images returned from camera app intent (picB4Change)
    String currentPhotoPath;
    // global identifier for image taking app intent (picB4Change)
    static final int REQUEST_TAKE_PHOTO = 1;

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

            // first button from left
            @Override
            public void onAvatarClick(DocumentSnapshot documentSnapshot, int position) {
                final PlayerList playerList = documentSnapshot.toObject(PlayerList.class);
                final String avatar = documentSnapshot.getId();

                // hidden layout for avatar button
                cameraPopUp = findViewById(R.id.cameraPopUp);

                picB4Change = findViewById(R.id.picB4Change);
                cameraIcon = findViewById(R.id.cameraIcon);
                cancel = findViewById(R.id.cancel);

                // if player's avatar is clicked in the PlayerAdapter of the r
                cameraPopUp.setVisibility(View.VISIBLE);

                // to take a picture and assigning it to a player and db
                cameraIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent lunchCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // ensure that there's a camera activity to handle the intent
                        if (lunchCamera.resolveActivity(getPackageManager()) != null) {
                            // photoFile is the child of File class with createImageFile() as its method
                            File photoFile = null;
                            try {
                                photoFile = createImageFile();
                            } catch (IOException ex) {
                                // TODO: 7/14/2019 add exception
                                // error occurred while creating the File
                                Log.d(TAG, "The file was not created");
                                Toast.makeText(getApplicationContext(), "Error while saving picture.", Toast.LENGTH_LONG).show();
                            }
                            // continue only if the File was successfully created
                            if (photoFile != null) {
                                // assign the address of the taken photo to photoURI
//                                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
//                                        "com.godofmafia.godofmafia.fileprovider",
//                                        photoFile);
//                                lunchCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                startActivityForResult(lunchCamera, REQUEST_TAKE_PHOTO);

                                // TODO: 7/14/2019 update players's image here with db.update
                                //  and add the updated player to a new permanent list.
                                //  The permanent list shall be called as a hint list for FirstActivity().
                                //  Also figure out where you can implement the db update commands.
                                // updatePlayersAvatar();

                               /* CollectionReference refForAvatar = FirebaseFirestore.getInstance()
                                        .collection("Player");
                                String updatedPlayersAvatar = String.valueOf(picB4Change);
                                avatar = currentPhotoPath;
                                Map<String, Object> player = new HashMap<>();
                                player.put (avatar, updatedPlayersAvatar);

                                refForAvatar.add(playerList.getAvatar(), SetOptions.merge());*/
                            }
                        }
                    }
                });

                // to close picture taking window
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraPopUp.setVisibility(View.INVISIBLE);
                    }
                });

            }

            // middle button
            @Override
            public void onNameClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(SecondActivity.this, "name clicked", Toast.LENGTH_LONG).show();
            }

            // last button
            @Override
            public void onIconClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(SecondActivity.this, "icon clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    // creating a collision resistant file name, using a date-time stamp, for players' pics
    private File createImageFile() throws IOException {
        // unique file name using dates. Accurate date is not intended.
//        String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(new Date());
//        // concat file name with image format
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        // using getExternalFilesDir() to make player images private to this app
//        File storageDir = getExterienalFilesDir(Environment.DIRECTORY_PICTURES);
//        // assigning players' pics to the unique file
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",   /* suffix */
//                storageDir     /* directory */
//        );
        File image = File.createTempFile("img", ".jpeg", getCacheDir());

        // path for use with ACTION_VIEW intents (Instance Variable)
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // updating players' avatar in the adapter as well as the db
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // may have to replace
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            Log.d(TAG, "photo have been taken");
            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            // TODO: 7/24/2019
            Log.d(TAG, bitmap.toString());
            picB4Change.setImageBitmap(bitmap);
        }
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
