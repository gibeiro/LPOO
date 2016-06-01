package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;
import com.mygdx.game.logic.Menu;

/**
 * Created by Nuno on 10/05/2016.
 */
public class GUIMenu {
    public SpriteBatch sprites;
    public RectButton playButton;
    public RectButton playButtonMult;

    public GUIMenu(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new RectButton(
                Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/10,
                Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/4,
                Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/10,
                Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/20,
                "playBtn.png");
        playButtonMult = new RectButton(
                Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/10,
                Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/20,
                Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/10,
                Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/4,
                "playBtnMult.png");
        sprites = new SpriteBatch();
    }
    public void render(Menu menu){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        playButton.render(sprites);
        playButtonMult.render(sprites);
        sprites.end();

    }
}
