package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIPause;
import com.mygdx.game.gui.GUIResult;
import com.mygdx.game.gui.GUISelection;
import com.mygdx.game.gui.GUIWaiting;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.socketnetwork.ClientGame;


/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private final static float RATE = 0.05f;
    private ClientGame client;
    private GUIGame gameGUI;
    private GUIPause pauseGUI;
    private GUIWaiting waitGUI;
    private GUIResult resultGUI;
    private GUISelection selectGUI;

    private float ratecounter;
    public StateGameMultiplayer(StateManager s) {
        super(s);
        ratecounter = 0f;
        try{
            client = new ClientGame();
        }catch(Exception e){
            e.printStackTrace();
        }
        waitGUI = new GUIWaiting();
        selectGUI = new GUISelection();
        gameGUI = new GUIGame();
        pauseGUI = new GUIPause();
    }


    @Override
    public void handleInput(){
        if(client.inPause){
            if(pauseGUI.resume.isPressed()){
                client.inPause = false;
            }
            if(pauseGUI.exit.isPressed()){
                client.sendMessage("EXIT");
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }
        }
        if(client.inWait){
            if(waitGUI.pauseButton.isPressed()){
                client.inPause = true;
            }
        }
        if(client.inSelect){
            if(selectGUI.power0.isPressed()){
                client.powerSelected = 0;
                client.inSelect = false;
                client.inGame = true;
            }
            if(selectGUI.power1.isPressed()){
                client.powerSelected = 1;
                client.inSelect = false;
                client.inGame = true;
            }
            if(selectGUI.power2.isPressed()){
                client.powerSelected = 2;
                client.inSelect = false;
                client.inGame = true;
            }
            if(selectGUI.power3.isPressed()){
                client.powerSelected = 3;
                client.inSelect = false;
                client.inGame = true;
            }
            if(selectGUI.power4.isPressed()){
                client.powerSelected = 4;
                client.inSelect = false;
                client.inGame = true;
            }
            if(selectGUI.pauseButton.isPressed()){
                client.inPause = true;
            }
        }
        if(client.inGame){
            if(gameGUI.leftButton.isPressed()){
                client.i.setMovingLeft(true);
            }else client.i.setMovingLeft(false);
            if(gameGUI.rightButton.isPressed()){
                client.i.setMovingRight(true);
            }else client.i.setMovingRight(false);
            if(gameGUI.jumpButton.isPressed()){
                client.i.setJump(true);
            }else client.i.setJump(false);
            if(gameGUI.powerButton.isPressed()){
                client.i.setPower(true);
            }else client.i.setPower(false);
        }

    }


    @Override
    public void update(double dt) {
        ratecounter += dt;
        if(client.connected == false){
            //SHOW TIMEOUT MESSAGE
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }
        if(client.inGame){
            client.game.update(dt);
        }
        if(ratecounter >= RATE){
            client.sendMessage("TEST");
            if(client.inSelect){
                if(client.powerSelected != -1){
                    String s = "";
                    s+="POWER";
                    s+="\n";
                    s+=client.powerSelected;
                    client.sendMessage(s);
                    client.inSelect = false;
                    client.inGame = true;
                }
            }else if(client.inGame){
                client.sendInfo();
            }
            ratecounter = 0;
        }
    }

    @Override
    public void render() {
        if(client.inWait){
            waitGUI.render();
        }else if(client.inSelect){
            selectGUI.render();
        }else if(client.inGame){
            gameGUI.render(client.game);
        }
        if(client.inPause){
            pauseGUI.render();
        }

    }

    @Override
    public void dispose(){
    }
}
