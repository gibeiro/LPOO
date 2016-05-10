package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Menu;

/**
 * Created by Nuno on 10/05/2016.
 */
public class GUIMenu {
    public SpriteBatch sprites;
    public Texture playButton;
    public Texture playButtonMult;
    public GUIMenu(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new Texture("playBtn.png");
        playButtonMult = new Texture("playBtnMult.png");
        sprites = new SpriteBatch();
    }
    public void render(Menu menu){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(playButton, Gdx.graphics.getWidth()/2-playButton.getWidth()/2, Gdx.graphics.getHeight()/2-playButton.getHeight()/2+Gdx.graphics.getHeight()/10);
        sprites.draw(playButtonMult, Gdx.graphics.getWidth()/2-playButton.getWidth()/2, Gdx.graphics.getHeight()/2-playButton.getHeight()/2-Gdx.graphics.getHeight()/10);
        sprites.end();
    }
}
