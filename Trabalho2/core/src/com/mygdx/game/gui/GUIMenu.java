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
    private Texture background;

    public GUIMenu(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.2),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.4),
                "buttons/playBtn.png");
        playButtonMult = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.6),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.8),
                "buttons/playBtnMult.png");
        background = new Texture("sprites/background.png");
        sprites = new SpriteBatch();
    }
    public void render(Menu menu){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        playButton.render(sprites);
        playButtonMult.render(sprites);
        sprites.end();

    }
    public void dispose(){
        background.dispose();
        playButton.dispose();
        playButtonMult.dispose();
    }
}
