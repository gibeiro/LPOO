package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;

/**
 * Menu mostrado ao jogador quando coloca o jogo em pausa.Tem um fundo transparente, escurecendo o render do estado atrás.
 */
public class GUIPause {
    Texture background;
    SpriteBatch backgroundSprite;
    SpriteBatch buttons;
    public RectButton resume;
    public RectButton exit;

    public GUIPause(){
        background = new Texture("sprites/black.png");
        backgroundSprite = new SpriteBatch();
        buttons = new SpriteBatch();
        resume = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.2),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.4),
                "buttons/resumebuttonup.png","buttons/resumebuttondown.png",true);
        exit = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.3),
                (int)(Gdx.graphics.getHeight()*0.6),
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.8),
                "buttons/leavebuttonup.png","buttons/leavebuttondown.png",true);
        backgroundSprite.enableBlending();
        backgroundSprite.setColor(backgroundSprite.getColor().r,backgroundSprite.getColor().g,backgroundSprite.getColor().b,0.5f);
    }
    public void render(){

        backgroundSprite.begin();
        backgroundSprite.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        backgroundSprite.end();
        buttons.begin();
        resume.render(buttons);
        exit.render(buttons);
        buttons.end();
    }
    public void dispose(){
        background.dispose();
        resume.dispose();
        exit.dispose();
    }
}
