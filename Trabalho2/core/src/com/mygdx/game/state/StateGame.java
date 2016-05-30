package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;


//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Game game;
    private GUIGame gameGUI;

    public StateGame(StateManager s) {
        super(s);

        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,100));
        game.getPlayer1().setPower(2);
        gameGUI = new GUIGame();
    }


    @Override
    public void handleInput(){
        if(gameGUI.leftButton.isPressed()){
            game.getPlayer1().getInputs().setMovingLeft(true);
        }else game.getPlayer1().getInputs().setMovingLeft(false);
        if(gameGUI.rightButton.isPressed()){
            game.getPlayer1().getInputs().setMovingRight(true);
        }else game.getPlayer1().getInputs().setMovingRight(false);
        if(gameGUI.jumpButton.isPressed()){
            game.getPlayer1().getInputs().setJump(true);
        }else game.getPlayer1().getInputs().setJump(false);
        if(gameGUI.powerButton.isPressed()){
            game.getPlayer1().getInputs().setPower(true);
        }else game.getPlayer1().getInputs().setPower(false);

    }


    @Override
    public void update(double dt) {

        if(!game.isGameEnd()){
            game.update(dt);
            game.getPlayer2().body.setTransform(1000,1000,0);
        }else{
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }

    }

    @Override
    public void render() {
        gameGUI.render(game);
    }

    @Override
    public void dispose(){
    }
}
