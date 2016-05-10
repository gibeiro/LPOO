package com.mygdx.game.logic;

/**
 * Created by Nuno on 10/05/2016.
 */
public class Menu {
    Boolean playButtonPressed;
    Boolean playButtonMultiplayerPressed;
    public boolean menuEnd;
    public Menu(){
        playButtonPressed = false;
        playButtonMultiplayerPressed = false;
        menuEnd = false;
    }
    public void setPlayPressed(){
        playButtonPressed = true;
        menuEnd = true;
    }
    public void setPlayMultPressed(){
        playButtonMultiplayerPressed = true;
        menuEnd = true;
    }
    public boolean getPlayPressed(){
        return playButtonPressed;
    }
    public boolean getPlayMultPressed(){
        return playButtonMultiplayerPressed;
    }
    public void update(){

    }


}
