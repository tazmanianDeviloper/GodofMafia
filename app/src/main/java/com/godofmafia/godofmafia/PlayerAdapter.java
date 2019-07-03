package com.godofmafia.godofmafia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

/*
    adapter to extract input from db to RecyclerView
    + model class of type PlayerList (java)
    + inflated holder class (XML)
 */
public class PlayerAdapter extends FirestoreRecyclerAdapter <PlayerList, PlayerAdapter.PlayerListHolder> {

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
    class PlayerListHolder extends RecyclerView.ViewHolder{

        // Java references for XML items in the player_list_item
        TextView playerAvatar;
        TextView playerName;
        TextView characterImage;

        public PlayerListHolder(@NonNull View itemView) {
            super(itemView);

            // XML items passed to corresponding java items above
            playerAvatar = itemView.findViewById(R.id.avatar);
            playerName = itemView.findViewById(R.id.playerName);
            characterImage = itemView.findViewById(R.id.icon);
        }
    }
}
