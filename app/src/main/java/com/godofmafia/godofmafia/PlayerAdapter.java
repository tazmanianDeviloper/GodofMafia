package com.godofmafia.godofmafia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

/*
    adapter to extract input from db to RecyclerView
    + model class of type PlayerList (java)
    + inflated holder class (XML)
 */
public class PlayerAdapter extends FirestoreRecyclerAdapter <PlayerList, PlayerAdapter.PlayerListHolder> {

    // interface reference for click handler of items within cardView
    private onItemClickListener listener;

    public PlayerAdapter(@NonNull FirestoreRecyclerOptions<PlayerList> options) {
        super(options);
    }

    // RecyclerView method
    @Override
    protected void onBindViewHolder(@NonNull PlayerListHolder holder, int position, @NonNull PlayerList model) {
        holder.playerAvatar.setText(model.getAvatar());
        holder.playerName.setText(model.getName());
        holder.characterImage.setText(model.getIcon());
    }

    // RecyclerView method
    @NonNull
    @Override
    public PlayerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_item, parent,
                false);
        return new PlayerListHolder (v);
    }

    // RecyclerView viewHolder for XML inflation
    class PlayerListHolder extends RecyclerView.ViewHolder {

        // Java references for XML items in the player_list_item
        TextView playerAvatar;
        TextView playerName;
        TextView characterImage;

        // the constructor for the cardView that holds the 3 textViews.
        public PlayerListHolder(@NonNull View itemView) {
            super(itemView);
            // XML items passed to corresponding java items above
            playerAvatar = itemView.findViewById(R.id.avatar);
            playerName = itemView.findViewById(R.id.playerName);
            characterImage = itemView.findViewById(R.id.icon);

            // handling click item for playerAvatar
            playerAvatar.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onAvatarClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

            // handling click item for playerName
            playerName.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onNameClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

            // handling click item for characterImage
            characterImage.setOnClickListener( new View.OnClickListener(){
                public void onClick (View v){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onIconClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    // interface for each button with different implementations in the Main (SecondActivity)
    public interface onItemClickListener {
        void onAvatarClick(DocumentSnapshot documentSnapshot, int position);
        void onNameClick(DocumentSnapshot documentSnapshot, int position);
        void onIconClick(DocumentSnapshot documentSnapshot, int position);
    }

    // object of the ClickListener class for my interface
    public void setOnItemClickListener (onItemClickListener listener){
        this.listener = listener;
    }

}


