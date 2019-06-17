package com.godofmafia.godofmafia;

class Player extends Characters {

    String names;
    Characters character;

    // getters
    public String getNames() {
        return names;
    }
    public Characters getCharacter() {
        return character;
    }

    // setters
    public void setNames(String names) {
        this.names = names;
    }
    public void setCharacter(Characters character) {
        this.character = character;
    }
}

