package com.mygdx.game.logic;

/**
 * Logica do menu
 */
public class Menu {
    /**
     * Booleano que indica se o botao pratice está premido
     */
    Boolean playButtonPressed;
    /**
     * Booleano que indica se o botao multiplayer está premido
     */
    Boolean playButtonMultiplayerPressed;
    /**
     * Booleano que indica se o menu deve ser fechado por ja ter sido selecionada uma opção.
     */
    boolean menuEnd;
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


    public boolean getMenuEnd() {
        return menuEnd;
    }
}
