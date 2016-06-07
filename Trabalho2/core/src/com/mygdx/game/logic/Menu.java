package com.mygdx.game.logic;

// TODO: Auto-generated Javadoc
/**
 * Logica do menu.
 */
public class Menu {
    
    /** Booleano que indica se o botao pratice está premido. */
    Boolean playButtonPressed;
    
    /** Booleano que indica se o botao multiplayer está premido. */
    Boolean playButtonMultiplayerPressed;
    /**
     * Booleano que indica se o menu deve ser fechado por ja ter sido selecionada uma opção.
     */
    boolean menuEnd;
    
    /**
     * Instantiates a new menu.
     */
    public Menu(){
        playButtonPressed = false;
        playButtonMultiplayerPressed = false;
        menuEnd = false;
    }
    
    /**
     * Sets the play pressed.
     */
    public void setPlayPressed(){
        playButtonPressed = true;
        menuEnd = true;
    }
    
    /**
     * Sets the play mult pressed.
     */
    public void setPlayMultPressed(){
        playButtonMultiplayerPressed = true;
        menuEnd = true;
    }
    
    /**
     * Gets the play pressed.
     *
     * @return the play pressed
     */
    public boolean getPlayPressed(){
        return playButtonPressed;
    }
    
    /**
     * Gets the play mult pressed.
     *
     * @return the play mult pressed
     */
    public boolean getPlayMultPressed(){

        return playButtonMultiplayerPressed;
    }
    
    /**
     * Update.
     */
    public void update(){

    }


    /**
     * Gets the menu end.
     *
     * @return the menu end
     */
    public boolean getMenuEnd() {
        return menuEnd;
    }
}
