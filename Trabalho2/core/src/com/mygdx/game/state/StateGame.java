package com.mygdx.game.state;

import com.mygdx.game.Inputs;
import com.mygdx.game.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Functions;
import com.mygdx.game.body.Ball;
import com.mygdx.game.body.Obstacle;
import com.mygdx.game.body.Player;
//import com.mygdx.game.server.ServerInterface;

import java.rmi.Naming;
import java.util.ArrayList;

//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Game game;

    public StateGame(StateManager s) {
        super(s);

        game = new Game();
    }


    @Override
    public void handleInput(){
        game.handleInput();

    }


    @Override
    public void update(double dt) {

        if(!game.gameEnd){
            game.update(dt);
        }else{
            sm.pop();
            sm.push(new StateMenu(sm));
        }

    }

    @Override
    public void render(SpriteBatch s) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.render(s);
    }

    @Override
    public void dispose(){
    }
}
