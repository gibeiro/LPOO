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
    private Texture waitingforoponent;
    private Texture timedout;
    private Texture enemyleft;
    private Texture youwon;
    private Texture youlost;

    public float timeOutTimer;
    public float enemyLeftTimer;
    public float youWonTimer;
    public float youLostTimer;
    public GUIWaiting(){
        timeOutTimer = -1;//4 nao tem efeito
        enemyLeftTimer = -1;
        youWonTimer = -1;
        youLostTimer = -1;
        sprites = new SpriteBatch();
        background = new Texture("sprites/background.png");
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "buttons/pausebuttonup.png","buttons/pausebuttondown.png");
        waitingforoponent = new Texture("text/waiting.png");
        timedout = new Texture("text/timeout.png");
        enemyleft = new Texture("text/oponentleft.png");
        youwon = new Texture("text/youwon.png");
        youlost = new Texture("text/youlost.png");
    }
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(background,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(timeOutTimer > 0)
            sprites.draw(timedout,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.4f,Gdx.graphics.getHeight()*0.2f);
        else if(enemyLeftTimer > 0)
            sprites.draw(enemyleft,Gdx.graphics.getWidth()*0.25f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.5f,Gdx.graphics.getHeight()*0.2f);
        else if(youWonTimer > 0){
            sprites.draw(youwon,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.4f,Gdx.graphics.getHeight()*0.2f);
        }else if(youLostTimer > 0){
            sprites.draw(youlost,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.4f,Gdx.graphics.getHeight()*0.2f);
        }
        else sprites.draw(waitingforoponent,Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.4f,Gdx.graphics.getWidth()*0.6f,Gdx.graphics.getHeight()*0.2f);

        pauseButton.render(sprites);
        sprites.end();
    }
}
