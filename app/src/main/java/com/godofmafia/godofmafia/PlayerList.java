package com.godofmafia.godofmafia;

// model class for the firestore RecyclerView
public class PlayerList {

    private String avatarOrImage;
    private String playerName;
    private String characterImage;

// parameterized constructor for firestore's onBindViewHolder ()
    public PlayerList(String avatarOrImage, String playerName, String characterImage) {
        this.avatarOrImage = avatarOrImage;
        this.playerName = playerName;
        this.characterImage = characterImage;
    }

// empty constructor for firestore objects on its end (DND)
    public PlayerList() {
    }

// getter methods for firestore initializations against XML resources
    public String getAvatarOrImage() {
        return avatarOrImage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getCharacterImage() {
        return characterImage;
    }

}
