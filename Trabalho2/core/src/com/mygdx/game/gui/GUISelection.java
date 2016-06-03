package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;
import com.mygdx.game.logic.Menu;

/**
 * Created by Nuno on 30/05/2016.
 */
public class GUISelection {
    public SpriteBatch sprites;
    public RectButton power0;
    public RectButton power1;
    public RectButton power2;
    public RectButton power3;
    public RectButton power4;
    public RectButton pauseButton;

    public GUISelection(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        power0 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.1),
                (int)(Gdx.graphics.getHeight()*0.1),
                (int)(Gdx.graphics.getWidth()*0.2),
                (int)(Gdx.graphics.getHeight()*0.2),
                "powerbuttonup.png");
        power1 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.33),
                (int)(Gdx.graphics.getHeight()*0.1),
                (int)(Gdx.graphics.getWidth()*0.43),
                (int)(Gdx.graphics.getHeight()*0.2),
                "powerbuttonup.png");
        power2 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.56),
                (int)(Gdx.graphics.getHeight()*0.1),
                (int)(Gdx.graphics.getWidth()*0.66),
                (int)(Gdx.graphics.getHeight()*0.2),
                "powerbuttonup.png");
        power3 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.8),
                (int)(Gdx.graphics.getHeight()*0.1),
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.2),
                "powerbuttonup.png");
        power4 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.1),
                (int)(Gdx.graphics.getHeight()*0.33),
                (int)(Gdx.graphics.getWidth()*0.2),
                (int)(Gdx.graphics.getHeight()*0.43),
                "powerbuttonup.png");
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "pausebuttonup.png","pausebuttondown.png");

        sprites = new SpriteBatch();
    }
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        power0.render(sprites);
        power1.render(sprites);
        power2.render(sprites);
        power3.render(sprites);
        power4.render(sprites);
        pauseButton.render(sprites);
        sprites.end();
    }

}
