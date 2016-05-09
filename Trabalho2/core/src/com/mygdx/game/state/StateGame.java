package com.mygdx.game.state;

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
import com.mygdx.game.game.Game;

import java.util.ArrayList;

//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private Game game;
    public StateGame(StateManager s) {
        super(s);
        game = new Game();
    }

    @Override
    public void handleInput(){
        /*
         * Verifica teclas premidas pelo jogador 1
         */
        if(Functions.leftButtonPressed()){
            game.player1.movingLeft = true;
        }else game.player1.movingLeft = false;
        if(Functions.rightButtonPressed()){
            game.player1.movingRight = true;
        }else game.player1.movingRight = false;
        if(Functions.jumpButtonPressed()){
            game.player1.jump = true;
        }else game.player1.jump = false;

    }


    @Override
    public void update(double dt) {
        game.updateGame(dt);
    }

    @Override
    public void render(SpriteBatch s) {
        game.renderGame(s);
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
