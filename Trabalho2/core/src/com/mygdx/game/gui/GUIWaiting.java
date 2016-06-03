package com.mygdx.game.gui;

/**
 * Created by Nuno on 01/06/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;

/**
 * Interface shown to the client when waiting for enemy player after joining server
 */
public class GUIWaiting {
    SpriteBatch sprites;
    public RectButton pauseButton;

    private Texture background;
    private Texture text;
    public GUIWaiting(){
        sprites = new SpriteBatch();
        background = new Texture("black.png");
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "pausebuttonup.png","pausebuttondown.png");
        text = new Texture("waiting.png");
    }
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(background,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprites.draw(text,Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.6f,Gdx.graphics.getHeight()*0.2f);
        pauseButton.render(sprites);
        sprites.end();
    }
}
