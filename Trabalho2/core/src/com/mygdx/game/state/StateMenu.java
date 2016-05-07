package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Functions;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateMenu extends State {
    Boolean playButtonPressed;
    Texture playButton;
    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new Texture("playBtn.png");
        playButtonPressed = false;
    }

    @Override
    public void handleInput(){

        if(Functions.rectanglePressed(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2,Gdx.graphics.getWidth()/2+playButton.getWidth()/2,Gdx.graphics.getHeight()/2+playButton.getHeight()/2)){
            playButtonPressed = true;
        }

    }

    @Override
    public void update(double dt) {
        if(playButtonPressed){
            sm.pop();
            sm.push(new StateGame(sm));
        }
    }

    @Override
    public void render(SpriteBatch s) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        s.begin();
        s.draw(playButton, Gdx.graphics.getWidth()/2-playButton.getWidth()/2, Gdx.graphics.getHeight()/2-playButton.getHeight()/2);
        s.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
