package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;

/**
 * Created by Nuno on 30/05/2016.
 */
public class GUISelection {
    public SpriteBatch sprites;
    public RectButton player1;
    public RectButton player2;
    public RectButton player3;
    public RectButton player4;
    public RectButton player5;
    public GUISelection(){
        Gdx.gl.glClearColor(0, 0, 0, 1);

        sprites = new SpriteBatch();
    }

}
