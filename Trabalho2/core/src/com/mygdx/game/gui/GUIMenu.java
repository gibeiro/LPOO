package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;
import com.mygdx.game.logic.Menu;

// TODO: Auto-generated Javadoc
/**
 * Menu principal.
 */
public class GUIMenu {
    
    /** The sprites. */
    public SpriteBatch sprites;
    
    /** The play button. */
    public RectButton playButton;
    
    /** The play button mult. */
    public RectButton playButtonMult;
    
    /** The background. */
    private Texture background;

    /**
     * Instantiates a new GUI menu.
     */
    public GUIMenu(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.2),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.4),
                "buttons/playBtn.png",true);
        playButtonMult = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.6),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.8),
                "buttons/playBtnMult.png",true);
        background = new Texture("sprites/background.png");
        sprites = new SpriteBatch();
    }
    
    /**
     * Render.
     *
     * @param menu the menu
     */
    public void render(Menu menu){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        playButton.render(sprites);
        playButtonMult.render(sprites);
        sprites.end();

    }
    
    /**
     * Dispose.
     */
    public void dispose(){
        background.dispose();
        playButton.dispose();
        playButtonMult.dispose();
    }
}
