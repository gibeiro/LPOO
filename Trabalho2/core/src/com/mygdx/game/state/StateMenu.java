package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateMenu extends State {
    Texture playButton;
    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        playButton = new Texture("playBtn.png");
    }

    @Override
    public void update(double dt) {
        for(int i = 0;i < 10;i++){
            int x = Gdx.input.getX(i);
            int y = Gdx.input.getY(i);
            if(x > Gdx.graphics.getWidth()/2-playButton.getWidth()/2 &&
                    x < Gdx.graphics.getWidth()/2+playButton.getWidth()/2 &&
                    y > Gdx.graphics.getHeight()/2-playButton.getHeight()/2 &&
                    y < Gdx.graphics.getHeight()/2+playButton.getHeight()/2){
                sm.pop();
                sm.push(new StateGame(sm));
            }
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
