package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.gui.GUIPause;
import com.mygdx.game.gui.GUISelection;
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
    private GUIPause pauseGUI;
    private GUISelection selectGUI;
    private boolean selectHero;
    private boolean paused;

    public StateGame(StateManager s) {
        super(s);
        selectHero = true;
        paused = false;
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        pauseGUI = new GUIPause();
        selectGUI = new GUISelection();
        gameGUI = new GUIGame();

    }


    @Override
    public void handleInput(){
        if(selectHero){
            if(selectGUI.power0.isPressed()){
                game.getPlayer1().setPower(0);
                selectHero = false;
            }
            if(selectGUI.power1.isPressed()){
                game.getPlayer1().setPower(1);
                selectHero = false;
            }
            if(selectGUI.power2.isPressed()){
                game.getPlayer1().setPower(2);
                selectHero = false;
            }
            if(selectGUI.power3.isPressed()){
                game.getPlayer1().setPower(3);
                selectHero = false;
            }
            if(selectGUI.power4.isPressed()){
                game.getPlayer1().setPower(4);
                selectHero = false;
            }
            if(selectGUI.pauseButton.isPressed()){
                paused = true;
            }

        }
        if(paused){
            if(pauseGUI.resume.isPressed()){
                paused = false;
            }else if(pauseGUI.exit.isPressed()){
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }
            return;
        }
        if(!selectHero){
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
        if(gameGUI.pauseButton.isPressed()){
            paused = true;
        }
        }

    }


    @Override
    public void update(double dt) {
        if(selectHero){
            return;
        }
        if(paused){
            return;
        }

        if(!game.isGameEnd()){
            game.update(dt);
            game.checkGoals();
            game.getPlayer2().body.setTransform(1000,1000,0);
        }else{
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }

    }

    @Override
    public void render() {
        if(selectHero){
            selectGUI.render();
        }
        else{
            gameGUI.render(game);
        }
        if(paused){
            pauseGUI.render();
        }

    }

    @Override
    public void dispose(){
    }
}
