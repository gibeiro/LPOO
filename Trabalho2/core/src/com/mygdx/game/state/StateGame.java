package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.logic.Game;



//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Game game;
    private GUIGame gamerenderer;

    public StateGame(StateManager s) {
        super(s);

        game = new Game();
        gamerenderer = new GUIGame();
    }


    @Override
    public void handleInput(){
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

        if(!game.gameEnd){
            game.update(dt);
        }else{
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }

    }

    @Override
    public void render() {
        gamerenderer.render(game);
    }

    @Override
    public void dispose(){
    }
}
