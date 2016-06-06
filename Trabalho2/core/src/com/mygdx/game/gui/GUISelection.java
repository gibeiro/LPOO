package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.RectButton;

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
    private Texture background;
    public int selected;

    public GUISelection(){
        selected = -1;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        power0 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.03),
                (int)(Gdx.graphics.getHeight()*0.17),
                (int)(Gdx.graphics.getWidth()*0.17),
                (int)(Gdx.graphics.getHeight()*0.37),
                "buttons/defaultbutton.png",true);
        power1 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.23),
                (int)(Gdx.graphics.getHeight()*0.17),
                (int)(Gdx.graphics.getWidth()*0.37),
                (int)(Gdx.graphics.getHeight()*0.37),
                "buttons/rocketbutton.png",true);
        power2 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.43),
                (int)(Gdx.graphics.getHeight()*0.17),
                (int)(Gdx.graphics.getWidth()*0.57),
                (int)(Gdx.graphics.getHeight()*0.37),
                "buttons/stopbutton.png",true);
        power3 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.63),
                (int)(Gdx.graphics.getHeight()*0.17),
                (int)(Gdx.graphics.getWidth()*0.77),
                (int)(Gdx.graphics.getHeight()*0.37),
                "buttons/flipbutton.png",true);
        power4 = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.83),
                (int)(Gdx.graphics.getHeight()*0.17),
                (int)(Gdx.graphics.getWidth()*0.97),
                (int)(Gdx.graphics.getHeight()*0.37),
                "buttons/magnetbutton.png",true);
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "buttons/pausebuttonup.png","buttons/pausebuttondown.png",true);

        background = new Texture("sprites/background.png");
        sprites = new SpriteBatch();
    }
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprites.begin();
        sprites.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        power0.render(sprites);
        power1.render(sprites);
        power2.render(sprites);
        power3.render(sprites);
        power4.render(sprites);
        pauseButton.render(sprites);
        Texture torender = power0.getButtonidle();
        if(selected == 1){
            torender = power1.getButtonidle();
        }else if(selected == 2){
            torender = power2.getButtonidle();
        }
        else if(selected == 3){
            torender = power3.getButtonidle();
        }
        else if(selected == 4){
            torender = power4.getButtonidle();
        }
        else if(selected == 0){
            torender = power0.getButtonidle();
        }
        if(selected != -1){
            sprites.draw(torender,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.1f,Gdx.graphics.getWidth()*0.4f,Gdx.graphics.getHeight()*0.40f);
        }
        sprites.end();
    }
    public void dispose(){
        background.dispose();
        power0.dispose();
        power1.dispose();
        power2.dispose();
        power3.dispose();
        power4.dispose();
        pauseButton.dispose();
    }

}
