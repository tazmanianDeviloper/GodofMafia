package com.godofmafia.godofmafia;

// model class for the fireStore RecyclerView
public class PlayerList {

    private String avatar;
    private String name;
    private String icon;

// parameterized constructor for fireStore's onBindViewHolder ()
    public PlayerList(String avatar, String name, String icon) {
        this.avatar = avatar;
        this.name = name;
        this.icon = icon;
    }

// empty constructor for fireStore objects on its end (DND)
    public PlayerList() {
    }

// getter methods for fireStore initializations against XML resources
    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

}
